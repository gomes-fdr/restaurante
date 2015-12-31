package com.fdr.gomes.bean;

import com.jdf.swing.helper.jtable.JTableColumnMetadata;

/**
 * @author Fabiano da Rosa Gomes<gomes.fdr@gmail.com>
 */
public class Mesa {
    
    @JTableColumnMetadata(name = "Código")
    private int numero;
    
    @JTableColumnMetadata(name = "Capacidade")
    private int capacidade;
    
    public Mesa(){
        
    }
    
    public Mesa(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return  "M:" + String.valueOf(numero) + " C:" + String.valueOf(capacidade)  ;
    }
    
    
}
