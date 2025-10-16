import java.util.*;

public class Menu {
    private Scanner scanner;
    private Biblioteca biblioteca;
    private RegistroUsuarios registroUsuarios;
    private Sistema sistema;

    public Menu(Biblioteca biblioteca, RegistroUsuarios registroUsuarios, Sistema sistema) {
        this.scanner = new Scanner(System.in);
        this.biblioteca = biblioteca;
        this.registroUsuarios = registroUsuarios;
        this.sistema = sistema;
    }

    //Bucle donde se sale si se pone 7
    public void iniciar() {
        int opcion = 0;
        while (opcion != 7) {
            mostrarMenuPrincipal();
            String entrada = scanner.nextLine();
            if (entrada != null && entrada.matches("\\d+")) {
                opcion = Integer.parseInt(entrada);
                procesarOpcion(opcion);
            } else {
                System.out.println("Ingresa un número válido.");
            }
            System.out.println();
        }
    }

    //Menú de opciones
    private void mostrarMenuPrincipal() {
        System.out.println("===== MENÚ DE LA BIBLIOTECA =====");
        System.out.println("1. Mostrar libros disponibles");
        System.out.println("2. Registrar nuevo usuario");
        System.out.println("3. Agregar nuevo libro");
        System.out.println("4. Prestar un libro");
        System.out.println("5. Devolver un libro");
        System.out.println("6. Mostrar mis libros prestados");
        System.out.println("7. Salir");
        System.out.print("Elige una opción: ");
    }

    //Cada número puesto ejecuta un método
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                biblioteca.mostrarLibros();
                break;
            case 2:
                registrarNuevoUsuario();
                break;
            case 3:
                agregarNuevoLibro();
                break;
            case 4:
                prestarUnLibro();
                break;
            case 5:
                devolverUnLibro();
                break;
            case 6:
                mostrarLibrosDeUsuario();
                break;
            case 7:
                System.out.println("Gracias por usar el sistema.");
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
                break;
        }
    }

    private void registrarNuevoUsuario() {
        System.out.print("Ingresa el nombre del nuevo usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa el CUI del nuevo usuario: ");
        String cui = scanner.nextLine();
        registroUsuarios.agregarUsuario(new Usuario(nombre, cui));
    }

    private void agregarNuevoLibro() {
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingresa el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingresa el ISBN del libro: ");
        String isbn = scanner.nextLine();
        biblioteca.agregarLibro(new Libro(titulo, autor, isbn, true));
    }

    private void prestarUnLibro() {
        System.out.print("Ingresa tu CUI: ");
        String cui = scanner.nextLine();
        Usuario usuario = registroUsuarios.buscarUsuario(cui);
        if (usuario != null) {
            System.out.print("Ingresa el ISBN del libro a prestar: ");
            String isbn = scanner.nextLine();
            sistema.prestarLibro(usuario.getLibrosprestados(), isbn);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void devolverUnLibro() {
        System.out.print("Ingresa tu CUI: ");
        String cui = scanner.nextLine();
        Usuario usuario = registroUsuarios.buscarUsuario(cui);
        if (usuario != null) {
            System.out.print("Ingresa el ISBN del libro a devolver: ");
            String isbn = scanner.nextLine();
            sistema.devolverLibro(usuario.getLibrosprestados(), isbn);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
    
    private void mostrarLibrosDeUsuario() {
        System.out.print("Ingresa tu CUI para ver tus libros: ");
        String cui = scanner.nextLine();
        Usuario usuario = registroUsuarios.buscarUsuario(cui);
        if (usuario != null) {
            usuario.mostrarLibrosprestados();
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}