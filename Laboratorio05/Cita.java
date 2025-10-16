package Laboratorio05;

public class Cita {
    private String codigo;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;
    private String hora;
    private String estado;

    public Cita(String codigo, Paciente paciente, Doctor doctor, String fecha, 
                String hora, String estado) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}