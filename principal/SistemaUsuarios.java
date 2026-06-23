package principal;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {

    private List<Usuario> usuarios;

    public SistemaUsuarios() {

        usuarios = new ArrayList<>();

        usuarios.add(new Admin("Bruno", "admin@gmail.com", "1234"));
        usuarios.add(new Tester("Tester Uno", "tester@gmail.com", "1234"));

    }

    public boolean existeUsuario(String email) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equals(email)) {

                return true;

            }

        }

        return false;

    }

    public void registrarUsuario(Usuario usuario) {

        if (!existeUsuario(usuario.getEmail())) {

            usuarios.add(usuario);

            System.out.println("Usuario registrado correctamente.");

        } else {

            System.out.println("El usuario ya existe.");

        }

    }

    public void login(String email, String contrasena) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equals(email)
                    && usuario.getContrasena().equals(contrasena)) {

                System.out.println("Acceso permitido.");
                System.out.println("Bienvenido " + usuario.getNombre());
                System.out.println("Tipo: " + usuario.getTipoUsuario());

                usuario.mostrarPermisos();

                return;

            }

        }

        System.out.println("Acceso denegado.");

    }

    public void listarUsuarios() {

        for (Usuario usuario : usuarios) {

            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Tipo: " + usuario.getTipoUsuario());

            System.out.println("--------------------");

        }

    }

    public Usuario buscarUsuario(String email) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equals(email)) {

                return usuario;

            }

        }

        return null;

    }

}