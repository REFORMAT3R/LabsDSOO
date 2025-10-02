import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String libroSeleccionadoISBN;
        String nombreUsuario;
        String CUI;

        Scanner sc=new Scanner(System.in);
        RegistroUsuarios usuario=new RegistroUsuarios();
        Biblioteca biblioteca=new Biblioteca();
        Prestamo prestamo=new Prestamo();

        System.out.println("Bienvenidos a la biblioteca Mario Vargas Llosa \nPara pedir prestado un libro primero, por favor registrese");
        System.out.println("Indique su nombre");
        nombreUsuario=sc.nextLine();
        System.out.println("Indique su CUI");
        CUI=sc.nextLine();

        Usuario u=new Usuario(nombreUsuario, CUI);
        usuario.setAlmacenUsuarios(u);

        System.out.println("Registro Completado");
        System.out.println("Los libros que estan disponible son:");
        biblioteca.mostrarLibros();

        System.out.println();
        System.out.println("¿Cual libro eliges? (Usted seleccionara el libro por el codigo ISBN)");
        libroSeleccionadoISBN=sc.nextLine();

        if (biblioteca.validarLibro(libroSeleccionadoISBN)) {
            prestamo.prestarLibro(libroSeleccionadoISBN);
        }

    }

}
