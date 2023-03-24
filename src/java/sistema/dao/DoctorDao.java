package sistema.dao;

import java.sql.*;
import java.util.*;
import sistema.model.*;
import sistema.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DoctorDao {

    private Connection connection;

    public DoctorDao() {
        connection = Database.getConnection();
    }//fin del constructor

    public boolean login(String Usuario, String Password) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT username , password FROM usuario WHERE username = ? AND password = ? AND status = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, Usuario);//convertir a String el parametro Usuario
            pstm.setString(2, Password);//convertir a String el parametro Password

            rs = pstm.executeQuery();//ejecutar el query 
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getUsuario(String nombre) {
        int usuario = 0;
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_persona FROM usuario WHERE username = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            if (rs.next()) {
                usuario = rs.getInt("id_persona");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

    /**
     * **********************************************************
     * obtiene el rol de un usuario dado su id usuario
     * **********************************************************
     */
    public int getRolUsuario(int id_usuario) {
        int id_rol = 0;
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_rol FROM usuario WHERE id_persona = ?";
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id_usuario);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id_rol = rs.getInt("id_rol");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id_rol;
    }

    public String getURLMenu(int usuario) {
        String url = "";
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT url FROM Roles as r INNER JOIN usuario as u ON u.id_rol = r.id_rol WHERE u.id_persona = ?";
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, usuario);
            rs = pstm.executeQuery();
            if (rs.next()) {
                url = rs.getString("url");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return url;
    }

    public Permisos obtenerPermisos(String user) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM permisos WHERE id_permiso IN (SELECT id_permiso FROM roles as r INNER JOIN usuario as u ON r.ID_Rol = u.id_rol WHERE u.username = ?);";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, user);
            rs = pstm.executeQuery();
            if (rs.next()) {
                Permisos p = new Permisos();
                p.setInfoSelect(rs.getBoolean("select_info"));
                p.setInfoUpdate(rs.getBoolean("update_info"));
                p.setInfoInsert(rs.getBoolean("insert_info"));
                p.setInfoDelete(rs.getBoolean("delete_info"));
                p.setAdmin(rs.getBoolean("Admin"));
                return p; //regresar el objeto con los permisos almacenados
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Doctor GetInformacionUsuario(int id_persona) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM personas WHERE id_persona= '" + id_persona + "'";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                Doctor usuario = new Doctor();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setRegisteredon(rs.getString("fecha_nac"));
                usuario.setTelefono(rs.getString("tel"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setDomicilio(rs.getString("domicilio"));
                return usuario; //regresar el objeto con los Datos almacenados
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Doctor> getEspecialidad(int id_persona) {

        List<Doctor> users = new ArrayList<Doctor>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT especialidad FROM medicos WHERE id_medico= '" + id_persona + "'";

            pstm = connection.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Doctor usuario = new Doctor();
                usuario.setEspecialidad(rs.getString("especialidad"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public boolean registroConexiones(int id_usuario, int id_rol) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "INSERT INTO conexiones(id_user, id_rol, fecha) VALUES (?,?,now())";
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id_usuario);
            pstm.setInt(2, id_rol);
            if (pstm.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean diagnosticos(int folio, int numerocama, String medico, String diagnostico, String tratamiento) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "INSERT INTO diagnostico(folio, numero_cama, medico,diagnostico,tratamiento) VALUES (?,?,?,?,?)";
            pstm = connection.prepareStatement(query);
            pstm.setInt(1, folio);
            pstm.setInt(2, numerocama);
            pstm.setString(3, medico);
            pstm.setString(4, diagnostico);
            pstm.setString(5, tratamiento);
            if (pstm.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Noticias> getnoticias() {

        List<Noticias> noticias = new ArrayList<Noticias>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT titulo,Descripcion FROM noticias";
            pstm = connection.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Noticias noticia = new Noticias();
                noticia.setNombreNoticia(rs.getString("titulo"));
                noticia.setDescripcion(rs.getString("Descripcion"));
                noticias.add(noticia);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return noticias;

    }

}
