import java.util.ArrayList;

public class GestorUsuarios {
    /* Atributo */
    private ArrayList<Usuario> listaUsuarios;

    /* Constructor */
    public GestorUsuarios() {
        this.listaUsuarios = new ArrayList<>();
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (!Validaciones.validarObjeto(usuario)) {
            System.out.println("Error: El usuario no puede ser nulo.");
            return false;
        }
        
        if (existeUsuario(usuario.getNombreUsuario())) {
            System.out.println("Error: Ya existe un usuario con el nombre '" + 
                        usuario.getNombreUsuario() + "'.");
            return false;
        }
        
        listaUsuarios.add(usuario);
        System.out.println("Usuario agregado correctamente: " + usuario.getNombreUsuario());
        return true;
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        if (!Validaciones.validarTexto(nombreUsuario)) {
            return null;
        }
        
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }

    public boolean existeUsuario(String nombreUsuario) {
        return buscarUsuario(nombreUsuario) != null;
    }

    public Usuario login(String nombreUsuario, String contrasenia) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return null;
        }
        
        if (usuario.login(nombreUsuario, contrasenia)) {
            return usuario;
        }
        
        return null;
    }

    public boolean cambiarContrasenia(String nombreUsuario, String contraseniaActual, 
                                String nuevaContrasenia) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        
        if (usuario == null) {
            System.out.println("Error: usuario no encontrado.");
            return false;
        }
        
        // Validar contraseña actual
        if (!usuario.getContrasenia().equals(contraseniaActual)) {
            System.out.println("Error: contraseña actual incorrecta.");
            return false;
        }
        
        // Validar que sea diferente
        if (contraseniaActual.equals(nuevaContrasenia)) {
            System.out.println("Error: la nueva contraseña debe ser diferente.");
            return false;
        }
        
        // Validar formato de la nueva contraseña
        if (!Validaciones.validarContrasena(nuevaContrasenia)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("contrasena"));
            return false;
        }
        
        usuario.setContrasenia(nuevaContrasenia);
        System.out.println("Contraseña actualizada correctamente.");
        return true;
    }

    public boolean cambiarEstadoUsuario(String nombreUsuario, boolean nuevoEstado) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        
        if (usuario == null) {
            System.out.println("Error: usuario no encontrado.");
            return false;
        }
        
        usuario.setEstado(nuevoEstado);
        String estado = nuevoEstado ? "activado" : "desactivado";
        System.out.println("Usuario " + estado + ": " + nombreUsuario);
        return true;
    }

    public boolean eliminarUsuario(String nombreUsuario) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        
        if (usuario == null) {
            System.out.println("Error: usuario no encontrado.");
            return false;
        }
        
        listaUsuarios.remove(usuario);
        System.out.println("Usuario eliminado: " + nombreUsuario);
        return true;
    }

    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        
        System.out.println("\n=== USUARIOS REGISTRADOS ===");
        for (Usuario u : listaUsuarios) {
            u.mostrarDatos();
            System.out.println("------------------------");
        }
    }

    public void mostrarUsuariosPorTipo(String tipo) {
        boolean encontrado = false;
        System.out.println("\n=== USUARIOS TIPO: " + tipo.toUpperCase() + " ===");
        
        for (Usuario u : listaUsuarios) {
            boolean coincide = false;
            
            if (tipo.equalsIgnoreCase("cliente") && u instanceof UsuarioCliente) {
                coincide = true;
            } else if (tipo.equalsIgnoreCase("empleado") && u instanceof UsuarioEmpleado) {
                coincide = true;
            } else if (tipo.equalsIgnoreCase("administrador") && u instanceof UsuarioAdministrador) {
                coincide = true;
            }
            
            if (coincide) {
                u.mostrarDatos();
                System.out.println("------------------------");
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("No hay usuarios de tipo " + tipo + " registrados.");
        }
    }

    public void mostrarEstadisticas() {
        int totalClientes = 0;
        int totalEmpleados = 0;
        int totalAdministradores = 0;
        int usuariosActivos = 0;
        
        for (Usuario u : listaUsuarios) {
            if (u instanceof UsuarioCliente) {
                totalClientes++;
            } else if (u instanceof UsuarioAdministrador) {
                totalAdministradores++;
            } else if (u instanceof UsuarioEmpleado) {
                totalEmpleados++;
            }
            
            if (u.getEstado()) {
                usuariosActivos++;
            }
        }
        
        System.out.println("\n=== ESTADÍSTICAS DE USUARIOS ===");
        System.out.println("Total de usuarios: " + listaUsuarios.size());
        System.out.println("Clientes: " + totalClientes);
        System.out.println("Empleados: " + totalEmpleados);
        System.out.println("Administradores: " + totalAdministradores);
        System.out.println("Usuarios activos: " + usuariosActivos);
        System.out.println("Usuarios inactivos: " + (listaUsuarios.size() - usuariosActivos));
    }

    /* Métodos para crear usuarios */
    
    public UsuarioCliente crearUsuarioCliente(String nombreUsuario, String contrasenia, Cliente cliente) {
        if (!Validaciones.validarObjeto(cliente)) {
            System.out.println("Error: El cliente no puede ser nulo.");
            return null;
        }
        
        if (!Validaciones.validarNombreUsuario(nombreUsuario)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("nombreusuario"));
            return null;
        }
        
        if (existeUsuario(nombreUsuario)) {
            System.out.println("Error: El nombre de usuario ya existe.");
            return null;
        }
        
        if (!Validaciones.validarContrasena(contrasenia)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("contrasena"));
            return null;
        }
        
        UsuarioCliente usuarioCliente = new UsuarioCliente(nombreUsuario, contrasenia, true, cliente);
        
        if (agregarUsuario(usuarioCliente)) {
            return usuarioCliente;
        }
        
        return null;
    }
    
    public UsuarioEmpleado crearUsuarioEmpleado(String nombreUsuario, String contrasenia, Empleado empleado) {
        if (!Validaciones.validarObjeto(empleado)) {
            System.out.println("Error: El empleado no puede ser nulo.");
            return null;
        }
        
        if (!Validaciones.validarNombreUsuario(nombreUsuario)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("nombreusuario"));
            return null;
        }
        
        if (existeUsuario(nombreUsuario)) {
            System.out.println("Error: El nombre de usuario ya existe.");
            return null;
        }
        
        if (!Validaciones.validarContrasena(contrasenia)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("contrasena"));
            return null;
        }
        
        UsuarioEmpleado usuarioEmpleado = new UsuarioEmpleado(nombreUsuario, contrasenia, true, empleado);
        
        if (agregarUsuario(usuarioEmpleado)) {
            return usuarioEmpleado;
        }
        
        return null;
    }
    
    public UsuarioAdministrador crearUsuarioAdministrador(String nombreUsuario, String contrasenia, Administrador administrador) {
        if (!Validaciones.validarObjeto(administrador)) {
            System.out.println("Error: El administrador no puede ser nulo.");
            return null;
        }
        
        if (!Validaciones.validarNombreUsuario(nombreUsuario)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("nombreusuario"));
            return null;
        }
        
        if (existeUsuario(nombreUsuario)) {
            System.out.println("Error: El nombre de usuario ya existe.");
            return null;
        }
        
        if (!Validaciones.validarContrasena(contrasenia)) {
            System.out.println("Error: " + Validaciones.obtenerMensajeError("contrasena"));
            return null;
        }
        
        UsuarioAdministrador usuarioAdmin = new UsuarioAdministrador(nombreUsuario, contrasenia, true, administrador);
        
        if (agregarUsuario(usuarioAdmin)) {
            return usuarioAdmin;
        }
        
        return null;
    }

    /* Getter */
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}