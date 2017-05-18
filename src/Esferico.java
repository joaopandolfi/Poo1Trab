import java.util.Random;

public class Esferico extends Rolamento{
	public Esferico(float timeInit){
		//passo os dados para o construtor da classe pai
		super(timeInit);
		prioridade = 3;
		mediaChegada = 8.0f;
		
		//Crio de aço ou titanio
		int prob = new Random().nextInt(10);
		if(prob<9)
			makeAco();
		else
			makeTitanio();
	}
	
	private void makeAco(){
		//defino dados especificos do rolamento Esferico de aço
		makeSequence("FMT");
		setTimeTorno(1.0f);
		setTimeFresa(0.5f);
		setTimeMandril(1.4f);
		
	}
	
	private void makeTitanio(){
		//defino dados especificos do rolamento Esferico de titanio
		makeSequence("FMTFT");
		setTimeTorno(1.6f);
		setTimeFresa(0.6f);
		setTimeMandril(1.5f);
		
	}
	
	public void finished(Controlador controle){
		controle.finishedItem(this);
	}
}
