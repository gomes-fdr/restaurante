/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rnval;

import com.fdr.gomes.bean.Mesa;
import com.fdr.gomes.infra.ValidacaoRN;
import com.fdr.gomes.infra.ValidacaoRNException;

/**
 *
 * @author Fabiano
 */
public class MesaRNVal implements ValidacaoRN<Mesa>{

    @Override
    public void validarSalvar(Mesa bean) {
        if (bean.getCapacidade()< 1) {
            throw new ValidacaoRNException("Valor invalido!");
        }
    }

    @Override
    public void validarExcluir(Mesa bean) {
        validarConsultar(bean);
    }

    @Override
    public void validarConsultar(Mesa bean) {
        if (bean.getNumero() < 1) {
            throw new RuntimeException("Campo não informado");
        }
    }

    @Override
    public void validarAlterar(Mesa bean) {
        validarSalvar(bean);
    }
    
}
