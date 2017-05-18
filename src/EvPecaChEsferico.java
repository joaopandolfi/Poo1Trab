public class EvPecaChEsferico extends Evento{
	private Controlador controlador;
	public EvPecaChEsferico(float time, Controlador controlador){
		super(time);
		this.controlador = controlador;
	}
	
	public void processa(){
		controlador.createRolamento(time,this, new Esferico(time));
	}
}
