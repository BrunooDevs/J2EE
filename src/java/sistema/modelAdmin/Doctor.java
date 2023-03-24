package sistema.modelAdmin;
public class Doctor {
    String nombre,apellidos,fecha_nac,telefono,correo,domicilio,user,password,id_rol,especialidad;
    int id_persona=-1;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getId_rol() {
        return id_rol;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getId_persona() {
        return id_persona;
    }
    
}
