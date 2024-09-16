package models;

import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;

public class JogadorNormal extends Jogador {
    
    private List<Jogador> jogadores;

    public JogadorNormal(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public void mover(int casas) {
        if (!podeJogar) return;


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
            if (carta.getTipo() == CartaAleatoria.Tipo.CARTA_AFORTUNADO) {
                JOptionPane.showMessageDialog(null, "Você agora é um Jogador Sortudo!");

                JogadorSortudo jogadorSortudo = new JogadorSortudo(nome, cor, jogadores);

                painelTabuleiro.substituirJogador(this, jogadorSortudo);

                
                if (painelTabuleiro != null) {
                    painelTabuleiro.revalidate();
                    painelTabuleiro.repaint();
                }
            }
        } else if (posicao == 5 || posicao == 15 || posicao == 30) {
            JOptionPane.showMessageDialog(null, "Você é um Jogador Normal e não pode avançar nas casas da sorte.");
        } else if (posicao == 17 || posicao == 27) {
            escolherJogadorParaRetornarAoInicio();
        }

        if (painelTabuleiro != null) {
            painelTabuleiro.revalidate();
            painelTabuleiro.repaint();
        }
    }

    private void escolherJogadorParaRetornarAoInicio() {
        if (painelTabuleiro == null) {
            JOptionPane.showMessageDialog(null, "Painel do tabuleiro não está disponível.");
        }
    }
}
