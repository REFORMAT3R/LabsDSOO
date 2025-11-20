public class Transaccion {
    /*Atributos*/
    protected Empleado empleado;
    protected double monto;
    protected String fecha;
    protected String hora;
    protected String id;

    /*Cosntructor con empleado*/
    public Transaccion(Empleado empleado, double monto, String fecha, String hora, String id) {
        this.empleado = empleado;
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.id = id;
    }

    /*Constructor sin empleado*/
    public Transaccion(double monto, String fecha, String hora, String id) {
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.id = id;
    }

    /*Polimorfismo*/
    public void procesar(Cuenta cuenta){

    }

    public boolean validarMonto() {
        return monto > 0;
    }

    /*Polimorfismo*/
    public void mostrarEstado() {
        System.out.println("--- Estado de Transacción ---");
        System.out.println("ID: " + id);
        System.out.println("Monto: " + monto);
        System.out.println("Fecha: " + fecha + " " + hora);
    }
}