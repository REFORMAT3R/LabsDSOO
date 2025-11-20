public class UsuarioCliente extends Usuario {
    private Cliente cliente;

    public UsuarioCliente(String nombreUsuario, String contrasenia, boolean estado, Cliente cliente) {
        super(nombreUsuario, contrasenia, estado);
        this.cliente = cliente;
    }

    public UsuarioCliente(String nombreUsuario, String contrasenia, boolean estado) {
        super(nombreUsuario, contrasenia, estado);
        this.cliente = null;
    }

    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    @Override
    public void mostrarPermisos() {
        System.out.println("=== Permisos de Usuario Cliente ===");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Ver historial de transacciones");
        System.out.println("3. Realizar depósitos");
        System.out.println("4. Realizar retiros");
        System.out.println("5. Realizar transferencias");
        System.out.println("6. Modificar datos personales");
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de usuario: Cliente");
        if (cliente != null) {
            System.out.println("Código de Cliente: " + cliente.getCodigoCliente());
        }
    }

    
}