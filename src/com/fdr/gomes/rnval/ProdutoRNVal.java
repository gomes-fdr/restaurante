/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rnval;

import com.fdr.gomes.bean.Produto;
import com.fdr.gomes.infra.ValidacaoRN;
import com.fdr.gomes.infra.ValidacaoRNException;

/**
 *
 * @author Fabiano
 */
public class ProdutoRNVal implements ValidacaoRN<Produto>{

    @Override
    public void validarSalvar(Produto bean) {
        if (bean.getNome().isEmpty()) {
            throw new ValidacaoRNException("Campo não informado");
        }
        
        if (bean.getTipo() < 0) {
            throw new ValidacaoRNException("Valor invalido");
        }
        
        if (bean.getPreco() <= 0) {
            throw new ValidacaoRNException("Valor invalido");
        }
    }

    @Override
    public void validarExcluir(Produto bean) {
        validarConsultar(bean);
    }

    @Override
    public void validarConsultar(Produto bean) {
        if (bean.getCodigo() < 0) {
            throw new RuntimeException("Campo não informado");
        }
    }

    @Override
    public void validarAlterar(Produto bean) {
        validarSalvar(bean);
    }
    
}
