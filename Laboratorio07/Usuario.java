public abstract class Usuario {
    protected String nombreUsuario;
    protected String contrasenia;
    protected boolean estado;

    /*Constructor*/
    public Usuario(String nombreUsuario, String contrasenia, boolean estado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    /*Getters*/
    public String getNombreUsuario() {return nombreUsuario;}
    public String getContrasenia() {return contrasenia;}
    public boolean getEstado() {return estado;}
    
    /*Setters*/
    public void setNombreUsuario(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}
    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}
    public void setEstado(boolean estado) {this.estado = estado;}

    public boolean login(String usuario, String password) {
        if (!estado) {
            System.out.println("Usuario inactivo. No puede iniciar sesión.");
            return false;
        }
        
        if (this.nombreUsuario.equals(usuario) && this.contrasenia.equals(password)) {
            System.out.println("Login exitoso. Bienvenido " + nombreUsuario);
            return true;
        } else {
            System.out.println("Credenciales incorrectas.");
            return false;
        }
    }

    public void mostrarDatos() {
        System.out.println("=== Datos de Usuario ===");
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
    }

    public abstract void mostrarPermisos();
}