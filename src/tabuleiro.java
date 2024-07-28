import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class tabuleiro {
    private static final int TOTAL_CASAS = 40;
    private static final int[] CASAS_SORTE = {5, 15, 30}; // Casas da sorte
    private JFrame frame;
    private painelTabuleiro painelTabuleiro;
    private JButton lancarDadosButton;
    private JLabel infoLabel;
    private JLabel dadosLabel;
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

        int numJogadores = obterNumeroJogadores();
        inicializarJogadores(numJogadores);

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

    private int obterNumeroJogadores() {
        int numJogadores = 0;
        while (numJogadores < 2 || numJogadores > 6) {
            String input = JOptionPane.showInputDialog(frame, "Informe o número de jogadores (2 a 6):");
            try {
                numJogadores = Integer.parseInt(input);
                if (numJogadores < 2 || numJogadores > 6) {
                    JOptionPane.showMessageDialog(frame, "Número de jogadores deve ser entre 2 e 6.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Entrada inválida. Por favor, insira um número.");
            }
        }
        return numJogadores;
    }

    private void inicializarJogadores(int numJogadores) {
        jogadores.clear();
        for (int i = 1; i <= numJogadores; i++) {
            if (i % 2 == 0) {
                jogadores.add(new jogadorAzarado("Jogador " + i, Color.getHSBColor(i / (float) numJogadores, 1.0f, 1.0f)));
            } else {
                jogadores.add(new jogadorSortudo("Jogador " + i, Color.getHSBColor(i / (float) numJogadores, 1.0f, 1.0f)));
            }
        }
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

            verificarCasaDaSorte(jogadorAtual);

            if (dado1 == dado2) {
                JOptionPane.showMessageDialog(frame, "Você tirou um duplo! Jogue novamente.");
            } else {
                proximoTurno();
            }

            painelTabuleiro.repaint();
            verificarVencedor();
        }
    }

    private void verificarCasaDaSorte(jogador jogadorAtual) {
        for (int casa : CASAS_SORTE) {
            if (jogadorAtual.getPosicao() == casa) {
                if (!(jogadorAtual instanceof jogadorAzarado)) {
                    jogadorAtual.setPosicao(jogadorAtual.getPosicao() + 3);
                    JOptionPane.showMessageDialog(frame, jogadorAtual.getNome() + " caiu na casa da sorte e avançou 3 casas!");
                } else {
                    JOptionPane.showMessageDialog(frame, jogadorAtual.getNome() + " caiu na casa da sorte, mas como é um jogador azarado, não avançou.");
                }
                break;
            }
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

        List<String> nomesJogadores = new ArrayList<>();
        for (jogador jogador : jogadores) {
            nomesJogadores.add(jogador.getNome());
        }

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
