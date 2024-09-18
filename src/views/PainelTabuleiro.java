package views;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class PainelTabuleiro extends JPanel {
    private Tabuleiro tabuleiro;
    private static final int TAMANHO_CASA = 60;
    private static final int MARGEM = 10;
    private static final int TAMANHO_JOGADOR = 30;
    private static final int TAMANHO_ICONE = 20;
    private static final Map<Class<? extends Casa>, Color> CORES_CASAS = new LinkedHashMap<>();

    static {
        CORES_CASAS.put(CasaSimples.class, Color.WHITE);
        CORES_CASAS.put(CasaSurpresa.class, Color.YELLOW);
        CORES_CASAS.put(CasaPrisao.class, Color.RED);
        CORES_CASAS.put(CasaSorte.class, Color.GREEN);
        CORES_CASAS.put(CasaAzar.class, Color.ORANGE);
        CORES_CASAS.put(CasaReversa.class, Color.CYAN);
        CORES_CASAS.put(CasaJogaDeNovo.class, Color.MAGENTA);
        CORES_CASAS.put(CasaTroca.class, Color.PINK);
    }

    public PainelTabuleiro() {
        setPreferredSize(new Dimension(1000, 700));
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
        desenharLegenda(g);
    }

    private void desenharCasas(Graphics g, List<Casa> casas) {
        int casasPorLado = (int) Math.ceil(Math.sqrt(casas.size()));
        int largura = getWidth() - 300 - 2 * MARGEM;
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
        }
    }

    private void desenharJogadores(Graphics g, List<Jogador> jogadores, List<Casa> casas) {
        int casasPorLado = (int) Math.ceil(Math.sqrt(casas.size()));
        int largura = getWidth() - 300 - 2 * MARGEM;
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
                if (img != null) {
                    g.drawImage(img, x - TAMANHO_ICONE / 2, y - TAMANHO_JOGADOR - TAMANHO_ICONE / 2, TAMANHO_ICONE, TAMANHO_ICONE, this);
                } else {
                    System.out.println("Imagem do ícone é null para " + jogador.getNome());
                }
            }
        }
    }

    private Color getCasaColor(Casa casa) {
        return CORES_CASAS.getOrDefault(casa.getClass(), Color.LIGHT_GRAY);
    }

    private void desenharLegenda(Graphics g) {
        int x = getWidth() - 280;
        int y = MARGEM;
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Legenda:", x, y);
        y += 30;

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        for (Map.Entry<Class<? extends Casa>, Color> entry : CORES_CASAS.entrySet()) {
            g.setColor(entry.getValue());
            g.fillRect(x, y, 20, 20);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 20, 20);
            g.drawString(entry.getKey().getSimpleName().replace("Casa", ""), x + 30, y + 15);
            y += 30;
        }

        // Adicionar informações dos jogadores
        y += 20;
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jogadores:", x, y);
        y += 30;

        if (tabuleiro != null) {
            for (Jogador jogador : tabuleiro.getJogadores()) {
                g.setColor(jogador.getCor());
                g.fillOval(x, y, 20, 20);
                g.setColor(Color.BLACK);
                g.drawString(jogador.getNome() + " - " + jogador.getMoedas() + " moedas", x + 30, y + 15);
                y += 30;
            }
        }
    }
}