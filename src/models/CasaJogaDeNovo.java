// CasaJogaDeNovo.java
package models;

public class CasaJogaDeNovo extends Casa {
    public CasaJogaDeNovo(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        System.out.println(jogador.getNome() + " joga novamente!");
        // A lógica para jogar novamente é implementada na classe Jogo
    }
}