package models;

import decorators.*;
import javax.swing.JOptionPane;

public class CasaTroca extends Casa {
    public CasaTroca(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        JOptionPane.showMessageDialog(null, jogador.getNome() + " pode trocar seus pontos por itens!", "Casa Troca", JOptionPane.INFORMATION_MESSAGE);

        String[] opcoes = {"Boné (2 moedas)", "Moleton (3 moedas)", "Óculos escuros (4 moedas)", "Pular"};
        int escolha = JOptionPane.showOptionDialog(null,
                "Escolha um item para comprar:\n" +
                        "Você tem " + jogador.getMoedas() + " moedas.",
                "Comprar Item",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, opcoes[0]);

        switch (escolha) {
            case 0: // Boné
                if (jogador.getMoedas() >= 2) {
                    jogador.removerMoedas(2);
                    jogador = new JogadorComBone(jogador);
                    JOptionPane.showMessageDialog(null, jogador.getNome() + " adquiriu um boné!", "Item Adquirido", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Moedas insuficientes para comprar o boné.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 1: // Moleton
                if (jogador instanceof JogadorComBone && jogador.getMoedas() >= 3) {
                    jogador.removerMoedas(3);
                    jogador = new JogadorComMoleton(jogador);
                    JOptionPane.showMessageDialog(null, jogador.getNome() + " adquiriu um moleton!", "Item Adquirido", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Você precisa ter um boné e 3 moedas para comprar o moleton.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 2: // Óculos escuros
                if (jogador instanceof JogadorComMoleton && jogador.getMoedas() >= 4) {
                    jogador.removerMoedas(4);
                    jogador = new JogadorComOculos(jogador);
                    JOptionPane.showMessageDialog(null, jogador.getNome() + " adquiriu óculos escuros!", "Item Adquirido", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Você precisa ter um moleton e 4 moedas para comprar os óculos escuros.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 3: // Pular
                JOptionPane.showMessageDialog(null, jogador.getNome() + " decidiu não comprar nada.", "Compra Pulada", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Atualiza o jogador no tabuleiro
        int index = tabuleiro.getJogadores().indexOf(jogador);
        tabuleiro.getJogadores().set(index, jogador);
    }
}