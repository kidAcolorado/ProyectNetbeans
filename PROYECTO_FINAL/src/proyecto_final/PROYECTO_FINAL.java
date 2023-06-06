/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Kid A
 */
public class PROYECTO_FINAL {

    private static Scanner reader;
    private static Usuario user;
    private static ArrayList<Alojamiento> listaParaElegir;
    private static Alojamiento alojamientoSeleccionado;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Declarando variables:
        int opcionMenu = -1;

        Agencia agencia1;

        ArrayList<Alojamiento> listaAlojamientos = new ArrayList<>();

        Alojamiento alojamiento1, alojamiento2, alojamiento3, alojamiento4, alojamiento5;
        Alojamiento alojamiento6, alojamiento7, alojamiento8, alojamiento9, alojamiento10;
        Alojamiento alojamiento11, alojamiento12, alojamiento13, alojamiento14, alojamiento15;

        //Inicializando el Scanner:
        reader = new Scanner(System.in);

        //Creando los diferentes alojamientos (5 de cada tipo):
        alojamiento1 = new Apartamento("ApartaMenchu", "Madrid", 200, true, false, 10);
        alojamiento2 = new Apartamento("AparTartesos", "Barcelona", 150, true, true, 8);
        alojamiento3 = new Apartamento("ApartaDos", "Badajoz", 300, false, true, 2);
        alojamiento4 = new Apartamento("ApartaMeFiletes", "Badajoz", 1000, true, true, 2);
        alojamiento5 = new Apartamento("ApartaRatachan", "Madrid", 100, true, false, 20);

        alojamiento6 = new Hotel("Love Hotel", "Badajoz", 150, 3, 30);
        alojamiento7 = new Hotel("Astoria", "Madrid", 50, 1, 20);
        alojamiento8 = new Hotel("Haris Pilton", "Madrid", 500, 5, 200);
        alojamiento9 = new Hotel("Tel Ho", "Barcelona", 250, 1, 30);
        alojamiento10 = new Hotel("HotelNet", "Badajoz", 100, 4, 50);

        alojamiento11 = new CasaRural("La zorrera", "Badajoz", 150, true, true, false, 8);
        alojamiento12 = new CasaRural("Casa Carroja", "Barcelona", 100, true, true, true, 4);
        alojamiento13 = new CasaRural("Casa Tarradellas", "Barcelona", 2000, true, true, true, 12);
        alojamiento14 = new CasaRural("Los Melasóticos", "Badajoz", 300, true, true, true, 10);
        alojamiento15 = new CasaRural("El Caserío", "Barcelona", 200, true, true, true, 4);

        //Añadiendo los alojamientos a la lista de alojamientos creada anteriormente:
        listaAlojamientos.add(alojamiento1);
        listaAlojamientos.add(alojamiento2);
        listaAlojamientos.add(alojamiento3);
        listaAlojamientos.add(alojamiento4);
        listaAlojamientos.add(alojamiento5);
        listaAlojamientos.add(alojamiento6);
        listaAlojamientos.add(alojamiento7);
        listaAlojamientos.add(alojamiento8);
        listaAlojamientos.add(alojamiento9);
        listaAlojamientos.add(alojamiento10);
        listaAlojamientos.add(alojamiento11);
        listaAlojamientos.add(alojamiento12);
        listaAlojamientos.add(alojamiento13);
        listaAlojamientos.add(alojamiento14);
        listaAlojamientos.add(alojamiento15);

        //Inicializando nuestra Agencia "agencia1":
        agencia1 = new Agencia(1111, "Cáceres");

        //Asignando la lista de alojamientos ya creada a nuestra Agencia "agencia1":
        agencia1.setAlojamientos(listaAlojamientos);

        //Creamos el menú general en bucle do-while con switch entre las diferentes opciones:
        do {
            try {
                System.out.println("\n\nPULSE [1] - REGISTRAR USUARIO"
                        + "\nPULSE [2] - BUSCAR ALOJAMIENTO POR CIUDAD"
                        + "\nPULSE [3] - BUSCAR ALOJAMIENTO POR CIUDAD Y PRECIO"
                        + "\nPULSE [4] - CONSULTAR RESERVAS POR NOMBRE DE ALOJAMIENTO Y CIUDAD"
                        + "\nPULSE [5] - CONSULTAR RESERVAS POR NOMBRE DE USUARIO"
                        + "\nPULSE [0] - SALIR\n\n");
                opcionMenu = reader.nextInt();
                limpiarBuffer();

                switch (opcionMenu) {
                    case 1:
                        opcionRegistrarUsuario();
                        break;

                    case 2:
                        opcionBuscarAlojamientoPorCiudad(agencia1);
                        break;

                    case 3:
                        opcionBuscarAlojamientoPorCiudadYPrecio(agencia1);
                        break;

                    case 4:
                        opcionConsultarReservaPorNombreYCiudad(agencia1);
                        break;

                    case 5:
                        opcionConsultarReservasPorNombreDeUsuario(agencia1);
                        break;

                    case 0:
                        opcionSalir();
                        break;

                    default:
                        System.out.println("\n");
                        System.out.println("OPCIÓN INVÁLIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n");
                System.out.println("ERROR: Debes ingresar un número válido");
                limpiarBuffer();
            }
        } while (opcionMenu != 0);

    }

    /**
     * Método para limpiar el buffer del Scanner.
     */
    public static void limpiarBuffer() {
        reader.nextLine();
    }

    //MÉTODOS CON LOS SUBPROCESOS CORRESPONDIENTES A LAS OPCIONES DEL MENÚ DEL MAIN:
    /**
     * Método que realiza las funciones de la OPCIÓN del MENÚ "Registrar
     * Usuario".
     */
    public static void opcionRegistrarUsuario() {
        System.out.println("\n");
        user = registrarUsuario();
        System.out.println("USUARIO '" + user.getNombre() + "' DADO DE ALTA");
    }

    /**
     * Método que contiene todas las funciones de la OPCIÓN del MENÚ "Buscar
     * alojamiento por ciudad". Realiza la búsqueda de alojamientos disponibles
     * en una ciudad especificada y ofrece la opción de hacer una reserva.
     *
     * @param agencia la instancia de la Agencia desde la cual se realizará la
     * búsqueda y reserva
     */
    public static void opcionBuscarAlojamientoPorCiudad(Agencia agencia) {
        String ciudad;
        int numeroDias;

        System.out.println("\n");
        /* Pedimos la ciudad, usamos el método para obtener coincidencias y los resultados obtenidos 
        los introducimos en lista listaParaElegir */
        System.out.println("INTRODUCE CIUDAD: ");
        try {
            ciudad = reader.nextLine();
            listaParaElegir = agencia.buscarAlojamiento(ciudad);

            // Checkeamos si hay coincidencias y mostramos:
            if (checkEmptyListaAlojamientosAndShow(listaParaElegir)) {
                //Verificamos si es usuario. En caso true avanzamos a la reserva. En caso false ofrecemos registrarse:
                if (checkUser()) {
                    // Ofrecemos hacer reserva con el metodo quiereReserva, en caso True continuamos:   
                    if (quiereReserva()) {

                        System.out.println("SELECCIONE EL NÚMERO DE DÍAS DE LA RESERVA");
                        numeroDias = reader.nextInt();
                        limpiarBuffer();

                        // Checkeamos disponibilidad de servicio habitaciones y ofrecemos el servicio en caso true:
                        if (checkDisponibilidadServiciohabitaciones()) {
                            quiereServicioHabitaciones();
                        }

                        // Realizamos la reserva y la mostramos:
                        agencia.hacerReserva(user, alojamientoSeleccionado, numeroDias);
                        mostrarReservaRealizada(user, alojamientoSeleccionado, numeroDias);

                    }
                    // (checkUser == False) Ofrecemos la posibilidad de registrarse :    
                } else {
                    if (quiereRegistrarse()) {
                        opcionRegistrarUsuario();
                    }
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: Debe introducir una ciudad válida.");
            limpiarBuffer();
        }
    }

    /**
     * Método que contiene todas las funciones de la OPCIÓN del MENÚ "Buscar
     * alojamiento por ciudad y precio". Realiza la búsqueda de alojamientos
     * disponibles en una ciudad especificada y con un precio máximo indicado, y
     * ofrece la opción de hacer una reserva.
     *
     * @param agencia la instancia de la Agencia desde la cual se realizará la
     * búsqueda y reserva
     */
    public static void opcionBuscarAlojamientoPorCiudadYPrecio(Agencia agencia) {
        String ciudad;
        int numeroDias;
        double precio;

        System.out.println("\n");
        try {
            System.out.println("INTRODUCE CIUDAD");
            ciudad = reader.nextLine();
            System.out.println("INTRODUCE PRECIO MÁXIMO");
            precio = reader.nextDouble();
            limpiarBuffer();
            listaParaElegir = agencia.buscarAlojamiento(ciudad, precio);

            //Checkeamos si hay coincidencias y mostramos:
            if (checkEmptyListaAlojamientosAndShow(listaParaElegir)) {
                //Verificamos si es usuario. En caso true avanzamos a la reserva. En caso false ofrecemos registrarse:
                if (checkUser()) {
                    //Ofrecemos hacer reserva con el metodo quiereReserva, en caso True avanzamos:
                    if (quiereReserva()) {

                        System.out.println("SELECCIONE EL NÚMERO DE DÍAS DE LA RESERVA");
                        numeroDias = reader.nextInt();
                        limpiarBuffer();

                        //Checkeamos disponibilidad de servicio habitaciones y ofrecemos el servicio en caso true:
                        if (checkDisponibilidadServiciohabitaciones()) {
                            quiereServicioHabitaciones();
                        }

                        //Realizamos la reserva y la mostramos:
                        agencia.hacerReserva(user, alojamientoSeleccionado, numeroDias);
                        mostrarReservaRealizada(user, alojamientoSeleccionado, numeroDias);

                    }
                // (checkUser == False) Ofrecemos la posibilidad de registrarse :
                } else {
                    if (quiereRegistrarse()) {
                        opcionRegistrarUsuario();
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: La entrada proporcionada no es válida. Introduce datos numéricos para el precio y el número de días.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: Debe introducir una ciudad válida.");
        }
    }

    /**
     * Método que contiene todas las funciones de la OPCIÓN del MENÚ "Consultar
     * reserva por nombre y ciudad". Consulta las reservas de alojamientos por
     * nombre y ciudad especificados y muestra los resultados obtenidos.
     *
     * @param agencia la instancia de la Agencia desde la cual se realizará la
     * consulta de reservas
     */
    public static void opcionConsultarReservaPorNombreYCiudad(Agencia agencia) {
        String nombreAlojamiento, ciudad;
        ArrayList<Reserva> listaReservas;

        System.out.println("\n");
        //Pedimos los datos nombre y ciudad del alojamiento para luego usarlos en nuestro método de búsqueda:
        System.out.println("INTRODUCE NOMBRE DEL ALOJAMIENTO");
        nombreAlojamiento = reader.nextLine();
        System.out.println("INTRODUCE CIUDAD DEL ALOJAMIENTO");
        ciudad = reader.nextLine();

        listaReservas = agencia.buscarReserva(nombreAlojamiento, ciudad);

        //Checkeamos si hay coincidencias y mostramos:
        checkEmptyListaReservasAndShow(listaReservas);

    }

    /**
     * Método que contiene todas las funciones de la OPCIÓN del MENÚ "Consultar
     * reservas por nombre de usuario". Consulta las reservas de alojamientos
     * asociadas a un nombre de usuario especificado y muestra los resultados
     * obtenidos.
     *
     * @param agencia la instancia de la Agencia desde la cual se realizará la
     * consulta de reservas
     */
    public static void opcionConsultarReservasPorNombreDeUsuario(Agencia agencia) {
        String nombreUsuario;
        ArrayList<Reserva> listaReservas;

        System.out.println("\n");
        //Pedimos el nombre del usuario para luego usarlo en nuestro método de búsqueda:
        System.out.println("INTRODUCE NOMBRE DE USUARIO");
        nombreUsuario = reader.nextLine();

        listaReservas = agencia.buscarReserva(nombreUsuario);

        //Checkeamos si hay coincidencias y mostramos:
        checkEmptyListaReservasAndShow(listaReservas);
    }

    /**
     * Método que contiene la OPCIÓN del MENÚ "Salir".
     */
    public static void opcionSalir() {
        System.out.println("\n");
        System.out.println("GRACIAS POR USAR NUESTROS SERVICIOS. HASTA LA PRÓXIMA.");
    }

    //MÉTODOS REFERENTES AL USUARIO:
    /**
     * Registra un nuevo usuario solicitando al usuario el nombre y la dirección
     * de correo electrónico.
     *
     * @return el objeto Usuario creado con el nombre y la dirección de correo
     * electrónico proporcionados
     */
    private static Usuario registrarUsuario() {
        Usuario u1;
        String nombre, email;

        System.out.println("");
        System.out.println("INTRODUCE EL NOMBRE DE UN USUARIO");
        nombre = reader.nextLine();
        System.out.println("INTRODUCE UNA DIRECCION DE EMAIL");
        email = reader.nextLine();

        u1 = new Usuario(nombre, email);

        return u1;
    }

    /**
     * Verifica si existe un usuario actualmente registrado.
     *
     * @return true si hay un usuario registrado, false en caso contrario
     */
    private static boolean checkUser() {
        boolean flag;

        if (user != null) {
            flag = true;

        } else {
            System.out.println("SI QUIERE REALIZAR UNA RESERVA DEBE DARSE DE ALTA COMO USUARIO");
            flag = false;

        }
        return flag;

    }

    /**
     * Verifica si el usuario desea registrarse.
     *
     * @return true si el usuario desea registrarse, false en caso contrario
     */
    private static boolean quiereRegistrarse() {
        boolean flag = false;
        int opcionMenuRegistro;

        System.out.println("\n¿QUIERE REGISTRARSE?"
                + "\nPULSE [0] - NO"
                + "\nPULSE [1] - SÍ");
        System.out.println("Introduce la opción");

        try {
            opcionMenuRegistro = reader.nextInt();
            limpiarBuffer();

            if (opcionMenuRegistro == 1) {
                flag = true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: La entrada proporcionada no es válida. Introduce 0 o 1.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: No se ha proporcionado suficiente entrada de datos.");
        }

        return flag;
    }

    //MÉTODOS DE MUESTRA (reserva, lista de alojamientos, lista de reservas) Y CHECK EMPTY:
    /**
     *
     * Muestra por pantalla los detalles de una reserva realizada con éxito.
     *
     * @param usuario El usuario que realizó la reserva.
     * @param alojamiento El alojamiento reservado.
     * @param numeroDias El número de días de la reserva.
     */
    public static void mostrarReservaRealizada(Usuario usuario, Alojamiento alojamiento, int numeroDias) {
        System.out.println("");
        System.out.println("\nRESERVA REALIZADA CON ÉXITO\n"
                + "Datos de la Reserva:"
                + "\nReserva a nombre de: " + usuario.getNombre()
                + "\nEmail de contacto: " + usuario.getEmail()
                + "\nAlojamiento reservado: " + alojamiento.getNombre()
                + "\nNúmero de días reservados: " + numeroDias);

    }

    /**
     * Genera un listado de alojamientos a partir de una lista dada y lo muestra
     * en pantalla.
     *
     * @param lista la lista de alojamientos a mostrar
     * @return una cadena que contiene el listado de alojamientos formateado
     */
    public static String mostrarListaAlojamientos(ArrayList<Alojamiento> lista) {
        String listadoAlojamientos = "";
        int indice = 1;

        System.out.println("\nLISTADO DE ALOJAMIENTOS: \n");
        for (Alojamiento a : lista) {
            listadoAlojamientos += "OPCIÓN: " + indice + "\nNOMBRE: " + a.getNombre() + "\nCIUDAD: " + a.getCiudad() + "\nPRECIO: " + a.getPrecio() + "\n" + "\n";
            indice++;
        }

        return listadoAlojamientos;

    }

    /**
     * Genera un listado de reservas a partir de una lista dada y lo muestra en
     * pantalla.
     *
     * @param lista la lista de reservas a mostrar
     * @return una cadena que contiene el listado de reservas formateado
     */
    public static String mostrarListaReservas(ArrayList<Reserva> lista) {
        String listadoReservas = "";

        System.out.println("\nLISTADO DE RESERVAS: \n");
        for (Reserva r : lista) {
            listadoReservas += "\nUSUARIO: " + r.getUsuario().getNombre() + "\nALOJAMIENTO: " + r.getAlojamiento().getNombre() + "\nNÚMERO DE DÍAS: " + r.getNumDias() + "\n" + "\n";

        }

        return listadoReservas;

    }

    /**
     * Verifica si la lista de alojamientos está vacía y muestra el resultado en
     * pantalla. Si la lista está vacía, muestra un mensaje indicando que no hay
     * coincidencias. Si la lista no está vacía, muestra el listado de
     * alojamientos.
     *
     * @param listaAlojamientos la lista de alojamientos a verificar y mostrar
     * @return true si la lista no está vacía, false si está vacía
     */
    private static boolean checkEmptyListaAlojamientosAndShow(ArrayList<Alojamiento> listaAlojamientos) {
        boolean flag = false;

        if (listaAlojamientos.isEmpty()) {
            System.out.println("NO HAY COINCIDENCIAS");
        } else {
            System.out.println(mostrarListaAlojamientos(listaAlojamientos));
            flag = true;
        }

        return flag;
    }

    /**
     * Verifica si la lista de reservas está vacía y muestra el resultado en
     * pantalla. Si la lista está vacía, muestra un mensaje indicando que no hay
     * coincidencias. Si la lista no está vacía, muestra el listado de reservas.
     *
     * @param listaReservas la lista de reservas a verificar y mostrar
     */
    private static void checkEmptyListaReservasAndShow(ArrayList<Reserva> listaReservas) {

        if (listaReservas.isEmpty()) {
            System.out.println("NO HAY COINCIDENCIAS");
        } else {
            System.out.println(mostrarListaReservas(listaReservas));

        }

    }

    //MÉTODOS PARA EL MANEJO DE MENÚS A LA HORA DE REALIZAR LAS RESERVAS:
    /**
     * Permite al usuario seleccionar si desea hacer una reserva. Muestra un
     * menú de opciones y solicita al usuario que introduzca su elección. Si el
     * usuario elige "Sí" (opción 1), se le permite elegir un alojamiento de la
     * lista disponible. Si el usuario elige "No" (opción 0), se muestra un
     * mensaje indicando que la reserva no se realizó. Si el usuario elige una
     * opción inválida, se muestra un mensaje de error.
     *
     * @return true si el usuario elige hacer una reserva y selecciona un
     * alojamiento válido, false en caso contrario.
     */
    private static boolean quiereReserva() {
        boolean flag = false;
        int opcionMenuReserva = -1;

        do {
            try {
                System.out.println("\n¿QUIERE HACER UNA RESERVA?"
                        + "\nPULSE [0] - NO"
                        + "\nPULSE [1] - SÍ");
                System.out.println("Introduce la opción");
                opcionMenuReserva = reader.nextInt();
                limpiarBuffer();

                switch (opcionMenuReserva) {
                    case 0:
                        System.out.println("RESERVA NO REALIZADA");
                        break;

                    case 1:
                        // En caso de querer hacer una reserva llamamos al método elegirAlojamiento()
                        elegirAlojamiento(listaParaElegir);

                        //Comprobamos que se selecciona un alojamiento válido:
                        if (alojamientoSeleccionado == null) {
                            System.out.println("OPCION DE ALOJAMIENTO INVÁLIDA");
                        } else {
                            flag = true;
                        }

                        break;

                    default:
                        System.out.println("OPCIÓN INVÁLIDA");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                limpiarBuffer();
            }
        } while (opcionMenuReserva != 0 && flag == false);

        return flag;
    }

    /**
     *
     * Permite al usuario elegir un alojamiento de una lista dada.
     *
     * @param lista La lista de alojamientos entre los cuales se realizará la
     * elección.
     *
     * @return El alojamiento seleccionado por el usuario. Si la opción es
     * inválida, retorna null.
     */
    private static Alojamiento elegirAlojamiento(ArrayList<Alojamiento> lista) {
        int opcionMenuAlojamientos;

        try {
            System.out.println("ELIJA SU OPCIÓN: ");
            opcionMenuAlojamientos = reader.nextInt();
            limpiarBuffer();

            //Checkeamos que estamos haciendo una elección válida:
            if (opcionMenuAlojamientos < 1 || opcionMenuAlojamientos > lista.size()) {
                System.out.println("OPCIÓN INVÁLIDA");
                alojamientoSeleccionado = null;
            } else {
                alojamientoSeleccionado = lista.get(opcionMenuAlojamientos - 1);
                //opcionMenuAlojamientos - 1 se utiliza para obtener el índice correcto en la lista de alojamientos.
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            limpiarBuffer();
            alojamientoSeleccionado = null;
        }

        return alojamientoSeleccionado;
    }

    /**
     *
     * Verifica la disponibilidad del servicio de habitaciones para el
     * alojamiento seleccionado.
     *
     * @return true si el alojamiento seleccionado es un Hotel o un Apartamento
     * y tiene servicio de habitaciones, false de lo contrario.
     */
    private static boolean checkDisponibilidadServiciohabitaciones() {
        boolean flag = false;

        if (isInstanceOfHotel(alojamientoSeleccionado) || isInstanceOfApartamento(alojamientoSeleccionado)) {
            flag = true;
        }

        return flag;
    }

    /**
     *
     * Permite al usuario elegir si desea contratar el servicio de habitaciones
     * para el alojamiento seleccionado.
     *
     * @return true si el usuario desea contratar el servicio de habitaciones,
     * false de lo contrario.
     */
    private static boolean quiereServicioHabitaciones() {
        boolean flag = false;
        int opcionMenuServicioHabitaciones = -1;
        Hotel h;
        Apartamento a;

        do {
            try {
                System.out.println("\n¿QUIERE SERVICIO DE HABITACIONES?"
                        + "\nPULSE [0] - NO"
                        + "\nPULSE [1] - SÍ");
                System.out.println("INTRODUCE LA OPCION");
                opcionMenuServicioHabitaciones = reader.nextInt();
                limpiarBuffer();

                switch (opcionMenuServicioHabitaciones) {
                    case 0:
                        System.out.println("SERVICIO DE HABITACIONES NO CONTRATADO");
                        break;

                    case 1:
                        if (isInstanceOfHotel(alojamientoSeleccionado)) {
                            h = (Hotel) alojamientoSeleccionado;
                            h.solicitarServicioHabitaciones();
                        } else /*if (isInstanceOfApartamento(alojamientoSeleccionado))*/ {
                            a = (Apartamento) alojamientoSeleccionado;
                            a.solicitarServicioHabitaciones();
                        }

                        flag = true;
                        break;

                    default:
                        System.out.println("OPCIÓN INVÁLIDA");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                limpiarBuffer();
            }
        } while (opcionMenuServicioHabitaciones != 0 && flag == false);

        return flag;
    }

    //MÉTODOS DE VERIFICACIÓN DE IS_INSTANCE_OF (hotel y apartamento) DE LOS ALOJAMIENTOS:
    /**
     *
     * Verifica si el alojamiento es una instancia de la clase Hotel.
     *
     * @param alojamiento El alojamiento a verificar.
     *
     * @return true si el alojamiento es una instancia de la clase Hotel, false
     * de lo contrario.
     */
    public static boolean isInstanceOfHotel(Alojamiento alojamiento) {
        boolean flag = false;

        if (alojamiento instanceof Hotel) {
            flag = true;
        }

        return flag;
    }

    /**
     *
     * Verifica si el alojamiento es una instancia de la clase Apartamento.
     *
     * @param alojamiento El alojamiento a verificar.
     *
     * @return true si el alojamiento es una instancia de la clase Apartamento,
     * false de lo contrario.
     */
    public static boolean isInstanceOfApartamento(Alojamiento alojamiento) {
        boolean flag = false;

        if (alojamiento instanceof Apartamento) {
            flag = true;
        }

        return flag;
    }

}
