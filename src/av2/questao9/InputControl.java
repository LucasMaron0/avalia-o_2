package av2.questao9;

import java.sql.Date;
import java.util.Scanner;

public class InputControl {

	private Scanner input;


	public InputControl() {
		super();
		this.input = new Scanner(System.in);
	}


	public int inputID () {

		int id = 0;
		boolean validação = false;

		while(!validação) {
			try {
				System.out.println("\nID do produto: ");
				id= Integer.valueOf(input.nextLine());
				validação = true;
			} catch (NumberFormatException e) {
				System.out.println("Digite um número válido");
			}
		}
		return id;

	}

	public Double inputDesconto() {
		Double desconto = 0.0;
		boolean validaçãoDesconto = false;
		while (!validaçãoDesconto) {
			try {
				System.out.println("\nDesconto: ");
				desconto= Double.valueOf(input.nextLine());
				if (desconto >= 0 && desconto <= 100) {
					validaçãoDesconto= true;
				}else {
					System.out.println("digite um número válido(0-100): ");
				}

			} catch (NumberFormatException e) {
				System.out.println("digite um número válido: ");
			}
		}return desconto;
	}


	public Date inputData () {
		String data;
		Date dataFormatada = new Date(0);	
		boolean validaçãoData = false;
		while(!validaçãoData) {
			try{
				System.out.println("\nData (yyyy-MM-dd): ");
				data= input.nextLine();
				dataFormatada = java.sql.Date.valueOf(data);
				validaçãoData = true;
			}catch (IllegalArgumentException e) {
				System.out.println("DATA INVALIDA, DIGITE NOVAMENTE NO FORMATO CORRETO. ");
			}
		}
		return dataFormatada;
	}

	public String inputNome () {
		String nome = "";
		System.out.println("Nome do produto: ");
		nome= input.nextLine();
		return nome;
	}

	public String inputDesc() {
		String desc= "";
		System.out.println("\nDescrição do produto: ");
		desc= input.nextLine();
		return desc;
	}

	public int inputCodigoAlteracao() {
		int i=0;
		boolean validação = false;
		while(!validação) {
			System.out.println("Que campo deseja alterar: ");			
			try {
				System.out.println(" 1-nome/ 2-descrição/ 3-desconto/ 4-data");
				i= Integer.valueOf(input.nextLine());
				if (i>0 && i<5) {
					validação = true;
				}else {
					System.out.println("Digite um número válido\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Digite um número válido\n");
			}
		}
		return i;
	}
}
