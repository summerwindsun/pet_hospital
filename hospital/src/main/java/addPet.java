import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;

@WebServlet(name = "addPet", value = "/addPet")
public class addPet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String docType = "<!DOCTYPE html>\n";
        System.out.println("--------------------------------------------------");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/pethospital";

            String user="root";
            String passwd="886942";
            Connection con= DriverManager.getConnection(url,user,passwd);
            Statement statement= con.createStatement();
            int age= Integer.parseInt(String.valueOf(request.getParameter("Age")));
            String name= String.valueOf(request.getParameter("Name"));
            String id= String.valueOf(request.getParameter("Id"));
            String visitime= String.valueOf(request.getParameter("visitime"));
            String visistatus= String.valueOf(request.getParameter("visitstatus"));

            String sqladd="insert into pet values(?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(sqladd);
            pst.setString(1,name);
            pst.setString(2,id);
            pst.setInt(3, age);
            pst.setString(4,visitime);
            pst.setString(5,visistatus);
            int line=pst.executeUpdate();
            String result="";
            if(line>0){
                 result="添加成功";
            }else {
                 result="添加失败";
            }
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + "</title>"+
                    "</head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">"+result +"</h1>\n");
            con.close();

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
