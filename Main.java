package principal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Usuario usuario = new Usuario();

        int opcion;

        do {

            System.out.println("1 - Registro");
            System.out.println("2 - Login");
            System.out.println("0 - Salir");

            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion == 1) {

                System.out.println("Ingrese email:");
                usuario.email = scan.nextLine();

                System.out.println("Ingrese contraseña:");
                usuario.contrasena = scan.nextLine();

                System.out.println("Usuario registrado");

            } else if (opcion == 2) {

                String emailIngresado;
                String contrasenaIngresada;

                System.out.println("Ingrese email:");
                emailIngresado = scan.nextLine();

                System.out.println("Ingrese contraseña:");
                contrasenaIngresada = scan.nextLine();

                if (emailIngresado.equals(usuario.email)
                        && contrasenaIngresada.equals(usuario.contrasena)) {

                    System.out.println("Acceso permitido");

                } else {

                    System.out.println("Acceso denegado");
                }
            }

        } while (opcion != 0);

    }
}