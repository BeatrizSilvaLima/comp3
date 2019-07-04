package dataMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dados.Usuario;

public class UsuarioDAO {
	
	private Connection con;
	
	public UsuarioDAO() {
		this.con = new Conexao().getConnection();
		System.out.println("usuarioDao Conectado");
	}
	
	public void criarTabela() throws SQLException, ClassNotFoundException{
        
        String sql = "create table USUARIO( cpf varchar(11),"
                + "nome varchar(255) not null,"
                + "senha varchar(6) not null"
                + ",primary key(cpf))";
        
        this.con = new Conexao().getConnection();
        try(Statement sttm = con.createStatement()){
            
            int r = sttm.executeUpdate(sql);
            
            if(r == 0){
                System.out.println("Tabela criada");
            }
            
            else{
                System.err.println("Tabela nao criada");
            }
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        
        finally {
        	con.close();
        	System.out.println("Conexao Fechada");
        }
	}
        
        
        public void inserirTabela(Usuario usuario) throws SQLException, ClassNotFoundException{

            String sql = "insert into USUARIO values(?,?,?)";
                        
            String cpf = usuario.getCpf();
            String nome = usuario.getNome();
            String senha = usuario.getSenha();
            this.con = new Conexao().getConnection();
            
            try(PreparedStatement sttm = con.prepareStatement(sql)){
                            
                sttm.setString(1, cpf);
                sttm.setString(2, nome);
                sttm.setString(3, senha); 
                            
                int rs  = sttm.executeUpdate();
                          if (rs == 1) {
                                System.out.println("Objeto inserido");
                            }
                            if (rs == 0){
                                System.out.println("Objeto nao inserido");
                            }
                            
              } 
            catch(SQLException e){
                            System.err.println(e);
            }
                            
             finally {
					con.close();
					System.out.println("COnexao fechada");
			}
                                  
        	
    }
        
        public void ApagarTabela() throws SQLException{
        	
        	String sql = "drop table USUARIO";
            
                        
        	try(Statement sttm = con.createStatement()){
                
                int r = sttm.executeUpdate(sql);
                
                if(r == 0){
                    System.out.println("Tabela apagada");
                }
                
                else{
                    System.err.println("Tabela nao criada");
                }
            }
            catch(SQLException ex){
                throw new RuntimeException(ex);
            }
                            
             finally {
					con.close();
					System.out.println("Conexao fechada");
			}
        }
}
