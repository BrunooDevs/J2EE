package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistema.model.Category;
import sistema.model.User;
import sistema.util.Database;

public class CategoryDAO {
    private Connection connection;
    public CategoryDAO()
    {
        connection=Database.getConnection(); 
    }
    
 
}
