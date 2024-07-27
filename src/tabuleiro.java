import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class tabuleiro {
    private static final int TOTAL_CASAS = 40;
    private JFrame frame;
    private painelTabuleiro painelTabuleiro;
    private JButton lancarDadosButton;
    private JLabel infoLabel;
    private JLabel dadosLabel; // Novo JLabel para mostrar o valor dos dados
    private List<jogador> jogadores = new ArrayList<>();
    private int turno = 0;
    private dados dados = new dados();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(tabuleiro::new);
    }

    public tabuleiro() {
        frame = new JFrame("Tabuleiro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        inicializarJogadores();

        if (jogadores.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum jogador foi inicializado.");
            System.exit(1); // Encerra o programa se não houver jogadores
        }

        painelTabuleiro = new painelTabuleiro(jogadores);
        frame.add(painelTabuleiro, BorderLayout.CENTER);

        JPanel controlePanel = new JPanel();
        controlePanel.setLayout(new FlowLayout());

        lancarDadosButton = new JButton("Lançar Dados");
        lancarDadosButton.addActionListener(new LancarDadosListener());
        controlePanel.add(lancarDadosButton);

        dadosLabel = new JLabel("Valores dos dados: ");
        controlePanel.add(dadosLabel);

        frame.add(controlePanel, BorderLayout.SOUTH);

        infoLabel = new JLabel("É a vez do " + jogadores.get(turno).getNome(), JLabel.CENTER);
        frame.add(infoLabel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void inicializarJogadores() {
        jogadores.add(new jogadorSortudo("Jogador 1", Color.RED));
        jogadores.add(new jogadorAzarado("Jogador 2", Color.BLUE));
        jogadores.add(new jogadorSortudo("Jogador 3", Color.GREEN));
        jogadores.add(new jogadorAzarado("Jogador 4", Color.YELLOW));
        jogadores.add(new jogadorSortudo("Jogador 5", Color.ORANGE));
        jogadores.add(new jogadorAzarado("Jogador 6", Color.MAGENTA));
    }

    private class LancarDadosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jogadores.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum jogador foi inicializado.");
                return;
            }

            jogador jogadorAtual = jogadores.get(turno);
            if (jogadorAtual == null) {
                JOptionPane.showMessageDialog(frame, "Jogador atual é null.");
                return;
            }

            if (!jogadorAtual.podeJogar()) {
                proximoTurno();
                return;
            }

            int[] dadosLançados = dados.lancarDados();
            if (dadosLançados == null || dadosLançados.length != 2) {
                JOptionPane.showMessageDialog(frame, "Erro ao lançar dados.");
                return;
            }

            int dado1 = dadosLançados[0];
            int dado2 = dadosLançados[1];
            int somaDados = dado1 + dado2;

            dadosLabel.setText("Valores dos dados: " + dado1 + " e " + dado2);

            jogadorAtual.mover(somaDados, tabuleiro.this);
            infoLabel.setText("Jogador atual: " + jogadorAtual.getNome() + " (Posição: " + jogadorAtual.getPosicao() + ")");

            if (dado1 == dado2) {
                JOptionPane.showMessageDialog(frame, "Você tirou um duplo! Jogue novamente.");
            } else {
                proximoTurno();
            }

            painelTabuleiro.repaint();
            verificarVencedor();
        }
    }

    private void proximoTurno() {
        if (jogadores.isEmpty()) return;

        turno = (turno + 1) % jogadores.size();
        if (turno < jogadores.size()) {
            jogador proximoJogador = jogadores.get(turno);
            infoLabel.setText("É a vez do " + proximoJogador.getNome());
        }
    }

    private void verificarVencedor() {
        if (jogadores.isEmpty()) return;

        for (jogador jogador : jogadores) {
            if (jogador != null && jogador.getPosicao() >= TOTAL_CASAS - 1) {
                JOptionPane.showMessageDialog(frame, "Parabéns, " + jogador.getNome() + "! Você ganhou!");
                System.exit(0);
            }
        }
    }

    public void alterarTipoJogador(jogador jogadorAntigo, jogador novoTipo) {
        if (jogadorAntigo == null || novoTipo == null) {
            JOptionPane.showMessageDialog(frame, "Jogador inválido.");
            return;
        }

        int index = jogadores.indexOf(jogadorAntigo);
        if (index != -1) {
            jogadores.set(index, novoTipo);
        }
    }

    public void pularVez() {
        proximoTurno();
    }

    public void retrocederJogador() {
        if (jogadores.size() < 2) {
            JOptionPane.showMessageDialog(frame, "Não há jogadores suficientes para retroceder.");
            return;
        }

        // Cria uma lista de nomes dos jogadores para mostrar na caixa de diálogo
        List<String> nomesJogadores = new ArrayList<>();
        for (jogador jogador : jogadores) {
            nomesJogadores.add(jogador.getNome());
        }

        // Exibe a caixa de diálogo com a lista de jogadores
        String jogadorSelecionadoNome = (String) JOptionPane.showInputDialog(
                frame,
                "Escolha um jogador para retroceder ao início:",
                "Escolha um Jogador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomesJogadores.toArray(),
                nomesJogadores.get(0)
        );

        if (jogadorSelecionadoNome != null) {
            // Encontra o jogador selecionado na lista de jogadores
            for (jogador jogador : jogadores) {
                if (jogador.getNome().equals(jogadorSelecionadoNome)) {
                    jogador.setPosicao(0);
                    break;
                }
            }
        }
    }

    public void trocarPosicaoJogadores(jogador jogadorAtual) {
        if (jogadorAtual == null || jogadores.isEmpty()) return;

        jogador jogadorMaisAtras = jogadores.stream()
                .filter(j -> j != jogadorAtual)
                .min((j1, j2) -> Integer.compare(j1.getPosicao(), j2.getPosicao()))
                .orElse(null);

        if (jogadorMaisAtras != null) {
            int posicaoAtual = jogadorAtual.getPosicao();
            jogadorAtual.setPosicao(jogadorMaisAtras.getPosicao());
            jogadorMaisAtras.setPosicao(posicaoAtual);
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
