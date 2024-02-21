package br.com.TabelaFipe.service;

import java.util.List;

public interface IConverteDados {

    //<T> T = Significa genêrico
    <T> T obterDados(String json, Class <T> classe);
    // Devera ser passado na chamado do método o tipo de classe no qual ele vai retornar.
    <T> List<T> obterLista(String json, Class<T> classe);
}
