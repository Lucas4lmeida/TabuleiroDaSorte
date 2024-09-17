package utils;

import javax.swing.JOptionPane;

public class InputValidator {

    public static int lerInteiro(String mensagem, String titulo, int min, int max) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // Usuário clicou em "Cancelar"
                throw new RuntimeException("Operação cancelada pelo usuário.");
            }

            try {
                int valor = Integer.parseInt(input.trim());
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, insira um número entre " + min + " e " + max + ".",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira um número válido.",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String lerString(String mensagem, String titulo) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // Usuário clicou em "Cancelar"
                throw new RuntimeException("Operação cancelada pelo usuário.");
            }

            input = input.trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Por favor, insira um valor não vazio.",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}