package controllers;

import models.*;
import views.TabuleiroView;
import javax.swing.JOptionPane;

public class TabuleiroController {
    private Tabuleiro tabuleiro;
    private TabuleiroView view;
    private boolean modoDebug;

    public TabuleiroController(Tabuleiro tabuleiro, TabuleiroView view) {
        this.tabuleiro = tabuleiro;
        this.view = view;
        this.modoDebug = false;
    }

    public void setModoDebug(boolean modoDebug) {
        this.modoDebug = modoDebug;
    }

    public void iniciarJogo() {
        while (!jogoTerminou()) {
            for (Jogador jogador : tabuleiro.getJogadores()) {
                if (jogador.estaPreso()) {
                    processarJogadorPreso(jogador);
                    continue;
                }

                realizarJogada(jogador);
                view.atualizarTabuleiro(tabuleiro);

                if (jogoTerminou()) {
                    anunciarVencedor(jogador);
                    return;
                }
            }
        }
    }

    private void processarJogadorPreso(Jogador jogador) {
        int escolha = JOptionPane.showConfirmDialog(null,
                jogador.getNome() + " está preso. Deseja pagar 2 moedas para sair?",
                "Jogador Preso", JOptionPane.YES_NO_OPTION);
        if (escolha == JOptionPane.YES_OPTION && jogador.getMoedas() >= 2) {
            jogador.removerMoedas(2);
            jogador.setPreso(false);
            JOptionPane.showMessageDialog(null, jogador.getNome() + " pagou a fiança e está livre.");
        } else {
            jogador.decrementarTurnosPreso();
            JOptionPane.showMessageDialog(null, jogador.getNome() + " continua preso. Turnos restantes: " + jogador.getTurnosPreso());
        }
    }

    private void realizarJogada(Jogador jogador) {
        JOptionPane.showMessageDialog(null, "Vez de " + jogador.getNome(), "Turno do Jogador", JOptionPane.INFORMATION_MESSAGE);

        int casasAndadas;
        if (modoDebug) {
            casasAndadas = lerCasasDebug();
        } else {
            ResultadoDados resultadoDados = jogador.jogarDados();
            casasAndadas = resultadoDados.getTotal();
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " lançou os dados:\n" + resultadoDados,
                    "Resultado dos Dados",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, jogador.getNome() + " andou " + casasAndadas + " casas.", "Movimento", JOptionPane.INFORMATION_MESSAGE);

        int novaPosicao = jogador.getPosicao() + casasAndadas;
        if (novaPosicao >= tabuleiro.getCasas().size()) {
            novaPosicao = tabuleiro.getCasas().size() - 1; // Garante que o jogador pare na última casa
        }
        jogador.setPosicao(novaPosicao);

        Casa casaAtual = tabuleiro.getCasas().get(novaPosicao);
        casaAtual.aplicarRegra(jogador, tabuleiro);

        if (jogoTerminou()) {
            anunciarVencedor(jogador);
        }
    }

    private int lerCasasDebug() {
        String input = JOptionPane.showInputDialog(null, "Modo Debug: Digite o número de casas para andar:", "Movimento Debug", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    private boolean jogoTerminou() {
        int ultimaCasa = tabuleiro.getCasas().size() - 1;
        return tabuleiro.getJogadores().stream().anyMatch(j -> j.getPosicao() >= ultimaCasa);
    }

    private void anunciarVencedor(Jogador vencedor) {
        JOptionPane.showMessageDialog(null, vencedor.getNome() + " venceu o jogo!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
        mostrarResultadoFinal();
    }

    private void mostrarResultadoFinal() {
        StringBuilder resultado = new StringBuilder("Resultado Final:\n\n");
        tabuleiro.getJogadores().stream()
                .sorted((j1, j2) -> Integer.compare(j2.getPosicao(), j1.getPosicao()))
                .forEach(jogador -> {
                    resultado.append(String.format("%s - Posição: %d, Moedas: %d, Jogadas: %d\n",
                            jogador.getNome(), jogador.getPosicao(), jogador.getMoedas(), jogador.getNumeroJogadas()));
                });
        JOptionPane.showMessageDialog(null, resultado.toString(), "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    }
}