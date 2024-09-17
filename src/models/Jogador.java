package models;

import java.awt.Color;
import javax.swing.ImageIcon;

public abstract class Jogador {
    protected String nome;
    protected Color cor;
    protected int posicao;
    protected int moedas;
    protected boolean preso;
    protected int turnosPreso;
    protected int numeroJogadas;

    public Jogador(String nome, Color cor) {
        this.nome = nome;
        this.cor = cor;
        this.posicao = 0;
        this.moedas = 0;
        this.preso = false;
        this.turnosPreso = 0;
        this.numeroJogadas = 0;
    }

    public ImageIcon getIcon() {
        return null; // Jogadores normais não têm ícone
    }

    public String getInfoBonus() {
        return "Sem bônus"; // Jogadores normais não têm bônus
    }

    // Método abstrato para jogar dados
    public abstract ResultadoDados jogarDados();

    // Getters e setters
    public String getNome() { return nome; }
    public Color getCor() { return cor; }
    public int getPosicao() { return posicao; }
    public void setPosicao(int posicao) { this.posicao = posicao; }
    public int getMoedas() { return moedas; }
    public void setMoedas(int moedas) { this.moedas = moedas; }
    public boolean estaPreso() { return preso; }
    public void setPreso(boolean preso) { this.preso = preso; }
    public int getTurnosPreso() { return turnosPreso; }
    public void setTurnosPreso(int turnosPreso) { this.turnosPreso = turnosPreso; }
    public int getNumeroJogadas() { return numeroJogadas; }
    public void setNumeroJogadas(int numeroJogadas) { this.numeroJogadas = numeroJogadas; }

    // Outros métodos
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

    public void decrementarTurnosPreso() {
        if (turnosPreso > 0) {
            turnosPreso--;
            if (turnosPreso == 0) {
                preso = false;
            }
        }
    }
}