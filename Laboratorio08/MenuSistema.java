import java.util.Scanner;

public class MenuSistema {
    private Banco banco;
    private GestorUsuarios gestorUsuarios;
    private Scanner sc;

    public MenuSistema(Banco banco, GestorUsuarios gestorUsuarios) {
        this.banco = banco;
        this.gestorUsuarios = gestorUsuarios;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            
            String opcion = sc.nextLine();
            if (opcion.equals("1")) {
                procesarLogin();
            } else if (opcion.equals("2")) {
                salir = true;
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private void procesarLogin() {
        System.out.println("\n--- LOGIN ---");
        String user = GestorEntradas.solicitarNombreUsuario();
        String pass = GestorEntradas.solicitarContrasena();
        
        Usuario u = gestorUsuarios.login(user, pass);
        
        if (u != null) {
            if (u instanceof UsuarioAdministrador) {
                mostrarMenuAdmin((UsuarioAdministrador) u);
            } else if (u instanceof UsuarioEmpleado) {
                mostrarMenuEmpleado((UsuarioEmpleado) u);
            } else if (u instanceof UsuarioCliente) {
                mostrarMenuCliente((UsuarioCliente) u);
            }
        }
    }

    // ==========================================
    //           MENÚ DE ADMINISTRADOR
    // ==========================================
    private void mostrarMenuAdmin(UsuarioAdministrador admin) {
        boolean atras = false;
        while (!atras) {
            System.out.println("\n=== PANEL ADMINISTRADOR (" + admin.getNombreUsuario() + ") ===");
            
            System.out.println("1. Registrar Empleado y Crear Usuario");
            System.out.println("2. Ver Usuarios Registrados");
            System.out.println("3. Modificar Usuario (Estado/Contraseña)"); 
            System.out.println("4. Eliminar Usuario");                     
            System.out.println("5. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            
            String op = sc.nextLine();
            switch(op) {
                case "1": 
                    registrarEmpleadoYUsuario();
                    break;
                case "2": 
                    gestorUsuarios.mostrarUsuarios(); 
                    break;
                case "3": 
                    modificarUsuario(admin); 
                    break;
                case "4": 
                    eliminarUsuario(admin); 
                    break;
                case "5": 
                    atras = true; 
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    // --- Lógica para Registrar Empleado ---
    private void registrarEmpleadoYUsuario() {
        Empleado nuevoEmp = GestorEntradas.solicitarDatosEmpleado(banco);
        banco.registrarEmpleado(nuevoEmp);
        System.out.println(">> Crear credenciales de acceso:");
        gestorUsuarios.crearUsuarioEmpleado(
            GestorEntradas.solicitarNombreUsuario(), 
            GestorEntradas.solicitarContrasena(), 
            nuevoEmp
        );
    }

    // --- Lógica para Modificar Usuario (Nueva) ---
    private void modificarUsuario(UsuarioAdministrador admin) {
        System.out.println("\n--- MODIFICAR USUARIO ---");
        String nombreUser = GestorEntradas.solicitarNombreUsuario();
        Usuario usuarioObjetivo = gestorUsuarios.buscarUsuario(nombreUser);

        if (usuarioObjetivo == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        System.out.println("Usuario encontrado: " + usuarioObjetivo.getNombreUsuario() + 
                           " | Estado actual: " + (usuarioObjetivo.getEstado() ? "Activo" : "Inactivo"));
        System.out.println("1. Cambiar Estado (Activar/Desactivar)");
        System.out.println("2. Restablecer Contraseña");
        System.out.println("3. Cancelar");
        System.out.print("Opción: ");
        
        String subOp = sc.nextLine();
        switch (subOp) {
            case "1":
                // Invertimos el estado actual
                boolean nuevoEstado = !usuarioObjetivo.getEstado();
                gestorUsuarios.cambiarEstadoUsuario(nombreUser, nuevoEstado);
                break;
            case "2":
                System.out.println("Ingrese la NUEVA contraseña para el usuario " + nombreUser + ":");
                String nuevaPass = GestorEntradas.solicitarContrasena();
                
                usuarioObjetivo.setContrasenia(nuevaPass); 
                System.out.println("Contraseña restablecida exitosamente.");
                break;
            case "3":
                System.out.println("Operación cancelada.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    // --- Eliminar Usuario---
    private void eliminarUsuario(UsuarioAdministrador admin) {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        String userEliminar = GestorEntradas.solicitarNombreUsuario();
        
        // Evitar que el admin se elimine a sí mismo
        if (userEliminar.equalsIgnoreCase(admin.getNombreUsuario())) {
            System.out.println("Error: No puedes eliminar tu propia cuenta de administrador.");
            return;
        }

        System.out.println("¿Está seguro de eliminar el acceso de '" + userEliminar + "'? (S/N): ");
        String confirm = sc.nextLine();
        
        if (confirm.equalsIgnoreCase("S")) {
            gestorUsuarios.eliminarUsuario(userEliminar);
            // Nota: Esto elimina el login, pero los datos del empleado/cliente en el Banco permanecen
            
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // ==========================================
    //           MENÚ DE EMPLEADO
    // ==========================================
    private void mostrarMenuEmpleado(UsuarioEmpleado emp) {
        boolean atras = false;
        while (!atras) {
            System.out.println("\n=== PANEL EMPLEADO (" + emp.getEmpleado().getNombre() + ") ===");
            System.out.println("1. Registrar Cliente y Usuario");
            System.out.println("2. Crear Cuenta Bancaria");
            System.out.println("3. Realizar Depósito");
            System.out.println("4. Realizar Retiro");
            System.out.println("5. Realizar Transferencia");
            System.out.println("6. Cerrar Sesión");
            System.out.print("Opción: ");
            
            String op = sc.nextLine();
            // Generador simple de ID para pruebas
            String idTxn = "TXN" + (int)(Math.random() * 10000); 

            switch(op) {
                case "1":
                    Cliente cli = GestorEntradas.solicitarDatosCliente(banco);
                    banco.registrarCliente(cli);
                    System.out.println("¿Crear usuario web para cliente? (S/N)");
                    if(sc.nextLine().equalsIgnoreCase("S")) {
                        gestorUsuarios.crearUsuarioCliente(
                            GestorEntradas.solicitarNombreUsuario(),
                            GestorEntradas.solicitarContrasena(), cli
                        );
                    }
                    break;
                case "2":
                    String codCli = GestorEntradas.solicitarCodigoCliente();
                    Cliente dueño = banco.buscarCliente(codCli);
                    if(dueño != null) banco.crearCuenta(GestorEntradas.solicitarCodigoCuenta(), dueño);
                    else System.out.println("Cliente no existe.");
                    break;
                case "3":
                    banco.depositar(GestorEntradas.solicitarCodigoCliente(), 
                                  GestorEntradas.solicitarCodigoCuenta(), 
                                  GestorEntradas.solicitarMonto("depositar"), 
                                  emp.getEmpleado(), idTxn);
                    break;
                case "4":
                    banco.retirar(GestorEntradas.solicitarCodigoCliente(), 
                                  GestorEntradas.solicitarCodigoCuenta(), 
                                  GestorEntradas.solicitarMonto("retirar"), 
                                  emp.getEmpleado(), idTxn);
                    break;
                case "5":
                    banco.transferir(GestorEntradas.solicitarCodigoCliente(),
                                   GestorEntradas.solicitarCodigoCuenta(),
                                   GestorEntradas.solicitarCodigoCuenta(),
                                   GestorEntradas.solicitarMonto("transferir"),
                                   emp.getEmpleado(), idTxn);
                    break;
                case "6": atras = true; break;
                default: System.out.println("Opción incorrecta.");
            }
        }
    }

    // ==========================================
    //           MENÚ DE CLIENTE
    // ==========================================
    private void mostrarMenuCliente(UsuarioCliente cli) {
        boolean atras = false;
        while (!atras) {
            System.out.println("\n=== PANEL CLIENTE (" + cli.getCliente().getNombre() + ") ===");
            System.out.println("1. Ver mis cuentas y saldos");
            System.out.println("2. Ver historial de transacciones");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Opción: ");
            
            String op = sc.nextLine();
            switch(op) {
                case "1": 
                    banco.mostrarCuentasDeCliente(cli.getCliente().getCodigoCliente()); 
                    break;
                case "2": 
                    for(Cuenta c : banco.buscarCuentasDeCliente(cli.getCliente().getCodigoCliente())) {
                        c.mostrarHistorial();
                    }
                    break;
                case "3": atras = true; break;
                default: System.out.println("Opción incorrecta.");
            }
        }
    }
}