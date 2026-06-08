package principal;

public class Tester extends Usuario {

    public Tester(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena);
    }

    @Override
    public String getTipoUsuario() {
        return "Tester";
    }
}