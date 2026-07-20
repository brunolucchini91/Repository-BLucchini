package principal;

import principal.excepciones.DatosInvalidosException;
import principal.excepciones.EmailDuplicadoException;
import principal.excepciones.UsuarioNoEncontradoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SistemaUsuarios {
    private static SistemaUsuarios instancia;
    private final List<Usuario> usuarios;
    private Usuario usuarioActual;

    private SistemaUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Admin("Bruno", "Lucchini", "Uruguay", "admin@gmail.com", "123456"));
        usuarios.add(new Tester("Tester", "Uno", "Uruguay", "tester@gmail.com", "123456", "Junior"));
    }

    public static SistemaUsuarios getInstance() {
        if (instancia == null) instancia = new SistemaUsuarios();
        return instancia;
    }

    public boolean existeUsuario(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email.trim())) return true;
        }
        return false;
    }

    public void registrarAdministrador(String nombre, String apellido, String paisNacimiento, String email, String contrasena, String confirmacion)
            throws DatosInvalidosException, EmailDuplicadoException {
        Validador.validarCamposObligatorios(nombre, apellido, paisNacimiento, email, contrasena, confirmacion);
        Validador.validarEmail(email);
        Validador.validarContrasena(contrasena);
        Validador.validarConfirmacionContrasena(contrasena, confirmacion);
        validarEmailDuplicado(email);
        usuarios.add(new Admin(nombre.trim(), apellido.trim(), paisNacimiento.trim(), email.trim().toLowerCase(), contrasena));
        System.out.println("Administrador registrado correctamente.");
    }

    public void registrarTester(String nombre, String apellido, String paisNacimiento, String email, String contrasena, String confirmacion, String nivelTester)
            throws DatosInvalidosException, EmailDuplicadoException {
        verificarAdministradorAutenticado();
        Validador.validarCamposObligatorios(nombre, apellido, paisNacimiento, email, contrasena, confirmacion, nivelTester);
        Validador.validarEmail(email);
        Validador.validarContrasena(contrasena);
        Validador.validarConfirmacionContrasena(contrasena, confirmacion);
        Validador.validarNivelTester(nivelTester);
        validarEmailDuplicado(email);
        usuarios.add(new Tester(nombre.trim(), apellido.trim(), paisNacimiento.trim(), email.trim().toLowerCase(), contrasena, normalizarNivelTester(nivelTester)));
        System.out.println("Tester registrado correctamente.");
    }

    public Usuario login(String email, String contrasena) throws DatosInvalidosException, UsuarioNoEncontradoException {
        Validador.validarCamposObligatorios(email, contrasena);
        Validador.validarEmail(email);
        Usuario usuario = buscarUsuario(email);
        if (!usuario.getContrasena().equals(contrasena)) {
            throw new DatosInvalidosException("La contraseña ingresada es incorrecta.");
        }
        usuarioActual = usuario;
        System.out.println("Acceso permitido.");
        System.out.println("Bienvenido/a " + usuarioActual.getNombreCompleto());
        System.out.println("Tipo: " + usuarioActual.getTipoUsuario());
        usuarioActual.mostrarPermisos();
        return usuarioActual;
    }

    public void cerrarSesion() throws DatosInvalidosException {
        if (usuarioActual == null) throw new DatosInvalidosException("No hay una sesión iniciada.");
        System.out.println("Sesión cerrada correctamente.");
        usuarioActual = null;
    }

    public void listarUsuarios() throws DatosInvalidosException {
        verificarAdministradorAutenticado();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("----- USUARIOS REGISTRADOS -----");
        for (Usuario usuario : usuarios) {
            mostrarDatosUsuario(usuario);
            System.out.println("-------------------------------");
        }
    }

    public Usuario buscarUsuario(String email) throws UsuarioNoEncontradoException, DatosInvalidosException {
        Validador.validarCamposObligatorios(email);
        Validador.validarEmail(email);
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email.trim())) return usuario;
        }
        throw new UsuarioNoEncontradoException("No se encontró un usuario con el email ingresado.");
    }

    public void buscarYMostrarUsuario(String email) throws UsuarioNoEncontradoException, DatosInvalidosException {
        verificarAdministradorAutenticado();
        Usuario usuario = buscarUsuario(email);
        mostrarDatosUsuario(usuario);
        usuario.mostrarPermisos();
    }

    public boolean haySesionIniciada() { return usuarioActual != null; }
    public boolean esAdministradorActual() { return usuarioActual instanceof Admin; }
    public Usuario getUsuarioActual() { return usuarioActual; }
    public List<Usuario> getUsuarios() { return Collections.unmodifiableList(usuarios); }

    private void validarEmailDuplicado(String email) throws EmailDuplicadoException {
        if (existeUsuario(email)) throw new EmailDuplicadoException("Ya existe un usuario registrado con ese email.");
    }

    private void verificarAdministradorAutenticado() throws DatosInvalidosException {
        if (usuarioActual == null) throw new DatosInvalidosException("Debe iniciar sesión para realizar esta acción.");
        if (!(usuarioActual instanceof Admin)) throw new DatosInvalidosException("La acción solo puede ser realizada por un administrador.");
    }

    private void mostrarDatosUsuario(Usuario usuario) {
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Apellido: " + usuario.getApellido());
        System.out.println("País de nacimiento: " + usuario.getPaisNacimiento());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Tipo: " + usuario.getTipoUsuario());
        if (usuario instanceof Tester tester) System.out.println("Nivel: " + tester.getNivelTester());
    }

    private String normalizarNivelTester(String nivelTester) {
        return switch (nivelTester.trim().toLowerCase()) {
            case "junior" -> "Junior";
            case "semi senior" -> "Semi Senior";
            case "senior" -> "Senior";
            default -> nivelTester.trim();
        };
    }
}
