public class Torno extends Maquina{
	public Torno(Controlador controlador,Fila fila){
		super(controlador,fila);
		//seto frequencia de quebra do equipamento
		setFreqQuebra(3f);
	}
}
