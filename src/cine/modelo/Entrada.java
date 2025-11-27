package cine.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cliente cliente;
    private int numeroSala;
    private int fila;
    private int numeroButaca;
    private LocalDateTime fechaCompra;

    public Entrada(Cliente cliente, int numeroSala, int fila, int numeroButaca) {
        this.cliente = cliente;
        this.numeroSala = numeroSala;
        this.fila = fila;
        this.numeroButaca = numeroButaca;
        this.fechaCompra = LocalDateTime.now();
    }

    public Cliente getCliente() { return cliente; }
    public int getNumeroSala() { return numeroSala; }
    public int getFila() { return fila; }
    public int getNumeroButaca() { return numeroButaca; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }

    @Override
    public String toString() {
        return cliente + " - Sala " + numeroSala + " F" + fila + " B" + numeroButaca;
    }
}



