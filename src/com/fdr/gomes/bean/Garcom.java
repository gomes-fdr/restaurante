package com.fdr.gomes.bean;

import com.jdf.swing.helper.jtable.JTableColumnMetadata;

/**
 * @author Fabiano da Rosa Gomes<gomes.fdr@gmail.com>
 */
public class Garcom {

    @JTableColumnMetadata(name = "Id")
    private int id;
    
    @JTableColumnMetadata(name = "Nome")
    private String nome;
    
    @JTableColumnMetadata(name = "Perfil")
    private int perfil;
    
    @JTableColumnMetadata(name = "Salario")
    private double salario;
    
    public Garcom(){
        
    }
    
    public Garcom(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int cpf) {
        this.id = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getPerfil() {
        return 1;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
