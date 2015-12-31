/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.bd;

import com.fdr.gomes.bean.Garcom;
import com.fdr.gomes.infra.CrudBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 631410128
 */
public class GarcomBD extends CrudBD<Garcom> {

    Connection conn = null;

    @Override
    public void salvar(Garcom bean) {
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO funcionario ");
            sql.append("(nome, perfil, salario) ");
            sql.append("VALUES  ");
            sql.append("(?,?,?) ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());

            pstm.setString(1, bean.getNome());
            pstm.setInt(2, bean.getPerfil());
            pstm.setDouble(3, bean.getSalario());

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
    public void excluir(Garcom bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("DELETE FROM funcionario WHERE id=?");
            pstm.setInt(1, bean.getId());

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
    public Garcom consultar(Garcom bean) {
        Garcom garcomRetorno = null;

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM funcionario WHERE id=?");
            pstm.setInt(1, bean.getId());

            logger.debug("Consultando: " + bean);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                logger.debug("Registro encontrado");
                garcomRetorno = new Garcom();
                garcomRetorno.setId(rs.getInt("id"));
                garcomRetorno.setNome(rs.getString("nome"));
                garcomRetorno.setPerfil(rs.getInt("perfil"));
                garcomRetorno.setSalario(rs.getDouble("salario"));
            }
            logger.debug("Consulta executada com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            fecharConexao(conn);
        }

        return garcomRetorno;
    }

    @Override
    public void alterar(Garcom bean) {
        Connection conn = null;
        try {
            conn = abrirConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE funcionario  ");
            sql.append("SET nome = ?, perfil = ?, salario = ?  ");
            sql.append("WHERE id = ?  ");

            PreparedStatement pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, bean.getNome());
            pstm.setInt(2, bean.getPerfil());
            pstm.setDouble(3, bean.getSalario());
            pstm.setInt(4, bean.getId());
            
            
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
    public List<Garcom> pesquisar(String pesquisa) {
        List<Garcom> lista = new ArrayList<>();

        Connection conn = null;
        try {
            conn = abrirConexao();

            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM funcionario WHERE nome like ?");
            pstm.setString(1, "%" + pesquisa + "%");

            logger.debug("Consultando: " + pesquisa);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                logger.debug("Registro encontrado");
                Garcom garcomRetorno = new Garcom();
                garcomRetorno.setId(rs.getInt("id"));
                garcomRetorno.setNome(rs.getString("nome"));
                garcomRetorno.setPerfil(rs.getInt("perfil"));
                garcomRetorno.setSalario(rs.getDouble("salario"));

                lista.add(garcomRetorno);
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
