package br.com.TabelaFipe.principal;

import br.com.TabelaFipe.model.Dados;
import br.com.TabelaFipe.service.ConsumoAPI;
import br.com.TabelaFipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    //https://parallelum.com.br/fipe/api/v1/carros/marcas

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    private ConsumoAPI consumo = new ConsumoAPI();

    private ConverteDados conversor = new ConverteDados();


    public void exibeMenu() {
        String enderecoTemp = "";

        var menu = """
                 *** OPÇÕES ***    
                 
                1 - MOTO  
                2 - CARRO  
                3 - CAMINHÃO   
                
                Escolha uma opção: """;

        System.out.println(menu);
        var escolha = leitura.nextInt();
        switch (escolha) {
            case 1 -> {
                System.out.println("MOTO");
                enderecoTemp = ENDERECO + "motos/marcas";
            }
            case 2 -> {
                System.out.println("CARRO");

                enderecoTemp = ENDERECO + "carros/marcas";
            }
            case 3 -> {
                System.out.println("CAMINHÃO");
                enderecoTemp = ENDERECO + "caminhoes/marcas";
            }
            default -> System.out.println("Opção inválida");
        }

        var json = consumo.obterDados(enderecoTemp);
        System.out.println(json);

       var dados = conversor.obterLista(json, Dados.class);

        System.out.println(dados);
//
        List<Dados> marcas = new ArrayList<>();

//        marcas.forEach(System.out::println);



    }
}
