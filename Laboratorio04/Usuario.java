import java.util.*;

public class Usuario{
    private String nombre;
    private String CUI;
    private HashMap <String,Libro> librosprestados = new HashMap<>();
    
    public Usuario(String nombre, String CUI) {
        this.nombre = nombre;
        this.CUI = CUI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCUI() {
        return CUI;
    }

    public HashMap<String, Libro> getLibrosprestados() {
        return librosprestados;
    }

    public void mostrarLibrosprestados(){
        for(Libro l : librosprestados.values()){
            System.out.println(l);
        }
    }

    public String toString(){
        return nombre + " - " + CUI + " - Libros prestados: " + librosprestados.size();
    }
}