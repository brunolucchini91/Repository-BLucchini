package principal;

public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String paisNacimiento;
    private String email;
    private String contrasena;

    public Usuario(String nombre, String apellido, String paisNacimiento, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.paisNacimiento = paisNacimiento;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getPaisNacimiento() { return paisNacimiento; }
    public String getEmail() { return email; }
    public String getContrasena() { return contrasena; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setPaisNacimiento(String paisNacimiento) { this.paisNacimiento = paisNacimiento; }
    public void setEmail(String email) { this.email = email; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
    public abstract String getTipoUsuario();
    public abstract void mostrarPermisos();
}
