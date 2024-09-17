import models.*;
import factories.*;
import views.TabuleiroView;
import controllers.TabuleiroController;
import javax.swing.JOptionPane;

public class Jogo {
    private Tabuleiro tabuleiro;
    private TabuleiroView view;
    private TabuleiroController controller;

    public Jogo() {
        this.tabuleiro = Tabuleiro.getInstance();
        this.view = new TabuleiroView();
        this.controller = new TabuleiroController(tabuleiro, view);
    }

    public void configTabuleiro(int numCasas) {
        for (int i = 0; i < numCasas; i++) {
            Casa casa = CasaFactory.criarCasa(i);
            tabuleiro.adicionarCasa(casa);
        }
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
            String tipo;
            switch (escolha) {
                case 0: tipo = "normal"; break;
                case 1: tipo = "sortudo"; break;
                case 2: tipo = "azarado"; break;
                default: throw new IllegalStateException("Escolha inválida");
            }

            String nome = JOptionPane.showInputDialog(null, "Digite o nome do jogador " + (i + 1) + ":", "Nome do Jogador", JOptionPane.QUESTION_MESSAGE);
            Jogador jogador = JogadorFactory.criarJogador(tipo, nome);
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