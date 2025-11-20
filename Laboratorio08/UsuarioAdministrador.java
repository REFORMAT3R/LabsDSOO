public class UsuarioAdministrador extends Usuario {
    /* Atributo adicional */
    private Administrador administrador; // Referencia al objeto Administrador asociado

    /* Constructor */
    public UsuarioAdministrador(String nombreUsuario, String contrasenia, boolean estado, Administrador administrador) {
        super(nombreUsuario, contrasenia, estado);
        this.administrador = administrador;
    }

    /* Constructor alternativo sin Administrador (se puede asignar después) */
    public UsuarioAdministrador(String nombreUsuario, String contrasenia, boolean estado) {
        super(nombreUsuario, contrasenia, estado);
        this.administrador = null;
    }

    public Administrador getAdministrador() {return administrador;}
    public void setAdministrador(Administrador administrador) {this.administrador = administrador;}

    /* Implementación de mostrarPermisos */
    @Override
    public void mostrarPermisos() {
        System.out.println("=== Permisos de Usuario Administrador ===");
        System.out.println("\n--- Gestión de Usuarios ---");
        System.out.println("1. Registrar, modificar y eliminar clientes");
        System.out.println("2. Registrar, modificar y eliminar empleados");
        System.out.println("3. Activar/desactivar cuentas de usuario");
        System.out.println("4. Restablecer contraseñas");
        System.out.println("\n--- Gestión de Cuentas ---");
        System.out.println("1. Crear, modificar y cerrar cuentas bancarias");
        System.out.println("2. Modificar saldos (ajustes administrativos)");
        System.out.println("3. Ver todas las cuentas del sistema");
        System.out.println("\n--- Gestión de Transacciones ---");
        System.out.println("1. Procesar todo tipo de transacciones");
        System.out.println("2. Anular o revertir transacciones");
        System.out.println("3. Auditar historial completo");
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de usuario: Administrador");
        if (administrador != null) {
            System.out.println("Código de Empleado: " + administrador.getCodigoEmpleado());
        }
    }
}