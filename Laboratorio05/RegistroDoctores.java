package Laboratorio05;

import java.util.*; 
public class RegistroDoctores {
    private HashMap<String, Doctor> doctores;

    /* Constructor */
    public RegistroDoctores() {
        this.doctores = new HashMap<>();
        inicializarDoctores();
    }

    public void inicializarDoctores() {
        agregarDoctor("D001", "Dr. Carlos Mendoza", "Cardiología", "08:00-14:00");
        agregarDoctor("D002", "Dra. María González", "Pediatría", "09:00-15:00");
        agregarDoctor("D003", "Dr. Juan Pérez", "Traumatología", "08:00-13:00");
        agregarDoctor("D004", "Dra. Ana Rodríguez", "Ginecología", "10:00-16:00");
        agregarDoctor("D005", "Dr. Luis Torres", "Medicina General", "07:00-14:00");
        agregarDoctor("D006", "Dra. Carmen Silva", "Dermatología", "08:30-14:30");
        agregarDoctor("D007", "Dr. Roberto Campos", "Neurología", "09:00-15:00");
        agregarDoctor("D008", "Dra. Patricia Vega", "Oftalmología", "08:00-12:00");
    }

    public boolean agregarDoctor(String codigo, String nombre, String especialidad, String horario) {
        if (doctores.containsKey(codigo)) {
            System.out.println("Error: El código del doctor ya existe");
            return false;
        }
        Doctor nuevoDoctor = new Doctor(codigo, nombre, especialidad, horario);
        doctores.put(codigo, nuevoDoctor);
        return true;
    }

    public boolean existeDoctor(String codigoDoctor) {
        return doctores.containsKey(codigoDoctor);
    }

    public Doctor obtenerDoctor(String codigo) {
        return doctores.get(codigo);
    }

    private boolean hayDoctores() {
        return !doctores.isEmpty();
    }

    private void imprimirListadoDoctores() {
        System.out.println("\n=== DOCTORES REGISTRADOS ===");
        for (Doctor doctor : doctores.values()) {
            System.out.println(doctor);
        }
    }

    public void listarDoctores() {
        if (!hayDoctores()) {
            System.out.println("No hay doctores registrados");
            return;
        }
        imprimirListadoDoctores();
    }
}
