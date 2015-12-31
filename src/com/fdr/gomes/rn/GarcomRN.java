package com.fdr.gomes.rn;

import com.fdr.gomes.bd.GarcomBD;
import com.fdr.gomes.bean.Garcom;
import com.fdr.gomes.infra.CrudBD;
import com.fdr.gomes.rnval.GarcomRNVal;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class GarcomRN extends CrudBD<Garcom> {
    
    private final GarcomBD garcomBD;
    private final GarcomRNVal garcomRNVal;
    
    public GarcomRN() {
        garcomBD = new GarcomBD();
        garcomRNVal = new GarcomRNVal();
    }
    
    public boolean validaIncluir(Garcom garcom) {
        boolean registroOK = true;

        if (garcom.getId()< 0) {
            registroOK = false;
        }

        if (garcom.getNome() == null) {
            registroOK = false;
        }

        if (garcom.getSalario() <= 0) {
            registroOK = false;
        }

        return registroOK;
    }

    @Override
    public void salvar(Garcom bean) {
        garcomRNVal.validarSalvar(bean);
        garcomBD.salvar(bean);
    }

    @Override
    public void excluir(Garcom bean) {
        garcomRNVal.validarExcluir(bean);
        garcomBD.excluir(bean);
    }

    @Override
    public Garcom consultar(Garcom bean) {
        garcomRNVal.validarConsultar(bean);
        return garcomBD.consultar(bean);
    }

    @Override
    public void alterar(Garcom bean) {
        garcomRNVal.validarAlterar(bean);
        garcomBD.alterar(bean);
    }

    @Override
    public List<Garcom> pesquisar(String pesquisa) {
        return garcomBD.pesquisar(pesquisa);
    }
    
}
