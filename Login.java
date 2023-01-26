package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/loger")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_page", "root", "33");
			String sql = "Select password from accounts where user_name = ? OR email = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, user);
			p.setString(2, user);
			PrintWriter out = response.getWriter();
			ResultSet rs = p.executeQuery();
			if (rs.next()) {			
				String pass = rs.getString(1);
				if (pass.equals(password)) {
//					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//					rd.forward(request, response);
					out.print("Working");
				} else {
					out.print("Invalid password");
				}
			} else {
				out.print("invalid username or email");
			}
		} catch(Exception e) {
			PrintWriter out = response.getWriter();
//			out.print("mit");
			out.print(e);
		}
	}
}
