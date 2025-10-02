import java.util.HashMap;

public class Prestamo {
    private Biblioteca biblioteca=new Biblioteca();
    private HashMap<String,Libro> libros=biblioteca.getBiblioteca();

    private RegistroPrestamos registroPrestamos=new RegistroPrestamos();
    private HashMap<String,Libro> prestamos=registroPrestamos.getLibrosPrestados();

    public void prestarLibro(String isbn){
        Libro libro=libros.remove(isbn);
        if (libro != null) {
            prestamos.put(libro.getISBN(), libro);
        }
    }



    /*Falta crear el toString para relacionar usuario con libros prestados


     *     public String toString(){
        return nombre + " - " + CUI + " - Libros prestados: " + librosprestados.size();
    }
}
     * 
     */

    

}
