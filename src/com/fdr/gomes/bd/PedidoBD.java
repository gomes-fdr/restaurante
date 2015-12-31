/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.bd;

import com.fdr.gomes.bean.Garcom;
import com.fdr.gomes.bean.Mesa;
import com.fdr.gomes.bean.Pedido;
import com.fdr.gomes.bean.Produto;
import com.fdr.gomes.infra.CrudBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class PedidoBD extends CrudBD<Pedido> {

    Connection conn = null;

    @Override
    public void salvar(Pedido bean) {
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO pedido ");
            sql.append("(dt_pedido, mesa, garcom, aberto, total_pedido) ");
            sql.append("VALUES  ");
            sql.append("(?,?,?,?,?) ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            pstm.setTimestamp(1, new Timestamp(bean.getData().getTime()));
            pstm.setInt(2, bean.getMesa().getNumero());
            pstm.setInt(3, bean.getGarcom().getId());
            pstm.setBoolean(4, bean.isAberto());
            pstm.setDouble(5, bean.getTotalPedido());
            
            logger.debug("Salvando: " + bean);
            pstm.execute();

            // Relações n:n (usado na cesta de produtos de um pedido)
            ResultSet res = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID() FROM pedido");
            res.next();
            Integer lastID = res.getInt(1);

            sql = new StringBuilder();
            sql.append("INSERT INTO pedido_produto ");
            sql.append("(pedido, produto, quantidade)  ");
            sql.append("VALUES  ");
            sql.append("(?,?,?) ");

            pstm = conn.prepareStatement(sql.toString());

            for (Produto p : bean.getProdutos()) {
                pstm.setInt(1, lastID);
                pstm.setInt(2, p.getCodigo());
                pstm.setInt(3, 1);
                
                logger.debug("Salvando produtos: " + bean);
                pstm.execute();
            }

            //pstm.setInt(3, bean.getProdutos().size());

           

            commitTransacao(conn);
            logger.debug("Salvamento executado com sucesso");
        } catch (Exception e) {
            rollbackTransacao(conn);
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }
    }

    @Override
    public void excluir(Pedido bean) {
        // Não faz sentido excluir um pedido, tenho flags de controle par saber se já fechei.
        // posso apenas fazer um update, usando o metodo de alterar...
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("DELETE FROM pedido_produto WHERE pedido=?");
            pstm.setInt(1, bean.getCodigo());

            logger.debug("Excluindo: " + bean);
            pstm.execute();
            
            pstm = conn.prepareStatement("DELETE FROM pedido WHERE id=?");            
            pstm.setInt(1, bean.getCodigo());

            logger.debug("Excluindo: " + bean);
            pstm.execute();
            
            commitTransacao(conn);
            logger.debug("Exclusão executada com sucesso");
        } catch (Exception e) {
            rollbackTransacao(conn);
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }
    }

    @Override
    public Pedido consultar(Pedido bean) {
        Pedido pedidoRetorno = null;
        MesaBD mesaBD = new MesaBD();
        GarcomBD garcomMesa = new GarcomBD();

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM pedido WHERE id=?");
            pstm.setInt(1, bean.getCodigo());

            logger.debug("Consultando: " + bean);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                logger.debug("Registro encontrado");
                pedidoRetorno = new Pedido();
                pedidoRetorno.setCodigo(rs.getInt("id"));
                pedidoRetorno.setData(rs.getTimestamp("dt_pedido"));
                
                // Coleto mesa do pedido
                Mesa m = new Mesa();
                m.setNumero(rs.getInt("mesa"));
                m = mesaBD.consultar(m);
                pedidoRetorno.setMesa(m);
                
                // Coleto garcom do pedido
                Garcom g = new Garcom();
                g.setId(rs.getInt("garcom"));
                g = garcomMesa.consultar(g);
                pedidoRetorno.setGarcom(g);
                
                pedidoRetorno.setAberto(rs.getBoolean("aberto"));
                pedidoRetorno.setParcialPedido(rs.getDouble("parcial_pedido"));
                pedidoRetorno.setTotalPedido(rs.getDouble("total_pedido"));
                pedidoRetorno.setPagaComissao(rs.getBoolean("paga_comissao"));
                pedidoRetorno.setComissao(rs.getDouble("comissao"));
            }
            logger.debug("Consulta executada com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }

        return pedidoRetorno;
    }

    @Override
    public void alterar(Pedido bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE pedido  ");
            sql.append("SET dt_pedido = ?, mesa = ?, garcom = ?,  ");
            sql.append("aberto = ?, parcial_pedido = ?, total_pedido = ?,  ");
            sql.append("paga_comissao = ?, comissao = ?,  ");
            sql.append("WHERE id = ?  ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            pstm.setTimestamp(1, new Timestamp(bean.getData().getTime()));
            pstm.setInt(2, bean.getMesa().getNumero());
            pstm.setInt(3, bean.getGarcom().getId());
            pstm.setBoolean(4, bean.isAberto());
            pstm.setDouble(5, bean.getParcialPedido());
            pstm.setDouble(6, bean.getTotalPedido());
            pstm.setBoolean(7, bean.pagaComissao());
            pstm.setDouble(8, bean.getComissao());

            logger.debug("Salvando: " + bean);
            pstm.execute();
            commitTransacao(conn);
            logger.debug("Salvamento executado com sucesso");
        } catch (Exception e) {
            rollbackTransacao(conn);
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }
    }

    @Override
    public List<Pedido> pesquisar(String pesquisa) {
        List<Pedido> lista = new ArrayList<>();
        MesaBD mesaBD = new MesaBD();
        GarcomBD garcomMesa = new GarcomBD();

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM pedido WHERE id like ?");
            pstm.setString(1, "%" + pesquisa + "%");

            logger.debug("Consultando: " + pesquisa);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                logger.debug("Registro encontrado");
                Pedido pedidoRetorno = new Pedido();
                
                pedidoRetorno.setCodigo(rs.getInt("id"));
                pedidoRetorno.setData(rs.getTimestamp("dt_pedido"));
                
                // Coleto mesa do pedido
                Mesa m = new Mesa();
                m.setNumero(rs.getInt("mesa"));
                m = mesaBD.consultar(m);
                pedidoRetorno.setMesa(m);
                
                // Coleto garcom do pedido
                Garcom g = new Garcom();
                g.setId(rs.getInt("garcom"));
                g = garcomMesa.consultar(g);
                pedidoRetorno.setGarcom(g);
                
                pedidoRetorno.setAberto(rs.getBoolean("aberto"));
                
                pedidoRetorno.setTotalPedido(rs.getDouble("total_pedido"));
                
                
                
                lista.add(pedidoRetorno);
            }
            logger.debug("Consulta executada com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }

        return lista;
    }

}
