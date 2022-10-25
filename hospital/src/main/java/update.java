import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "update", value = "/update")
public class update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {


            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/pethospital";

            String user="root";
            String passwd="886942";
            Connection con= DriverManager.getConnection(url,user,passwd);
            Statement statement= con.createStatement();

            String id=request.getParameter("Id");
            String sqldelet="delete from pet where id="+id;
            statement.executeUpdate(sqldelet);

            int age= Integer.parseInt(String.valueOf(request.getParameter("age")));
            String name= String.valueOf(request.getParameter("Name"));
            String Id= String.valueOf(request.getParameter("Id"));
            String visitime= String.valueOf(request.getParameter("time"));
            String visistatus= String.valueOf(request.getParameter("state"));

            String sqladd="insert into pet values(?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(sqladd);
            pst.setString(1,name);
            pst.setString(2,Id);
            pst.setInt(3, age);
            pst.setString(4,visitime);
            pst.setString(5,visistatus);
            pst.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
