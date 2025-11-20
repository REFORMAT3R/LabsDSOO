public class Inicializador {
    
    public static void cargarDatosIniciales(Banco banco, GestorUsuarios gestorUsuarios) {
        // 1. Crear un Administrador físico
        Administrador admin = new Administrador("Super", "Admin", "999999999", "admin@banco.com", 
                                              30, "12345678", "Calle Principal 123", "EMP001");
        
        // 2. Registrarlo en el banco
        banco.getListaEmpleados().add(admin);

        // 3. Crear su Usuario para login
        gestorUsuarios.crearUsuarioAdministrador("admin", "admin123", admin);
        
        System.out.println(">> SISTEMA INICIALIZADO: Administrador creado nombreUsuario: admin; contraseña: admin123");
        System.out.println(">> SISTEMA INICIALIZADO: Datos cargados correctamente.");
    }
}