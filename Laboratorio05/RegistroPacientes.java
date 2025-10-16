package Laboratorio05;

import java.util.*;

public class RegistroPacientes {
    private HashMap<String, Paciente> pacientes;

    public RegistroPacientes() {
        this.pacientes = new HashMap<>();
        inicializarPacientes();
    }

    private void inicializarPacientes() {
        agregarPaciente("P001", "Juan", "Pérez García", 45, "12345678");
        agregarPaciente("P002", "María", "González López", 32, "87654321");
        agregarPaciente("P003", "Carlos", "Rodríguez Silva", 28, "11223344");
        agregarPaciente("P004", "Ana", "Martínez Torres", 55, "44332211");
        agregarPaciente("P005", "Luis", "Fernández Castro", 60, "55667788");
        agregarPaciente("P006", "Carmen", "Sánchez Ruiz", 38, "99887766");
        agregarPaciente("P007", "Roberto", "Díaz Morales", 42, "12121212");
        agregarPaciente("P008", "Patricia", "Vega Ramírez", 25, "34343434");
    }

    public boolean agregarPaciente(String codigo, String nombres, String apellidos, int edad, String dni) {
        if (pacientes.containsKey(codigo)) {
            System.out.println("Error: El código del paciente ya existe");
            return false;
        }

        if (edad <= 0) {
            System.out.println("Error: La edad debe ser mayor que 0");
            return false;
        }

        if (existeDni(dni)) {
            System.out.println("Error: El DNI ya está registrado");
            return false;
        }

        Paciente nuevoPaciente = new Paciente(codigo, nombres, apellidos, edad, dni);
        pacientes.put(codigo, nuevoPaciente);
        return true;
    }

    public boolean existePaciente(String codigoPaciente) {
        return pacientes.containsKey(codigoPaciente);
    }

    private boolean existeDni(String dni) {
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public Paciente obtenerPaciente(String codigo) {
        return pacientes.get(codigo);
    }

    private boolean hayPacientes() {
        return !pacientes.isEmpty();
    }

    private void imprimirListadoPacientes() {
        System.out.println("\n=== PACIENTES REGISTRADOS ===");
        for (Paciente paciente : pacientes.values()) {
            System.out.println(paciente);
        }
    }

    public void listarPacientes() {
        if (!hayPacientes()) {
            System.out.println("No hay pacientes registrados");
            return;
        }
        imprimirListadoPacientes();
    }
}