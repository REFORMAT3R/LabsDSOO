package Laboratorio05;

public class Doctor{
    private String codigo;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String horarioAtencion;
    public Doctor(String codigo, String nombre, String apellido, String especialidad, String horarioAtencion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.horarioAtencion = horarioAtencion;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public String getHorarioAtencion() {
        return horarioAtencion;
    }
} 