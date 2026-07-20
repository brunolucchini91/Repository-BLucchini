# Sistema de Gestión de Usuarios

Proyecto final en Java para la materia **Introducción a la Programación para Testers**.

## Requisitos
- Java 17 o superior.
- IntelliJ IDEA, Eclipse, NetBeans o terminal con JDK.

## Funcionalidades
### Sin sesión
- Iniciar sesión.
- Registrar administrador.
- Salir.

### Administrador autenticado
- Alta de Testers.
- Listar usuarios.
- Buscar por email.
- Cerrar sesión.
- Salir.

### Tester autenticado
- Ver permisos.
- Cerrar sesión.
- Salir.

## Usuarios precargados
- Admin: `admin@gmail.com` / `123456`
- Tester: `tester@gmail.com` / `123456`

## Validaciones
- Campos obligatorios.
- Email válido y no duplicado.
- Contraseña mínima de 6 caracteres.
- Confirmación de contraseña.
- Nivel de Tester válido.
- Opciones de menú válidas.
- Permisos según perfil.

## Excepciones
- `NumberFormatException`.
- `EmailDuplicadoException`.
- `UsuarioNoEncontradoException`.
- `DatosInvalidosException`.

## Estructura
```text
src/principal/
├── Admin.java
├── Main.java
├── SistemaUsuarios.java
├── Tester.java
├── Usuario.java
├── Validador.java
└── excepciones/
    ├── DatosInvalidosException.java
    ├── EmailDuplicadoException.java
    └── UsuarioNoEncontradoException.java
```

## Ejecución
```bash
javac -d out src/principal/excepciones/*.java src/principal/*.java
java -cp out principal.Main
```


<img width="1536" height="1024" alt="DiagramaVC4" src="https://github.com/user-attachments/assets/d56ddb18-78b8-4011-a2c3-95b9bf5e0f57" />
