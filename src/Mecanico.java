
public class Mecanico {
	protected boolean ocupado; //estado do mecanico
	
	//objetos
	protected Fila fila;
	protected Maquina current;
	protected Controlador controle;
	
	//tempo
	protected float currentTime;
	protected float deltaTime; 
	
	public Mecanico(Controlador controle, Fila fila){
		this.controle = controle;
		this.fila = fila;
		this.currentTime = 0;
		this.deltaTime = 20; //T que o mecanico demora pra construir
		this.ocupado = false;
	}
	
	//atualiza o mecanico
	public void Update(float time){
		currentTime = time;
		if(ocupado){}
		else {
			nextItem();
		}
	}
	
	//finaliza peça corrente
	public void finishedItem(float time){
		current.unbroked(time);
		ocupado = false;
	}
	
	//busca a proxima peça da fila
	private void nextItem(){
		//Recupera o primeiro rolamento da fila
		current = (Maquina) fila.remove();
		if(current != null){
			//se tiver alguem na fila (seta maquina ocupada, calcula tempo de proc e lança evento de pcConcluida)
			ocupado = true;
			controle.insertEvent(new EvMaqPronta((currentTime+deltaTime),this));
		}
	}
	
}
