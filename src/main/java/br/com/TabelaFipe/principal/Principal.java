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

    }
}
