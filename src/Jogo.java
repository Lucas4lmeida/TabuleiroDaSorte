import models.*;
import factories.*;
import views.TabuleiroView;
import controllers.TabuleiroController;
import javax.swing.JOptionPane;
import java.awt.Color;

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

    public void configTabuleiro(int numCasas) {
        String[] opcoesCasas = {"Simples", "Surpresa", "Prisão", "Sorte", "Azar", "Reversa", "Joga de Novo", "Troca"};

        for (int i = 0; i < numCasas; i++) {
            int escolha = JOptionPane.showOptionDialog(null,
                    "Escolha o tipo da casa " + i + ":",
                    "Configuração do Tabuleiro",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoesCasas,
                    opcoesCasas[0]);

            Casa casa = CasaFactory.criarCasaEspecifica(opcoesCasas[escolha], i);
            tabuleiro.adicionarCasa(casa);
        }

        tabuleiro.imprimirLayout();  // Imprime o layout do tabuleiro
    }

    public void config() {
        String numCasasStr = JOptionPane.showInputDialog(null, "Digite o número de casas do tabuleiro:", "Configuração do Tabuleiro", JOptionPane.QUESTION_MESSAGE);
        int numCasas = Integer.parseInt(numCasasStr);
        configTabuleiro(numCasas);

        String numJogadoresStr = JOptionPane.showInputDialog(null, "Digite o número de jogadores (2-6):", "Configuração de Jogadores", JOptionPane.QUESTION_MESSAGE);
        int numJogadores = Integer.parseInt(numJogadoresStr);

        for (int i = 0; i < numJogadores; i++) {
            String[] opcoes = {"Normal", "Sortudo", "Azarado"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha o tipo do jogador " + (i + 1) + ":",
                    "Tipo de Jogador", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            String tipo = opcoes[escolha].toLowerCase();

            String nome = JOptionPane.showInputDialog(null, "Digite o nome do jogador " + (i + 1) + ":", "Nome do Jogador", JOptionPane.QUESTION_MESSAGE);
            Color cor = CORES[corIndex++ % CORES.length];
            Jogador jogador = JogadorFactory.criarJogador(tipo, nome, cor);
            tabuleiro.adicionarJogador(jogador);
        }

        int modoDebugEscolha = JOptionPane.showConfirmDialog(null, "Ativar modo debug?", "Modo Debug", JOptionPane.YES_NO_OPTION);
        boolean modoDebug = (modoDebugEscolha == JOptionPane.YES_OPTION);
        controller.setModoDebug(modoDebug);
    }

    public void printTabuleiro() {
        view.atualizarTabuleiro(tabuleiro);
    }

    public void start() {
        controller.iniciarJogo();
    }
}