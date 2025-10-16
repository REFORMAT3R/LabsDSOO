package Laboratorio05;

import java.util.ArrayList;

public class Paciente {
    private String codigo;
    private String nombres;
    private String apellidos;
    private int edad;
    private String DNI;
    private ArrayList<Cita> misCitas=new ArrayList<>();

    public Paciente (String codigo, String nombres, String apellidos, int edad, String DNI) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.DNI = DNI;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getDNI() {
        return DNI;
    }

    public ArrayList<Cita> getMiscitas() {
        return misCitas;
    }

    public void agregarCita(Cita micita){
        misCitas.add(micita);
    }

    public String nombreCompleto(){
        return apellidos + " " + nombres;
    }

    @Override
    public String toString(){
        return "Paciente: " + apellidos + " " + nombres + "\nEdad: " + edad + "\nDNI: " + DNI +
        "\nCódigo: " + codigo;
    }
}