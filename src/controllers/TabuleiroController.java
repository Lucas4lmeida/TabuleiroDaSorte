package controllers;

import models.Jogador;
import models.Dados;
import views.PainelTabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TabuleiroController {
    private PainelTabuleiro painelTabuleiro;
    private Dados dados;
    private List<Jogador> jogadores;
    private int turno = 0;

    private JFrame frame;
    private JButton lancarDadosButton;
    private JLabel infoLabel;
    private JLabel dadosLabel;

    public TabuleiroController(PainelTabuleiro painelTabuleiro, Dados dados, List<Jogador> jogadores) {
        this.painelTabuleiro = painelTabuleiro;
        this.dados = dados;
        this.jogadores = jogadores;
        criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Tabuleiro do Jogo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        frame.setLayout(new BorderLayout());

        frame.add(painelTabuleiro, BorderLayout.CENTER);

        
        JPanel controlePanel = new JPanel();
        controlePanel.setLayout(new FlowLayout());

        
        lancarDadosButton = new JButton("Lançar Dados");
        lancarDadosButton.addActionListener(new LancarDadosListener());
        controlePanel.add(lancarDadosButton);

        dadosLabel = new JLabel("Valores dos dados: ");
        controlePanel.add(dadosLabel);

        
        infoLabel = new JLabel("É a vez do " + jogadores.get(turno).getNome(), JLabel.CENTER);
        frame.add(infoLabel, BorderLayout.NORTH);

        frame.add(controlePanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class LancarDadosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jogadores.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum jogador foi inicializado.");
                return;
            }

            Jogador jogadorAtual = jogadores.get(turno);
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

            dadosLabel.setText("Valores dos dados: " + dado1 + " e " + dado2 + ". Valor total dos dados: " + somaDados);

            jogadorAtual.mover(somaDados);
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
        turno = (turno + 1) % jogadores.size();
        infoLabel.setText("É a vez do " + jogadores.get(turno).getNome());
    }

    private void verificarVencedor() {
        for (Jogador jogador : jogadores) {
            if (jogador.getPosicao() >= 40) {
                JOptionPane.showMessageDialog(frame, "Parabéns, " + jogador.getNome() + "! Você ganhou!");
                System.exit(0);
            }
        }
    }

    public void alterarTipoJogador(Jogador jogador, Jogador novoTipo) {
       
        int index = jogadores.indexOf(jogador);
        if (index >= 0) {
            
            jogadores.set(index, novoTipo);
            painelTabuleiro.repaint();
        }
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
