/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rn;

import com.fdr.gomes.bd.PedidoBD;
import com.fdr.gomes.bean.Pedido;
import com.fdr.gomes.infra.CrudBD;
import com.fdr.gomes.rnval.PedidoRNVal;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class PedidoRN extends CrudBD<Pedido>{
    
    private final PedidoBD pedidoBD;
    private final PedidoRNVal pedidoRNVal;
    
    public PedidoRN() {
        pedidoBD = new PedidoBD();
        pedidoRNVal = new PedidoRNVal();
    }
    
    public boolean validaIncluir(Pedido pedido) {
        boolean registroOK = true;

        if (pedido.getGarcom() == null) {
            registroOK = false;
        }
        
        if (pedido.getMesa()== null) {
            registroOK = false;
        }
        return registroOK;
    }

    @Override
    public void salvar(Pedido bean) {
        pedidoRNVal.validarSalvar(bean);
        pedidoBD.salvar(bean);
    }

    @Override
    public void excluir(Pedido bean) {
        pedidoRNVal.validarExcluir(bean);
        pedidoBD.excluir(bean);
    }

    @Override
    public Pedido consultar(Pedido bean) {
        pedidoRNVal.validarConsultar(bean);
        return pedidoBD.consultar(bean);
    }

    @Override
    public void alterar(Pedido bean) {
        pedidoRNVal.validarAlterar(bean);
        pedidoBD.alterar(bean);
    }

    @Override
    public List<Pedido> pesquisar(String pesquisa) {
        return pedidoBD.pesquisar(pesquisa);
    }
    
}
