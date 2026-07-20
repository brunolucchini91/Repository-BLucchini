package principal;

public class Tester extends Usuario {
    private String nivelTester;

    public Tester(String nombre, String apellido, String paisNacimiento, String email, String contrasena, String nivelTester) {
        super(nombre, apellido, paisNacimiento, email, contrasena);
        this.nivelTester = nivelTester;
    }

    public String getNivelTester() { return nivelTester; }
    public void setNivelTester(String nivelTester) { this.nivelTester = nivelTester; }

    @Override
    public String getTipoUsuario() { return "Tester"; }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos: ejecutar pruebas y reportar errores.");
        System.out.println("Nivel de tester: " + nivelTester);
    }
}
