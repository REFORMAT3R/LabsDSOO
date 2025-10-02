import java.util.ArrayList;

public class RegistroUsuarios {
    private ArrayList<Usuario> registroUsuarios=new ArrayList<>(); /*Aqui almacenaremos y añadiremos a los usuarios */


    /*Usuarios Inicializados */
    public RegistroUsuarios(){
        registroUsuarios.add(new Usuario("Ronald","20213456"));
        registroUsuarios.add(new Usuario("Alonzo", "20251167"));
        registroUsuarios.add(new Usuario("Osmeyer", "20245678"));
        registroUsuarios.add(new Usuario("Leonardo", "20234567"));
        registroUsuarios.add(new Usuario("Daniel", "20251167"));

    }
    
    public ArrayList<Usuario> getAlmacenUsuarios(){
        return registroUsuarios;
    }

    public void setAlmacenUsuarios(Usuario a){
        registroUsuarios.add(a);
    }

}
