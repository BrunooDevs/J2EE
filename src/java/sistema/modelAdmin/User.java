package sistema.modelAdmin;
import java.util.Date;
import java.text.DateFormat;

public class User {
    
    private String nombre,Apellidos, password, email, telefono,correo,domicilio,usuario,repcontraseña;
    private String registeredon;
   private static int iid_persona;

       public static int getId_persona() {
        return iid_persona;
    }

    public static void setId_persona(int id_persona) {
        iid_persona = id_persona;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRepcontraseña() {
        return repcontraseña;
    }

    public void setRepcontraseña(String repcontraseña) {
        this.repcontraseña = repcontraseña;
    }

    public String getRegisteredon() {
        return registeredon;
    }

    public void setRegisteredon(String registeredon) {
        this.registeredon = registeredon;
    }
}
