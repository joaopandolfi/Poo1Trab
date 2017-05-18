//evento quando a maquina acaba de ser consertada
public class EvMaqPronta extends Evento {
	private Mecanico mecanico;
	public EvMaqPronta(float time,Mecanico mecanico){
		super(time);
		this.mecanico = mecanico;
	}
	
	public void processa(){
		mecanico.finishedItem(time);
	}
}
