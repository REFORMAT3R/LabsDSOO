public class Retiro extends Transaccion {
    /*Constructor con empleado*/
    public Retiro(Empleado empleado, double monto, String fecha, String hora, String id) {
        super(empleado, monto, fecha, hora, id);
    }

    /*Constructor sin empleado*/
    public Retiro(double monto, String fecha, String hora, String id) {
        super(monto, fecha, hora, id);
    }

    @Override
    public void procesar(Cuenta cuenta) {
        if (validarMonto() && saldoSuficiente(cuenta)) {
            double nuevoSaldo = cuenta.getSaldo() - monto;
            cuenta.setSaldo(nuevoSaldo);
        } else {
            System.out.println("Transacción de retiro no válida.");
        }
    }

    @Override
    public boolean validarMonto() {
        return super.validarMonto() && monto <= 5000;
    }

    public boolean saldoSuficiente(Cuenta cuenta) {
        return cuenta.getSaldo() >= monto;
    }

    @Override
    public void mostrarEstado() {
        System.out.println("--- Retiro ---");
        super.mostrarEstado();
    }
}