import java.util.ArrayList;

public class RegistroUsuarios {
    private ArrayList<Usuario> registroUsuarios=new ArrayList<>();

    public RegistroUsuarios(){
        registroUsuarios.add(new Usuario("Ronald",20213456));
        registroUsuarios.add(new Usuario("Alonzo", 20251167));
        registroUsuarios.add(new Usuario("Osmeyer", 20245678));
        registroUsuarios.add(new Usuario("Leonardo", 20234567));
        registroUsuarios.add(new Usuario("Daniel", 20251167));

    }
}
