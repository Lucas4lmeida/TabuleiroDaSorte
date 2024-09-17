// CasaAzar.java
package models;

public class CasaAzar extends Casa {
    public CasaAzar(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        if (!(jogador instanceof JogadorSortudo)) {
            jogador.removerMoedas(3);
            System.out.println(jogador.getNome() + " perdeu 3 moedas na casa de azar!");
        } else {
            System.out.println(jogador.getNome() + " é sortudo e não perdeu moedas na casa de azar.");
        }
    }
}