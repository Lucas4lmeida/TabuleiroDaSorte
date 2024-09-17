package models;

import javax.swing.JOptionPane;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro) {
        int moedasAnteriores = jogador.getMoedas();
        jogador.adicionarMoedas(1);
        int moedasGanhas = jogador.getMoedas() - moedasAnteriores;

        StringBuilder mensagem = new StringBuilder();
        mensagem.append(jogador.getNome()).append(" caiu em uma casa simples.\n");
        mensagem.append("Moedas ganhas: ").append(moedasGanhas).append("\n");

        if (moedasGanhas > 1) {
            mensagem.append("(Incluindo ").append(moedasGanhas - 1).append(" moeda(s) extra(s) devido a itens)\n");
        }

        mensagem.append("Total de moedas: ").append(jogador.getMoedas());

        JOptionPane.showMessageDialog(null,
                mensagem.toString(),
                "Casa Simples",
                JOptionPane.INFORMATION_MESSAGE);
    }
}