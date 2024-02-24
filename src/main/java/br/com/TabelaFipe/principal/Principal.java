package br.com.TabelaFipe.principal;

import br.com.TabelaFipe.model.Dados;
import br.com.TabelaFipe.model.Modelos;
import br.com.TabelaFipe.model.Veiculo;
import br.com.TabelaFipe.service.ConsumoAPI;
import br.com.TabelaFipe.service.ConverteDados;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.*;

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
                                
                Escolha uma opção: \n""";

        System.out.println(menu);
        var escolha = leitura.nextInt();
        switch (escolha) {
            case 1 -> {
                System.out.println("\nMOTO");
                enderecoTemp = ENDERECO + "motos/marcas";
            }
            case 2 -> {
                System.out.println("\nCARRO");

                enderecoTemp = ENDERECO + "carros/marcas";
            }
            case 3 -> {
                System.out.println("\nCAMINHÃO");
                enderecoTemp = ENDERECO + "caminhoes/marcas";
            }
            default -> System.out.println("Opção inválida");
        }

        var json = consumo.obterDados(enderecoTemp);


        var dados = conversor.obterLista(json, Dados.class);
        System.out.println("\nMarcas: \n");
        dados.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

        System.out.println("\nDigite a marca para pesquisa: ");
        var marcaPesquisa = leitura.next();

        Optional<Dados> marcaBuscada = dados.stream()
                .filter(d -> d.nome()
                        .toUpperCase()
                        .contains(marcaPesquisa.toUpperCase()))
                .findFirst();
        // Optinal que recebe a pesquisa do trecho digitado, filtrando objetos pelo nome


        enderecoTemp += "/" + marcaBuscada.get().codigo() + "/modelos";

        json = consumo.obterDados(enderecoTemp);


        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: \n");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

        System.out.println("\nDigite o código do modelo para pesquisa: \n");
        var codigoModelo = leitura.next();

        Optional<Dados> modeloBusca = modeloLista.modelos()
                .stream()
                .filter(d -> d.codigo().contains(codigoModelo))
                .findFirst();


        enderecoTemp += "/" + modeloBusca.get().codigo() + "/anos";

        json = consumo.obterDados(enderecoTemp);
        List<Dados> anos = conversor.obterLista(json, Dados.class);

        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size() ; i++) {
            var enderecoAnos = enderecoTemp + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);

        }

        System.out.println("Todos os veículos: \n");

        veiculos.stream()
                .sorted(Comparator.comparing(Veiculo::anoModelo))
                .forEach(v -> System.out.println(v.valor() + " - " + v.marca() + " - " + v.modelo() + " - " + v.anoModelo() + " - " + v.combustivel() ));
//

//
//
//        System.out.println("\nDados do Veiculo" +
//                "\nValor: " + veiculo.Valor() +
//                "\nMarca: " + veiculo.Marca() +
//                "\nModelo: " + veiculo.Modelo() +
//                "\nAno Modelo: " + veiculo.AnoModelo().toString() +
//                "\nCombustível: " + veiculo.Combustivel() +
//                "\nCódigo Fipe: " + veiculo.CodigoFipe() +
//                "\nMês Referência: " + veiculo.MesReferencia() +
//                "\nSigla Combustível: " + veiculo.SiglaCombustivel()
//        );


    }
}
