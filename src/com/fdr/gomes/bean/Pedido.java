package com.fdr.gomes.bean;

import com.jdf.swing.helper.jtable.JTableColumnMetadata;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Fabiano da Rosa Gomes<gomes.fdr@gmail.com>
 */
public class Pedido {

    @JTableColumnMetadata(name = "Número")
    private int codigo;
    
    @JTableColumnMetadata(name = "Data")
    private Date data;
    
    @JTableColumnMetadata(name = "Mesa")
    private Mesa mesa;
    
    @JTableColumnMetadata(name = "Garcom")
    private Garcom garcom;
    
    
    private List<Produto> produtos;
    private boolean aberto;
    private double parcialPedido;
    
    @JTableColumnMetadata(name = "Total")
    private double totalPedido;
    
    private double comissao;
    private boolean pagaComissao;

    public Pedido() {
        produtos = new ArrayList<>();
        aberto = true;
    }
    
    public Pedido(int id){
        this.codigo = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public double getParcialPedido() {
        return parcialPedido;
    }

    public void setParcialPedido(double parcialPedido) {
        this.parcialPedido = parcialPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public boolean pagaComissao() {
        return pagaComissao;
    }

    public void setPagaComissao(boolean pagaComissao) {
        this.pagaComissao = pagaComissao;
    }

}
