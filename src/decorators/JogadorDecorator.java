package decorators;

import models.Jogador;
import models.ResultadoDados;
import java.awt.Color;
import javax.swing.ImageIcon;

public abstract class JogadorDecorator extends Jogador {
    protected Jogador jogadorDecorado;

    public JogadorDecorator(Jogador jogador) {
        super(jogador.getNome(), jogador.getCor());
        this.jogadorDecorado = jogador;
        this.posicao = jogador.getPosicao();
        this.moedas = jogador.getMoedas();
        this.preso = jogador.estaPreso();
        this.turnosPreso = jogador.getTurnosPreso();
        this.numeroJogadas = jogador.getNumeroJogadas();
    }

    @Override
    public ResultadoDados jogarDados() {
        return jogadorDecorado.jogarDados();
    }

    @Override
    public void mover(int casas, int totalCasas) {
        jogadorDecorado.mover(casas, totalCasas);
        this.posicao = jogadorDecorado.getPosicao();
        this.numeroJogadas = jogadorDecorado.getNumeroJogadas();
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        jogadorDecorado.adicionarMoedas(quantidade);
        this.moedas = jogadorDecorado.getMoedas();
    }

    @Override
    public void removerMoedas(int quantidade) {
        jogadorDecorado.removerMoedas(quantidade);
        this.moedas = jogadorDecorado.getMoedas();
    }

    @Override
    public boolean chegouAoFinal(int totalCasas) {
        return jogadorDecorado.chegouAoFinal(totalCasas);
    }

    @Override
    public void decrementarTurnosPreso() {
        jogadorDecorado.decrementarTurnosPreso();
        this.preso = jogadorDecorado.estaPreso();
        this.turnosPreso = jogadorDecorado.getTurnosPreso();
    }

    @Override
    public ImageIcon getIcon() {
        return jogadorDecorado.getIcon();
    }

    @Override
    public String getInfoItens() {
        return jogadorDecorado.getInfoItens();
    }

    // Os getters e setters devem delegar para o jogadorDecorado
    @Override
    public int getPosicao() { return jogadorDecorado.getPosicao(); }
    @Override
    public void setPosicao(int posicao) { jogadorDecorado.setPosicao(posicao); }
    @Override
    public int getMoedas() { return jogadorDecorado.getMoedas(); }
    @Override
    public void setMoedas(int moedas) { jogadorDecorado.setMoedas(moedas); }
    @Override
    public boolean estaPreso() { return jogadorDecorado.estaPreso(); }
    @Override
    public void setPreso(boolean preso) { jogadorDecorado.setPreso(preso); }
    @Override
    public int getTurnosPreso() { return jogadorDecorado.getTurnosPreso(); }
    @Override
    public void setTurnosPreso(int turnosPreso) { jogadorDecorado.setTurnosPreso(turnosPreso); }
    @Override
    public int getNumeroJogadas() { return jogadorDecorado.getNumeroJogadas(); }
}