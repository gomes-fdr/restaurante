/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.bd;

import com.fdr.gomes.bean.Produto;
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
public class ProdutoBD extends CrudBD<Produto> {

    Connection conn = null;

    @Override
    public void salvar(Produto bean) {
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO produto ");
            sql.append("(descricao, tipo, preco) ");
            sql.append("VALUES  ");
            sql.append("(?,?,?) ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            pstm.setString(1, bean.getNome());
            pstm.setInt(2, bean.getTipo());
            pstm.setDouble(3, bean.getPreco());

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
    public void excluir(Produto bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("DELETE FROM produto WHERE id=?");
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
    public Produto consultar(Produto bean) {
        Produto produtoRetorno = null;

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM funcionario WHERE id=?");
            pstm.setInt(1, bean.getCodigo());

            logger.debug("Consultando: " + bean);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                logger.debug("Registro encontrado");
                produtoRetorno = new Produto();
                produtoRetorno.setNome(rs.getString("descricao"));
                produtoRetorno.setTipo(rs.getInt("tipo"));
            }
            logger.debug("Consulta executada com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }

        return produtoRetorno;
    }

    @Override
    public void alterar(Produto bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE produto  ");
            sql.append("SET descricao = ?, tipo = ?, preco = ?  ");
            sql.append("WHERE id = ?  ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, bean.getNome());
            pstm.setInt(2, bean.getTipo());
            pstm.setDouble(3, bean.getPreco());
            pstm.setInt(4, bean.getCodigo());

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
    public List<Produto> pesquisar(String pesquisa) {
              List<Produto> lista = new ArrayList<>();

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM produto WHERE descricao like ?");
            pstm.setString(1, "%" + pesquisa + "%");

            logger.debug("Consultando: " + pesquisa);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                logger.debug("Registro encontrado");
                Produto produtoRetorno = new Produto();
                produtoRetorno.setCodigo(rs.getInt("id"));
                produtoRetorno.setNome(rs.getString("descricao"));
                produtoRetorno.setTipo(rs.getInt("tipo"));
                produtoRetorno.setPreco(rs.getDouble("preco"));

                lista.add(produtoRetorno);
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
