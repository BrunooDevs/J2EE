package sistema.dao;
import java.sql.*;
import java.util.*;
import sistema.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UploadDAOAdmin {
     private Connection connection;
     //un metodo constructor no regresa ningun tipo de dato e inicializa el objeto 
    public UploadDAOAdmin()
    {
        connection=Database.getConnection();
        
    }//fin del constructor
    
    public void addURLfromImageName(int id_persona,String archivo) 
    {
     try{        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Images(id_persona,url) VALUES (?,?)");
        preparedStatement.setInt(1, id_persona);
        preparedStatement.setString(2, archivo);
        preparedStatement.executeUpdate();
     }catch(SQLException e){
        e.printStackTrace();
     }  
    }
    
    public String getImageById(int urlId) {        
        String urlImagen=null;//variable local temporal
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
    
    
      public void Deleteurl(int Id_persona) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Images WHERE id_persona=?;");
            preparedStatement.setInt(1, Id_persona);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
