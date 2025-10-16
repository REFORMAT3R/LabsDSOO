package Laboratorio05;

import java.util.ArrayList;
import java.util.HashMap;

public class SistemaCitas{
    private RegistroDoctores listaDoctores;

    public SistemaCitas() {
        listaDoctores = new RegistroDoctores();
    }

    //Métodos generales
    public boolean mismoDoctor(ArrayList<Cita> misCitas, String codigo){
        for(Cita cita : misCitas){
            if(cita.getDoctor().getCodigo().equals(codigo))
                return false;
        }
        return true;
    }

    public boolean verificarEdad(Paciente paciente){
        return paciente.getEdad() > 0; 
    }

    public boolean noHayDuplicado(HashMap<String, Paciente> registro, String DNI){
        int contador = 0;
        for(Paciente paciente : registro.values()){
            if(paciente.getDNI().equals(DNI))
                contador++;
        }
        return contador < 2;
    }
    //Faltan mas verificaciones para recien poder agendar una cita
}
