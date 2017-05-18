
public class Cilindrico extends Rolamento{
	
	public Cilindrico(float timeInit){
		//passo os dados para o construtor da classe pai
		super(timeInit);
		//defino dados especificos do rolamento cilindrico
		prioridade = 1;
		mediaChegada = 21.5f;
		makeSequence("TFTM");
		setTimeTorno(0.8f);
		setTimeFresa(0.5f);
		setTimeMandril(1.2f);
	}
	
	public void finished(Controlador controle){
		controle.finishedItem(this);
	}
}
