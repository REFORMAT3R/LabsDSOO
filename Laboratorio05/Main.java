package Laboratorio05;

public class Main {
    public static void main(String[] args) {
        // Inicializar el sistema
        SistemaCitas sistema = new SistemaCitas();
        RegistroPacientes registroPacientes = new RegistroPacientes();
        RegistroDoctores registroDoctores = new RegistroDoctores();
        Reporte reporte = new Reporte();
        
        System.out.println("=== SISTEMA DE GESTIÓN DE CITAS MÉDICAS ===\n");
        
        //Mostrar algunos doctores disponibles
        System.out.println("--- DOCTORES DISPONIBLES ---");
        Doctor doctor1 = registroDoctores.getDoctores().get("D001");
        Doctor doctor2 = registroDoctores.getDoctores().get("D002");
        Doctor doctor3 = registroDoctores.getDoctores().get("D003");
        System.out.println(doctor1);
        System.out.println();
        System.out.println(doctor2);
        System.out.println();
        System.out.println(doctor3);
        System.out.println();
        
        //Mostrar algunos pacientes registrados
        System.out.println("\n--- PACIENTES REGISTRADOS ---");
        Paciente paciente1 = registroPacientes.getRegistro().get("20251177");
        Paciente paciente2 = registroPacientes.getRegistro().get("20251234");
        Paciente paciente3 = registroPacientes.getRegistro().get("20251089");
        System.out.println(paciente1);
        System.out.println();
        System.out.println(paciente2);
        System.out.println();
        System.out.println(paciente3);
        System.out.println();
        
        //Agregar un nuevo paciente válido
        System.out.println("\n--- AGREGAR NUEVO PACIENTE ---");
        Paciente nuevoPaciente = new Paciente("20251500", "María Elena", "García López",
                                                25, "48956123");
        sistema.confirmarAgregarPaciente(registroPacientes, nuevoPaciente);
        sistema.mostrarResultadoAgregarPaciente(registroPacientes, nuevoPaciente);
        
        //Intentar agregar un paciente duplicado
        System.out.println("\n--- INTENTAR AGREGAR PACIENTE DUPLICADO ---");
        Paciente pacienteDuplicado = new Paciente("20251177", "Juan", "Pérez",
                                                20, "12345678");
        sistema.confirmarAgregarPaciente(registroPacientes, pacienteDuplicado);
        sistema.mostrarResultadoAgregarPaciente(registroPacientes, pacienteDuplicado);
        
        //Intentar agregar paciente con edad inválida
        System.out.println("\n--- INTENTAR AGREGAR PACIENTE CON EDAD INVÁLIDA ---");
        Paciente pacienteEdadInvalida = new Paciente("20251600", "Pedro", "Martínez",
                                                    -5, "98765432");
        sistema.confirmarAgregarPaciente(registroPacientes, pacienteEdadInvalida);
        sistema.mostrarResultadoAgregarPaciente(registroPacientes, pacienteEdadInvalida);
        
        //Crear y agregar citas para pacientes existentes
        System.out.println("\n--- CREAR CITAS ---");
        Cita cita1 = new Cita("C001", paciente1, doctor1, "15/11/2024", "10:00", "pendiente");
        Cita cita2 = new Cita("C002", paciente1, doctor2, "20/11/2024", "14:30", "atendida");

        sistema.imprimirValidacionCita(paciente1, cita1);
        sistema.agregarCita(paciente1, cita1);

        sistema.imprimirValidacionCita(paciente1, cita2);
        sistema.agregarCita(paciente1, cita2);
        
        //Intentar agregar cita duplicada con el mismo doctor
        System.out.println("\n--- INTENTAR AGREGAR CITA CON MISMO DOCTOR ---");
        Cita citaDuplicada = new Cita("C003", paciente1, doctor1, "25/11/2024",
                                    "11:00", "pendiente");
        sistema.imprimirValidacionCita(paciente1, citaDuplicada);
        sistema.agregarCita(paciente1, citaDuplicada);
        
        //Agregar más citas para otros pacientes
        Cita cita3 = new Cita("C004", paciente2, doctor1, "18/11/2024",
                                "09:00", "atendida");
        Cita cita4 = new Cita("C005", paciente2, doctor3, "22/11/2024",
                                "08:00", "cancelada");
        
        sistema.agregarCita(paciente2, cita3);
        sistema.agregarCita(paciente2, cita4);
        
        Cita cita5 = new Cita("C006", paciente3, doctor2, "19/11/2024",
                                "10:30", "pendiente");
        sistema.agregarCita(paciente3, cita5);
        
        //Cambiar estado de una cita
        System.out.println("\n--- CAMBIAR ESTADO DE CITA ---");
        sistema.validarCambioEstado(paciente1, cita1, "atendida");
        System.out.println("Estado de cita " + cita1.getCodigo() +
                            " cambiado a: " + cita1.getEstado());
        
        //Verificar existencia de cita
        System.out.println("\n--- VERIFICAR EXISTENCIA DE CITAS ---");
        sistema.imprimirExistenciaCita(paciente1, cita1);
        Cita citaInexistente = new Cita("C999", paciente1, doctor1, "30/11/2024",
                                        "15:00", "pendiente");
        sistema.imprimirExistenciaCita(paciente1, citaInexistente);
        
        //Mostrar reportes
        reporte.reporteCompleto(registroPacientes.getRegistro());
        
        System.out.println("\n");
        reporte.reportePorPaciente(paciente1);
        
        System.out.println("\n");
        reporte.reportePorPaciente(paciente2);
        
        System.out.println("\n");
        reporte.reportePorDoctor(registroPacientes.getRegistro(), "D001");
        
        //Listar citas por paciente
        System.out.println("\n--- CITAS POR PACIENTE ---");
        reporte.listarCitasPorPaciente(sistema, "20251177", registroPacientes.getRegistro());
        
        System.out.println("\n");
        reporte.listarCitasPorPaciente(sistema, "20251234", registroPacientes.getRegistro());
        
        System.out.println("\n=== FIN DEL SISTEMA ===");
    }
}