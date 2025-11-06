import java.util.*;

public class nuevoMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        // Datos de prueba precargados
        banco.registrarCliente(new Cliente("Juan", "Pérez", "987654321", "juan@email.com", 30, "12345678", "Av. Principal 123", "CLI001"));
        banco.registrarCliente(new Cliente("María", "García", "987654322", "maria@email.com", 25, "87654321", "Calle Central 456", "CLI002"));
        banco.registrarCliente(new Cliente("Carlos", "López", "987654323", "carlos@email.com", 35, "11223344", "Jr. Los Olivos 789", "CLI003"));

        banco.registrarEmpleado(new Empleado("Ana", "Rodríguez", "987111222", "ana@banco.com", 28, "22334455", "Av. Bancaria 100", "EMP001"));
        banco.registrarEmpleado(new Empleado("Pedro", "Martínez", "987111333", "pedro@banco.com", 32, "33445566", "Calle Comercio 200", "EMP002"));

        banco.registrarCuenta(banco.crearCuenta("CTA00012345"));
        banco.registrarCuenta(banco.crearCuenta("CTA00012346"));
        banco.registrarCuenta(banco.crearCuenta("CTA00012347"));
        
        banco.buscarCuenta("CTA00012345").setSaldo(5000);
        banco.buscarCuenta("CTA00012346").setSaldo(3000);
        banco.buscarCuenta("CTA00012347").setSaldo(10000);

        banco.registrarTitular(new Titular(banco.buscarCliente("CLI001"), banco.buscarCuenta("CTA00012345")));
        banco.registrarTitular(new Titular(banco.buscarCliente("CLI002"), banco.buscarCuenta("CTA00012346")));
        banco.registrarTitular(new Titular(banco.buscarCliente("CLI003"), banco.buscarCuenta("CTA00012347")));

        while (true) {
            System.out.println("\n=== MENÚ BANCO ===");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Empleado");
            System.out.println("3. Crear Cuenta (con Titular)");
            System.out.println("4. Buscar persona");
            System.out.println("5. Realizar transacción");
            System.out.println("6. Ver listas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            int op = 0;
            try {
                op = sc.nextInt();
                sc.nextLine(); // limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                sc.nextLine(); // limpiar buffer
                continue;
            }

            switch (op) {
                case 1 -> {
                    // Validación de nombre
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    if (!Validaciones.validarNombre(nombre)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("nombre"));
                        break;
                    }

                    // Validación de apellido
                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();
                    if (!Validaciones.validarNombre(apellido)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("apellido"));
                        break;
                    }

                    // Validación de teléfono
                    System.out.print("Ingrese teléfono: ");
                    String telefono = sc.nextLine();
                    if (!Validaciones.validarCelular(telefono)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("telefono"));
                        break;
                    }

                    // Validación de correo
                    System.out.print("Ingrese correo: ");
                    String correo = sc.nextLine();
                    if (!Validaciones.validarCorreo(correo)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("correo"));
                        break;
                    }

                    // Validación de edad con try-catch
                    System.out.print("Ingrese edad: ");
                    int edad = 0;
                    try {
                        edad = sc.nextInt();
                        sc.nextLine(); // limpiar buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número válido para la edad.");
                        sc.nextLine(); // limpiar buffer
                        break;
                    }
                    if (!Validaciones.validarEdad(edad)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("edad"));
                        break;
                    }

                    // Validación de DNI
                    System.out.print("Ingrese DNI: ");
                    String dni = sc.nextLine();
                    if (!Validaciones.validarDNI(dni)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("dni"));
                        break;
                    }

                    // Validación de dirección
                    System.out.print("Ingrese dirección: ");
                    String direccion = sc.nextLine();
                    if (!Validaciones.validarDireccion(direccion)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("direccion"));
                        break;
                    }

                    // Validación de código de cliente
                    System.out.print("Ingrese código del cliente: ");
                    String codigoCliente = sc.nextLine();
                    if (!Validaciones.validarCodigoCliente(codigoCliente)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("codigo_cliente"));
                        break;
                    }

                    // Registro del cliente en el banco
                    Cliente cliente = new Cliente(nombre, apellido, telefono, correo, edad, dni, direccion, codigoCliente);
                    if (banco.registrarCliente(cliente)) {
                        System.out.println("Cliente registrado correctamente.");
                    } else {
                        banco.imprimirUltimoError();
                    }
                }

                case 2 -> {
                    // Validación de nombre
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    if (!Validaciones.validarNombre(nombre)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("nombre"));
                        break;
                    }

                    // Validación de apellido
                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();
                    if (!Validaciones.validarNombre(apellido)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("apellido"));
                        break;
                    }

                    // Validación de teléfono
                    System.out.print("Ingrese teléfono: ");
                    String telefono = sc.nextLine();
                    if (!Validaciones.validarCelular(telefono)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("telefono"));
                        break;
                    }

                    // Validación de correo
                    System.out.print("Ingrese correo: ");
                    String correo = sc.nextLine();
                    if (!Validaciones.validarCorreo(correo)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("correo"));
                        break;
                    }

                    // Validación de edad con try-catch
                    System.out.print("Ingrese edad: ");
                    int edad = 0;
                    try {
                        edad = sc.nextInt();
                        sc.nextLine(); // limpiar buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número válido para la edad.");
                        sc.nextLine(); // limpiar buffer
                        break;
                    }
                    if (!Validaciones.validarEdad(edad)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("edad"));
                        break;
                    }

                    // Validación de DNI
                    System.out.print("Ingrese DNI: ");
                    String dni = sc.nextLine();
                    if (!Validaciones.validarDNI(dni)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("dni"));
                        break;
                    }

                    // Validación de dirección
                    System.out.print("Ingrese dirección: ");
                    String direccion = sc.nextLine();
                    if (!Validaciones.validarDireccion(direccion)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("direccion"));
                        break;
                    }

                    // Validación de código de empleado
                    System.out.print("Ingrese código del empleado: ");
                    String codigoEmpleado = sc.nextLine();
                    if (!Validaciones.validarCodigoEmpleado(codigoEmpleado)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("codigo_empleado"));
                        break;
                    }

                    // Registro del empleado en el banco
                    Empleado empleado = new Empleado(nombre, apellido, telefono, correo, edad, dni, direccion, codigoEmpleado);
                    if (banco.registrarEmpleado(empleado)) {
                        System.out.println("Empleado registrado correctamente.");
                    } else {
                        banco.imprimirUltimoError();
                    }
                }

                case 3 -> {
                    // Validación y creación de cuenta
                    System.out.print("Ingrese código de la cuenta: ");
                    String codCuenta = sc.nextLine();
                    if (!Validaciones.validarCodigoCuenta(codCuenta)) {
                        System.out.println("Error: " + Validaciones.obtenerMensajeError("codigo_cuenta"));
                        break;
                    }

                    Cuenta cuenta = banco.crearCuenta(codCuenta);
                    if (cuenta == null) {
                        banco.imprimirUltimoError();
                        break;
                    }

                    if (banco.registrarCuenta(cuenta)) {
                        System.out.println("Cuenta creada correctamente.");
                    } else {
                        banco.imprimirUltimoError();
                        break;
                    }

                    // Asignación de titular a la cuenta
                    System.out.print("Ingrese código del cliente titular: ");
                    String codClienteTitular = sc.nextLine();
                    Cliente clienteTitular = banco.buscarCliente(codClienteTitular);

                    if (clienteTitular == null) {
                        System.out.println("El cliente no existe. No se puede crear el titular.");
                        banco.imprimirUltimoError();
                        break;
                    }

                    Titular titular = new Titular(clienteTitular, cuenta);
                    if (banco.registrarTitular(titular)) {
                        System.out.println("Titular registrado correctamente.");
                    } else {
                        banco.imprimirUltimoError();
                    }
                }

                case 4 -> {
                    // Submenú de búsqueda
                    while (true) {
                        System.out.println("\n===== SUBMENÚ DE BÚSQUEDA =====");
                        System.out.println("1. Buscar Cliente");
                        System.out.println("2. Buscar Empleado");
                        System.out.println("3. Buscar Cuenta");
                        System.out.println("4. Buscar Titularidad");
                        System.out.println("5. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        
                        int opcionBuscar = 0;
                        try {
                            opcionBuscar = sc.nextInt();
                            sc.nextLine(); // limpiar buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número válido.");
                            sc.nextLine(); // limpiar buffer
                            continue;
                        }

                        switch (opcionBuscar) {
                            case 1:
                                System.out.print("Ingrese el código del cliente: ");
                                String codCliente = sc.nextLine();
                                Cliente clienteEncontrado = banco.buscarCliente(codCliente);
                                if (clienteEncontrado != null) {
                                    clienteEncontrado.mostrarDatos();
                                } else {
                                    System.out.println("Cliente no encontrado.");
                                    banco.imprimirUltimoError();
                                }
                                break;

                            case 2:
                                System.out.print("Ingrese el código del empleado: ");
                                String codEmpleado = sc.nextLine();
                                Empleado empleadoEncontrado = banco.buscarEmpleado(codEmpleado);
                                if (empleadoEncontrado != null) {
                                    empleadoEncontrado.mostrarDatos();
                                } else {
                                    System.out.println("Empleado no encontrado.");
                                    banco.imprimirUltimoError();
                                }
                                break;

                            case 3:
                                System.out.print("Ingrese el código de la cuenta: ");
                                String codCuenta = sc.nextLine();
                                Cuenta cuentaEncontrada = banco.buscarCuenta(codCuenta);
                                if (cuentaEncontrada != null) {
                                    cuentaEncontrada.mostrarDatos();
                                } else {
                                    System.out.println("Cuenta no encontrada.");
                                    banco.imprimirUltimoError();
                                }
                                break;

                            case 4:
                                System.out.print("Ingrese el código del cliente: ");
                                String codClienteTitular = sc.nextLine();
                                System.out.print("Ingrese el código de la cuenta: ");
                                String codCuentaTitular = sc.nextLine();

                                Titular titularEncontrado = banco.existeTitularCuenta(codCuentaTitular, codClienteTitular);
                                if (titularEncontrado != null) {
                                    System.out.println("=== TITULARIDAD ENCONTRADA ===");
                                    System.out.println("Cliente asociado:");
                                    titularEncontrado.getCliente().mostrarDatos();
                                    System.out.println("Cuenta asociada:");
                                    titularEncontrado.getCuenta().mostrarDatos();
                                } else {
                                    System.out.println("No se encontró relación titular-cuenta.");
                                }
                                break;

                            case 5:
                                System.out.println("Regresando al menú principal...");
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }

                        if (opcionBuscar == 5) break;
                    }
                }

                case 5 -> {
                    // Submenú de transacciones
                    while (true) {
                        System.out.println("\n===== SUBMENÚ DE TRANSACCIONES =====");
                        System.out.println("1. Depósito");
                        System.out.println("2. Retiro");
                        System.out.println("3. Transferencia");
                        System.out.println("4. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        
                        int tipo = 0;
                        try {
                            tipo = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número válido.");
                            sc.nextLine(); // limpiar buffer
                            continue;
                        }

                        if (tipo == 4) {
                            System.out.println("Regresando al menú principal...");
                            break;
                        }

                        System.out.print("Ingrese código del cliente: ");
                        String codCliente = sc.nextLine();
                        System.out.print("Ingrese código de la cuenta: ");
                        String codCuenta = sc.nextLine();

                        // Verificar que el cliente sea titular de la cuenta
                        Titular titular = banco.existeTitularCuenta(codCuenta, codCliente);
                        if (titular == null) {
                            System.out.println("Error: la cuenta no pertenece al cliente o no existe.");
                            break;
                        }

                        Cuenta cuenta = banco.validarTransaccion(titular);
                        if (cuenta == null) {
                            System.out.println("Error: No se pudo validar la transacción.");
                            break;
                        }

                        // Captura de datos de la transacción
                        System.out.print("Ingrese monto: ");
                        double monto = 0;
                        try {
                            monto = sc.nextDouble();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un monto válido.");
                            sc.nextLine(); // limpiar buffer
                            break;
                        }
                        
                        // Validar saldo suficiente para retiro y transferencia
                        if ((tipo == 2 || tipo == 3) && cuenta.getSaldo() < monto) {
                            System.out.println("Error: Saldo insuficiente. Saldo disponible: S/." + cuenta.getSaldo());
                            break;
                        }
                        
                        // Validación de fecha
                        System.out.print("Ingrese fecha (dd/mm/aaaa): ");
                        String fecha = sc.nextLine();
                        if (!Validaciones.validarFecha(fecha)) {
                            System.out.println("Error: " + Validaciones.obtenerMensajeError("fecha"));
                            break;
                        }

                        // Validación de hora
                        System.out.print("Ingrese hora (hh:mm:ss): ");
                        String hora = sc.nextLine();
                        if (!Validaciones.validarHora(hora)) {
                            System.out.println("Error: " + Validaciones.obtenerMensajeError("hora"));
                            break;
                        }

                        // Validación de ID de transacción
                        System.out.print("Ingrese ID de la transacción: ");
                        String id = sc.nextLine();
                        if (!Validaciones.validarIdTransaccion(id)) {
                            System.out.println("Error: " + Validaciones.obtenerMensajeError("id_transaccion"));
                            break;
                        }

                        Transaccion transaccion = null;

                        switch (tipo) {
                            case 1 -> { // Depósito
                                transaccion = banco.crearDepositoCliente(cuenta, monto, fecha, hora, id);
                                if (transaccion == null) {
                                    banco.imprimirUltimoError();
                                    break;
                                }
                                if (banco.agregarTransaccion(transaccion, cuenta)) {
                                    System.out.println("Depósito realizado exitosamente.");
                                    transaccion.mostrarEstado();
                                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                                } else {
                                    banco.imprimirUltimoError();
                                }
                            }

                            case 2 -> { // Retiro
                                transaccion = banco.crearRetiroCliente(cuenta, monto, fecha, hora, id);
                                if (transaccion == null) {
                                    banco.imprimirUltimoError();
                                    break;
                                }
                                if (banco.agregarTransaccion(transaccion, cuenta)) {
                                    System.out.println("Retiro realizado exitosamente.");
                                    transaccion.mostrarEstado();
                                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                                } else {
                                    banco.imprimirUltimoError();
                                }
                            }

                            case 3 -> { // Transferencia
                                // Búsqueda de cuenta destino
                                System.out.print("Ingrese código de la cuenta destino: ");
                                String codDestino = sc.nextLine();
                                Cuenta cuentaDestino = banco.buscarCuenta(codDestino);

                                if (cuentaDestino == null) {
                                    System.out.println("Cuenta destino no encontrada.");
                                    banco.imprimirUltimoError();
                                    break;
                                }

                                // Creación y procesamiento de transferencia
                                transaccion = banco.crearTransferenciaCliente(cuenta, cuentaDestino, monto, fecha, hora, id);
                                if (transaccion == null) {
                                    banco.imprimirUltimoError();
                                    break;
                                }

                                if (banco.agregarTransaccion(transaccion, cuenta)) {
                                    System.out.println("Transferencia realizada exitosamente.");
                                    transaccion.mostrarEstado();
                                    System.out.println("Saldo actual (cuenta origen): " + cuenta.getSaldo());
                                    System.out.println("Saldo actual (cuenta destino): " + cuentaDestino.getSaldo());
                                } else {
                                    banco.imprimirUltimoError();
                                }
                            }

                            default -> System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    }
                }

                case 6 -> {
                    // Submenú para ver listas del sistema
                    while (true) {
                        System.out.println("\n===== SUBMENÚ DE LISTAS =====");
                        System.out.println("1. Ver lista de Clientes");
                        System.out.println("2. Ver lista de Empleados");
                        System.out.println("3. Ver lista de Cuentas");
                        System.out.println("4. Ver lista de Titulares");
                        System.out.println("5. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        
                        int opcionLista = 0;
                        try {
                            opcionLista = sc.nextInt();
                            sc.nextLine(); // limpiar buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número válido.");
                            sc.nextLine(); // limpiar buffer
                            continue;
                        }

                        switch (opcionLista) {
                            case 1:
                                // Mostrar todos los clientes registrados
                                System.out.println("\n=== LISTA DE CLIENTES ===");
                                if (banco.getListaClientes().isEmpty()) {
                                    System.out.println("No hay clientes registrados.");
                                } else {
                                    for (Cliente c : banco.getListaClientes()) {
                                        System.out.println("----------------------------");
                                        c.mostrarDatos();
                                    }
                                    System.out.println("Total de clientes: " + banco.getListaClientes().size());
                                }
                                break;

                            case 2:
                                // Mostrar todos los empleados registrados
                                System.out.println("\n=== LISTA DE EMPLEADOS ===");
                                if (banco.getListaEmpleados().isEmpty()) {
                                    System.out.println("No hay empleados registrados.");
                                } else {
                                    for (Empleado e : banco.getListaEmpleados()) {
                                        System.out.println("----------------------------");
                                        e.mostrarDatos();
                                    }
                                    System.out.println("Total de empleados: " + banco.getListaEmpleados().size());
                                }
                                break;

                            case 3:
                                // Mostrar todas las cuentas registradas
                                System.out.println("\n=== LISTA DE CUENTAS ===");
                                if (banco.getListaCuentas().isEmpty()) {
                                    System.out.println("No hay cuentas registradas.");
                                } else {
                                    for (Cuenta cu : banco.getListaCuentas()) {
                                        System.out.println("----------------------------");
                                        cu.mostrarDatos();
                                    }
                                    System.out.println("Total de cuentas: " + banco.getListaCuentas().size());
                                }
                                break;

                            case 4:
                                // Mostrar todas las relaciones titular-cuenta
                                System.out.println("\n=== LISTA DE TITULARES ===");
                                if (banco.getListaTitular().isEmpty()) {
                                    System.out.println("No hay titulares registrados.");
                                } else {
                                    for (Titular t : banco.getListaTitular()) {
                                        System.out.println("----------------------------");
                                        t.mostrarDatos();
                                    }
                                    System.out.println("Total de titulares: " + banco.getListaTitular().size());
                                }
                                break;

                            case 5:
                                System.out.println("Regresando al menú principal...");
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }

                        if (opcionLista == 5) break;
                    }
                }

                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}