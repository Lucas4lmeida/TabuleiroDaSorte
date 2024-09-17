// CasaSimples.java
package models;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        jogador.adicionarMoedas(1);
        System.out.println(jogador.getNome() + " ganhou 1 moeda na casa simples.");
    }
}