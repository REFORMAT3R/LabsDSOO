package Laboratorio05;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private String codigo;
    private Paciente paciente;
    private Doctor doctor;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public Cita(String codigo, Paciente paciente, Doctor doctor, LocalDate fecha, LocalTime hora, String estado) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "pendiente";
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
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}