import java.util.Random;


public class Rolamento {
	//Maquinas
	protected Fila torno;
	protected Fila fresa;
	protected Fila mandril;
	
	//controle
	protected int prioridade;
	protected float tempoProcessamento; //tempo de processamento corrente
	protected char[] sequencia; //sequencia de maquinas a serem visitadas
	protected int tamSeq; //tamanho da sequencia
	protected int posSeq; //posição atual da sequencia
	protected boolean pronto; //informa se o rolamento está pronto
	protected float mediaChegada; //guarda a media de chegada da maquina
	
	//tempo de estadia
	protected float tempoTorno;
	protected float tempoFresa;
	protected float tempoMandril;
	protected float timeInit;
	protected float timeFin;
	
	public Rolamento(float timeInit){
		this.prioridade = 0;
		this.tempoProcessamento = 0;
		this.posSeq = 0;
		this.tamSeq = 0;
		this.timeFin = 0;
		this.timeInit = timeInit;
		this.pronto = false;
	}
	
	public void initializeQeue(Fila torno, Fila fresa, Fila mandril){
		this.torno = torno;
		this.fresa = fresa;
		this.mandril = mandril;		
	}
	
	//vai para proxima maquina
	public void goToNext(float time){
		if((posSeq >= tamSeq) || pronto){
			pronto = true;
			timeFin = time;
			return;
		}
		else if(sequencia[posSeq] == 'T'){
			tempoProcessamento = tempoTorno;
			torno.insere(this,prioridade);
		}
		else if(sequencia[posSeq] == 'F'){
			tempoProcessamento = tempoFresa;
			fresa.insere(this,prioridade);
		}
		else if(sequencia[posSeq] == 'M'){
			tempoProcessamento = tempoMandril;
			mandril.insere(this,prioridade);
		}
		//incremento posição
		posSeq++;
	}

	//recupera tempo de processamento
	public float getDeltaTime(){
		return tempoProcessamento;
	}
	
	//recupera prioridade
	public int getPriority(){
		return prioridade;
	}
	
	//recupera a media de chegada
	public float getAvg(){
		return mediaChegada;
	}
	
	//informa se a peça está pronta
	public boolean isDone(){
		return pronto;
	}
	
	//recupero o tempo total de processamento
	public float totalTime(){
		return timeFin - timeInit;
	}
	
	//informa ao controlador que ela está pronta
	public void finished(Controlador controle){
		//está vazia pois tem que ser alterada pelo filho para dizer ser ela mesma
	}
	
	// ==== funções internas dos Rolamentos ====
	
	//seta sequencia de maquinas a serem passadas
	protected void makeSequence(String seq){
		tamSeq = seq.length();
		sequencia = new char[tamSeq];
		for(int i = 0; i<tamSeq ; i++)
			sequencia[i] = seq.charAt(i);
	}
	
	//calcula os respectivos tempos de processamento
	protected void setTimeMandril(float tMandril){
		this.tempoMandril = 2.0f*tMandril*new Random().nextFloat();
	}
	
	protected void setTimeFresa(float tFresa){
		this.tempoFresa = 2.0f*tFresa*new Random().nextFloat();		
	}
	
	protected void setTimeTorno(float tTorno){
		this.tempoTorno = 2.0f*tTorno*new Random().nextFloat();		
	}
	
}
