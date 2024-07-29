
import java.awt.Color;
import javax.swing.JOptionPane;

public class JogadorAzarado extends Jogador {

    public JogadorAzarado(String nome, Color cor) {
        super(nome, cor);
    }

    @Override
    public void mover(int casas, Tabuleiro jogo) {
        if (!podeJogar) return;

        int novaPosicao = this.posicao + casas;
        if (novaPosicao >= 40) {
            novaPosicao = 39;
        }
        this.posicao = novaPosicao;

        verificarCasaEspecial(jogo);
        this.incrementarJogadas();
    }

    private void verificarCasaEspecial(Tabuleiro jogo) {
        if (posicao == 10 || posicao == 25 || posicao == 38) {
            JOptionPane.showMessageDialog(jogo.getFrame(), "Você não joga a próxima rodada.");
            jogo.pularVez();
        } else if (posicao == 17 || posicao == 27) {
            jogo.retrocederJogador();
        }
    }
}
