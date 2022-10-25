import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet{

	 // JDBC ????????????? URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/pethospital?&useSSL=false&serverTimezone=UTC";
    
    // ?????????????????????????????????
    static final String USER = "root";
    static final String PASS = "886942";
    String sqlStr = "select ID,Name,Department from person";
    String sqlInsert = "insert into person " +"VALUES (79, 'Mike', 'R&D')";
    String sqlUpdate = "update pet " +"set Department = 'QA' where ID in (1,3)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        // ???????????????
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet MySQL Connection";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + title + "</h1>\n");
        try{
        	Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println( "???????????!" );

            Connection con = DriverManager.getConnection(DB_URL, USER,PASS );

            System.out.println( "???????????!" );

            Statement st = con.createStatement();
            System.out.println( "????Statement???!" );
            System.out.println( "??????????" );
          //???????
            //st.executeUpdate(sqlInsert);
            //System.out.println("???????????");
            
            //????????
            st.executeUpdate(sqlUpdate);
            System.out.println("??????????");

            //???????
            ResultSet rs = st.executeQuery( sqlStr );
            System.out.println( "?????????????!" );
            System.out.println( "----------------!" );

           
            while(rs.next()){
                // ?????¦Ì???
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String age = rs.getString("age");
                String visitime = rs.getString("visitime");
                String visistatus = rs.getString("age");
                // ???????
                out.println("ID: " + id);
                out.println(", name: " + name);
                out.println(", age " +age );
                out.println(", visitime " +visitime );
                out.println(", visitstatus " +visistatus );
                out.println("<br />");
            }
            out.println("</body></html>");

            // ??????
         
        } catch(SQLException se) {
            // ???? JDBC ????
            se.printStackTrace();
        } catch(Exception e) {
            // ???? Class.forName ????
            e.printStackTrace();
        }finally{
            // ?????????????????
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
       
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}