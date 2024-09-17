// PainelTabuleiro.java
package views;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelTabuleiro extends JPanel {
    private Tabuleiro tabuleiro;
    private static final int TAMANHO_CASA = 60;
    private static final int MARGEM = 10;

    public PainelTabuleiro() {
        setPreferredSize(new Dimension(800, 600));
    }

    public void atualizarTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tabuleiro == null) return;

        List<Casa> casas = tabuleiro.getCasas();
        List<Jogador> jogadores = tabuleiro.getJogadores();

        desenharCasas(g, casas);
        desenharJogadores(g, jogadores, casas);
    }

    private void desenharCasas(Graphics g, List<Casa> casas) {
        int x = MARGEM, y = MARGEM;
        int largura = getWidth() - 2 * MARGEM;
        int altura = getHeight() - 2 * MARGEM;
        int casasPorLado = (int) Math.ceil(Math.sqrt(casas.size()));

        for (int i = 0; i < casas.size(); i++) {
            Casa casa = casas.get(i);
            int coluna = i % casasPorLado;
            int linha = i / casasPorLado;

            int casaX = x + coluna * (largura / casasPorLado);
            int casaY = y + linha * (altura / casasPorLado);

            g.setColor(getCasaColor(casa));
            g.fillRect(casaX, casaY, TAMANHO_CASA, TAMANHO_CASA);
            g.setColor(Color.BLACK);
            g.drawRect(casaX, casaY, TAMANHO_CASA, TAMANHO_CASA);
            g.drawString(Integer.toString(i), casaX + 5, casaY + 15);
        }
    }

    private void desenharJogadores(Graphics g, List<Jogador> jogadores, List<Casa> casas) {
        int casasPorLado = (int) Math.ceil(Math.sqrt(casas.size()));
        int largura = getWidth() - 2 * MARGEM;
        int altura = getHeight() - 2 * MARGEM;

        for (Jogador jogador : jogadores) {
            int posicao = jogador.getPosicao();
            int coluna = posicao % casasPorLado;
            int linha = posicao / casasPorLado;

            int x = MARGEM + coluna * (largura / casasPorLado) + TAMANHO_CASA / 2;
            int y = MARGEM + linha * (altura / casasPorLado) + TAMANHO_CASA / 2;

            g.setColor(jogador.getCor());
            g.fillOval(x - 10, y - 10, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(jogador.getNome().substring(0, 1), x - 3, y + 5);
        }
    }

    private Color getCasaColor(Casa casa) {
        if (casa instanceof CasaSimples) return Color.WHITE;
        if (casa instanceof CasaSurpresa) return Color.YELLOW;
        if (casa instanceof CasaPrisao) return Color.RED;
        if (casa instanceof CasaSorte) return Color.GREEN;
        if (casa instanceof CasaAzar) return Color.ORANGE;
        if (casa instanceof CasaReversa) return Color.CYAN;
        if (casa instanceof CasaJogaDeNovo) return Color.MAGENTA;
        if (casa instanceof CasaTroca) return Color.PINK;
        return Color.LIGHT_GRAY;
    }
}