// CasaSurpresa.java
package models;

import factories.JogadorFactory;
import java.util.Random;

public class CasaSurpresa extends Casa {
    private Random random = new Random();

    public CasaSurpresa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        System.out.println(jogador.getNome() + " caiu na casa surpresa!");
        String[] tipos = {"normal", "sortudo", "azarado"};
        String novoTipo = tipos[random.nextInt(tipos.length)];
        Jogador novoJogador = JogadorFactory.criarJogador(novoTipo, jogador.getNome());
        novoJogador.setPosicao(jogador.getPosicao());
        novoJogador.adicionarMoedas(jogador.getMoedas());
        int index = tabuleiro.getJogadores().indexOf(jogador);
        tabuleiro.getJogadores().set(index, novoJogador);
        System.out.println(jogador.getNome() + " agora é um jogador " + novoTipo + "!");
    }
}