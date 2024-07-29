package models;

import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;

public class JogadorSortudo extends Jogador {

    private List<Jogador> jogadores;

    public JogadorSortudo(String nome, Color cor, List<Jogador> jogadores) {
        super(nome, cor);
        this.jogadores = jogadores;
    }

    @Override
    public void mover(int casas) {
        if (!podeJogar) return;

        // Verifica a soma dos dados e ajusta a posição
        if (casas < 7) {
            casas = 7; // Garante que a soma dos dados seja sempre >= 7
        }

        int novaPosicao = this.posicao + casas;
        if (novaPosicao >= 41) {
            novaPosicao = 40;
        }
        this.posicao = novaPosicao;

        verificarCasaEspecial();
    }

    private void verificarCasaEspecial() {
        if (posicao == 13) {
            CartaAleatoria carta = CartaAleatoria.sortearCarta();
            JOptionPane.showMessageDialog(null, "Você tirou a carta: " + carta);
            if (carta.getTipo() == CartaAleatoria.Tipo.CARTA_AZARADO) {
                JOptionPane.showMessageDialog(null, "Você agora é um Jogador Azarado!");
                substituirPorJogadorAzarado();
            }
        } else if (posicao == 5 || posicao == 15 || posicao == 30) {
            JOptionPane.showMessageDialog(null, "Você caiu em uma casa da sorte! Avance 3 casas.");
            this.posicao = Math.min(this.posicao + 3, 40);
        } else if (posicao == 17 || posicao == 27) {
            escolherJogadorParaRetornarAoInicio();
        } else if (posicao == 20 || posicao == 35) {
            trocarComJogadorMaisAtras();
        }
    }

    private void substituirPorJogadorAzarado() {
        if (jogadores == null || jogadores.size() < 2) {
            JOptionPane.showMessageDialog(null, "Não há jogadores suficientes para substituir.");
            return;
        }

        JogadorAzarado novoJogadorAzarado = new JogadorAzarado(getNome(), getCor());
        int index = jogadores.indexOf(this);
        if (index != -1) {
            jogadores.set(index, novoJogadorAzarado);
            // Atualizar o painelTabuleiro, se necessário
            if (painelTabuleiro != null) {
                painelTabuleiro.substituirJogador(this, novoJogadorAzarado);
                painelTabuleiro.revalidate();
                painelTabuleiro.repaint();
            }
            JOptionPane.showMessageDialog(null, "Você foi substituído por um Jogador Azarado!");
        } else {
            JOptionPane.showMessageDialog(null, "Jogador Sortudo não encontrado na lista.");
        }
    }

    private void escolherJogadorParaRetornarAoInicio() {
        if (jogadores == null || jogadores.size() < 2) {
            JOptionPane.showMessageDialog(null, "Não há jogadores suficientes para escolher.");
            return;
        }

        String[] nomesJogadores = jogadores.stream()
                .filter(j -> j != this)
                .map(Jogador::getNome)
                .toArray(String[]::new);

        String jogadorEscolhido = (String) JOptionPane.showInputDialog(
                null,
                "Você caiu em uma casa especial! Escolha um jogador para voltar ao início:",
                "Escolher Jogador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomesJogadores,
                nomesJogadores[0]);

        if (jogadorEscolhido != null) {
            for (Jogador jogador : jogadores) {
                if (jogador.getNome().equals(jogadorEscolhido)) {
                    jogador.setPosicao(0);
                    JOptionPane.showMessageDialog(null, jogador.getNome() + " foi enviado de volta ao início.");
                    break;
                }
            }
        }
    }

    private void trocarComJogadorMaisAtras() {
        if (jogadores == null || jogadores.size() < 2) {
            JOptionPane.showMessageDialog(null, "Não há jogadores suficientes para trocar de lugar.");
            return;
        }

        Jogador jogadorMaisAtras = null;
        for (Jogador jogador : jogadores) {
            if (jogador != this && (jogadorMaisAtras == null || jogador.getPosicao() < jogadorMaisAtras.getPosicao())) {
                jogadorMaisAtras = jogador;
            }
        }

        if (jogadorMaisAtras != null && jogadorMaisAtras.getPosicao() < this.getPosicao()) {
            int posicaoOriginal = this.getPosicao();
            this.setPosicao(jogadorMaisAtras.getPosicao());
            jogadorMaisAtras.setPosicao(posicaoOriginal);
            JOptionPane.showMessageDialog(null, "Você trocou de lugar com " + jogadorMaisAtras.getNome() + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Você está no final do jogo e não pode trocar de lugar.");
        }
    }
}
