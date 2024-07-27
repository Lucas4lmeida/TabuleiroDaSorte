
import java.awt.Color;

public abstract class jogador {
    protected String nome;
    protected Color cor;
    protected int posicao;
    protected int jogadas;
    protected boolean podeJogar;

    public jogador(String nome, Color cor) {
        this.nome = nome;
        this.cor = cor;
        this.posicao = 0;
        this.jogadas = 0;
        this.podeJogar = true;
    }

    public abstract void mover(int casas, tabuleiro jogo);

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

    public void incrementarJogadas() {
        jogadas++;
    }

    public int getJogadas() {
        return jogadas;
    }

    public boolean podeJogar() {
        return podeJogar;
    }

    public void setPodeJogar(boolean podeJogar) {
        this.podeJogar = podeJogar;
    }
}
