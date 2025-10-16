package Laboratorio05;

import java.util.*; 
public class RegistroDoctores {
    private HashMap<String, Doctor> doctores=new HashMap<>();

    /* Constructor */
    public RegistroDoctores() {
        
        doctores.put("D001", new Doctor("D001", "Carlos", "Mendoza","Cardiología", "08:00-14:00"));
        doctores.put("D002", new Doctor("D002", "Lucía", "Fernández", "Pediatría", "09:00-15:00"));
        doctores.put("D003", new Doctor("D003", "Andrés", "Salazar", "Neurología", "07:00-13:00"));
        doctores.put("D004", new Doctor("D004", "Mariana", "Torres", "Dermatología", "10:00-16:00"));
        doctores.put("D005", new Doctor("D005", "Ricardo", "Guzmán", "Oftalmología", "12:00-18:00"));
        doctores.put("D006", new Doctor("D006", "Valeria", "Rojas", "Ginecología", "08:00-14:00"));
        doctores.put("D007", new Doctor("D007", "Jorge", "Paredes", "Traumatología", "14:00-20:00"));
        doctores.put("D008", new Doctor("D008", "Natalia", "Vega", "Endocrinología", "09:00-15:00"));
    }

    public HashMap<String, Doctor> getDoctores() {
        return doctores;
    }
    
    public void agregarDoctor(Doctor doctor){
        doctores.put(doctor.getCodigo(), doctor);
    }
}