package av2.questao9;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;


public class Produto     {

	private int id;
	private String nome;
	private String descricao;
	private Double desconto;
	private Date dataInicio;

	public Produto(int id, String nome, String descricao, Double desconto, Date dataInicio) {
		super();
		this.id  = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.dataInicio = dataInicio;
	}
	public static void inputProduto (ProdutoDAO dao, int id) throws SQLException  {

		InputControl  ic = new InputControl ();
		String nome, desc, data;
		double desconto= 0.0;
		Date dataFormatada = new Date(0);

		if (id < 0) {
			autoGenerate(dao);
			System.out.println("Crie um produto:\n");
			
			boolean validação = false;
			while (!validação) { 
				id = ic.inputID();
				if(dao.validarId(id)) {
					System.out.println("ID: "+id+" já exsite, tente outro.");
				}else {
					validação = true;
				}
			}
		}

		nome = ic.inputNome();
		desc = ic.inputDesc();
		desconto = ic.inputDesconto();
		dataFormatada = ic.inputData();

		Produto produto = new Produto(id, nome,desc,desconto, dataFormatada);
		dao.salvar(produto);
	}

	public static void autoGenerate(ProdutoDAO dao) throws SQLException {

		System.out.println("Gerando três produtos automaticamente...");
		Random random = new Random();

		for (int i = 0; i<3; i++) {
			int randomId = random.nextInt(1, Integer.MAX_VALUE);
			double randomDesconto= random.nextDouble(100.00);
			if(!dao.validarId(randomId)) {
				dao.salvar(new Produto(
						randomId,
						"nomeAuto",
						"descAuto",
						randomDesconto,
						new Date(0)));
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
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

}
