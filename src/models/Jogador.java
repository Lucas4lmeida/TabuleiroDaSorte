// Jogador.java
package models;

import java.awt.Color;

public abstract class Jogador {
    protected String nome;
    protected Color cor;
    protected int posicao = 0;
    protected int moedas = 0;
    protected boolean preso = false;
    protected int turnosPreso = 0;
    protected int numeroJogadas = 0;

    public Jogador(String nome, Color cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public abstract int[] jogarDados();

    public void mover(int casas) {
        this.posicao += casas;
        this.numeroJogadas++;
    }

    public void adicionarMoedas(int quantidade) {
        this.moedas += quantidade;
    }

    public void removerMoedas(int quantidade) {
        this.moedas = Math.max(0, this.moedas - quantidade);
    }

    // Getters e setters
    public String getNome() { return nome; }
    public Color getCor() { return cor; }
    public int getPosicao() { return posicao; }
    public void setPosicao(int posicao) { this.posicao = posicao; }
    public int getMoedas() { return moedas; }
    public boolean estaPreso() { return preso; }
    public void setPreso(boolean preso) { this.preso = preso; }
    public int getTurnosPreso() { return turnosPreso; }
    public void setTurnosPreso(int turnosPreso) { this.turnosPreso = turnosPreso; }
    public int getNumeroJogadas() { return numeroJogadas; }

    public void decrementarTurnosPreso() {
        if (turnosPreso > 0) {
            turnosPreso--;
            if (turnosPreso == 0) {
                preso = false;
            }
        }
    }
}