package models;

import java.util.Random;

public class Dados {
    private Random random;

    public Dados() {
        this.random = new Random();
    }

    public ResultadoDados lancarDados() {
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        return new ResultadoDados(dado1, dado2);
    }
}