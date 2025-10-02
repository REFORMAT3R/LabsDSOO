import java.util.*;

public class Usuario{
    private String nombre;
    private String CUI;  /*Cambie el tipo de dato del CUI a Int */
    private HashMap <String,Libro> librosprestados = new HashMap<>(); /*No va en la clase Usuario, mejor crear una clase Prestamos como intermediario */
    
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

/*Tambien que esto este en la clase Prestamos */
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