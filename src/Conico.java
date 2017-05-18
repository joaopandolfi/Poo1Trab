public class Conico extends Rolamento{
	
	public Conico(float timeInit){
		//passo os dados para o construtor da classe pai
		super(timeInit);
		//defino dados especificos do rolamento cilindrico
		prioridade = 2;
		mediaChegada = 19.1f;
		makeSequence("TMT");
		setTimeTorno(1.8f);
		setTimeFresa(0f);
		setTimeMandril(2.1f);
	}
	
	public void finished(Controlador controle){
		controle.finishedItem(this);
	}
}
