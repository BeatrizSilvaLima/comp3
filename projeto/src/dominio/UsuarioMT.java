package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dados.Usuario;
import dataMapper.Conexao;
import dominio.enums.Status;
import metodos_validacao.ValidarCPF;

public class UsuarioMT {
	
		private String nome;
		private String cpf;
		private String senha;
		
		
		public String getNome() {
			return nome;
		}

		public String getCpf() {
			return cpf;
		}

		public String getSenha() {
			return senha;
		}

		
		protected static boolean verificarSenha(String senha) {
			
			if (senha.length() == 6)
			{
				return true;
			}
			
			else {
				return false;
			}
		}
		
		protected static boolean VerificarCPF(String cpf) {
			
			return ValidarCPF.isCPF(cpf);
		}
		
		protected static boolean VerificarNome(String nome) {
			if (nome == "")
			{
				return false;
			}
			
			return true;
		}
		
		public static Status criarUsuario(String cpf, String nome, String senha){
			
			if (!VerificarNome(nome) || cpf ==""  || senha =="")
			{
				System.out.println("Campos obrigatorios nao preenchido!");
				return Status.CamposNaoPreenchidos;
				
			}
			
			
			if(!VerificarCPF(cpf))
			{
				System.out.println("Cpf invalido");
				return Status.cpfInvalido;
			}
			
			if(!verificarSenha(senha))
			{
				System.out.println("Senha invalida " + senha.length());
				
				return Status.senhaInvalida;
			}
			
			System.out.println("Usuario Criado");
			System.out.println(nome);
			System.out.println(cpf);
			System.out.println(senha);
			
			
			return Status.Ok;
			
			
		}
		
		public static ResultSet buscarUsuario(ResultSet rs, String cpf)
		{
			Connection con = new Conexao().getConnection();
			String sql = "SELECT * FROM USUARIO WHERE CPF ==" + cpf;
			
			try(Statement sttm = con.createStatement()){
	       
	        	rs = sttm.executeQuery(sql);
	        	while(rs.next()) {
	        		int id = rs.getInt(1);
	        		String nome = rs.getString(2);
	        		
	        		System.out.println("ID: "+id);
	        		System.out.println("Nome: "+nome);
	        		
	        		System.out.println("Lendo");
	        	}
	        }catch(SQLException e) {
	        	System.err.println(e);
	        }
			
			
			return rs;
		}
		
		public static void inserirUsuario(ResultSet rs, Usuario usuario) {
			
			Connection con = new Conexao().getConnection();
			
			String nome = usuario.getNome();
			String cpf = usuario.getCpf();
			String senha = usuario.getSenha();
			
			String sql = "insert into USUARIO values(?,?,?)";
			try(PreparedStatement sttm = con.prepareStatement(sql)){
	        	
	        	sttm.setString(1, cpf);
	        	sttm.setString(2, nome);
	        	sttm.setString(3, senha);
	        

	        	int res  = sttm.executeUpdate();
	        	if (res == 1) {
	        		System.out.println("Objeto inserido");
	        	}
	        	if (res == 0){
	        		System.out.println("Objeto nao inserido");
	        	}
	        	
			}
			catch(SQLException e){
	            System.err.println(e);
	       
			}
			
		}

}
