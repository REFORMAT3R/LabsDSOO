public abstract class Persona {
    /*Atributos*/
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;
    protected int edad;
    protected String dni;
    protected String direccion;

    /*Constructor*/
    public Persona(String nombre, String apellido, String telefono,
                    String correo, int edad, String dni, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.edad = edad;
        this.dni = dni;
        this.direccion = direccion;
    }

    /*Getters*/
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getTelefono() {return telefono;}
    public String getCorreo() {return correo;}
    public int getEdad() {return edad;}
    public String getDni() {return dni;}
    public String getDireccion() {return direccion;}

    
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);
        System.out.println("Edad: " + edad);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
    }
}