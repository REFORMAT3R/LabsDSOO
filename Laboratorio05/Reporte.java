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
}