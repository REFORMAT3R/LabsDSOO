import java.util.*;

public class Sistema {
    private Biblioteca biblioteca;

    public Sistema(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }

    //Métodos generales

    public Libro verificarExistencia(String ISBN){
        return biblioteca.getBiblioteca().get(ISBN);
    }

    public boolean verificarDisponibilidad(String ISBN){
        if(verificarExistencia(ISBN) == null)
            return false;
        return verificarExistencia(ISBN).getDisponible();
    }

    //Prestamo

    public boolean prestarLibro(HashMap <String,Libro> librosprestados, String ISBN){
        if(verificarDisponibilidad(ISBN)){
            librosprestados.put(ISBN, biblioteca.getBiblioteca().get(ISBN));
            biblioteca.getBiblioteca().get(ISBN).setDisponible(false);
            return true;
        }
        return false;
    }

    public void mostrarEstadoPrestamo(HashMap <String,Libro> librosprestados, String ISBN){
        if(librosprestados.containsKey(ISBN)){
            System.out.println("El libro " + biblioteca.getBiblioteca().get(ISBN).getTitulo() +" se prestó correctamente\n");
        } else {
            System.out.println("No se pudo prestar el libro " + biblioteca.getBiblioteca().get(ISBN).getTitulo() + "\n");
        }
    }

    //Devolver

    public boolean devolverLibro(HashMap <String,Libro> librosprestados, String ISBN){
        if(!verificarDisponibilidad(ISBN)){
            librosprestados.remove(ISBN);
            biblioteca.getBiblioteca().get(ISBN).setDisponible(true);
            return true;
        }
        return false;
    }

    public void mostrarEstadoDevolver(HashMap <String,Libro> librosprestados, String ISBN){
        if(!librosprestados.containsKey(ISBN)){
            System.out.println("El libro " + biblioteca.getBiblioteca().get(ISBN).getTitulo() + " se devolvió correctamente\n");
        } else {
            System.out.println("No se pudo devolver el libro " + biblioteca.getBiblioteca().get(ISBN).getTitulo() + "\n");
        }
    }
} 