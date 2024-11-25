package controlador;

import modelo.Coche;

import java.io.*;
import java.util.ArrayList;

public class Concesionario {

    private ArrayList<Coche> coches;

    public Concesionario() {
        coches = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos() {

        File file = new File("src/main/java/resources/coches.dat");

        if (file.exists()) {
            try {

                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                coches = (ArrayList<Coche>) inputStream.readObject();
                for (Coche coche : coches) {
                    System.out.println(coche);
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String anadirCoche(int id, String matricula, String marca, String modelo, String color) {

        for (Coche coche : coches) {
            if (coche.getMatricula().equals(matricula)) {
                return "Ya existe un coche con esa matrícula";
            }
            if (coche.getId() == id) {
                return "Ya existe un coche con ese id";
            }
        }

        Coche coche = new Coche(id, matricula, marca, modelo, color);
        coches.add(coche);
        return "Coche nuevo añadido";

    }

    public String eliminarCoche(int id) {

        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getId() == id) {
                coches.remove(i);
                return "Se ha eliminado el coche";
            }
        }
        return "No existe un coche con ese id";
    }

    public String buscarCoche(int id) {

        for (Coche coche : coches) {
            if (coche.getId() == id) {
                return coche.toString();
            }
        }
        return "No se ha encontrado el coche";
    }

    public void listarCoches() {

        System.out.println("Coches:");
        for (Coche coche : coches) {
            System.out.println(coche.toString() + "\n");
        }
    }

    public void guardarCoches() {

        File file = new File("src/main/java/resources/coches.dat");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(coches);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarCSV() {
        File file = new File("src/main/java/resources/coches.csv");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("Id;Matrícula;Marca;Modelo;Color");
            for (Coche c : coches) {
                writer.printf("%d;%s;%s;%s;%s%n", c.getId(), c.getMatricula(), c.getMarca(), c.getModelo(), c.getColor());
            }
            System.out.println("Coches exportados a coches.csv");
        } catch (IOException e) {
            System.out.println("Error al exportar los coches: " + e.getMessage());
        }
    }
}
