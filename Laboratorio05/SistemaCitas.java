package Laboratorio05;

import java.util.*;

public class SistemaCitas{
    private RegistroDoctores listaDoctores;
    

    public SistemaCitas() {
        listaDoctores = new RegistroDoctores();
    }

    //Métodos de validación 
    public boolean existePaciente(HashMap<String, Paciente> registro, String codigoPaciente) {
        for (Paciente paciente : registro.values()){
            if(paciente.getCodigo().equals(codigoPaciente))
                return true;
        }
        return false;
    }

    public boolean verificarEdad(Paciente paciente){
        return paciente.getEdad() > 0; 
    }

    public boolean noHayDuplicadoDNI(HashMap<String, Paciente> registro, String DNI){
        int contador = 0;
        for(Paciente paciente : registro.values()){
            if(paciente.getDNI().equals(DNI))
                contador++;
        }
        return contador < 2;
    }

    public boolean mismoDoctor(ArrayList<Cita> misCitas, String codigo){
        for(Cita cita : misCitas){
            if(cita.getDoctor().getCodigo().equals(codigo))
                return false;
        }
        return true;
    }

    //Todas las validaciones se concentran en un mismo método menos el método mismoDoctor
    public boolean confirmarTodasValidaciones(RegistroPacientes registro, Paciente paciente){
    return !existePaciente(registro.getRegistro(), paciente.getCodigo()) &&
        verificarEdad(paciente) &&
        noHayDuplicadoDNI(registro.getRegistro(), paciente.getDNI());
    }

    //Si todas las validaciones son verdaderas se confirma que se peuda agregar al paciente
    public void confirmarAgregarPaciente(RegistroPacientes registro, Paciente paciente){
        if(confirmarTodasValidaciones(registro, paciente))
            registro.agregarPaciente(paciente);
    }

    //Imprime el estado de las validaciones y si se pudo agregar al paciente
    public void mostrarResultadoAgregarPaciente(RegistroPacientes registro, Paciente paciente) {
        if (existePaciente(registro.getRegistro(), paciente.getCodigo())) {
            System.out.println("El paciente con código " + paciente.getCodigo() + " ya existe en el registro.");
            return;
        }

        if (!verificarEdad(paciente)) {
            System.out.println("Edad inválida. Debe ser mayor que 0.");
            return;
        }

        if (!noHayDuplicadoDNI(registro.getRegistro(), paciente.getDNI())) {
            System.out.println("Hay más de un paciente con el DNI " + paciente.getDNI() + ".");
            return;
        }
        System.out.println("Paciente agregado correctamente: " + paciente.getNombres());
    }

    //Usando formatos especiales del String validamos el fromato de fecha y hora 
    public boolean validarFechaHora(String fechaTexto, String horaTexto) {
        boolean formatoFechaValido = fechaTexto.matches("\\d{2}/\\d{2}/\\d{4}");
        boolean formatoHoraValido = horaTexto.matches("([01]\\d|2[0-3]):[0-5]\\d");
        return formatoFechaValido && formatoHoraValido;
    }

    //Se usan las validaciones anteriores junto fecha y hora y mismoDoctor(Verifica si tiene cita con ese doctor)
    public boolean validarCita(RegistroPacientes registro, Paciente paciente, Cita cita){
        if(confirmarTodasValidaciones(registro, paciente) && validarFechaHora(cita.getFecha(), cita.getHora())
        && mismoDoctor(paciente.getMiscitas(), cita.getDoctor().getCodigo()))
            return true;
        return false;
    }

    public void agregarCita(RegistroPacientes registro, Paciente paciente, Cita cita){
        if(validarCita(registro, paciente, cita))
            paciente.getMiscitas().add(cita);
    }

    //Confirma si la cita existe
    public boolean existenciaCita(Paciente paciente, Cita cita){
        for(Cita c : paciente.getMiscitas()){
            if(c.getCodigo().equals(cita.getCodigo()))
                return true;
        }
        return false;
    }

    //Si existe se peude cambiar el estado
    public void validarCambioEstado(Paciente paciente, Cita cita, String estado){
        if(existenciaCita(paciente, cita)){
            cita.setEstado(estado);
        }
    }

    //Se imprimen las validacines de agregar cita y si existe la cita
    public void imprimirValidacionCita(RegistroPacientes registro, Paciente paciente, Cita cita) {
        boolean esValida = validarCita(registro, paciente, cita);
        if (esValida) {
            System.out.println("La cita es válida y puede agregarse.");
        } else {
            System.out.println("La cita no cumple con los criterios de validación.");
        }
    }

    public void imprimirExistenciaCita(Paciente paciente, Cita cita) {
        boolean existe = existenciaCita(paciente, cita);
        if (existe) {
            System.out.println("La cita con código " + cita.getCodigo() + " ya existe para este paciente.");
        } else {
            System.out.println("No existe una cita con el código " + cita.getCodigo() + ". Puede registrarse.");
        }
    }
}