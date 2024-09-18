package models;

import factories.JogadorFactory;
import javax.swing.JOptionPane;

public class CasaSurpresa extends Casa {
    public CasaSurpresa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        JOptionPane.showMessageDialog(null,
                jogador.getNome() + " caiu na casa surpresa!",
                "Casa Surpresa",
                JOptionPane.INFORMATION_MESSAGE);

        CartaAleatoria carta = CartaAleatoria.sortearCarta();
        JOptionPane.showMessageDialog(null,
                jogador.getNome() + " tirou uma carta: " + carta,
                "Carta Aleatória",
                JOptionPane.INFORMATION_MESSAGE);

        String novoTipo;
        switch (carta.getTipo()) {
            case CARTA_NORMAL:
                novoTipo = "normal";
                break;
            case CARTA_SORTUDO:
                novoTipo = "sortudo";
                break;
            case CARTA_AZARADO:
                novoTipo = "azarado";
                break;
            default:
                throw new IllegalStateException("Tipo de carta desconhecido");
        }

        Jogador novoJogador = JogadorFactory.criarJogador(novoTipo, jogador.getNome(), jogador.getCor());
        novoJogador.setPosicao(jogador.getPosicao());
        novoJogador.setMoedas(jogador.getMoedas());
        novoJogador.setNumeroJogadas(jogador.getNumeroJogadas());
        novoJogador.setPreso(jogador.estaPreso());
        novoJogador.setTurnosPreso(jogador.getTurnosPreso());

        int index = tabuleiro.getJogadores().indexOf(jogador);
        tabuleiro.getJogadores().set(index, novoJogador);

        JOptionPane.showMessageDialog(null,
                jogador.getNome() + " agora é um jogador " + novoTipo + "!",
                "Mudança de Tipo",
                JOptionPane.INFORMATION_MESSAGE);
    }
}