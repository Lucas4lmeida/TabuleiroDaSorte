// CasaReversa.java
package models;

public class CasaReversa extends Casa {
    public CasaReversa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        Jogador jogadorMaisAtras = tabuleiro.getJogadorMaisAtras(jogador);
        if (jogadorMaisAtras != null) {
            int posicaoTemp = jogador.getPosicao();
            jogador.setPosicao(jogadorMaisAtras.getPosicao());
            jogadorMaisAtras.setPosicao(posicaoTemp);
            System.out.println(jogador.getNome() + " trocou de lugar com " + jogadorMaisAtras.getNome() + "!");
        } else {
            System.out.println(jogador.getNome() + " é o último e não trocou de lugar.");
        }
    }
}