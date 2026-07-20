package principal;

public class Admin extends Usuario {
    public Admin(String nombre, String apellido, String paisNacimiento, String email, String contrasena) {
        super(nombre, apellido, paisNacimiento, email, contrasena);
    }

    @Override
    public String getTipoUsuario() { return "Admin"; }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos: gestionar usuarios y acceder al sistema completo.");
    }
}
