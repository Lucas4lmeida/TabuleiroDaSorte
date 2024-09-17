// Tabuleiro.java
package models;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private static Tabuleiro instance;
    private List<Jogador> jogadores;
    private List<Casa> casas;

    private Tabuleiro() {
        jogadores = new ArrayList<>();
        casas = new ArrayList<>();
    }

    public static synchronized Tabuleiro getInstance() {
        if (instance == null) {
            instance = new Tabuleiro();
        }
        return instance;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void adicionarCasa(Casa casa) {
        casas.add(casa);
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public Jogador getJogadorMaisAtras(Jogador jogadorAtual) {
        return jogadores.stream()
                .filter(j -> j != jogadorAtual)
                .min((j1, j2) -> Integer.compare(
                        (j1.getPosicao() <= jogadorAtual.getPosicao() ? j1.getPosicao() : j1.getPosicao() + casas.size()),
                        (j2.getPosicao() <= jogadorAtual.getPosicao() ? j2.getPosicao() : j2.getPosicao() + casas.size())
                ))
                .orElse(null);
    }
}