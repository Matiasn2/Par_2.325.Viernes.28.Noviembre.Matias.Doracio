package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cine implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Sala> salas = new ArrayList<>();
    private List<Entrada> entradas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public List<Sala> getSalas() { return salas; }
    public List<Entrada> getEntradas() { return entradas; }
    public List<Cliente> getClientes() { return clientes; }

    public void agregarSala(Sala sala) { salas.add(sala); }
    public void agregarEntrada(Entrada entrada) { entradas.add(entrada); }
    public void agregarCliente(Cliente cliente) { clientes.add(cliente); }

    public Sala buscarSalaPorNumero(int numero) {
        for (Sala sala : salas)
            if (sala.getNumeroSala() == numero)
                return sala;
        return null;
    }

    public Cliente login(String email, String contrasena) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)
                    && cliente.getContrasena().equals(contrasena)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente registrar(String nombre, String email, String contrasena) {
        for (Cliente cliente : clientes)
            if (cliente.getEmail().equalsIgnoreCase(email))
                return null;

        Cliente nuevo = new Cliente(nombre, email, contrasena);
        clientes.add(nuevo);
        return nuevo;
    }
}

