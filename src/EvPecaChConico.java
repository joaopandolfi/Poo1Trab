public class EvPecaChConico extends Evento {
	private Controlador controlador;
	
	public EvPecaChConico(float time, Controlador controlador){
		super(time);
		this.controlador = controlador;
	}
	
	public void processa(){
		controlador.createRolamento(time,this, new Conico(time));
	}
}
