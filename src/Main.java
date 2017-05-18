
public class Main {
	public static void main(String args[]){
		Controlador controle = new Controlador();
		//tratamento dos argumentos
		float totalTime = Float.parseFloat(args[0]);
		int quantFresa = Integer.parseInt(args[1]);
		int quantTorno = Integer.parseInt(args[2]);
		int quantMandril = Integer.parseInt(args[3]);
		
		//seto o tempo total de processamento
		controle.setTimeSimulate(totalTime);
		
		//crio as maquinas
		for(int i = 0; i<quantFresa;i++)
			controle.createMachine(new Fresa(controle, controle.getFFresa()));

		for(int i = 0; i<quantTorno;i++)
			controle.createMachine(new Torno(controle, controle.getFTorno()));
		
		for(int i = 0; i<quantMandril;i++)
			controle.createMachine(new Mandril(controle, controle.getFMandril()));
		
		//insiro os primeiros eventos
		controle.insertEvent(new EvPecaChCilindrico(0.0f, controle));
		controle.insertEvent(new EvPecaChConico(0.01f, controle));
		controle.insertEvent(new EvPecaChEsferico(0.02f, controle));
		
		//processo os eventos
		controle.listenEvents();
	}
}
