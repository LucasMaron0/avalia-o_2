package av2.questao9;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import av2.connections.ConnectionFactory;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO () throws SQLException {

		try {
			this.connection = new ConnectionFactory().getConnection();
			System.out.println("CONEXÃO ESTABELECIDA COM SUCESSO");
		} catch (Exception e) {
			System.out.println("erro de conexão: " + e.getMessage());
		}
	}



	public void salvar (Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (ID, NOME, DESCRIÇÃO, DESCONTO, DATAINICIO) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql)){

			pstm.setInt(1, produto.getId());
			pstm.setString(2, produto.getNome());
			pstm.setString(3, produto.getDescricao());
			pstm.setDouble(4, produto.getDesconto());
			pstm.setDate(5, produto.getDataInicio());

			pstm.execute();

		}catch (SQLException e) {
			System.out.println("Não foi possível salvar o produto.");
			System.out.println(e.getMessage());
		}

	}


	public void atualizar (ProdutoDAO dao) throws SQLException {

		InputControl ic = new InputControl();
		Scanner input = new Scanner(System.in);
		String sql = "";

		System.out.println(" ---ATUALIZAR PRODUTO--- ");
		int id  = ic.inputID();				

		if (!validarId(id)) {
			System.out.println("ID inexistente, adicione um Produto com este ID: ");
			Produto.inputProduto(dao, id);												
		}else {
			int campo  = ic.inputCodigoAlteracao();

			if(campo ==1 ) {
				sql = "UPDATE PRODUTO SET NOME = ? where id = ?";
			}
			if(campo ==2 ) {
				sql = "UPDATE PRODUTO SET DESCRIÇÃO = ? where id = ?";
			}
			if(campo ==3 ) {
				sql = "UPDATE PRODUTO SET DESCONTO = ? where id = ?";
			}
			if(campo ==4 ) {
				sql = "UPDATE PRODUTO SET DATAINICIO = ? where id = ?";
			}

			try (PreparedStatement pstm = connection.prepareStatement(sql)){		

				System.out.println("Novo valor: ");
				if(campo ==1 ) {					
					String nome = ic.inputNome();
					pstm.setString(1, nome );
				}
				if(campo ==2 ) {					
					String desc = ic.inputDesc();
					pstm.setString(1, desc );
				}
				if(campo ==3 ) {				
					Double desconto = ic.inputDesconto();
					pstm.setDouble(1, desconto );		
				}
				if(campo ==4 ) {
					Date data = ic.inputData();
					pstm.setDate(1, data );					
				}

				pstm.setInt(2, id);

				int verificarModificadas= pstm.executeUpdate();

				if (verificarModificadas == 0) {
					System.out.println("Não foi possível atualizar o produto: " + id);
				}else {
					System.out.println("Produto " + id + " atualizado");
				}

			}catch (SQLException e) {
				System.out.println("Não foi possível atualizar o produto.");
				System.out.println(e.getMessage());
			}

		}
	}

	public void deletar () throws SQLException {

		InputControl ic = new InputControl();
		String sql = "DELETE FROM PRODUTO WHERE id= ?";

		System.out.println("---DELETAR PRODUTO POR ID--- ");
		int id  = ic.inputID();

		try (PreparedStatement pstm = connection.prepareStatement(sql)){		
			pstm.setInt(1, id);		
			int verificarModificadas= pstm.executeUpdate();

			if (verificarModificadas == 0) {
				System.out.println("Não foi possível deletar o produto: " + id);
			}else {
				System.out.println("Produto " + id + " deletado com sucesso");
			}

		}catch (Exception e) {
			System.out.println("Não foi possível deletar.");
			System.out.println(e.getMessage());
		}
	}


	public void listar ()  throws SQLException {

		ArrayList<Produto> produtos = new ArrayList<Produto>();
		String s, sql;
		boolean filtro = true;
		Scanner input = new Scanner(System.in);

		System.out.println("O que está procurando? (digite palavra chave para realizar a busca(* para listar tudo)");
		s = input.nextLine();

		if(s.equals("*")){
			sql = "SELECT * FROM PRODUTO";
			filtro = false;
		}else {
			sql = "SELECT * FROM PRODUTO WHERE DESCRIÇÃO LIKE ?";
		}

		try (PreparedStatement pstm = connection.prepareStatement(sql)){

			if(filtro) {
				pstm.setString(1, "%" + s + "%");			
			}

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					Produto produto = new Produto (
							rst.getInt(1),
							rst.getString(2),
							rst.getString(3),
							rst.getDouble(4),
							rst.getDate(5));

					produtos.add(produto);
				}
			}catch (SQLException e) {
				System.out.println("Não foi possível listar(erro ao resgar result set).");
				System.out.println(e.getMessage());
			}
		}catch (SQLException e) {
			System.out.println("Falha na listagem.");
			System.out.println(e.getMessage());
		}

		for (Produto p: produtos) {
			System.out.println("\nID: "+ p.getId());
			System.out.println("NOME: "+ p.getNome());
			System.out.println("DESCRIÇÃO : "+ p.getDescricao());
			System.out.println("DESCONTO: "+ p.getDesconto() + "%");
			System.out.println("DATA: "+ p.getDataInicio());
		}
	}


	public boolean validarId (int id) throws SQLException {

		String sql = "SELECT * FROM PRODUTO WHERE ID = ?";		
		try (PreparedStatement pstm = connection.prepareStatement(sql)){		

			pstm.setInt(1, id);
			pstm.execute();

			try(ResultSet rst = pstm.getResultSet()){
				if (!rst.isBeforeFirst()) {
					return false;

				}else {
					return true;
				}
			}catch (SQLException e) {
				System.out.println("Não foi possível  validar ID (erro ao resgatar result set), resetando sistema e conexão.");
				System.out.println(e.getMessage());
				closeConnection();
				Main.main(null);
				return false;
			}
		}catch (SQLException e) {
			System.out.println("Não foi possível  validar ID, resetando sistema e conexão, tente novamente.");
			System.out.println(e.getMessage());
			closeConnection();			
			Main.main(null);
			return false;
		}
	}

	public void closeConnection () throws SQLException {
		this.connection.close();
		System.out.println("CONEXÃO FECHADA.\n");
	}
}



