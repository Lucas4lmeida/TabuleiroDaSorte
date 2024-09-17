// JogadorDecorator.java
package decorators;

import models.Jogador;
import models.ResultadoDados;

public abstract class JogadorDecorator extends Jogador {
    protected Jogador jogadorDecorado;

    public JogadorDecorator(Jogador jogador) {
        super(jogador.getNome(), jogador.getCor());
        this.jogadorDecorado = jogador;
    }

    @Override
    public ResultadoDados jogarDados() {
        return jogadorDecorado.jogarDados();
    }

    @Override
    public void mover(int casas) {
        jogadorDecorado.mover(casas);
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        jogadorDecorado.adicionarMoedas(quantidade);
    }

    @Override
    public void removerMoedas(int quantidade) {
        jogadorDecorado.removerMoedas(quantidade);
    }

    @Override
    public int getPosicao() {
        return jogadorDecorado.getPosicao();
    }

    @Override
    public void setPosicao(int posicao) {
        jogadorDecorado.setPosicao(posicao);
    }

    @Override
    public int getMoedas() {
        return jogadorDecorado.getMoedas();
    }

    @Override
    public boolean estaPreso() {
        return jogadorDecorado.estaPreso();
    }

    @Override
    public void setPreso(boolean preso) {
        jogadorDecorado.setPreso(preso);
    }

    @Override
    public int getTurnosPreso() {
        return jogadorDecorado.getTurnosPreso();
    }

    @Override
    public void setTurnosPreso(int turnosPreso) {
        jogadorDecorado.setTurnosPreso(turnosPreso);
    }

    @Override
    public int getNumeroJogadas() {
        return jogadorDecorado.getNumeroJogadas();
    }

    @Override
    public void decrementarTurnosPreso() {
        jogadorDecorado.decrementarTurnosPreso();
    }
}