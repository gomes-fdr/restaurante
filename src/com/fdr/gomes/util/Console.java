package com.fdr.gomes.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author lossurdo
 */
public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    
    public static void mensagem(Object texto) {
        System.out.print(">>> " + texto + " ");
        pularLinha();
        pularLinha();
    }
    
    private static void imprimir(String texto) {
        System.out.print(">>> " + texto + " ");
    }
    
    private static void pularLinha() {
        System.out.println();
    }
    
    public static String lerString(String texto) {        
        imprimir(texto);
        String valor = scanner.next();
        pularLinha();
        return valor;
    }
    
    public static Double lerDouble(String texto) {        
        imprimir(texto);
        Double valor = scanner.nextDouble();
        pularLinha();
        return valor;
    }
    
    public static Long lerLong(String texto) {        
        imprimir(texto);
        Long valor = scanner.nextLong();
        pularLinha();
        return valor;
    }
    
    public static Integer lerInteger(String texto) {        
        imprimir(texto);
        Integer valor = scanner.nextInt();
        pularLinha();
        return valor;
    }
    
    public static String lerEnum(String texto, Class<? extends Enum> clazz) {
        HashMap<Integer, String> hm = new HashMap<>();
        int o = 1;
        for(Object obj : EnumSet.allOf(clazz)) {
            Enum e = (Enum) obj;
            imprimir(o + "-" + e.name());
            pularLinha();
            hm.put(o, e.name());
            o++;
        }
        imprimir("Qual a sua opção? ");
        Integer valor = scanner.nextInt();
        pularLinha();
        return hm.get(valor);
    }
    
    public static void cabecalho(String texto) {
        pularLinha();
        System.out.println("=====================================");
        System.out.println(texto);
        System.out.println("=====================================");
    }
}
