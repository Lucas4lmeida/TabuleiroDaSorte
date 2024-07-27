import javax.swing.*;
import java.awt.*;
import java.util.List;

public class painelTabuleiro extends JPanel {
    private List<jogador> jogadores;

    public painelTabuleiro(List<jogador> jogadores) {
        this.jogadores = jogadores;
        setPreferredSize(new Dimension(800, 600)); // Define o tamanho preferido do painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharTabuleiro(g);
        desenharJogadores(g);
    }

    private void desenharTabuleiro(Graphics g) {
        // Desenha o retângulo que representa o tabuleiro
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 700, 500);

        // Opcional: desenha a marcação das casas
        g.setColor(Color.GRAY);
        for (int i = 0; i < 40; i++) {
            int x = 50 + (i % 10) * 70;
            int y = 50 + (i / 10) * 50;
            g.drawRect(x, y, 70, 50);
            g.drawString(Integer.toString(i), x + 10, y + 25);
        }
    }

    private void desenharJogadores(Graphics g) {
        for (jogador jogador : jogadores) {
            // Desenha a posição do jogador
            g.setColor(jogador.getCor());
            int x = 50 + (jogador.getPosicao() % 10) * 70;
            int y = 50 + (jogador.getPosicao() / 10) * 50;
            g.fillOval(x + 10, y + 10, 20, 20);
            g.drawString(jogador.getNome(), x + 10, y + 40);
        }
    }
}
