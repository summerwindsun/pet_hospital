import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Delete", value = "/Delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id=request.getParameter("Id");
        String docType = "<!DOCTYPE html>\n";
        try {



            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/pethospital";

            String user="root";
            String passwd="886942";
            Connection con= DriverManager.getConnection(url,user,passwd);
            Statement statement= con.createStatement();


            String sqldelet="delete from pet where id="+id;
            System.out.println(id);
            System.out.println(sqldelet);
            int res=statement.executeUpdate(sqldelet);

            String result="";
            if (res>0){
                result="删除成功";
            }else {
                result="删除失败";
            }
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + "</title>"+
                    "</head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">"+result +"</h1>\n");
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
