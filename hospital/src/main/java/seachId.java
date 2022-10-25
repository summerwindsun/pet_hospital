import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "seachId", value = "/seachId")
public class seachId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------------------------------------------");
        String id=request.getParameter("Id");
        System.out.println(id);
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

            String sqlsear="select * from pet where id="+id;
            print(out, title, docType, statement, sqlsear);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void print(PrintWriter out, String title, String docType, Statement statement, String sqlsear) throws SQLException {
        ResultSet rs=statement.executeQuery(sqlsear);
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title><style type='text/css'>" +
                "body{text-align:center;border；1px solid green}"+"</style>"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">"+
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n"+
                "<table text-align=\"center\";border=\"1px solid green\">"+
                "<tr><th>   id</th><th>   name</th><th>   age</th><th>   visitime</th><th>   visistatus</th></tr>");

        while(rs.next()){

            int Id  = rs.getInt("id");
            String name = rs.getString("name");
            String age = rs.getString("age");
            String visitime = rs.getString("visitime");
            String visistatus = rs.getString("visitstatus");

            String line="<tr><td>"+Id+"</td><td>"+name+"</td><td>"+age+"</td><td>"+visitime+"</td><td>"+visistatus+"</td></tr>\n";
            out.println(line);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
