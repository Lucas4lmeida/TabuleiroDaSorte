package controllers;

import models.*;
import views.*;
import javax.swing.JOptionPane;
import java.util.*;

public class TabuleiroController {
    private Tabuleiro tabuleiro;
    private TabuleiroView view;
    private Scanner scanner;
    private boolean modoDebug;

    public TabuleiroController(Tabuleiro tabuleiro, TabuleiroView view) {
        this.tabuleiro = tabuleiro;
        this.view = view;
        this.scanner = new Scanner(System.in);
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
            int[] resultadoDados = jogador.jogarDados();
            casasAndadas = resultadoDados[2]; // soma dos dados
            JOptionPane.showMessageDialog(null,
                    jogador.getNome() + " lançou os dados:\n" +
                            "Dado 1: " + resultadoDados[0] + "\n" +
                            "Dado 2: " + resultadoDados[1] + "\n" +
                            "Total: " + casasAndadas,
                    "Resultado dos Dados",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, jogador.getNome() + " andou " + casasAndadas + " casas.", "Movimento", JOptionPane.INFORMATION_MESSAGE);

        int novaPosicao = (jogador.getPosicao() + casasAndadas) % tabuleiro.getCasas().size();
        jogador.setPosicao(novaPosicao);

        Casa casaAtual = tabuleiro.getCasas().get(novaPosicao);
        casaAtual.aplicarRegra(jogador, tabuleiro);

        view.atualizarTabuleiro(tabuleiro);

        if (casaAtual instanceof CasaJogaDeNovo) {
            JOptionPane.showMessageDialog(null, jogador.getNome() + " joga novamente!", "Jogar Novamente", JOptionPane.INFORMATION_MESSAGE);
            realizarJogada(jogador);
        }
    }

    private int lerCasasDebug() {
        String input = JOptionPane.showInputDialog(null, "Modo Debug: Digite o número de casas para andar:", "Movimento Debug", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(input);
    }

    private boolean jogoTerminou() {
        return tabuleiro.getJogadores().stream().anyMatch(j -> j.getPosicao() >= tabuleiro.getCasas().size() - 1);
    }

    private void anunciarVencedor(Jogador vencedor) {
        JOptionPane.showMessageDialog(null, vencedor.getNome() + " venceu o jogo!", "Vencedor", JOptionPane.INFORMATION_MESSAGE);
        mostrarResultadoFinal();
    }

    private void mostrarResultadoFinal() {
        List<Jogador> jogadores = tabuleiro.getJogadores();
        jogadores.sort((j1, j2) -> Integer.compare(j2.getPosicao(), j1.getPosicao()));

        StringBuilder resultado = new StringBuilder("Resultado Final:\n\n");

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            resultado.append(String.format("%dº lugar: %s\n", i + 1, jogador.getNome()))
                    .append(String.format("Posição: %d\n", jogador.getPosicao()))
                    .append(String.format("Moedas: %d\n", jogador.getMoedas()))
                    .append(String.format("Jogadas: %d\n\n", jogador.getNumeroJogadas()));
        }

        JOptionPane.showMessageDialog(null, resultado.toString(), "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    }
}