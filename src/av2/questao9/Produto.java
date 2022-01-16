package av2.questao9;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Produto     {

	private int id;
	private String nome;
	private String descricao;
	private String desconto;
	private Date dataInicio;



	public Produto(int id, String nome, String descricao, String desconto, Date dataInicio) {
		super();
		this.id  = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.dataInicio = dataInicio;
	}
	public static void inputProduto (ProdutoDAO dao, int id) throws SQLException  {

		Scanner input = new Scanner(System.in);
		String nome, desc, desconto,data;
		Date dataFormatada = new Date(0);

		if (id < 0) {
			autoGenerate(dao);

			System.out.println("Crie um produto:\n");

			boolean validação = false;

			while (!validação) { 
				System.out.println("\nID do produto: ");
				id= Integer.valueOf(input.nextLine());
				if(dao.validarId(id)) {
					System.out.println("ID: "+id+" já exsite, tente outro.");
				}else {
					validação = true;
				}
			}
		}

		System.out.println("Nome do produto: ");
		nome= input.nextLine();

		System.out.println("\nDescrição do produto: ");
		desc= input.nextLine();

		System.out.println("\nDesconto: ");
		desconto= input.nextLine();

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
		
		Produto produto = new Produto(id, nome,desc,desconto, dataFormatada);
		dao.salvar(produto);

	}


	public static void autoGenerate(ProdutoDAO dao) throws SQLException {

		System.out.println("Gerando três produtos automaticamente...");
		Random random = new Random();
		int randomId;

		for (int i = 0; i<3; i++) {
			randomId = random.nextInt(1000);
			if(!dao.validarId(randomId)) {
				dao.salvar(new Produto(
						randomId,
						"nomeAuto",
						"descAuto",
						"descontoAuto",
						new Date(randomId)));
				System.out.println("Produto gerado automaticamente id: "+randomId);
			}else {				
				System.out.println("ID repetido na geração automática de produtos"+ "(" +"ID:" +randomId + "), crindo novamente...");
				i--;
			}
		}
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}




}
