package av2.questao10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mensagem {

	private String mensagem;
	private String status;



	public Mensagem(String mensagem) {
		super();
		this.mensagem = mensagem;

		String patternFeliz = "\\:\\-\\)";
		String patternTriste = "\\:\\-\\(";
			
		long contadorFeliz =  contarRepetições(patternFeliz);
		long contadorTriste = contarRepetições(patternTriste);
		
		if (contadorFeliz > contadorTriste){
			this.status= "satisfeito";
		}
		if (contadorFeliz < contadorTriste){
			this.status= "insatisfeito";
		}
		if (contadorFeliz == contadorTriste){
			this.status= "neutro";
		}		
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getStatus() {
		return status;
	}

	public long contarRepetições(String s) {

		Pattern pattern = Pattern.compile(s);
		Matcher matcher = pattern.matcher(this.mensagem);

		return matcher.results().count();
	}



}
