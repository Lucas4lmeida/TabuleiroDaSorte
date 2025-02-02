import models.*;
import factories.*;
import views.TabuleiroView;
import controllers.TabuleiroController;
import utils.InputValidator;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.*;

public class Jogo {
    private Tabuleiro tabuleiro;
    private TabuleiroView view;
    private TabuleiroController controller;
    private static final Color[] CORES = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK};
    private int corIndex = 0;

    public Jogo() {
        this.tabuleiro = Tabuleiro.getInstance();
        this.view = new TabuleiroView();
        this.controller = new TabuleiroController(tabuleiro, view);
    }

    public void config() {
        int numCasas = InputValidator.lerInteiro("Digite o número de casas do tabuleiro:", "Configuração do Tabuleiro", 20, 100);
        configTabuleiro(numCasas);

        int numJogadores = InputValidator.lerInteiro("Digite o número de jogadores (2-6):", "Configuração de Jogadores", 2, 6);

        for (int i = 0; i < numJogadores; i++) {
            String[] opcoes = {"Normal", "Sortudo", "Azarado"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha o tipo do jogador " + (i + 1) + ":",
                    "Tipo de Jogador", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            String tipo = opcoes[escolha].toLowerCase();

            String nome = InputValidator.lerString("Digite o nome do jogador " + (i + 1) + ":", "Nome do Jogador");
            Color cor = CORES[corIndex++ % CORES.length];
            Jogador jogador = JogadorFactory.criarJogador(tipo, nome, cor);
            tabuleiro.adicionarJogador(jogador);
        }

        int modoDebugEscolha = JOptionPane.showConfirmDialog(null, "Ativar modo debug?", "Modo Debug", JOptionPane.YES_NO_OPTION);
        boolean modoDebug = (modoDebugEscolha == JOptionPane.YES_OPTION);
        controller.setModoDebug(modoDebug);
    }

    public void configTabuleiro(int numCasas) {
        Map<String, Set<Integer>> casasEspeciais = new HashMap<>();
        String[] tiposCasas = {"Surpresa", "Prisão", "Sorte", "Azar", "Reversa", "Joga de Novo", "Troca"};

        for (String tipo : tiposCasas) {
            String input = JOptionPane.showInputDialog(null,
                    "Digite os números das casas " + tipo + " (separados por vírgula):",
                    "Configuração de Casas " + tipo,
                    JOptionPane.QUESTION_MESSAGE);

            Set<Integer> casas = new HashSet<>();
            if (input != null && !input.trim().isEmpty()) {
                for (String num : input.split(",")) {
                    try {
                        casas.add(Integer.parseInt(num.trim()));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Número inválido ignorado: " + num,
                                "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            casasEspeciais.put(tipo, casas);
        }

        for (int i = 0; i < numCasas; i++) {
            Casa casa = criarCasa(i, casasEspeciais);
            tabuleiro.adicionarCasa(casa);
        }

        tabuleiro.imprimirLayout();
    }

    private Casa criarCasa(int numero, Map<String, Set<Integer>> casasEspeciais) {
        for (Map.Entry<String, Set<Integer>> entry : casasEspeciais.entrySet()) {
            if (entry.getValue().contains(numero)) {
                return CasaFactory.criarCasaEspecifica(entry.getKey(), numero);
            }
        }
        return new CasaSimples(numero);
    }

    public void printTabuleiro() {
        view.atualizarTabuleiro(tabuleiro);
    }

    public void start() {
        controller.iniciarJogo();
    }
}