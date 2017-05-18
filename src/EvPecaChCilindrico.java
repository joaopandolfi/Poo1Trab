public class EvPecaChCilindrico extends Evento{
	private Controlador controlador;
	
	public EvPecaChCilindrico(float time, Controlador controlador){
		super(time);
		this.controlador = controlador;
	}
	
	public void processa(){
		controlador.createRolamento(time,this, new Cilindrico(time));
	}
}
