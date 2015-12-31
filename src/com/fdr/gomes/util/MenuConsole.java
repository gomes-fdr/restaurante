package com.fdr.gomes.util;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 * @author lossurdo
 */
public class MenuConsole {
    private final String nomeSistema;
    private final String descricao;
    private final Class classe;
    private HashMap<String, String> acaoTexto;
    private HashMap<Integer, String> codigoAcao;
    private Integer contador;

    public MenuConsole(String nomeSistema, String descricao, Class classe) {
        this.nomeSistema = nomeSistema;
        this.descricao = descricao;
        this.classe = classe;
        this.contador = 1;
        this.acaoTexto = new HashMap<>();
        this.codigoAcao = new HashMap<>();
    }

    public void adicionarAcao(String texto, String acao) {
        acaoTexto.put(acao, texto);
        codigoAcao.put(contador, acao);
        contador++;
    }

    public void executarAcao(Integer codigo) throws Exception {
        String acao = codigoAcao.get(codigo);
        Object obj = classe.newInstance();
        Method m = classe.getMethod(acao, null);
        System.out.println(">>> Executando método: " + acao);
        m.invoke(obj, null);
    }
    
    public String getTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n\r\n");
        sb.append("///////////////////////////////////////////////////////\r\n");
        sb.append(nomeSistema).append("\r\n");
        sb.append(descricao).append("\r\n\r\n");
        
        for(Integer codigo : codigoAcao.keySet()) {
            sb.append(codigo.toString()).append("-> ").
                    append(acaoTexto.get(codigoAcao.get(codigo))).append("\r\n");
        }
        
        sb.append("\r\n");
        
        return sb.toString();
    }
    
}
