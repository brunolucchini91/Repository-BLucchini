package principal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SistemaUsuarios sistema = new SistemaUsuarios();

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("---- MENU ----");
            System.out.println("1 - Login");
            System.out.println("2 - Registro");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Buscar usuario");
            System.out.println("5 - Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion == 1) {
                System.out.print("Email: ");
                String email = scan.nextLine();

                System.out.print("Contrasena: ");
                String contrasena = scan.nextLine();

                sistema.login(email, contrasena);
            }

            else if (opcion == 2) {
                System.out.print("Nombre: ");
                String nombre = scan.nextLine();

                System.out.print("Email: ");
                String email = scan.nextLine();

                System.out.print("Contrasena: ");
                String contrasena = scan.nextLine();

                System.out.println("Tipo de usuario:");
                System.out.println("1 - Admin");
                System.out.println("2 - Tester");
                int tipo = scan.nextInt();
                scan.nextLine();

                if (tipo == 1) {
                    sistema.registrarUsuario(new Admin(nombre, email, contrasena));
                } else {
                    sistema.registrarUsuario(new Tester(nombre, email, contrasena));
                }
            }

            else if (opcion == 3) {
                sistema.listarUsuarios();
            }

            else if (opcion == 4) {
                System.out.print("Ingrese email a buscar: ");
                String email = scan.nextLine();

                sistema.buscarUsuario(email);
            }

            else if (opcion == 5) {
                System.out.println("Saliendo...");
            }

            else {
                System.out.println("Opcion incorrecta.");
            }
        }
    }
}