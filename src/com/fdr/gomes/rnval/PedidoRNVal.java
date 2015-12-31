/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rnval;

import com.fdr.gomes.bean.Pedido;
import com.fdr.gomes.infra.ValidacaoRN;
import com.fdr.gomes.infra.ValidacaoRNException;

/**
 *
 * @author Fabiano
 */
public class PedidoRNVal implements ValidacaoRN<Pedido>{

    @Override
    public void validarSalvar(Pedido bean) {
        if (bean.getData() == null) {
            throw new ValidacaoRNException("Campo não informado");
        }
        
        if (bean.getMesa() == null) {
            throw new ValidacaoRNException("Campo não informado");
        }
        
        if (bean.getGarcom() == null) {
            throw new ValidacaoRNException("Campo não informado");
        }
    }

    @Override
    public void validarExcluir(Pedido bean) {
        validarConsultar(bean);
    }

    @Override
    public void validarConsultar(Pedido bean) {
        if (bean.getCodigo()<0) {
            throw new RuntimeException("Campo não informado");
        }
    }

    @Override
    public void validarAlterar(Pedido bean) {
        validarSalvar(bean);
    }
    
}
