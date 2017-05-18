public class Mandril extends Maquina{
	public Mandril(Controlador controlador,Fila fila){
		super(controlador,fila);
		//seto a frequencia de quebra da maquina
		setFreqQuebra(1.0f);
	}
}
