/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rnval;

import com.fdr.gomes.bean.Garcom;
import com.fdr.gomes.infra.ValidacaoRN;
import com.fdr.gomes.infra.ValidacaoRNException;

/**
 *
 * @author 631410128
 */
public class GarcomRNVal implements ValidacaoRN<Garcom>{

    @Override
    public void validarSalvar(Garcom bean) {
        if (bean.getId() <0) {
            throw new ValidacaoRNException("Campo CPF não informado");
        }
        
        if (bean.getNome() == null) {
            throw new ValidacaoRNException("Campo Nome não informado");
        }
    }

    @Override
    public void validarExcluir(Garcom bean) {
        validarConsultar(bean);
    }

    @Override
    public void validarConsultar(Garcom bean) {
        if (bean.getId()< 0) {
            throw new RuntimeException("Campo não informado");
        }
    }

    @Override
    public void validarAlterar(Garcom bean) {
        validarSalvar(bean);
    }
    
}
