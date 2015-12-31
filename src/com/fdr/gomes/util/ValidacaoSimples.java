package com.fdr.gomes.util;

/**
 *
 * @author lossurdo
 */
public class ValidacaoSimples {

    public static final boolean isVazio(String valor) {
        if (valor == null || valor.isEmpty()) {
            return true;
        }
        return false;
    }

    public static final boolean isVazio(Number valor) {
        if (valor == null || Long.parseLong(valor.toString()) == 0) {
            return true;
        }
        return false;
    }

}
