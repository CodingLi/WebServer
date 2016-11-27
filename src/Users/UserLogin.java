package Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import utils.LoginService;

public class UserLogin extends HttpServlet {
	
	private LoginService loginService = new LoginService();

	/**
	 * Constructor of the object.
	 */
	public UserLogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��Ҫ����sqlע��,�ڹ������м��
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String token = null;
		JSONObject jsonOut = new JSONObject();
		try {
			token = loginService.login(username, password);
			if(token != null){
				jsonOut.put("flag", "ok");
				jsonOut.put("username", username);
				jsonOut.put("token", token);
			}else{
				jsonOut.put("flag", "no");
				jsonOut.put("username", username);
				jsonOut.put("token", "");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonOut.put("no", "error");
		}
		out.println(jsonOut.toString());
		
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
