/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.bd;

import com.fdr.gomes.bean.Mesa;
import com.fdr.gomes.infra.CrudBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class MesaBD extends CrudBD<Mesa> {

    Connection conn = null;

    @Override
    public void salvar(Mesa bean) {
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO mesa ");
            sql.append("(capacidade) ");
            sql.append("VALUES  ");
            sql.append("(?) ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            pstm.setInt(1, bean.getCapacidade());

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
    public void excluir(Mesa bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("DELETE FROM mesa WHERE id=?");
            pstm.setInt(1, bean.getNumero());

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
    public Mesa consultar(Mesa bean) {
        Mesa mesaRetorno = null;

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM mesa WHERE id=?");
            pstm.setInt(1, bean.getNumero());

            logger.debug("Consultando: " + bean);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                logger.debug("Registro encontrado");
                mesaRetorno = new Mesa();
                mesaRetorno.setNumero(rs.getInt("id"));
                mesaRetorno.setCapacidade(rs.getInt("capacidade"));
            }
            logger.debug("Consulta executada com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }

        return mesaRetorno;
    }

    @Override
    public void alterar(Mesa bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE mesa  ");
            sql.append("SET capacidade = ?  ");
            sql.append("WHERE id = ?  ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, bean.getCapacidade());
            pstm.setInt(2, bean.getNumero());
            

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
    public List<Mesa> pesquisar(String pesquisa) {
        List<Mesa> lista = new ArrayList<>();

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM mesa");
            
            logger.debug("Consultando: " + pesquisa);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                logger.debug("Registro encontrado");
                Mesa mesaRetorno = new Mesa();
                mesaRetorno.setNumero(rs.getInt("id"));
                mesaRetorno.setCapacidade(rs.getInt("capacidade"));

                lista.add(mesaRetorno);
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
