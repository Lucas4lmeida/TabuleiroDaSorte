// CasaSorte.java
package models;

public class CasaSorte extends Casa {
    public CasaSorte(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        if (!(jogador instanceof JogadorAzarado)) {
            jogador.mover(3);
            System.out.println(jogador.getNome() + " avançou 3 casas na casa da sorte!");
        } else {
            System.out.println(jogador.getNome() + " é azarado e não avançou na casa da sorte.");
        }
    }
}