public class Empleado extends Persona {
    /*Atributos*/
    private String codigoEmpleado;

    /*Constructor*/
    public Empleado(String nombre, String apellido, String telefono,String correo,
                    int edad, String dni, String direccion, String codigoEmpleado) {
        super(nombre, apellido, telefono, correo, edad, dni, direccion);
        this.codigoEmpleado = codigoEmpleado;
    }

    /*Getter*/
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Código de Empleado: " + codigoEmpleado);
    }
}