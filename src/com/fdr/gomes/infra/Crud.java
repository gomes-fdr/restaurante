package com.fdr.gomes.infra;

import java.util.List;

interface Crud<T> {

    void salvar(T bean);
    void excluir(T bean);
    T consultar(T bean);
    void alterar(T bean);
    List<T> pesquisar(String pesquisa);
    
}
