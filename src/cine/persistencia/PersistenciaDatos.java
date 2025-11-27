package cine.persistencia;

import cine.modelo.Cine;
import java.io.*;

public class PersistenciaDatos {

    private static final String ARCHIVO = "cine.ser";

    public static void guardar(Cine cine) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            salida.writeObject(cine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cine cargar() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (Cine) entrada.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}


