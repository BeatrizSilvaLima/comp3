package controladores_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dados.Usuario;
import dataMapper.UsuarioDAO;
import dominio.enums.Status;
import dominio.UsuarioMT;



/**
 * Servlet implementation class LoginUsuarioServlet
 */
@WebServlet("/LoginUsuarioServlet")
public class LoginUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet1 at " + request.getContextPath() + "</h1>");
            out.println("<% nome %>");
            out.println("</body>");
            out.println("</html>");
            
        
           /* Usuario user = new Usuario();
            UsuarioDAO userdao = new UsuarioDAO();
            
            user.setCpf(cpf);
            user.setNome(nome);
            user.setSenha(senha);
            //userdao.criarTabela();
            userdao.inserirTabela(user);
            //userdao.ApagarTabela();*/
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");
		Status vd = null;
		vd = UsuarioMT.criarUsuario(cpf, nome, senha);
			//boolean hidden = true;
		response.setContentType("text/html");
			

	        System.out.println("doPost()");
	        request.setAttribute("nome", nome);
	        request.setAttribute("cpf", cpf);
	        request.setAttribute("senha", senha);
	 
	        /*try {
	            processRequest(request, response);
	        } catch (ClassNotFoundException | SQLException ex) {
	            System.out.println("Erro de execuçao");
	        }*/
	        
	        if(vd == Status.Ok)
			{
				//Usuario u = new Usuario(nome, cpf, senha);
				
				//UsuarioDAO d= new UsuarioDAO();
				
				
				request.setAttribute("nome", nome);
				request.setAttribute("cpf", cpf);
				request.setAttribute("senha", senha);
				
				/*try {
					processRequest(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

				Usuario user = new Usuario();
	            UsuarioDAO userdao = new UsuarioDAO();
	            
	            user.setCpf(cpf);
	            user.setNome(nome);
	            user.setSenha(senha);
	            //userdao.criarTabela();
	            try {
					userdao.inserirTabela(user);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuario.jsp");
				rd.forward(request, response);
				
			}
			
			if(vd == Status.CamposNaoPreenchidos) {	
				response.sendRedirect("UsuarioNaoInformado.jsp");

			}
			
			if(vd == Status.cpfInvalido)
			{
				
				response.sendRedirect("UsuarioCpfErrado.jsp");
			}
			
			if(vd == Status.senhaInvalida)
			{
				
				response.sendRedirect("UsuarioSenhaErrada.jsp");
			}
	
	}

}
