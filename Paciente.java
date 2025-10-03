//Clase Paciente que representa a un paciente en el sistema
public class Paciente {
    String id;
    String nombre;
    int edad;

    public Paciente(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String toString() {
        return "Paciente: " + nombre + " | Edad: " + edad + " | ID: " + id;
    }
}

