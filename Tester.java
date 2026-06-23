package principal;

public class Tester extends Usuario {

    public Tester(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena);
    }

    @Override
    public String getTipoUsuario() {
        return "Tester";
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos: ejecutar pruebas y reportar errores.");
    }
}