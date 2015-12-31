package com.fdr.gomes.bean;

import com.jdf.swing.helper.jtable.JTableColumnMetadata;

/**
 * @author Fabiano da Rosa Gomes<gomes.fdr@gmail.com>
 */
public class Produto {

    @JTableColumnMetadata(name = "Codigo")
    private int codigo;
    
    @JTableColumnMetadata(name = "Nome")
    private String nome;
    
    @JTableColumnMetadata(name = "Tipo")
    private int tipo;
    
    @JTableColumnMetadata(name = "Preco")
    private double preco;
    
    public Produto() {
        
    }
    
    public Produto(int codigo) {
        this.codigo = codigo;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
    

}
