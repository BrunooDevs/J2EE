package sistema.dao;
import sistema.modelAdmin.Noticias;
import sistema.modelAdmin.Plantas;
import sistema.modelAdmin.Doctor;
import sistema.modelAdmin.Camas;
import sistema.modelAdmin.Diagnosticos;
import java.sql.*;
import java.util.*;
import sistema.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AdminDao {
    private Connection connection;
    
    public AdminDao() {
        connection = Database.getConnection();
    }
    //----------------------------------Plantas Del Hospital----------------------------------------
    public List<Plantas> getAllplantas() {
        List<Plantas> plantas = new ArrayList<Plantas>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from plantas");
            while (rs.next()) {
            Plantas planta = new Plantas();
            planta.setId_planta(rs.getInt("id_planta"));
            planta.setNombre(rs.getString("nombre_planta"));
            planta.setNumerocamas(rs.getInt("numero_camas"));
            plantas.add(planta);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
            return plantas;
        }
    
    public void checkplanta(Plantas planta){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT id_planta FROM plantas WHERE id_planta = ?;");
            ps.setInt(1, planta.getId_planta());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                updateplanta(planta);
            } else {
                addplanta(planta);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
     
    public void updateplanta(Plantas planta) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE plantas SET id_planta=?, nombre_planta=?, numero_camas=? WHERE id_planta = ?;");
            preparedStatement.setInt(1,planta.getId_planta());
            preparedStatement.setString(2,planta.getNombre());
            preparedStatement.setInt(3,planta.getNumerocamas());
            preparedStatement.setInt(4, planta.getId_planta());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addplanta(Plantas planta) {
        try {    
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO plantas(id_planta,nombre_planta,numero_camas) VALUES (?, ?, ?);");
        preparedStatement.setInt(1, planta.getId_planta());
        preparedStatement.setString(2, planta.getNombre());
        preparedStatement.setInt(3, planta.getNumerocamas());
        preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }  
    }
       
    public void deleteplanta(int id_planta) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM plantas WHERE id_planta=?;");
            preparedStatement.setInt(1,id_planta);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public Plantas getPlantaById(int id_planta) {
        Plantas planta = new Plantas();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM plantas WHERE id_planta = ?;");
            preparedStatement.setInt(1,id_planta);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                planta.setId_planta(rs.getInt("id_planta"));
                planta.setNombre(rs.getString("nombre_planta"));
                planta.setNumerocamas(rs.getInt("numero_camas"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return planta;
    }
    //---------------------------------Plantas Hospital--------------------------------------------------   
    
    //---------------------------------Camas Hospital---------------------------------------------------- 
        public List<Camas> getAllcamas() {
        List<Camas> camas = new ArrayList<Camas>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from camas");
            while (rs.next()) {
            Camas cama = new Camas();
            cama.setNumero_cama(rs.getInt("numero_cama"));
            cama.setId_planta(rs.getInt("id_planta"));
            cama.setCaracteristicas(rs.getString("caracteristicas"));
            cama.setOxigeno(rs.getInt("oxigeno"));
            cama.setTermometro(rs.getInt("termometro"));
            camas.add(cama);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
            return camas;
        }
    
    public void checkcama(Camas cama){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT numero_cama FROM camas WHERE numero_cama = ?;");
            ps.setInt(1, cama.getNumero_cama());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                
                updatecama(cama);
            } else {
                addcama(cama);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
     
    public void updatecama(Camas cama) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE camas SET numero_cama=?, id_planta=?, caracteristicas=?, oxigeno=?, termometro=? WHERE numero_cama = ?;");
            preparedStatement.setInt(1,cama.getNumero_cama());
            preparedStatement.setInt(2,cama.getId_planta());
            preparedStatement.setString(3,cama.getCaracteristicas());
            preparedStatement.setInt(4,cama.getOxigeno());
            preparedStatement.setInt(5, cama.getTermometro());
            preparedStatement.setInt(6, cama.getNumero_cama());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addcama(Camas cama) {
        try {    
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO camas(numero_cama,id_planta,caracteristicas,oxigeno,termometro) VALUES (?, ?, ?, ?, ?);");
        preparedStatement.setInt(1,cama.getNumero_cama());
        preparedStatement.setInt(2,cama.getId_planta());
        preparedStatement.setString(3,cama.getCaracteristicas());
        preparedStatement.setInt(4,cama.getOxigeno());
        preparedStatement.setInt(5, cama.getTermometro());
        preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }  
    }
       
    public void deletecama(int numero_cama) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM camas WHERE numero_cama=?;");
            preparedStatement.setInt(1,numero_cama);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
        
    public Camas getCamaById(int numero_cama) {
        Camas cama = new Camas();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM camas WHERE numero_cama = ?;");
            preparedStatement.setInt(1,numero_cama);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                
                cama.setNumero_cama(rs.getInt("numero_cama"));
                cama.setId_planta(rs.getInt("id_planta"));
                cama.setCaracteristicas(rs.getString("caracteristicas"));
                cama.setOxigeno(rs.getInt("oxigeno"));
                cama.setTermometro(rs.getInt("termometro"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return cama;
    }
    
    public List<Plantas> getAllIDPlantas() {
        List<Plantas> plantas = new ArrayList<Plantas>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM plantas";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Plantas planta = new Plantas();
                planta.setId_planta(rs.getInt("id_planta"));
                plantas.add(planta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return plantas;

    }
    //---------------------------------Camas Hospital---------------------------------------------------- 
    
    //---------------------------------Imagenes Hospital-------------------------------------------------- 
    
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
    //---------------------------------Noticias Hospital--------------------------------------------------     
    public void addnoticia(Noticias noticia) {
        try {    
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO noticias(id_noticia,titulo,descripcion) VALUES (?, ?, ?);");
        preparedStatement.setInt(1,noticia.getId_noticia());
        preparedStatement.setString(2,noticia.getTitulo());
        preparedStatement.setString(3,noticia.getDescripcion());
        preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }  
    }
    
    public List<Noticias> getnoticiass() {
        List<Noticias> noticias = new ArrayList<Noticias>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM noticias");
            while (rs.next()) {
                Noticias noticia = new Noticias();
                noticia.setId_noticia(rs.getInt("id_noticia"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setDescripcion(rs.getString("descripcion"));
                noticias.add(noticia);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return noticias;
    }
    public void deletenoticia(int id_noticia) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM noticias WHERE id_noticia=?;");
            preparedStatement.setInt(1,id_noticia);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
        
    public Noticias getNoticiaById(int id_noticia) {
        Noticias noticia=new Noticias();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM noticias WHERE id_noticia = ?;");
            preparedStatement.setInt(1,id_noticia);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                noticia.setId_noticia(rs.getInt("id_noticia"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return noticia;
    }
    public void checknoticia(Noticias noticia){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT id_noticia FROM noticias WHERE id_noticia = ?;");
            ps.setInt(1, noticia.getId_noticia());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                
                updatenoticia(noticia);
            } else {
                addnoticia(noticia);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
     
    public void updatenoticia(Noticias noticia) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE noticias SET titulo=?, descripcion=? WHERE id_noticia = ?;");
            preparedStatement.setString(1,noticia.getTitulo());
            preparedStatement.setString(2,noticia.getDescripcion());
            preparedStatement.setInt(3,noticia.getId_noticia());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //---------------------------------Noticias Hospital-------------------------------------------------- 
    //---------------------------------Doctores Hospital--------------------------------------------------
         public List<Doctor> getAllDoctores() {
        List<Doctor> doctores = new ArrayList<Doctor>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM medicos AS m INNER JOIN personas AS p ON m.id_medico=p.id_persona INNER JOIN usuario AS u ON u.id_persona=p.id_persona;");
            while (rs.next()) {
            Doctor doctor = new Doctor();
            doctor.setId_persona(rs.getInt("id_persona"));
            doctor.setNombre(rs.getString("nombre"));
            doctor.setApellidos(rs.getString("apellidos"));
            doctor.setFecha_nac(rs.getString("fecha_nac"));
            doctor.setTelefono(rs.getString("tel"));
            doctor.setCorreo(rs.getString("correo"));
            doctor.setDomicilio(rs.getString("domicilio"));
            doctor.setEspecialidad(rs.getString("especialidad"));
            doctor.setUser(rs.getString("username"));
            doctor.setPassword(rs.getString("password"));
            doctores.add(doctor);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
            return doctores;
        }
    
    public void checkdoctor(Doctor doctor){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT id_persona FROM personas WHERE id_persona = ?;");
            ps.setInt(1, doctor.getId_persona());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                
                updatedoctor(doctor);
            } else {
                adddoctor(doctor);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
     
    public void updatedoctor(Doctor doctor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE personas SET nombre=?, apellidos=?, fecha_nac=?,tel=?, correo=?,domicilio=? WHERE id_persona = ?;");
            preparedStatement.setString(1,doctor.getNombre());
            preparedStatement.setString(2,doctor.getApellidos());
            preparedStatement.setString(3,doctor.getFecha_nac());
            preparedStatement.setString(4,doctor.getTelefono());
            preparedStatement.setString(5,doctor.getCorreo());
            preparedStatement.setString(6,doctor.getDomicilio());
            preparedStatement.setInt(7,doctor.getId_persona());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuario SET username=?, password=? WHERE id_persona = ?;");
            preparedStatement.setString(1,doctor.getUser());
            preparedStatement.setString(2,doctor.getPassword());
            preparedStatement.setInt(3,doctor.getId_persona());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medicos SET nombre=?, especialidad=? WHERE id_medico = ?;");
            preparedStatement.setString(1,doctor.getNombre());
            preparedStatement.setString(2,doctor.getEspecialidad());
            preparedStatement.setInt(3,doctor.getId_persona());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void adddoctor(Doctor doctor) {
        int idpersona=0;
        try {    
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO personas(nombre,apellidos,fecha_nac,tel,correo,domicilio) VALUES(?,?,?,?,?,?) ;");
            preparedStatement.setString(1,doctor.getNombre());
            preparedStatement.setString(2,doctor.getApellidos());
            preparedStatement.setString(3,doctor.getFecha_nac());
            preparedStatement.setString(4,doctor.getTelefono());
            preparedStatement.setString(5,doctor.getCorreo());
            preparedStatement.setString(6,doctor.getDomicilio());
        preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }  
        try {    
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_persona from personas where nombre=? AND apellidos=?;");
            preparedStatement.setString(1,doctor.getNombre());
            preparedStatement.setString(2,doctor.getApellidos());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                idpersona=rs.getInt("id_persona");
            }
            
        }
        catch (SQLException e) {
        e.printStackTrace();
        }  
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario(id_persona,username,password,id_rol) VALUES (?,?,?,3);");
            preparedStatement.setInt(1,idpersona);
            preparedStatement.setString(2,doctor.getUser());
            preparedStatement.setString(3,doctor.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medicos(id_medico,nombre,especialidad) VALUES (?,?,?)");
            preparedStatement.setInt(1,idpersona);
            preparedStatement.setString(2,doctor.getNombre());
            preparedStatement.setString(3,doctor.getEspecialidad());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       
    public void deletedoctor(int id_persona) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuario WHERE id_persona=?;");
            preparedStatement.setInt(1,id_persona);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM personas WHERE id_persona=?;");
            preparedStatement.setInt(1,id_persona);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medicos WHERE id_medico=?;");
            preparedStatement.setInt(1,id_persona);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public Doctor getDoctorById(int id_persona) {
        Doctor doctor=new Doctor();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM medicos AS m INNER JOIN personas AS p ON m.id_medico=p.id_persona INNER JOIN usuario AS u ON u.id_persona=p.id_persona WHERE p.id_persona=?;");
            preparedStatement.setInt(1,id_persona);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                doctor.setId_persona(rs.getInt("id_persona"));
                doctor.setNombre(rs.getString("nombre"));
                doctor.setApellidos(rs.getString("apellidos"));
                doctor.setFecha_nac(rs.getString("fecha_nac"));
                doctor.setTelefono(rs.getString("tel"));
                doctor.setCorreo(rs.getString("correo"));
                doctor.setDomicilio(rs.getString("domicilio"));
                doctor.setEspecialidad(rs.getString("especialidad"));
                doctor.setUser(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return doctor;
    }
    //---------------------------------Doctores Hospital-------------------------------------------------- 
    
    //---------------------------------Diagnosticos Del Hospital------------------------------------------
    public List<Diagnosticos> getAlldiagnosticos() {
        List<Diagnosticos> diagnosticos = new ArrayList<Diagnosticos>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from diagnostico");
            while (rs.next()) {
            Diagnosticos diagnostico=new Diagnosticos();
            diagnostico.setFolio(rs.getInt("folio"));
            diagnostico.setNumero_cama(rs.getInt("numero_cama"));
            diagnostico.setMedico(rs.getString("medico"));
            diagnostico.setDiagnostico(rs.getString("diagnostico"));
            diagnostico.setTratamiento(rs.getString("tratamiento"));
            diagnosticos.add(diagnostico);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
            return diagnosticos;
        }
   
    public void checkdiagnostico(Diagnosticos diagnostico){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT folio FROM diagnostico WHERE folio = ?;");
            ps.setInt(1, diagnostico.getFolio());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                updatediagnostico(diagnostico);
            } else {
                adddiagnostico(diagnostico);
            }
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
        }
    }
      
    public void updatediagnostico(Diagnosticos diagnostico) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE diagnostico SET numero_cama=?, medico=?, diagnostico=?, tratamiento=? WHERE folio = ?;");
            preparedStatement.setInt(1,diagnostico.getNumero_cama());
            preparedStatement.setString(2,diagnostico.getMedico());
            preparedStatement.setString(3,diagnostico.getDiagnostico());
            preparedStatement.setString(4,diagnostico.getTratamiento());
            preparedStatement.setInt(5,diagnostico.getFolio());
               
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void adddiagnostico(Diagnosticos diagnostico) {
        try {    
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO diagnostico(folio,numero_cama,medico,diagnostico,tratamiento) VALUES (?,?,?,?,?);");
        preparedStatement.setInt(1,diagnostico.getFolio());
        preparedStatement.setInt(2,diagnostico.getNumero_cama());
        preparedStatement.setString(3,diagnostico.getMedico());
        preparedStatement.setString(4,diagnostico.getDiagnostico());
        preparedStatement.setString(5,diagnostico.getTratamiento());
        
        preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
        e.printStackTrace();
        }  
    }
       
    public void deletediagnostico(int folio) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM diagnostico WHERE folio=?;");
            preparedStatement.setInt(1,folio);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public Diagnosticos getdiagnosticoById(int folio) {
        Diagnosticos diagnostico =new Diagnosticos();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM diagnostico WHERE folio= ?;");
            preparedStatement.setInt(1,folio);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                diagnostico.setFolio(rs.getInt("folio"));
                diagnostico.setNumero_cama(rs.getInt("numero_cama"));
                diagnostico.setMedico(rs.getString("medico"));
                diagnostico.setDiagnostico(rs.getString("diagnostico"));
                diagnostico.setTratamiento(rs.getString("tratamiento"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
       return diagnostico;
    }
    public List<Diagnosticos> getAllfolios() {
        List<Diagnosticos> diagnosticos= new ArrayList<Diagnosticos>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM paciente";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Diagnosticos diagnostico=new Diagnosticos();
                diagnostico.setFolio(rs.getInt("folio"));
                diagnosticos.add(diagnostico);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return diagnosticos;
    }
        public List<Diagnosticos> getAlllistcamas() {
        List<Diagnosticos> diagnosticos= new ArrayList<Diagnosticos>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM camas";
            pstm = connection.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Diagnosticos diagnostico=new Diagnosticos();
                diagnostico.setNumero_cama(rs.getInt("numero_cama"));
                diagnosticos.add(diagnostico);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return diagnosticos;
    }
    /**/
    //---------------------------------Plantas Hospital--------------------------------------------------   
    
}
