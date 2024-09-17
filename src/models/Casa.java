// Casa.java
package models;

public abstract class Casa {
    protected int numero;

    public Casa(int numero) {
        this.numero = numero;
    }

    public abstract void aplicarRegra(Jogador jogador, Tabuleiro tabuleiro);

    public int getNumero() {
        return numero;
    }
}