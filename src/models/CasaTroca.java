package models;

import decorators.*;
import javax.swing.JOptionPane;

public class CasaTroca extends Casa {
    public CasaTroca(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        boolean continuarEscolhendo = true;

        while (continuarEscolhendo) {
            String[] opcoes = {"Boné (2 moedas)", "Moleton (3 moedas)", "Óculos escuros (4 moedas)", "Pular"};
            int escolha = JOptionPane.showOptionDialog(null,
                    jogador.getNome() + " pode trocar seus pontos por itens!\n" +
                            "Você tem " + jogador.getMoedas() + " moedas.\n" +
                            "Escolha um item para comprar:",
                    "Casa Troca",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0: // Boné
                    if (jogador.getMoedas() >= 2 && !(jogador instanceof JogadorComBone)) {
                        jogador.removerMoedas(2);
                        jogador = new JogadorComBone(jogador);
                        JOptionPane.showMessageDialog(null, jogador.getNome() + " adquiriu um boné!", "Item Adquirido", JOptionPane.INFORMATION_MESSAGE);
                        continuarEscolhendo = false;
                    } else if (jogador instanceof JogadorComBone) {
                        JOptionPane.showMessageDialog(null, "Você já possui um boné.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Moedas insuficientes para comprar o boné.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 1: // Moleton
                    if (jogador instanceof JogadorComBone && jogador.getMoedas() >= 3 && !(jogador instanceof JogadorComMoleton)) {
                        jogador.removerMoedas(3);
                        jogador = new JogadorComMoleton(jogador);
                        JOptionPane.showMessageDialog(null, jogador.getNome() + " adquiriu um moleton!", "Item Adquirido", JOptionPane.INFORMATION_MESSAGE);
                        continuarEscolhendo = false;
                    } else if (!(jogador instanceof JogadorComBone)) {
                        JOptionPane.showMessageDialog(null, "Você precisa ter um boné antes de comprar o moleton.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    } else if (jogador instanceof JogadorComMoleton) {
                        JOptionPane.showMessageDialog(null, "Você já possui um moleton.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Moedas insuficientes para comprar o moleton.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 2: // Óculos escuros
                    if (jogador instanceof JogadorComMoleton && jogador.getMoedas() >= 4 && !(jogador instanceof JogadorComOculos)) {
                        jogador.removerMoedas(4);
                        jogador = new JogadorComOculos(jogador);
                        JOptionPane.showMessageDialog(null, jogador.getNome() + " adquiriu óculos escuros!", "Item Adquirido", JOptionPane.INFORMATION_MESSAGE);
                        continuarEscolhendo = false;
                    } else if (!(jogador instanceof JogadorComMoleton)) {
                        JOptionPane.showMessageDialog(null, "Você precisa ter um moleton antes de comprar os óculos escuros.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    } else if (jogador instanceof JogadorComOculos) {
                        JOptionPane.showMessageDialog(null, "Você já possui óculos escuros.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Moedas insuficientes para comprar os óculos escuros.", "Compra Falhou", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 3: // Pular
                case -1: // Fechar janela
                    JOptionPane.showMessageDialog(null, jogador.getNome() + " decidiu não comprar nada.", "Compra Pulada", JOptionPane.INFORMATION_MESSAGE);
                    continuarEscolhendo = false;
                    break;
            }
        }

        // Atualiza o jogador no tabuleiro
        for (int i = 0; i < tabuleiro.getJogadores().size(); i++) {
            if (tabuleiro.getJogadores().get(i).getNome().equals(jogador.getNome())) {
                tabuleiro.getJogadores().set(i, jogador);
                break;
            }
        }
    }
}