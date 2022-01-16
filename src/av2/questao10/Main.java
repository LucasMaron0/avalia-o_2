package av2.questao10;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connections.ConnectionFactory;

public class Main {

	public static void main(String[] args) throws SQLException {	

		String s ="";
		Scanner input = new Scanner (System.in);
		System.out.println("Qual a sua mensagem?");
		s = input.nextLine();
		input.close();

		Mensagem msg = new Mensagem(s);
		System.out.println("Status da mensagem:" + msg.getStatus());

		try(Connection connection = new ConnectionFactory().getConnection()){
			MensagemDAO dao = new MensagemDAO(connection);
			dao.salvar(msg);
			connection.close();
		}catch (SQLException e) {
			System.out.println("falha: " + e.getMessage());
		}
	}
}
