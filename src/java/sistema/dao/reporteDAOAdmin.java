package sistema.dao;
import java.sql.Connection;
import sistema.modelAdmin.*;
import sistema.util.*;
import java.sql.*;
public class reporteDAOAdmin {
    private Connection conn;
    public reporteDAOAdmin()
    {
        conn = Database.getConnection();  
    }//fin del constructor 
    
}
