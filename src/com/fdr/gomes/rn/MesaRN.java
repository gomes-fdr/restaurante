/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdr.gomes.rn;

import com.fdr.gomes.bd.MesaBD;
import com.fdr.gomes.bean.Mesa;
import com.fdr.gomes.infra.CrudBD;
import com.fdr.gomes.rnval.MesaRNVal;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class MesaRN extends CrudBD<Mesa>{
    
    private final MesaBD mesaBD;
    private final MesaRNVal mesaRNVal;
    
    public MesaRN(){
        mesaBD = new MesaBD();
        mesaRNVal = new MesaRNVal();
    }
    
    public boolean validaIncluir(Mesa mesa) {
        boolean registroOK = true;

        if (mesa.getCapacidade() < 1) {
            registroOK = false;
        }
        return registroOK;
    }

    @Override
    public void salvar(Mesa bean) {
        mesaRNVal.validarSalvar(bean);
        mesaBD.salvar(bean);
    }

    @Override
    public void excluir(Mesa bean) {
        mesaRNVal.validarExcluir(bean);
        mesaBD.excluir(bean);
    }

    @Override
    public Mesa consultar(Mesa bean) {
        mesaRNVal.validarConsultar(bean);
        return mesaBD.consultar(bean);
    }

    @Override
    public void alterar(Mesa bean) {
        mesaRNVal.validarAlterar(bean);
        mesaBD.alterar(bean);
    }

    @Override
    public List<Mesa> pesquisar(String pesquisa) {
        return mesaBD.pesquisar(pesquisa);
    }
    
}
