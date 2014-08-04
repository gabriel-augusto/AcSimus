package linguagensEmensagens;

import java.util.List;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class Mensagem {
	public static String QUAL_LOCALIZACAO = "Qual Localizacao?";
	public static String QUAL_INDICE = "Qual índice?";
	public static final String NAO_COMPREENDIDA = "Mensagem nao compreendida.";
	
	
	public static ACLMessage prepararMensagem(int tipo, String linguagem, String conteudo, AID destino){
		ACLMessage mensagem = new ACLMessage(tipo);
		mensagem.setLanguage(linguagem);
		mensagem.addReceiver(destino);
		mensagem.setContent(conteudo);
		
		return mensagem;
	}
	
	public static ACLMessage prepararMensagem(int tipo, String linguagem, String conteudo, AID destino1, AID destino2){
		ACLMessage mensagem = new ACLMessage(tipo);
		mensagem.setLanguage(linguagem);
		mensagem.addReceiver(destino1);
		mensagem.addReceiver(destino2);
		mensagem.setContent(conteudo);
		
		return mensagem;
	}
	
	public static ACLMessage prepararMensagem(int tipo, String linguagem, String conteudo, List <AID> destinos){
		ACLMessage mensagem = new ACLMessage(tipo);
		mensagem.setLanguage(linguagem);
		for(AID destino : destinos)
			mensagem.addReceiver(destino);
		mensagem.setContent(conteudo);
		
		return mensagem;
	}
	
	public static ACLMessage getRespostaDeMensagemNaoCompreendida(AID destino) {
		ACLMessage resposta = new ACLMessage(
				ACLMessage.NOT_UNDERSTOOD);
		resposta.addReceiver(destino);
		resposta.setContent(Mensagem.NAO_COMPREENDIDA);
		return resposta;
	}
}
