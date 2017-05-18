public class EvMaqQuebrada extends Evento{
	private Maquina machine;
	public EvMaqQuebrada(float time,Maquina machine){
		super(time);
		this.machine = machine;
	}
	
	public void processa(){
		if(!machine.isBroked()){//se a maquina já não estiver quebrada
			//informo a maquina que ela quebrou (quebro a maquina)
			machine.setBraked();
			machine.setTimeBroken(time);
		}
	}
}
