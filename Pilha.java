package com.example;

public class Pilha {
    private Node topo;
    private int tamanho;

    public Pilha(int tamanho) {
        topo = null;
        this.tamanho = tamanho;
    }

    public void push(int dado) {
        if (topo == null) {
            topo = new Node(dado);
        }
        else if (!cheia()) {
            Node newNode = new Node(dado);
            topo.setProximo(newNode);
            newNode.setAnterior(topo);
            topo = newNode;
        }
    }

    public int pop() {
        if (topo != null) {
            int dado = topo.getDado();
            topo = topo.getAnterior();
            return dado;
        }
        return -1;
    }

    public boolean checaCrescente() {
        Node atual = topo;
        while (atual.getAnterior() != null) {
            if (atual.getDado() > atual.getAnterior().getDado()) {
                return false;
            }
            atual = atual.getAnterior();
        }
        return true;
    }

    public boolean checaDecrescente() {
        Node atual = topo;
        while (atual != null && atual.getAnterior() != null) {
            if (atual.getDado() <= atual.getAnterior().getDado()) {
                return false;
            }
            atual = atual.getAnterior();
        }
        return true;
    }

    public void print() {
        Node atual = topo;
        while (atual != null) {
            System.out.print(atual.getDado() + " ");
            atual = atual.getAnterior();
        }
        System.out.println();
    }

    public boolean vazia() {
        return topo == null;
    }

    public int first() {
        if (!vazia()) {
            return topo.getDado();
        }
        return -1;
    }

    public boolean cheia() {
        Node atual = topo;
        int cont = 0;
        while (atual != null) {
            atual = atual.getAnterior();
            cont++;
        }
        return cont == this.tamanho;
    }

}
