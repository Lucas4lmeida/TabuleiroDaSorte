
import java.awt.Color;
import javax.swing.JOptionPane;

public class JogadorSortudo extends Jogador {

    public JogadorSortudo(String nome, Color cor) {
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
        if (posicao == 13) {
            CartaAleatoria carta = CartaAleatoria.sortearCarta();
            JOptionPane.showMessageDialog(jogo.getFrame(), "Você tirou a carta: " + carta);
            if (carta.getTipo() == CartaAleatoria.Tipo.CARTA_AZARADO) {
                jogo.alterarTipoJogador(this, new JogadorAzarado(this.nome, this.cor));
            }
        } else if (posicao == 5 || posicao == 15 || posicao == 30) {
           // if (this instanceof jogadorAzarado) {
              //   JOptionPane.showMessageDialog(jogo.getFrame(), "Você é um jogador azarado e não pode avançar.");
            // } else
            {
                this.posicao += 3;
                if (this.posicao >= 40) this.posicao = 39;
            }
        } else if (posicao == 20 || posicao == 35) {
            jogo.trocarPosicaoJogadores(this);
        }
    }
}
