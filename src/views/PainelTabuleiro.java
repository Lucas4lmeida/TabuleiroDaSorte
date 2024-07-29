package views;

import models.Jogador;
import java.awt.*;
import javax.swing.*;
import java.util.List;

public class PainelTabuleiro extends JPanel {
    private List<Jogador> jogadores;

    public PainelTabuleiro(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharTabuleiro(g);
        desenharJogadores(g);
    }

    private void desenharTabuleiro(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 700, 500);

        g.setColor(Color.GRAY);
        for (int i = 0; i < 40; i++) {
            int x = 50 + (i % 10) * 70;
            int y = 50 + (i / 10) * 50;
            g.drawRect(x, y, 70, 50);
            g.drawString(Integer.toString(i), x + 10, y + 25);
        }
    }

    private void desenharJogadores(Graphics g) {
        for (Jogador jogador : jogadores) {
            g.setColor(jogador.getCor());
            int x = 50 + (jogador.getPosicao() % 10) * 70;
            int y = 50 + (jogador.getPosicao() / 10) * 50;
            g.fillOval(x + 10, y + 10, 20, 20);
            g.drawString(jogador.getNome(), x + 10, y + 40);
        }
    }
}
