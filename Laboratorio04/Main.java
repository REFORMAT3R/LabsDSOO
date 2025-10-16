public class Main {
    public static void main(String[] args) {
        
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();
        Sistema sistema = new Sistema();
        
        System.out.println(registroUsuarios.getRegistroUsuarios().get(0)); //Muestra usuario 0
        sistema.prestarLibro(registroUsuarios.getRegistroUsuarios().get(0).getLibrosprestados(),"978-0307474728"); //Usuario 0 se presta un libro
        sistema.mostrarEstadoPrestamo(registroUsuarios.getRegistroUsuarios().get(0).getLibrosprestados(),"978-0307474728"); //Muestra estado del préstamo
        registroUsuarios.getRegistroUsuarios().get(0).mostrarLibrosprestados(); //Muestra lista de libros del usuario 0
        System.out.println(registroUsuarios.getRegistroUsuarios().get(0)); //Muestra usuario 0

        //Intentando prestar el mismo libro
        System.out.println(registroUsuarios.getRegistroUsuarios().get(1)); //Muestra usuario 1
        sistema.prestarLibro(registroUsuarios.getRegistroUsuarios().get(1).getLibrosprestados(),"978-0307474728"); //Usuario 1 se intenta prestar el mismo libro
        sistema.mostrarEstadoPrestamo(registroUsuarios.getRegistroUsuarios().get(1).getLibrosprestados(),"978-0307474728"); //Muestra estado del prstamo - No pudo prestarse el libro
        System.out.println(registroUsuarios.getRegistroUsuarios().get(1)); //Muestra usuario 1

        //Devolver libro del usuario 0
        sistema.devolverLibro(registroUsuarios.getRegistroUsuarios().get(0).getLibrosprestados(),"978-0307474728"); //Devuelve el libro prestado
        sistema.mostrarEstadoDevolver(registroUsuarios.getRegistroUsuarios().get(0).getLibrosprestados(),"978-0307474728"); //Muestra si se pudo devolver el libro o no
        System.out.println(registroUsuarios.getRegistroUsuarios().get(0)); // Muestra usuario 0
        
        Menu menu = new Menu(new Biblioteca(), registroUsuarios, sistema);
        menu.iniciar();
    }
}