package login.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		PrintWriter out = response.getWriter();
        String loggingemail = request.getParameter("email");
        String loggingpass = request.getParameter("password");
        String loggingrole = request.getParameter("role");
        
        UserDataBase db =  new UserDataBase(ConnectionPro.getConnection());
        User user = db.loggingUser(loggingemail, loggingpass, loggingrole);
        
        if(user!=null && user.getRole().equals("RH")){
            HttpSession session = request.getSession();
            session.setAttribute("logUser", user);
                response.sendRedirect("RH.jsp");
                
        }else if(user!=null && user.getRole().equals("Psychologue")){
            HttpSession session = request.getSession();
            session.setAttribute("logUser", user);
                response.sendRedirect("Psychologue.jsp");
                
        }else if(user!=null && user.getRole().equals("Utilisateur")){
            HttpSession session = request.getSession();
            session.setAttribute("logUser", user);
                response.sendRedirect("Utilisateur.jsp");
                
        }
        
        
        else{
			
			out.println("user not found");
        }

		
		
		
		doGet(request, response);
	}

}
