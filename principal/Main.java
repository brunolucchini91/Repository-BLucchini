package principal;

import principal.excepciones.DatosInvalidosException;
import principal.excepciones.EmailDuplicadoException;
import principal.excepciones.UsuarioNoEncontradoException;
import java.util.Scanner;

public class Main {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final SistemaUsuarios SISTEMA = SistemaUsuarios.getInstance();

    public static void main(String[] args) {
        boolean continuar = true;
        System.out.println("Sistema de Gestión de Usuarios");
        while (continuar) {
            try {
                if (!SISTEMA.haySesionIniciada()) continuar = mostrarMenuPublico();
                else if (SISTEMA.esAdministradorActual()) continuar = mostrarMenuAdministrador();
                else continuar = mostrarMenuTester();
            } catch (DatosInvalidosException | EmailDuplicadoException | UsuarioNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
        SCAN.close();
        System.out.println("Fin del programa.");
    }

    private static boolean mostrarMenuPublico() throws DatosInvalidosException, EmailDuplicadoException, UsuarioNoEncontradoException {
        System.out.println();
        System.out.println("----- MENÚ PRINCIPAL -----");
        System.out.println("1 - Iniciar sesión");
        System.out.println("2 - Registrar administrador");
        System.out.println("3 - Salir");
        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
            case 1 -> iniciarSesion();
            case 2 -> registrarAdministrador();
            case 3 -> { return false; }
            default -> throw new DatosInvalidosException("La opción seleccionada no existe.");
        }
        return true;
    }

    private static boolean mostrarMenuAdministrador() throws DatosInvalidosException, EmailDuplicadoException, UsuarioNoEncontradoException {
        System.out.println();
        System.out.println("----- MENÚ ADMINISTRADOR -----");
        System.out.println("Usuario: " + SISTEMA.getUsuarioActual().getNombreCompleto());
        System.out.println("1 - Alta usuario Tester");
        System.out.println("2 - Listar usuarios");
        System.out.println("3 - Buscar usuario por email");
        System.out.println("4 - Cerrar sesión");
        System.out.println("5 - Salir");
        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
            case 1 -> registrarTester();
            case 2 -> SISTEMA.listarUsuarios();
            case 3 -> buscarUsuario();
            case 4 -> SISTEMA.cerrarSesion();
            case 5 -> { return false; }
            default -> throw new DatosInvalidosException("La opción seleccionada no existe.");
        }
        return true;
    }

    private static boolean mostrarMenuTester() throws DatosInvalidosException {
        System.out.println();
        System.out.println("----- MENÚ TESTER -----");
        System.out.println("Usuario: " + SISTEMA.getUsuarioActual().getNombreCompleto());
        System.out.println("1 - Ver permisos");
        System.out.println("2 - Cerrar sesión");
        System.out.println("3 - Salir");
        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
            case 1 -> SISTEMA.getUsuarioActual().mostrarPermisos();
            case 2 -> SISTEMA.cerrarSesion();
            case 3 -> { return false; }
            default -> throw new DatosInvalidosException("La opción seleccionada no existe.");
        }
        return true;
    }

    private static void iniciarSesion() throws DatosInvalidosException, UsuarioNoEncontradoException {
        System.out.print("Email: ");
        String email = SCAN.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = SCAN.nextLine();
        SISTEMA.login(email, contrasena);
    }

    private static void registrarAdministrador() throws DatosInvalidosException, EmailDuplicadoException {
        System.out.println("----- REGISTRO DE ADMINISTRADOR -----");
        DatosRegistro datos = solicitarDatosComunes();
        SISTEMA.registrarAdministrador(datos.nombre(), datos.apellido(), datos.paisNacimiento(), datos.email(), datos.contrasena(), datos.confirmacion());
    }

    private static void registrarTester() throws DatosInvalidosException, EmailDuplicadoException {
        System.out.println("----- ALTA DE TESTER -----");
        DatosRegistro datos = solicitarDatosComunes();
        System.out.print("Nivel de Tester (Junior/Semi Senior/Senior): ");
        String nivelTester = SCAN.nextLine();
        SISTEMA.registrarTester(datos.nombre(), datos.apellido(), datos.paisNacimiento(), datos.email(), datos.contrasena(), datos.confirmacion(), nivelTester);
    }

    private static DatosRegistro solicitarDatosComunes() {
        System.out.print("Nombre: "); String nombre = SCAN.nextLine();
        System.out.print("Apellido: "); String apellido = SCAN.nextLine();
        System.out.print("País de nacimiento: "); String paisNacimiento = SCAN.nextLine();
        System.out.print("Email: "); String email = SCAN.nextLine();
        System.out.print("Contraseña: "); String contrasena = SCAN.nextLine();
        System.out.print("Confirmar contraseña: "); String confirmacion = SCAN.nextLine();
        return new DatosRegistro(nombre, apellido, paisNacimiento, email, contrasena, confirmacion);
    }

    private static void buscarUsuario() throws UsuarioNoEncontradoException, DatosInvalidosException {
        System.out.print("Ingrese email: ");
        String email = SCAN.nextLine();
        SISTEMA.buscarYMostrarUsuario(email);
    }

    private static int leerEntero(String mensaje) throws DatosInvalidosException {
        System.out.print(mensaje);
        String entrada = SCAN.nextLine();
        try { return Integer.parseInt(entrada); }
        catch (NumberFormatException e) { throw new DatosInvalidosException("Debe ingresar un número entero."); }
    }

    private record DatosRegistro(String nombre, String apellido, String paisNacimiento, String email, String contrasena, String confirmacion) { }
}
