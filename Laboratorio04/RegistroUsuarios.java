import java.util.ArrayList;

public class RegistroUsuarios {
    private ArrayList<Usuario> registroUsuarios=new ArrayList<>();

    public RegistroUsuarios(){
        registroUsuarios.add(new Usuario("Ronald","20213456"));
        registroUsuarios.add(new Usuario("Alonzo", "20251167"));
        registroUsuarios.add(new Usuario("Osmeyer", "20255678"));
        registroUsuarios.add(new Usuario("Leonardo", "20234567"));
        registroUsuarios.add(new Usuario("Daniel", "20251167"));
    }

    public ArrayList<Usuario> getRegistroUsuarios() {
        return registroUsuarios;
    }

    public void agregarUsuario(Usuario usuario) {
        registroUsuarios.add(usuario);
        System.out.println("Usuario registrado exitosamente: " + usuario.getNombre());
    }
    
    public Usuario buscarUsuario(String cui) {
        for (Usuario u : registroUsuarios) {
            if (u.getCUI().equals(cui)) {
                return u;
            }
        }
        return null; 
    }
}
