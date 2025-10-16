import java.util.*;

public class Biblioteca{
    private HashMap<String,Libro> biblioteca=new HashMap<>();
    
    public Biblioteca(){
        biblioteca.put("978-0307474728", new Libro("Cien años de soledad", "Gabriel García Marquez", "978-0307474728", true));
        biblioteca.put("978-8491050299", new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978-8491050299", false));
        biblioteca.put("978-8408172177", new Libro("La sombra del viento", "Carlos Ruiz Zafón", "978-8408172177", true));
        biblioteca.put("978-0451524935", new Libro("1984", "George Orwell", "978-0451524935", true));
        biblioteca.put("978-8437604947", new Libro("Rayuela", "Julio Cortázar", "978-8437604947", false));
    }

    public HashMap<String, Libro> getBiblioteca() {
        return biblioteca;
    }

    public void agregarLibro(Libro libro) {
        biblioteca.put(libro.getISBN(), libro);
        System.out.println("Libro agregado exitosamente: " + libro.getTitulo());
    }

    public void mostrarLibros(){
        for(Libro l : biblioteca.values()){
            System.out.println(l);
        }
    }
}