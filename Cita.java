//Clase Cita que representa una cita médica
public class Cita {
    String fecha;
    String hora;
    Doctor doctor;
    Paciente paciente;

    public Cita(String fecha, String hora, Doctor doctor, Paciente paciente) {
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String toString() {
        return "Cita: " + fecha + " " + hora + "\n" + doctor + "\n" + paciente;
    }
}


