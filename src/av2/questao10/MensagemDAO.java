package av2.questao10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import av2.questao9.Produto;

public class MensagemDAO {

	private Connection connection;

	public MensagemDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	public void salvar (Mensagem mensagem) throws SQLException {
		String sql = "INSERT INTO MENSAGENS (MENSAGEM, STATUS) VALUES (?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql)){


			pstm.setString(1, mensagem.getMensagem());
			pstm.setString(2, mensagem.getStatus());			
			pstm.execute();
			
			System.out.println("Mensagem Salva;");

		}catch (SQLException e) {
			System.out.println("Não foi possível salvar mensagem.");
			System.out.println(e.getMessage());
		}


	}




}
