package Laboratorio05;

public class Paciente {
    private String codigo;
    private String nombres;
    private String apellidos;
    private int edad;
    private String dni;

    public Paciente (String codigo, String nombres, String apellidos, int edad, String dni) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dni = dni;
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

    public String getDni() {
        return dni;
    }

    
    



}