public class UsuarioEmpleado extends Usuario {
    private Empleado empleado;

    public UsuarioEmpleado(String nombreUsuario, String contrasenia, boolean estado, Empleado empleado) {
        super(nombreUsuario, contrasenia, estado);
        this.empleado = empleado;
    }

    public UsuarioEmpleado(String nombreUsuario, String contrasenia, boolean estado) {
        super(nombreUsuario, contrasenia, estado);
        this.empleado = null;
    }

    public Empleado getEmpleado() {return empleado;}
    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}

    @Override
    public void mostrarPermisos() {
        System.out.println("=== Permisos de Usuario Empleado ===");
        System.out.println("1. Registrar nuevos clientes");
        System.out.println("2.Crear cuentas bancarias");
        System.out.println("3. Procesar transacciones (depósitos, retiros, transferencias)");
        System.out.println("4. Consultar información de clientes");
        System.out.println("5. Consultar información de cuentas");
        System.out.println("6. Ver historial de transacciones");
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de usuario: Empleado");
        if (empleado != null) {
            System.out.println("Código de Empleado: " + empleado.getCodigoEmpleado());
        }
    }
}