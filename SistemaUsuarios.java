package principal;

public class SistemaUsuarios {
    private Usuario[] usuarios;
    private int cantidad;

    public SistemaUsuarios() {
        usuarios = new Usuario[10];

        usuarios[0] = new Admin("Bruno", "admin@gmail.com", "1234");
        usuarios[1] = new Tester("Tester Uno", "tester@gmail.com", "1234");

        cantidad = 2;
    }

    public boolean existeUsuario(String email) {
        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void registrarUsuario(Usuario usuario) {
        if (cantidad < usuarios.length) {
            if (!existeUsuario(usuario.getEmail())) {
                usuarios[cantidad] = usuario;
                cantidad++;
                System.out.println("Usuario registrado correctamente.");
            } else {
                System.out.println("El usuario ya existe.");
            }
        } else {
            System.out.println("No hay espacio para registrar mas usuarios.");
        }
    }

    public void login(String email, String contrasena) {
        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].getEmail().equals(email)
                    && usuarios[i].getContrasena().equals(contrasena)) {

                System.out.println("Acceso permitido.");
                System.out.println("Bienvenido " + usuarios[i].getNombre());
                System.out.println("Tipo: " + usuarios[i].getTipoUsuario());
                return;
            }
        }

        System.out.println("Acceso denegado.");
    }
}