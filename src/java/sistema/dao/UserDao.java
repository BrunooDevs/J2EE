package sistema.dao;

import java.sql.*;
import java.util.*;
import sistema.model.*;
import sistema.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = Database.getConnection();

    }//fin del constructor

    //FIN 
    public boolean checkUser(User user) {
        try {

            Statement stmt = connection.createStatement();
            String sql = "SELECT username , password FROM usuario WHERE username= '" + user.getUsuario() + "' AND password= '" + user.getPassword() + "'";

            stmt = connection.createStatement(); // Esta es linea que tienes que incluir
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) // found
            {
                //metodos
                return true;
            } else {
                //metodo
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
            return false;
        }
    }

    public void addUser(User user) {
        try {

            Statement stmt;
            stmt = connection.createStatement();
            int res = stmt.executeUpdate("INSERT INTO personas (nombre,apellidos,fecha_nac,tel,correo,domicilio) VALUES ('" + user.getNombre() + "', '" + user.getApellidos() + "' ,'" + user.getRegisteredon() + "', '" + user.getTelefono() + "', '" + user.getEmail() + "'   ,'" + user.getDomicilio() + "');");

            if (res > 0) {

                stmt.executeUpdate("INSERT INTO usuario (id_persona,username,password,id_rol) VALUES ((select MAX(id_persona) from personas),'" + user.getUsuario() + "', '" + user.getPassword() + "' ,'" + 2 + "');");

            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE uname=?;");
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuarios SET password=?, email=? WHERE uname = ?;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            //quitar la fecha porque es de solo lectura en user.jsp
            preparedStatement.setString(3, user.getNombre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public User GetInformacionUsuario(int id_persona) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM personas WHERE id_persona= '" + id_persona + "'";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                User usuario = new User();
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

    public int getIdMedico(String especialidad) {
        int id_medico = 0;
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_medico FROM medicos WHERE especialidad = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, especialidad);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id_medico = rs.getInt("id_medico");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id_medico;
    }

    public boolean AltaCita(int id_persona, int id_medico, String nombre, String apelldos, String numeross, String INE,
            String fecha, String hora, String especialidad, String sintomas) {

        int claveGenerada = 0;

        try {

            PreparedStatement pstm = null;

            pstm = connection.prepareStatement("INSERT INTO paciente(id_persona,id_medico,nombre, Apellidos,numero_ss,INE) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, id_persona);
            pstm.setInt(2, id_medico);
            pstm.setString(3, nombre);
            pstm.setString(4, apelldos);
            pstm.setString(5, numeross);
            pstm.setString(6, INE);

            int verificar = pstm.executeUpdate();
            /*
             ResultSet rs = pstm.getGeneratedKeys();

                while (rs.next()) {
                    claveGenerada = rs.getInt(0);
                }
             */
            if (verificar > 0) {

                Statement stmt;
                stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO agendarcita (folio,fecha,hora,especialidad,sintomas,estado) VALUES ((select MAX(folio) from paciente),'" + fecha + "', '" + hora + "' ,'" + especialidad + "' ,'" + sintomas + "' ,'" + 0 + "');");

                return true;

            } else {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public List<User> getAllUsersCitas(int id_persona) {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM agendarcita as citas INNER JOIN paciente as persona ON citas.folio = persona.folio WHERE persona.id_persona= '" + id_persona + "'";

            pstm = connection.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setFolio(rs.getInt("folio"));
                usuario.setFecha(rs.getString("fecha"));
                usuario.setHora(rs.getString("hora"));
                usuario.setEspecialidad(rs.getString("especialidad"));
                usuario.setSintomas(rs.getString("sintomas"));
                usuario.setEstado(rs.getBoolean("estado"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public List<User> getAllEspecialidades() {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT especialidad FROM medicos";

            pstm = connection.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setMedico(rs.getString("especialidad"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public List<User> getCitasAtender(int id_medico) {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT folio,id_persona,id_medico,nombre,Apellidos,numero_ss,INE FROM paciente WHERE id_medico = ?";

            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id_medico);
            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setFolio(rs.getInt("folio"));
                usuario.setId_persona(rs.getInt("id_persona"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("Apellidos"));
                usuario.setNumero_ss(rs.getString("numero_ss"));
                usuario.setINE(rs.getString("INE"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public String getImageById(int urlId) {
        String urlImagen = null;//variable local temporal
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select url from images where id_persona = ?;");
            preparedStatement.setInt(1, urlId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                urlImagen = rs.getString("url");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urlImagen;
    }

    //**ESTOS DOS METODOS VAN EN EL DAO DE ADMINISTRADOR YA QUE EL DA DE ALTA UNA NOTICIA
    public void addnoticia(String titulo, String descripcion) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Noticias(titulo,Descripcion) VALUES (?,?)");

            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, descripcion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public List<User> getFolios(int id_persona) {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT folio FROM paciente WHERE id_persona = ?";

            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id_persona);
            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setFolio(rs.getInt("folio"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public List<User> getMiAgenda(int id_medico) {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT folio FROM paciente WHERE id_medico = ?";

            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id_medico);
            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setFolio(rs.getInt("folio"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public List<User> getCamas() {

        List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT numero_cama FROM camas";

            pstm = connection.prepareStatement(query);
            //pstm.setInt(1, id_medico);
            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setCama(rs.getInt("numero_cama"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

    public List<User> getDiagnosticos(int id_persona) {

        List<User> users = new ArrayList<User>();

        try {

            /*
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM permisos WHERE id_permiso IN (SELECT id_permiso FROM roles as r INNER JOIN usuario as u ON r.ID_Rol = u.id_rol WHERE u.username = ?);";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, user);
             */
            
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM diagnostico WHERE folio IN (SELECT folio FROM paciente as r WHERE r.id_persona = ?);";

           // String query = "SELECT * FROM diagnostico WHERE folio= '" + 3 + "'";

            pstm = connection.prepareStatement(query);
            pstm.setInt(1, id_persona);
            rs = pstm.executeQuery();

            while (rs.next()) {
                User usuario = new User();
                usuario.setFolio(rs.getInt("folio"));
                usuario.setCama(rs.getInt("numero_cama"));
                usuario.setMedico(rs.getString("medico"));
                usuario.setDiagnostico(rs.getString("diagnostico"));
                usuario.setTratamiento(rs.getString("tratamiento"));
                users.add(usuario);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return users;

    }

}
