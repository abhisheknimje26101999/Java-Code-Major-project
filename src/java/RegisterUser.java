import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterUser extends HttpServlet {

   
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       PrintWriter out=response.getWriter();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("psw");
        String retype_password = request.getParameter("rpsw");
        String recovery_email = request.getParameter("remail");
        
        PreparedStatement preparedStatement = null;
        if (password.equals(retype_password)) 
        {
           try
            {
                Connection con = Connect.getConnection();
                String query = "insert into users(firstname,lastname,email,password,recovery_mail) values (?,?,?,?,?)"; //Insert user details into the table 'Registered_user'
                preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
                preparedStatement.setString(1, fname);
                preparedStatement.setString(2, lname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, recovery_email);               
                int i = preparedStatement.executeUpdate();
                con.close();
                if (i!=0)
                {
                    out.println("User Registered");
                }
                else
                {
                  out.println("User not Registered");
                }
            } 
            
            catch(java.sql.SQLIntegrityConstraintViolationException e)
            {
              out.println("User already exists");
            }
            catch (Exception e)
            {
                out.println(e);
            } 
        }
        else
        {
            out.println("Password doesn't match");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}