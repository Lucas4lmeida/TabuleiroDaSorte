package models;

public class ResultadoDados {
    private int dado1;
    private int dado2;

    public ResultadoDados(int dado1, int dado2) {
        this.dado1 = dado1;
        this.dado2 = dado2;
    }

    public int getDado1() {
        return dado1;
    }

    public int getDado2() {
        return dado2;
    }

    public int getTotal() {
        return dado1 + dado2;
    }

    @Override
    public String toString() {
        return "Dado 1: " + dado1 + ", Dado 2: " + dado2 + ", Total: " + getTotal();
    }
}