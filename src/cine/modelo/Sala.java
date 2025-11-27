package cine.modelo;

import java.io.Serializable;

public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numeroSala;
    private String pelicula;
    private Butaca[][] butacas;

    public Sala(int numeroSala, String pelicula, int filas, int columnas) {
        this.numeroSala = numeroSala;
        this.pelicula = pelicula;
        this.butacas = new Butaca[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                butacas[f][c] = new Butaca(f + 1, c + 1);
            }
        }
    }

    public int getNumeroSala() { return numeroSala; }
    public String getPelicula() { return pelicula; }
    public Butaca[][] getButacas() { return butacas; }

    public Butaca obtenerButaca(int fila, int numeroButaca) {
        if (fila < 1 || numeroButaca < 1) return null;
        if (fila > butacas.length || numeroButaca > butacas[0].length) return null;
        return butacas[fila - 1][numeroButaca - 1];
    }

    @Override
    public String toString() {
        return "Sala " + numeroSala + " - " + pelicula;
    }
}

