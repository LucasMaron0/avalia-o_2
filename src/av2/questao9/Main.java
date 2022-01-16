package av2.questao9;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import connections.ConnectionFactory;

public class Main {

	public static void main(String[] args) throws SQLException  {

		ProdutoDAO dao = new ProdutoDAO();		
		mainMenu(dao);
	}

	public static void mainMenu (ProdutoDAO dao) throws SQLException {



		while (true) {
			System.out.println("\nSISTEMA");
			System.out.println("\nDigite a opção desejada:");
			System.out.println("1 - para INSERIR nova oferta");
			System.out.println("2 - para ATUALIZAR uma oferta");
			System.out.println("3 - para DELETAR uma oferta");
			System.out.println("4 - para listar as palavras que contem ?");
			System.out.println("0 - para SAIR");

			Scanner input = new Scanner(System.in);
			int escolha =  Integer.valueOf(input.nextLine());

			if (escolha == 1) {
				Produto.inputProduto(dao, -1);					
			}

			if (escolha ==2) {	
				dao.atualizar(dao);				
			} 

			if(escolha ==3) {
				dao.deletar();				
			}

			if(escolha == 4) {
				dao.listar();				
			}

			if (escolha == 0) {
				dao.closeConnection();
				System.out.println("SAINDO DO SISTEMA...");
				System. exit(0);
			}
		}
	}
}
