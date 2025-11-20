import java.util.*;

public class Banco {
    /*Atributos - ArrayLists*/
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<Cuenta> listaCuentas;
    private ArrayList<Titular> listaTitular;
    private String ultimoError;

    /*Constructor*/
    public Banco() {
        this.listaClientes = new ArrayList<>();
        this.listaEmpleados = new ArrayList<>();
        this.listaCuentas = new ArrayList<>();
        this.listaTitular = new ArrayList<>();
        this.ultimoError = "";
    }

    /*======== REGISTROS ========*/
    public boolean registrarCliente(Cliente cliente) {
        if (!validarRegistroCliente(cliente)) return false;
        listaClientes.add(cliente);
        return true;
    }

    public boolean registrarEmpleado(Empleado empleado) {
        if (!validarRegistroEmpleado(empleado)) return false;
        listaEmpleados.add(empleado);
        return true;
    }

    public boolean registrarCuenta(Cuenta cuenta) {
        if (!validarRegistroCuenta(cuenta)) return false;
        listaCuentas.add(cuenta);
        return true;
    }

    public boolean registrarTitular(Titular titular) {
        if (!validarRegistroTitular(titular)) return false;
        listaTitular.add(titular);
        return true;
    }

    /*======== BÚSQUEDA ========*/
    public Cliente buscarCliente(String codigoCliente) {
        if (!Validaciones.validarCodigoCliente(codigoCliente)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_cliente");
            return null;
        }
        for (Cliente cliente : listaClientes) {
            if (cliente.getCodigoCliente().equalsIgnoreCase(codigoCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public Empleado buscarEmpleado(String codigoEmpleado) {
        if (!Validaciones.validarCodigoEmpleado(codigoEmpleado)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_empleado");
            return null;
        }
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCodigoEmpleado().equalsIgnoreCase(codigoEmpleado)) {
                return empleado;
            }
        }
        return null;
    }
    
    public Cuenta buscarCuenta(String codigoCuenta) {
        if (!Validaciones.validarCodigoCuenta(codigoCuenta)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_cuenta");
            return null;
        }
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getCodigoCuenta().equalsIgnoreCase(codigoCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    /*======== CREACIÓN DE CUENTA ========*/
    public Cuenta crearCuenta(String codigoCuenta) {
        if (!validarCreacionCuenta(codigoCuenta)) return null;
        return new Cuenta(codigoCuenta);
    }

    /*======== CREACIÓN DE TRANSACCIONES ========*/
    public Transaccion crearDepositoEmpleado(Cuenta cuenta, Empleado empleado, double monto, 
                                            String fecha, String hora, String id) {
        if (!validarDepositoEmpleado(cuenta, empleado, monto, fecha, hora, id)) return null;
        Deposito deposito = new Deposito(empleado, monto, fecha, hora, id);
        deposito.procesar(cuenta);
        return deposito;
    }

    public Transaccion crearDepositoCliente(Cuenta cuenta, double monto, String fecha, 
                                            String hora, String id) {
        if (!validarDepositoCliente(cuenta, monto, fecha, hora, id)) return null;
        Deposito deposito = new Deposito(monto, fecha, hora, id);
        deposito.procesar(cuenta);
        return deposito;
    }

    public Transaccion crearRetiroEmpleado(Cuenta cuenta, Empleado empleado, double monto, 
                                            String fecha, String hora, String id) {
        if (!validarRetiroEmpleado(cuenta, empleado, monto, fecha, hora, id)) return null;
        Retiro retiro = new Retiro(empleado, monto, fecha, hora, id);
        retiro.procesar(cuenta);
        return retiro;
    }

    public Transaccion crearRetiroCliente(Cuenta cuenta, double monto, String fecha, 
                                            String hora, String id) {
        if (!validarRetiroCliente(cuenta, monto, fecha, hora, id)) return null;
        Retiro retiro = new Retiro(monto, fecha, hora, id);
        retiro.procesar(cuenta);
        return retiro;
    }

    public Transaccion crearTransferenciaEmpleado(Empleado empleado, Cuenta cuenta, 
                                                    Cuenta cuentaDestino, double monto, 
                                                    String fecha, String hora, String id) {
        if (!validarTransferenciaEmpleado(empleado, cuenta, cuentaDestino, monto, fecha, hora, id))
            return null;
        Transferencia transferencia = new Transferencia(empleado, cuentaDestino, monto, fecha, hora, id);
        transferencia.procesar(cuenta);
        return transferencia;
    }

    public Transaccion crearTransferenciaCliente(Cuenta cuenta, Cuenta cuentaDestino, 
                                                double monto, String fecha, String hora, String id) {
        if (!validarTransferenciaCliente(cuenta, cuentaDestino, monto, fecha, hora, id)) return null;
        Transferencia transferencia = new Transferencia(cuentaDestino, monto, fecha, hora, id);
        transferencia.procesar(cuenta);
        return transferencia;
    }
    
    /*======== AGREGAR TRANSACCION A HISTORIAL ========*/
    public boolean agregarTransaccion(Transaccion transaccion, Cuenta cuenta) {
        if (!Validaciones.validarObjeto(transaccion) || !Validaciones.validarObjeto(cuenta)) {
            ultimoError = "Error: Transacción o cuenta no válidas";
            return false;
        }
        cuenta.getHistorial().add(transaccion);
        return true;
    }

    public Titular existeTitularCuenta(String codigoCuenta, String codigoCliente) {
        if (!Validaciones.validarCodigoCuenta(codigoCuenta) || 
            !Validaciones.validarCodigoCliente(codigoCliente)) {
            return null;
        }
        for (Titular titular : listaTitular) {
            if (titular.getCliente().getCodigoCliente().equalsIgnoreCase(codigoCliente) && 
                titular.getCuenta().getCodigoCuenta().equalsIgnoreCase(codigoCuenta)) {
                return titular;
            }
        }
        return null;
    }

    public Cuenta validarTransaccion(Titular titular) {
        return titular != null ? titular.getCuenta() : null;
    }

    // ==================== MÉTODOS DE IMPRESIÓN ====================
    
    public void imprimirEstadoCliente(Cliente cliente) {
        System.out.println(cliente == null ? "No se encontró el cliente" :
                                            "El cliente fue encontrado");
    }

    public void imprimirEstadoCuenta(Cuenta cuenta) {
        System.out.println(cuenta == null ? "No se encontró la cuenta" :
                                            "La cuenta fue encontrada");
    }

    public void imprimirEstadoEmpleado(Empleado empleado) {
        System.out.println(empleado == null ? "No se encontró el empleado" :
                                            "El empleado fue encontrado");
    }

    public void verificarClientes(Cliente nuevoCliente) {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            registrarCliente(nuevoCliente);
            System.out.println("Cliente registrado correctamente.");
        }
    }

    public void verificarEmpleados(Empleado nuevoEmpleado) {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            registrarEmpleado(nuevoEmpleado);
            System.out.println("Empleado registrado correctamente.");
        }
    }

    public void verificarCuentas(Cuenta nuevaCuenta) {
        if (listaCuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
        } else {
            registrarCuenta(nuevaCuenta);
            System.out.println("Cuenta registrada correctamente.");
        }
    }

    public void verificarTitulares(Titular nuevoTitular) {
        if (listaTitular.isEmpty()) {
            System.out.println("No hay titulares registrados.");
        } else {
            registrarTitular(nuevoTitular);
            System.out.println("Titular registrado correctamente.");
        }
    }

    public void imprimirUltimoError() {
        if (!ultimoError.isEmpty()) {
            System.out.println(ultimoError);
        }
    }

    // ==================== MÉTODOS PRIVADOS DE VALIDACIÓN ====================
    
    private boolean validarRegistroCliente(Cliente cliente) {
        if (!Validaciones.validarObjeto(cliente)) {
            ultimoError = "Error: Cliente no válido";
            return false;
        }
        if (!Validaciones.validarCodigoCliente(cliente.getCodigoCliente())) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_cliente");
            return false;
        }
        if (buscarCliente(cliente.getCodigoCliente()) != null) {
            ultimoError = "Error: Ya existe un cliente con ese código";
            return false;
        }
        return true;
    }

    private boolean validarRegistroEmpleado(Empleado empleado) {
        if (!Validaciones.validarObjeto(empleado)) {
            ultimoError = "Error: Empleado no válido";
            return false;
        }
        if (!Validaciones.validarCodigoEmpleado(empleado.getCodigoEmpleado())) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_empleado");
            return false;
        }
        if (buscarEmpleado(empleado.getCodigoEmpleado()) != null) {
            ultimoError = "Error: Ya existe un empleado con ese código";
            return false;
        }
        return true;
    }

    private boolean validarRegistroCuenta(Cuenta cuenta) {
        if (!Validaciones.validarObjeto(cuenta)) {
            ultimoError = "Error: Cuenta no válida";
            return false;
        }
        if (!Validaciones.validarCodigoCuenta(cuenta.getCodigoCuenta())) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_cuenta");
            return false;
        }
        if (buscarCuenta(cuenta.getCodigoCuenta()) != null) {
            ultimoError = "Error: Ya existe una cuenta con ese código";
            return false;
        }
        return true;
    }

    private boolean validarRegistroTitular(Titular titular) {
        if (!Validaciones.validarObjeto(titular) || 
            !Validaciones.validarObjeto(titular.getCliente()) || 
            !Validaciones.validarObjeto(titular.getCuenta())) {
            ultimoError = "Error: Titular, cliente o cuenta no válidos";
            return false;
        }
        if (existeTitularCuenta(titular.getCuenta().getCodigoCuenta(), 
                                titular.getCliente().getCodigoCliente()) != null) {
            ultimoError = "Error: Ya existe esta relación cliente-cuenta";
            return false;
        }
        return true;
    }

    private boolean validarCreacionCuenta(String codigoCuenta) {
        if (!Validaciones.validarCodigoCuenta(codigoCuenta)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("codigo_cuenta");
            return false;
        }
        if (buscarCuenta(codigoCuenta) != null) {
            ultimoError = "Error: Ya existe una cuenta con ese código";
            return false;
        }
        return true;
    }

    private boolean validarDepositoEmpleado(Cuenta cuenta, Empleado empleado, double monto, 
                                            String fecha, String hora, String id) {
        if (!Validaciones.esCuentaValida(cuenta)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuenta_invalida");
            return false;
        }
        if (!Validaciones.validarObjeto(empleado)) {
            ultimoError = "Error: Empleado no válido";
            return false;
        }
        if (!Validaciones.validarMontoDeposito(monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("monto_deposito");
            return false;
        }
        if (!Validaciones.validarDatosTransaccion(fecha, hora, id)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("datos_transaccion");
            return false;
        }
        return true;
    }

    private boolean validarDepositoCliente(Cuenta cuenta, double monto, String fecha, 
                                            String hora, String id) {
        if (!Validaciones.esCuentaValida(cuenta)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuenta_invalida");
            return false;
        }
        if (!Validaciones.validarMontoDeposito(monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("monto_deposito");
            return false;
        }
        if (!Validaciones.validarDatosTransaccion(fecha, hora, id)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("datos_transaccion");
            return false;
        }
        return true;
    }

    private boolean validarRetiroEmpleado(Cuenta cuenta, Empleado empleado, double monto, 
                                            String fecha, String hora, String id) {
        if (!Validaciones.esCuentaValida(cuenta)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuenta_invalida");
            return false;
        }
        if (!Validaciones.validarObjeto(empleado)) {
            ultimoError = "Error: Empleado no válido";
            return false;
        }
        if (!Validaciones.validarMontoRetiro(monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("monto_retiro");
            return false;
        }
        if (!Validaciones.validarSaldoSuficiente(cuenta, monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("saldo_insuficiente");
            return false;
        }
        if (!Validaciones.validarDatosTransaccion(fecha, hora, id)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("datos_transaccion");
            return false;
        }
        return true;
    }

    private boolean validarRetiroCliente(Cuenta cuenta, double monto, String fecha, 
                                        String hora, String id) {
        if (!Validaciones.esCuentaValida(cuenta)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuenta_invalida");
            return false;
        }
        if (!Validaciones.validarMontoRetiro(monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("monto_retiro");
            return false;
        }
        if (!Validaciones.validarSaldoSuficiente(cuenta, monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("saldo_insuficiente");
            return false;
        }
        if (!Validaciones.validarDatosTransaccion(fecha, hora, id)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("datos_transaccion");
            return false;
        }
        return true;
    }

    private boolean validarTransferenciaEmpleado(Empleado empleado, Cuenta cuenta, Cuenta cuentaDestino, 
                                                double monto, String fecha, String hora, String id) {
        if (!Validaciones.esCuentaValida(cuenta) || !Validaciones.esCuentaValida(cuentaDestino)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuenta_invalida");
            return false;
        }
        if (!Validaciones.validarCuentasDiferentes(cuenta.getCodigoCuenta(), cuentaDestino.getCodigoCuenta())) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuentas_iguales");
            return false;
        }
        if (!Validaciones.validarObjeto(empleado)) {
            ultimoError = "Error: Empleado no válido";
            return false;
        }
        if (!Validaciones.validarMontoTransferencia(monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("monto_transferencia");
            return false;
        }
        if (!Validaciones.validarSaldoSuficiente(cuenta, monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("saldo_insuficiente");
            return false;
        }
        if (!Validaciones.validarDatosTransaccion(fecha, hora, id)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("datos_transaccion");
            return false;
        }
        return true;
    }

    private boolean validarTransferenciaCliente(Cuenta cuenta, Cuenta cuentaDestino, double monto, 
                                                String fecha, String hora, String id) {
        if (!Validaciones.esCuentaValida(cuenta) || !Validaciones.esCuentaValida(cuentaDestino)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuenta_invalida");
            return false;
        }
        if (!Validaciones.validarCuentasDiferentes(cuenta.getCodigoCuenta(), cuentaDestino.getCodigoCuenta())) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("cuentas_iguales");
            return false;
        }
        if (!Validaciones.validarMontoTransferencia(monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("monto_transferencia");
            return false;
        }
        if (!Validaciones.validarSaldoSuficiente(cuenta, monto)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("saldo_insuficiente");
            return false;
        }
        if (!Validaciones.validarDatosTransaccion(fecha, hora, id)) {
            ultimoError = "Error: " + Validaciones.obtenerMensajeError("datos_transaccion");
            return false;
        }
        return true;
    }

    // ==================== GETTERS ====================
    
    public ArrayList<Cliente> getListaClientes() {return listaClientes;}
    public ArrayList<Empleado> getListaEmpleados() {return listaEmpleados;}
    public ArrayList<Cuenta> getListaCuentas() {return listaCuentas;}
    public ArrayList<Titular> getListaTitular() {return listaTitular;}
    public String getUltimoError() {return ultimoError;}
}