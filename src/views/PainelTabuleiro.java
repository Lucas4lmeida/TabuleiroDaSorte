package views;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelTabuleiro extends JPanel {
    private Tabuleiro tabuleiro;
    private static final int TAMANHO_CASA = 60;
    private static final int MARGEM = 10;
    private static final int TAMANHO_JOGADOR = 30;
    private static final int TAMANHO_ICONE = 20;

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
        int casasPorLado = (int) Math.ceil(Math.sqrt(casas.size()));
        int largura = getWidth() - 2 * MARGEM;
        int altura = getHeight() - 2 * MARGEM;

        for (int i = 0; i < casas.size(); i++) {
            Casa casa = casas.get(i);
            int coluna = i % casasPorLado;
            int linha = i / casasPorLado;

            int x = MARGEM + coluna * (largura / casasPorLado);
            int y = MARGEM + linha * (altura / casasPorLado);

            g.setColor(getCasaColor(casa));
            g.fillRect(x, y, TAMANHO_CASA, TAMANHO_CASA);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, TAMANHO_CASA, TAMANHO_CASA);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString(Integer.toString(i), x + 5, y + 15);
            g.drawString(casa.getClass().getSimpleName(), x + 5, y + TAMANHO_CASA - 5);
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

            // Desenhar o jogador
            g.setColor(jogador.getCor());
            g.fillOval(x - TAMANHO_JOGADOR / 2, y - TAMANHO_JOGADOR / 2, TAMANHO_JOGADOR, TAMANHO_JOGADOR);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            String inicial = jogador.getNome().substring(0, 1).toUpperCase();
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(inicial);
            int textHeight = fm.getHeight();
            g.drawString(inicial, x - textWidth / 2, y + textHeight / 4);

            // Desenhar o ícone do item, se houver
            ImageIcon icon = jogador.getIcon();
            if (icon != null) {
                Image img = icon.getImage();
                g.drawImage(img, x - TAMANHO_ICONE / 2, y - TAMANHO_JOGADOR - TAMANHO_ICONE / 2, TAMANHO_ICONE, TAMANHO_ICONE, this);
            }
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