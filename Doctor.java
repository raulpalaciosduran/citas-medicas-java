//Clase Doctor que representa a un doctor en el sistema
public class Doctor {
    String id;
    String nombre;
    String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String toString() {
        return "Doctor: " + nombre + " | Especialidad: " + especialidad + " | ID: " + id;
    }
}
