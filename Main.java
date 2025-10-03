import java.util.*;
import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Doctor> doctores = cargarDoctores();
        ArrayList<Paciente> pacientes = cargarPacientes();
        ArrayList<Cita> citas = cargarCitas(doctores, pacientes);


        int opcion;
        do {
            System.out.println("\n--- Sistema de Citas Médicas ---");
            System.out.println("1. Alta de doctor");
            System.out.println("2. Alta de paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Mostrar citas");
            System.out.println("5. Mostrar doctores");
            System.out.println("6. Mostrar pacientes");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
    
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); // limpiar buffer
                } else {
                    System.out.println("Entrada inválida. Escribe un número del menú.");
                    sc.nextLine(); // limpiar entrada incorrecta
                    opcion = -1; // fuerza repetir el menú
}
            switch (opcion) {
                case 1:
                    System.out.print("ID del doctor: ");
                    String idDoc = sc.nextLine();
                    System.out.print("Nombre del doctor: ");
                    String nomDoc = sc.nextLine();
                    System.out.print("Especialidad: ");
                    String esp = sc.nextLine();
                    doctores.add(new Doctor(idDoc, nomDoc, esp));
                    break;
                case 2:
                    System.out.print("ID del paciente: ");
                    String idPac = sc.nextLine();
                    System.out.print("Nombre del paciente: ");
                    String nomPac = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    pacientes.add(new Paciente(idPac, nomPac, edad));
                    break;
                case 3:
                    if (doctores.isEmpty() || pacientes.isEmpty()) {
                        System.out.println("Primero debes registrar al menos un doctor y un paciente.");
                        break;
                    }
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();
                    System.out.print("Hora (hh:mm): ");
                    String hora = sc.nextLine();

                    System.out.println("Selecciona doctor (0 a " + (doctores.size() - 1) + "):");
                    for (int i = 0; i < doctores.size(); i++) {
                        System.out.println(i + ". " + doctores.get(i));
                    }
                    int docIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Selecciona paciente (0 a " + (pacientes.size() - 1) + "):");
                    for (int i = 0; i < pacientes.size(); i++) {
                        System.out.println(i + ". " + pacientes.get(i));
                    }
                    int pacIndex = sc.nextInt();
                    sc.nextLine();

                    citas.add(new Cita(fecha, hora, doctores.get(docIndex), pacientes.get(pacIndex)));
                    break;
                case 4:
                    if (citas.isEmpty()) {
                        System.out.println("No hay citas registradas.");
                    } else {
                        for (Cita c : citas) {
                            System.out.println("\n" + c);
                        }
                    }
                    break;
                case 5:
                    if (doctores.isEmpty()) {
                        System.out.println("No hay doctores registrados.");
                    } else {
                        System.out.println("--- Doctores registrados ---");
                        for (Doctor d : doctores) {
                            System.out.println(d);
                        }
                    }
                    break;

                case 6:
                    if (pacientes.isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        System.out.println("--- Pacientes registrados ---");
                        for (Paciente p : pacientes) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 7:
                    guardarDoctores(doctores);
                    guardarPacientes(pacientes);
                    guardarCitas(citas);
                    System.out.println("Datos guardados. Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                        }
                    } while (opcion != 7);
     }
     // MÉTODO PARA CARGAR DOCTORES DESDE CSV
     public static ArrayList<Doctor> cargarDoctores() {
    ArrayList<Doctor> lista = new ArrayList<>();
    try (Scanner sc = new Scanner(new File("doctores.csv"))) {
        while (sc.hasNextLine()) {
            String[] partes = sc.nextLine().split(",");
            if (partes.length == 3) {
                lista.add(new Doctor(partes[0], partes[1], partes[2]));
            }
        }
    } catch (Exception e) {
        System.out.println("No se pudo cargar doctores.");
    }
    return lista;
}

//medotodo para cargar pacientes desde csv
public static ArrayList<Paciente> cargarPacientes() {
    ArrayList<Paciente> lista = new ArrayList<>();
    try (Scanner sc = new Scanner(new File("pacientes.csv"))) {
        while (sc.hasNextLine()) {
            String[] partes = sc.nextLine().split(",");
            if (partes.length == 3) {
                String id = partes[0];
                String nombre = partes[1];
                int edad = Integer.parseInt(partes[2]);
                lista.add(new Paciente(id, nombre, edad));
            }
        }
    } catch (Exception e) {
        System.out.println("No se pudo cargar pacientes.");
    }
    return lista;
}
//metodo para cargar citas desde csv
public static ArrayList<Cita> cargarCitas(ArrayList<Doctor> doctores, ArrayList<Paciente> pacientes) {
    ArrayList<Cita> lista = new ArrayList<>();
    try (Scanner sc = new Scanner(new File("citas.csv"))) {
        while (sc.hasNextLine()) {
            String[] partes = sc.nextLine().split(",");
            if (partes.length == 4) {
                String fecha = partes[0];
                String hora = partes[1];
                String idDoc = partes[2];
                String idPac = partes[3];

                Doctor doctor = null;
                Paciente paciente = null;

                for (Doctor d : doctores) {
                    if (d.id.equals(idDoc)) {
                        doctor = d;
                        break;
                    }
                }
                for (Paciente p : pacientes) {
                    if (p.id.equals(idPac)) {
                        paciente = p;
                        break;
                    }
                }

                if (doctor != null && paciente != null) {
                    lista.add(new Cita(fecha, hora, doctor, paciente));
                }
            }
        }
    } catch (Exception e) {
        System.out.println("No se pudo cargar citas.");
    }
    return lista;
}


    // MÉTODO PARA GUARDAR DOCTORES
    public static void guardarDoctores(ArrayList<Doctor> doctores) {
        try (PrintWriter pw = new PrintWriter("doctores.csv")) {
            for (Doctor d : doctores) {
                pw.println(d.id + "," + d.nombre + "," + d.especialidad);
            }
        } catch (Exception e) {
            System.out.println("Error al guardar doctores.");
        }
    }
    // MÉTODO PARA GUARDAR PACIENTES
    public static void guardarPacientes(ArrayList<Paciente> pacientes) {
        try (PrintWriter pw = new PrintWriter("pacientes.csv")) {
            for (Paciente p : pacientes) {
                pw.println(p.id + "," + p.nombre + "," + p.edad);
            }
        } catch (Exception e) {
            System.out.println("Error al guardar pacientes.");
        }
    }
    // MÉTODO PARA GUARDAR CITAS
    public static void guardarCitas(ArrayList<Cita> citas) {
        try (PrintWriter pw = new PrintWriter("citas.csv")) {
            for (Cita c : citas) {
                pw.println(c.fecha + "," + c.hora + "," + c.doctor.id + "," + c.paciente.id);
            }
        } catch (Exception e) {
            System.out.println("Error al guardar citas.");
        }
    }
}
