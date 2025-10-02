import java.util.*;

public class Prestamo {
    private Biblioteca biblioteca;
    
    public Libro verificarExistencia(String ISBN){
        return biblioteca.getBiblioteca().get(ISBN);
    }

    public boolean verificarDisponibilidad(String ISBN){
        if(verificarExistencia(ISBN) == null)
            return false;
        return verificarExistencia(ISBN).getDisponible();
    }

    public boolean prestarLibro(HashMap <String,Libro> librosprestados, String ISBN){
        if(verificarDisponibilidad(ISBN)){
            librosprestados.put(ISBN, biblioteca.getBiblioteca().get(ISBN));
            biblioteca.getBiblioteca().get(ISBN).setDisponible(false);
            return true;
        }
        return false;
    }

    public void mostrarEstadoPrestamo(HashMap <String,Libro> librosprestados, String ISBN){
        if(prestarLibro(librosprestados, ISBN)){
            System.out.println("El libro se prestó correctamente");
        } else {
            System.out.println("No se pudo prestar el libro");
        }
    }
} 