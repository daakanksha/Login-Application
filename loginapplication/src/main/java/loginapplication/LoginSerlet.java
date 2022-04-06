package loginapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginSerlet
 */
@WebServlet("/login")
public class LoginSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String p=request.getParameter("pws");
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		boolean isFound=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/fisglobal";
			String username="root";
			String pass="rps@12345";
			
			Connection cn=DriverManager.getConnection(url, username, pass);
			Statement s=cn.createStatement();
			String query="Select * From Users";
			ResultSet result=s.executeQuery(query);
			while(result.next()) {
				String a=result.getString(1);
				String b=result.getString(2);
				if(a.equals(id)&&b.equals(p)) {
					isFound=true;
					break;
				}
			}
			if(isFound)
				out.println("<b>Your successfull login </b>");
			else
				out.println("<b>Your failed to login </b>");
			result.close();
			s.close();
			cn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		out.println("</html></body>");
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
