package principal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        SistemaUsuarios sistema = new SistemaUsuarios();

        int opcion = 0;

        while (opcion != 5) {

            System.out.println("----- MENU -----");
            System.out.println("1 - Login");
            System.out.println("2 - Registro");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Buscar usuario");
            System.out.println("5 - Salir");

            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Email: ");
                    String emailLogin = scan.nextLine();

                    System.out.print("Contraseña: ");
                    String contrasenaLogin = scan.nextLine();

                    sistema.login(emailLogin, contrasenaLogin);

                    break;

                case 2:

                    System.out.print("Nombre: ");
                    String nombre = scan.nextLine();

                    System.out.print("Email: ");
                    String email = scan.nextLine();

                    System.out.print("Contraseña: ");
                    String contrasena = scan.nextLine();

                    System.out.println("1 - Admin");
                    System.out.println("2 - Tester");

                    int tipo = scan.nextInt();
                    scan.nextLine();

                    if (tipo == 1) {

                        sistema.registrarUsuario(
                                new Admin(nombre, email, contrasena));

                    } else {

                        sistema.registrarUsuario(
                                new Tester(nombre, email, contrasena));

                    }

                    break;

                case 3:

                    sistema.listarUsuarios();

                    break;

                case 4:

                    System.out.print("Ingrese email: ");

                    String emailBusqueda = scan.nextLine();

                    Usuario usuario = sistema.buscarUsuario(emailBusqueda);

                    if (usuario != null) {

                        System.out.println("Nombre: " + usuario.getNombre());
                        System.out.println("Email: " + usuario.getEmail());
                        System.out.println("Tipo: " + usuario.getTipoUsuario());

                        usuario.mostrarPermisos();

                    } else {

                        System.out.println("Usuario no encontrado.");

                    }

                    break;

                case 5:

                    System.out.println("Fin del programa.");

                    break;

                default:

                    System.out.println("Opción incorrecta.");

            }

        }

        scan.close();

    }

}