package sistema.modelAdmin;
public class Admin {
    private String id_persona,nombre,Apellidos, password, email, telefono, fechanac,correo,domicilio,usuario,repcontraseña;
    
    public String getId_persona() {
        return id_persona;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return Apellidos;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getFechanac() {
        return fechanac;
    }
    public String getCorreo() {
        return correo;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getRepcontraseña() {
        return repcontraseña;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setRepcontraseña(String repcontraseña) {
        this.repcontraseña = repcontraseña;
    }
}
