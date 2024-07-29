package models;

import java.awt.Color;
import views.PainelTabuleiro;

public abstract class Jogador {
    protected String nome;
    protected Color cor;
    protected int posicao = 0;
    protected boolean podeJogar = true;
    // Adicionando referência ao painel
    protected PainelTabuleiro painelTabuleiro; 

    public Jogador(String nome, Color cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public void setPainelTabuleiro(PainelTabuleiro painelTabuleiro) {
        this.painelTabuleiro = painelTabuleiro;
    }

    public abstract void mover(int casas);

    public String getNome() {
        return nome;
    }

    public Color getCor() {
        return cor;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean podeJogar() {
        return podeJogar;
    }

    public void setPodeJogar(boolean podeJogar) {
        this.podeJogar = podeJogar;
    }
}
