package Laboratorio05;

import java.util.*;

public class Reporte{
    
    public int totalCitasAtendidas(HashMap<String, Paciente> registro){
        int contador = 0;
        for (Paciente paciente : registro.values()){
            for (Cita cita : paciente.getMiscitas()){
                if(cita.getEstado().equals("atendida"))
                    contador++;
            }
        }
        return contador;
    }

    public int totalCitasCanceladas(HashMap<String, Paciente> registro) {
        int contador = 0;
        for (Paciente paciente : registro.values()) {
            for (Cita cita : paciente.getMiscitas()) {
                if (cita.getEstado().equals("cancelada"))
                    contador++;
            }
        }
        return contador;
    }

    public int totalCitasPendientes(HashMap<String, Paciente> registro) {
        int contador = 0;
        for (Paciente paciente : registro.values()) {
            for (Cita cita : paciente.getMiscitas()) {
                if (cita.getEstado().equals("pendiente"))
                    contador++;
            }
        }
        return contador;
    }

    public void reporteCompleto(HashMap<String, Paciente> registro) {
        int atendidas = totalCitasAtendidas(registro);
        int canceladas = totalCitasCanceladas(registro);
        int pendientes = totalCitasPendientes(registro);
        int total = atendidas + canceladas + pendientes;

        System.out.println("\n========== REPORTE DE CITAS ==========");
        System.out.println("Total de citas registradas: " + total);
        System.out.println("Citas atendidas: " + atendidas);
        System.out.println("Citas canceladas: " + canceladas);
        System.out.println("Citas pendientes: " + pendientes);
    }

    public void reportePorPaciente(Paciente paciente) {
        ArrayList<Cita> citas = paciente.getMiscitas();
        
        if (citas.isEmpty()) {
            System.out.println("El paciente " + paciente.getCodigo() + 
                                " no tiene citas registradas.");
            return;
        }

        int atendidas = 0, canceladas = 0, pendientes = 0;

        for (Cita cita : citas) {
            switch (cita.getEstado()) {
                case "atendida": atendidas++; break;
                case "cancelada": canceladas++; break;
                case "pendiente": pendientes++; break;
            }
        }

        System.out.println("\n========== REPORTE DE " + paciente.nombreCompleto() 
                            + " ==========");
        System.out.println("Total de citas: " + citas.size());
        System.out.println("Citas atendidas: " + atendidas);
        System.out.println("Citas canceladas: " + canceladas);
        System.out.println("Citas pendientes: " + pendientes);
    }

    public void reportePorDoctor(HashMap<String, Paciente> registro, String codigoDoctor) {
        int atendidas = 0, canceladas = 0, pendientes = 0;
        String nombreDoctor = "";

        for (Paciente paciente : registro.values()) {
            for (Cita cita : paciente.getMiscitas()) {
                if (cita.getDoctor().getCodigo().equals(codigoDoctor)) {
                    nombreDoctor = cita.getDoctor().getApellido() + " "
                                    + cita.getDoctor().getNombre();
                    switch (cita.getEstado()) {
                        case "atendida": atendidas++; 
                        break;
                        case "cancelada": canceladas++; 
                        break;
                        case "pendiente": pendientes++; 
                        break;
                    }
                }
            }
        }

        int total = atendidas + canceladas + pendientes;

        System.out.println("\n========== REPORTE DEL DR. " + nombreDoctor + " ==========");
        System.out.println("Total de citas: " + total);
        System.out.println("Citas atendidas: " + atendidas);
        System.out.println("Citas canceladas: " + canceladas);
        System.out.println("Citas pendientes: " + pendientes);
    }

    public void mostrarTodasLasCitas(ArrayList<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No hay citas programadas.");
            return;
        }

        System.out.println("\n=== CITAS PROGRAMADAS - GENERAL ===");

        for (Cita cita : citas) {
            System.out.println("Código: " + cita.getCodigo() +
                                "\nPaciente: " + cita.getPaciente().nombreCompleto() +
                                "\nDoctor: " + cita.getDoctor().getApellido() + " "
                                            + cita.getDoctor().getNombre() + 
                                "\nEspecialidad: " + cita.getDoctor().getEspecialidad() + 
                                "\nFecha: " + cita.getFecha() + " - Hora" + cita.getHora() +
                                "\nEstado: " + cita.getEstado());
        }
    }

    public void listarCitasPorDoctor(ArrayList<Cita> citas, String codigoDoctor) {
        System.out.println("\n=== CITAS DEL DOCTOR " + codigoDoctor + " ===");
        
        int contador = 0;
        for (Cita cita : citas) {
            if (cita.getDoctor().getCodigo().equals(codigoDoctor)) {
                if (contador == 0) {
                    System.out.println("Dr. " + cita.getDoctor().getApellido() + " "
                                        + cita.getDoctor().getNombre() +
                                        "\nEspecialidad: " + cita.getDoctor().getEspecialidad()
                                        + "\n");
                    
                }
                System.out.println("Código: " + cita.getCodigo() +
                                    "\nPaciente: " + cita.getPaciente().nombreCompleto() +
                                    "\nFecha: " + cita.getFecha() + " - Hora: " + cita.getHora() +
                                    "\nEstado: " + cita.getEstado());
                contador++;
            }
        }
        
        if (contador == 0) {
            System.out.println("No hay citas para el doctor " + codigoDoctor + ".");
        }
    }

    public void listarCitasPorPaciente(SistemaCitas sistema, String codigoPaciente,
                                        HashMap<String, Paciente> registroPacientes) {

        if (!sistema.existePaciente(registroPacientes,codigoPaciente)) {
            System.out.println("El paciente no existe.");
            return;
        }
        
        Paciente paciente = registroPacientes.get(codigoPaciente);
        ArrayList<Cita> citas = paciente.getMiscitas();

        if (citas.isEmpty()) {
            System.out.println("El paciente " + paciente.getCodigo() +
                                " no tiene citas registradas.");
            return;
        }

        System.out.println("\n=== CITAS DEL PACIENTE " + codigoPaciente + " ===");
        
        for (Cita cita : citas) {
            System.out.println("Código: " + cita.getCodigo() +
                                "\nDoctor: " + cita.getDoctor().getApellido() + " "
                                    + cita.getDoctor().getNombre() + 
                                "\nEspecialidad: " + cita.getDoctor().getEspecialidad() + 
                                "\nFecha: " + cita.getFecha() + " - Hora: " + cita.getHora() +
                                "\nEstado: " + cita.getEstado());
        }
    }
}