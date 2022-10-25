import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "searname", value = "/searname")
public class searname extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------------------------------------------");
        String name=request.getParameter("Name");
        System.out.println(name);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet MySQL Connection";
        String docType = "<!DOCTYPE html>\n";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/pethospital";

            String user="root";
            String passwd="886942";
            Connection con= DriverManager.getConnection(url,user,passwd);
            Statement statement= con.createStatement();

            String sqlsear="select * from pet where name='"+name+"'";
            seachId.print(out, title, docType, statement, sqlsear);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
