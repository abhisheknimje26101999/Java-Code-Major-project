import java.sql.DriverManager;
import java.sql.Connection;


public class Connect
{
    
   public static Connection getConnection() throws Exception
   {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/major_project?autoReconnect=true&useSSL=false","root","password@123");  
        //here major_project is database name, root is username and password is last argument            
       return con; 
   }
}