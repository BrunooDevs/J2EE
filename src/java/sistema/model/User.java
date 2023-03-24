package sistema.model;

import java.util.Date;
import java.text.DateFormat;
import java.util.*;

public class User {

    //VARIABLES DE INFORMACION BASICA DEL USUARIO
    private String nombre, Apellidos, password, email, telefono, correo, domicilio, usuario, repcontraseña;
    private String numero_ss,INE;
    private static int iid_persona;
    
    private int cama;

    public int getCama() {
        return cama;
    }

    public void setCama(int cama) {
        this.cama = cama;
    }

    public String getNumero_ss() {
        return numero_ss;
    }

    public void setNumero_ss(String numero_ss) {
        this.numero_ss = numero_ss;
    }

    public String getINE() {
        return INE;
    }

    public void setINE(String INE) {
        this.INE = INE;
    }
    
    private String registeredon;


    //VARIABLES DE LA TABLA AgendarCita
    private int folio;
    private String fecha, hora, especialidad, sintomas;
    private boolean estado;
    
    //Nombre medicos disponibles
    private String medico;
    private String diagnostico,tratamiento;

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
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


    //**************************************//
    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    public static int getId_persona() {
        return iid_persona;
    }

    public static void setId_persona(int id_persona) {
        iid_persona = id_persona;
    }
    
    
    

}
