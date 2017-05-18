
public class EvPecaConcluida extends Evento {
	private Maquina maquina;
	public EvPecaConcluida(float time, Maquina maquina){
		super(time);
		this.maquina = maquina;
	}
	
	public void processa(){
		//verifica se a maquina está quebrada
		if(!maquina.isBroked()){
			maquina.finishedItem();
		}
	}
}
