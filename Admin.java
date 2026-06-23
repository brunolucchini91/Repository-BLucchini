package principal;

public class Admin extends Usuario {

    public Admin(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena);
    }

    @Override
    public String getTipoUsuario() {
        return "Admin";
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos: gestionar usuarios y acceder al sistema completo.");
    }
}