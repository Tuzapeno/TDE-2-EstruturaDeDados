package com.example;

import java.util.Random;

public class Main {
    private static int jogadas = 0;
    private static int orientacao = 0;

    public static void arrumaAuto(Pilha[] pilhas) {
        while (!pilhas[2].vazia()) {
            mover(pilhas, 3, 1);
            jogadas++;
        }
        while(!pilhas[1].vazia()) {
            mover(pilhas, 2, 1);
            jogadas++;
        }
    }

    public static void checaFim(Pilha[] pilhas) {
        if (orientacao == 0) {
            for(Pilha p : pilhas) {
                if (p.cheia() && p.checaCrescente()) {
                    System.out.println("Jogo finalizado com: " + jogadas + " jogadas");
                    System.exit(0);
                }
            }
        }
        else {
            for(Pilha p : pilhas) {
                if (p.cheia() && p.checaDecrescente()) {
                    System.out.println("Jogo finalizado com: " + jogadas + " jogadas");
                    System.exit(0);
                }
            }
        }
    }

    public static void automatica(Pilha[] pilhas) {

        // Se a organização terminou, colocar o resto na pilha 2
        if (pilhas[0].vazia()) {
            while(!pilhas[2].vazia()) {
                mover(pilhas, 3, 2);
                jogadas++;
            }
            return;
        }

        // Enquanto houver valores na pilha 3 adicione a pilha 2
        while (!pilhas[2].vazia()) {
            mover(pilhas, 3, 2);
            jogadas++;
        }

        if (orientacao == 0) {
            // Crescente
            // Se pilha1 é menor que pilha2, mova o conteúdo até encontrar um valor menor
            while (!(pilhas[0].first() <= pilhas[1].first()) && !pilhas[1].vazia()) {
                mover(pilhas, 2, 3);
                jogadas++;
            }
        }
        else {
            // Decrescente
            while (!(pilhas[0].first() >= pilhas[1].first()) && !pilhas[1].vazia()) {
                mover(pilhas, 2, 3);
                jogadas++;
            }
        }

        // Mova o valor para pilha 2 
        mover(pilhas, 1, 2);
        jogadas++;
        automatica(pilhas);
    }

    public static void mover(Pilha[] pilhas, int origem, int destino) {
        origem--;
        destino--;

        if (pilhas[origem].vazia()) {
            System.out.println("Pilha " + (origem+1) + " vazia");
            return;
        }

        pilhas[destino].push(pilhas[origem].pop());
    }

    public static void printaPilhas(Pilha[] pilhas) {
        for (int i = 0; i < pilhas.length; i++) {
            System.out.println("Pilha " + (i+1) + ":");
            pilhas[i].print();
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        
        int menu = -1;
        int tamanho = 0;

        do {
            System.out.println("Tamanho (0-100): ");
            tamanho = Integer.parseInt(System.console().readLine());
        } while (tamanho < 0 && tamanho > 100);

        do {
            System.out.println("Crescente (0) ou Decrescente (1)?");
            orientacao = Integer.parseInt(System.console().readLine());
        } while (orientacao != 0 && orientacao != 1);
        
        Random random = new Random();
        Pilha[] pilhas = new Pilha[3];
        pilhas[0] = new Pilha(tamanho);
        pilhas[1] = new Pilha(tamanho);
        pilhas[2] = new Pilha(tamanho);

        for (int i = 0; i < tamanho; i++) {
            pilhas[0].push(1 + random.nextInt(100));
        }
        
        do {
            printaPilhas(pilhas);
            checaFim(pilhas);
            System.out.println("0. Sair");
            System.out.println("1. Movimentar");
            System.out.println("2. Solucao Automatica");
            menu = Integer.parseInt(System.console().readLine());


            switch(menu) {
                case 1:
                    System.out.print("Origem: ");
                    int origem = Integer.parseInt(System.console().readLine());
                    System.out.print("Destino:");
                    int destino = Integer.parseInt(System.console().readLine());
                    System.out.println();
                    if (origem > 3 || origem < 1 || destino > 3 || destino < 1) {
                        System.out.println("Pilha invalida");
                        break;
                    }
                    mover(pilhas, origem, destino);
                    jogadas++;
                    break;
                case 2:
                    arrumaAuto(pilhas);
                    mover(pilhas, 1, 2);
                    jogadas++;
                    automatica(pilhas);
                    printaPilhas(pilhas);
                    System.out.println("Jogo finalizado com " + jogadas + " jogadas");
                    menu = 0;
                    break;
            }
        } while (menu != 0);
    

    }

}