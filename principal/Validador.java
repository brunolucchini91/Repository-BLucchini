package principal;

import principal.excepciones.DatosInvalidosException;
import java.util.regex.Pattern;

public final class Validador {
    public static final int LONGITUD_MINIMA_CONTRASENA = 6;
    private static final Pattern PATRON_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private Validador() { }

    public static void validarCamposObligatorios(String... campos) throws DatosInvalidosException {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                throw new DatosInvalidosException("Todos los campos obligatorios deben estar completos.");
            }
        }
    }

    public static void validarEmail(String email) throws DatosInvalidosException {
        if (!PATRON_EMAIL.matcher(email.trim()).matches()) {
            throw new DatosInvalidosException("El email ingresado no tiene un formato válido.");
        }
    }

    public static void validarContrasena(String contrasena) throws DatosInvalidosException {
        if (contrasena.length() < LONGITUD_MINIMA_CONTRASENA) {
            throw new DatosInvalidosException("La contraseña debe tener al menos " + LONGITUD_MINIMA_CONTRASENA + " caracteres.");
        }
    }

    public static void validarConfirmacionContrasena(String contrasena, String confirmacion) throws DatosInvalidosException {
        if (!contrasena.equals(confirmacion)) {
            throw new DatosInvalidosException("La confirmación de la contraseña no coincide.");
        }
    }

    public static void validarNivelTester(String nivelTester) throws DatosInvalidosException {
        String nivel = nivelTester.trim().toLowerCase();
        if (!nivel.equals("junior") && !nivel.equals("semi senior") && !nivel.equals("senior")) {
            throw new DatosInvalidosException("El nivel de tester debe ser Junior, Semi Senior o Senior.");
        }
    }
}
