package com.fdr.gomes.app;

import com.fdr.gomes.bean.Garcom;
import com.fdr.gomes.bean.Mesa;
import com.fdr.gomes.bean.Pedido;
import com.fdr.gomes.bean.Produto;
import com.fdr.gomes.infra.Propriedades;
import com.fdr.gomes.rn.GarcomRN;
import com.fdr.gomes.rn.MesaRN;
import com.fdr.gomes.rn.PedidoRN;
import com.fdr.gomes.rn.ProdutoRN;
import com.fdr.gomes.util.Console;
import com.fdr.gomes.util.MenuConsole;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 *
 * @author Fabiano
 */
public class RestauranteConsole {
    
    private static final Logger logger = Logger.getLogger(RestauranteConsole.class);

    public static void main(String[] args) throws Exception {
        
        System.out.println(Propriedades.getInstance().get("db.driver"));
        
        
        // informação de versão do java que está rodando
        logger.debug("Sistema de restaurante v1.0.1");
        logger.debug("Segunda entrega");
        logger.debug("Fabiano Gomes<gomes.fdr@gmail.com>");
        
        
        while (true) {
            MenuConsole mc = new MenuConsole("Restaurante Senac",
                    "Controle de Restaurante (Desktop)",
                    RestauranteConsole.class);
            mc.adicionarAcao("Cadastrar garçom", "cadastrarGarcom");
            mc.adicionarAcao("Cadastrar mesa", "cadastrarMesa");
            mc.adicionarAcao("Cadastrar produto", "cadastrarProduto");
            mc.adicionarAcao("Listar mesas", "listarMesa");
            mc.adicionarAcao("Listar garcom", "listarGarcom");
            mc.adicionarAcao("Fazer pedido", "fazerPedido");
            mc.adicionarAcao("Encerrar pedido", "encerrarPedido");
            mc.adicionarAcao("Emitir relatorio", "emitirRelatorio");
            //mc.adicionarAcao("Listar tudo", "listarTudo");
            mc.adicionarAcao("Sair", "sair");

            System.out.println(mc.getTexto());

            Integer op = Console.lerInteger("Qual a sua opção:");

            mc.executarAcao(op);
        }
    }

    public void cadastrarGarcom() {
        logger.debug("Cadastrar garçom...");

        int cpf;
        String nome;
        double salario;

        boolean cadastroOK = true;

        do {
            nome = Console.lerString("Nome do garçom");
            cpf = Console.lerInteger("CPF");
            salario = Console.lerDouble("Salario garçom");
        } while (!cadastroOK);

        Garcom garcom = new Garcom();
        garcom.setNome(nome);
        garcom.setId(cpf);
        garcom.setSalario(salario);

        GarcomRN rn = new GarcomRN();
        if (rn.validaIncluir(garcom)) {
            rn.salvar(garcom);
            logger.debug("Garçom cadastrado com sucesso!");
        } else {
            logger.debug("Impossível cadastrar registro! Dados informados violam regras de negócio.");
        }

    }

    public void cadastrarMesa() {
        logger.debug("Cadastrar mesa...");

        int capacidade;
        boolean cadastroOK = true;

        do { 
            capacidade = Console.lerInteger("Número de pessoas da mesa: ");
        } while (!cadastroOK);

        Mesa mesa = new Mesa();
        mesa.setCapacidade(capacidade);

        MesaRN rn = new MesaRN();

        if (rn.validaIncluir(mesa)) {
            rn.salvar(mesa);
            logger.debug("Mesa cadastrada com sucesso!");
        } else {
            logger.debug("Impossível cadastrar registro! Dados informados violam regras de negócio.");
        }

    }

    public void cadastrarProduto() {
        logger.debug("Cadastrar produto...");

        String nome;
        int tipo;

        boolean cadastroOK = true;

        do {
            nome = Console.lerString("Informe o nome do produto: ");            
            Console.mensagem("1-COMIDA, 2-BEBIDA, 3-SOBREMESA, 4-OUTRO");
            tipo = Console.lerInteger("Tipo de produto: ");

        } while (!cadastroOK);

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setTipo(tipo);

        ProdutoRN rn = new ProdutoRN();
        if (rn.validaIncluir(produto)) {
            rn.salvar(produto);
            logger.debug("Produto cadastrado com sucesso!");
        } else {
            logger.debug("Impossível cadastrar registro! Dados informados violam regras de negócio.");
        }
    }

    public void listarMesa() {
        logger.debug("Listar mesa...");
    }
    
    public void listarGarcom() {
        logger.debug("Listar garcom5...");
    }

    public void fazerPedido() throws Exception{
        logger.debug("Fazer pedido...");

        Date data;
        Mesa mesa;
        Garcom garcom;
        Produto p;
        List<Produto> produtos = new ArrayList<>();;
        
        boolean aberto;
        double parcialPedido;
        double totalPedido;
        double comissao;
        boolean pagaComissao;

        boolean cadastroOK = true;

        do {
            data = new Date();

            mesa = new Mesa();
            mesa.setNumero(1);
            
            garcom = new Garcom();
            garcom.setId(111);
            
            data = new SimpleDateFormat("dd/MM/yyyy").parse("22/11/2015");
            
            
                        
            Pedido pedido = new Pedido();
            
            pedido.setData(data);
            pedido.setMesa(mesa);
            pedido.setGarcom(garcom);
            pedido.setAberto(true);
            pedido.setParcialPedido(100.00);
            pedido.setTotalPedido(120.00);
            pedido.setPagaComissao(true);
            pedido.setComissao(20.00);
            
            p = new Produto();
            p.setCodigo(3);
            
            produtos.add(p);
            
            pedido.setProdutos(produtos);
            
            PedidoRN rn = new PedidoRN();
            if (rn.validaIncluir(pedido)) {
                rn.salvar(pedido);
                logger.debug("Pedido cadastrado com sucesso!");
            } else {
                logger.debug("Impossível cadastrar registro! Dados informados violam regras de negócio.");
            }

        } while (!cadastroOK);

    }

    public void encerrarPedido() {
        logger.debug("Encerrar pedido...");
    }

    public void emitirRelatorio() {
        logger.debug("Emitir relatorio...");
    }

    public void sair() {
        System.exit(0);
    }

}
