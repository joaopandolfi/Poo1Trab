public class Fresa extends Maquina{
	public Fresa(Controlador controlador,Fila fila){
		super(controlador,fila);
		//seto frequencia de quebra da maquina
		setFreqQuebra(0.5f);
	}
}
