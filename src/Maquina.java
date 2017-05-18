
public class Maquina {
	//objetos
	protected Rolamento current;
	protected Fila espera;
	protected Controlador controle;
	
	//controle
	protected boolean ocupada; //informa se a maquina esta ocupada ou nao
	
	//controle tempo
	protected float timeIn;	//tempo de entrada da pe�a
	protected float timeOut; //tempo de saida da pe�a
	protected float currentTime; //tempo atual
	
	//quebra
	protected float timeBroke; //tempo no qual a maquina quebrou
	protected boolean broken; //informa se a maquina quebrou ou nao
	protected float freqQuebra; //frequencia de quebra do equipamento
	
	//construtor da classe
	public Maquina(Controlador controle,Fila fila){
		timeBroke = 0;
		broken = false;
		ocupada = false;
		timeBroke = 0;
		this.controle = controle;
		this.espera = fila;
		
	}
	
	//atualiza a maquina
	public void Update(float deltaTime){
		currentTime = deltaTime;
		if(broken){ //maquina quebrada
		}
		else if(ocupada){	//maquina ocupada
		}
		else{
			//busca proximo item da fila
			nextItem();
		}
	}
	
	//busca a proxima pe�a da fila
	private void nextItem(){
		//Recupera o primeiro rolamento da fila
		current = (Rolamento) espera.remove();
		if(current != null){
			//se tiver alguem na fila (seta maquina ocupada, calcula tempo de proc e lan�a evento de pcConcluida)
			ocupada = true;
			timeIn = currentTime;
			timeOut = currentTime + current.getDeltaTime();
			controle.insertEvent(new EvPecaConcluida(timeOut,this));
		}
	}
	
	//finaliza pe�a
	public void finishedItem(){
		//se n�o existe maquina
		if(current == null)
			return;
		//faz a pe�a ir para outra maquina
		current.goToNext(currentTime);
		//verifica se o rolamento est� pronto e informa ao controlador
		if(current.isDone())
			current.finished(controle);
			//controle.finishedItem(current);
		ocupada = false;
	}

	// ====== QUEBRA DA MAQUINA =======
	
	//seta frequencia de quebra da maquina
	public void setFreqQuebra(float freqQuebra){
		this.freqQuebra = freqQuebra;
	}
	
	//retorna frequencia de quebra da maquina
	public float getFreqQuebra(){
		return this.freqQuebra;
	}
	
	//verifica se a maquina est� quebrada
	public boolean isBroked(){
		return this.broken;
	}
	
	//informa que a maquina quebrou
	public void setBraked(){
		this.broken = true;
		//informo ao controlador que a maquina quebrou
		this.controle.thisMachineBraked(this);
	}
	
	//informa que a maquina foi consertada
	public void unbroked(float time){
		this.broken = false;
		//crio novo evento de finaliza��o da pe�a ajustando o tempo que ficou parado [tempoFalta]+[tempoAtual]
		timeOut =  (timeOut - timeBroke) + time;
		controle.insertEvent(new EvPecaConcluida(timeOut,this));
	}
	
	//seta tempo de quebra da maquina
	public void setTimeBroken(float timeBroke){
		this.timeBroke = timeBroke;
	}
	
	//retorna o tempo que a maquina quebrou
	public float getTimeBroken(){
		return this.timeBroke;
	}
}
