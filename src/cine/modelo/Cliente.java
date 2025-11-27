package cine.modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String email;
    private String contrasena;

    public Cliente(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getContrasena() { return contrasena; }

    @Override
    public String toString() {
        return nombre + " <" + email + ">";
    }
}

