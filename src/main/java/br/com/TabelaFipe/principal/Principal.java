package br.com.TabelaFipe.principal;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);


    public void exibeMenu() {

        System.out.println("*** OPÇÕES ***  \n" +
                "1 - MOTO \n" +
                "2 - CARRO \n" +
                "3 - CAMINHÃO \n" +
                "Escolha uma opção: ");
        var escolha = leitura.nextInt();
        switch (escolha) {
            case 1 -> {
                System.out.println("MOTO");
                break;
            }
            case 2 -> {
                System.out.println("CARRO");
                break;
            }
            case 3 -> {
                System.out.println("CAMINHÃO");
                break;
            }
            default -> System.out.println("Opção inválida");
        }

    }
}
