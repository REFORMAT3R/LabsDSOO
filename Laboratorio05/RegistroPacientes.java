package Laboratorio05;

import java.util.*;
class RegistroPacientes {
    private HashMap <String, Paciente> registro = new HashMap<>();
    
    public RegistroPacientes() {
        registro.put("20251177", new Paciente("20251177", "Guevara Martinez", "Osmeyer Teodoro", 18, "45648781"));
        registro.put("20251234", new Paciente("20251234", "Soncco Flores", "Ana Sofía", 22, "47892341"));
        registro.put("20251089", new Paciente("20251089", "Quispe Macedo", "Josue Raúl", 19, "46123789"));
        registro.put("20251367", new Paciente("20251367", "Torres Jiménez", "Valeria Nicole", 18, "45789234"));
        registro.put("20251421", new Paciente("20251421", "Chupa Vargas", "Fernando José", 21, "48345678"));
    }

    public HashMap<String, Paciente> getRegistro() {
        return registro;
    }

    //Se usa el código del paciente como llave y el objeto paciente como contenido
    public void agregarPaciente(Paciente paciente){
        registro.put(paciente.getCodigo(), paciente);
    }
}