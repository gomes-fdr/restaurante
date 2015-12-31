/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rn;

import com.fdr.gomes.bd.ProdutoBD;
import com.fdr.gomes.bean.Produto;
import com.fdr.gomes.infra.CrudBD;
import com.fdr.gomes.rnval.ProdutoRNVal;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class ProdutoRN extends CrudBD<Produto>  {
    
    private final ProdutoBD produtoBD;
    private final ProdutoRNVal produtoRNVal;
    
    public ProdutoRN() {
        produtoBD = new ProdutoBD();
        produtoRNVal = new ProdutoRNVal();
    }
    
    public boolean validaIncluir(Produto produto) {
        boolean registroOK = true;

        if (produto.getNome() == null) {
            registroOK = false;
        }
        
        if (produto.getTipo() < 0) {
            registroOK = false;
        }
        
        if(produto.getPreco() <= 0) {
            registroOK = false;
        }

        return registroOK;
    }

    @Override
    public void salvar(Produto bean) {
        produtoRNVal.validarSalvar(bean);
        produtoBD.salvar(bean);
    }

    @Override
    public void excluir(Produto bean) {
        produtoRNVal.validarExcluir(bean);
        produtoBD.excluir(bean);
    }

    @Override
    public Produto consultar(Produto bean) {
        produtoRNVal.validarConsultar(bean);
        return produtoBD.consultar(bean);
    }

    @Override
    public void alterar(Produto bean) {
        produtoRNVal.validarAlterar(bean);
        produtoBD.alterar(bean);
    }

    @Override
    public List<Produto> pesquisar(String pesquisa) {
        return produtoBD.pesquisar(pesquisa);
    }
    
}
