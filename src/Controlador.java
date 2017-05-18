import java.util.Random;
import java.util.ArrayList;

public class Controlador {
	
	//TEMPO
	protected float currentTime;
	protected float timeSimulate;
	
	//lista de maquinas
	protected ArrayList<Maquina> maquinas;
	
	//Mecanico
	protected Mecanico mecanico;
	
	//filas das maquinas
	protected Fila fTorno;
	protected Fila fFresa;
	protected Fila fMandril;
	
	//fila do mecanico
	protected Fila fMecanico;
	
	//fila de eventos
	protected Fila fEvento;
	
	//tempos totais
	protected float tCilindrico;
	protected float tConico;
	protected float tEsferico;
	
	//quantidade de peças
	protected int qCilindrico;
	protected int qConico;
	protected int qEsferico;
	
	
	public Controlador(){
		//inicializo variaveis
		maquinas = new ArrayList<Maquina>();
		tCilindrico = 0;
		tConico = 0;
		tEsferico = 0;
		qCilindrico = 0;
		qConico = 0;
		qEsferico = 0;
		currentTime= 0;
		timeSimulate = 0;
		//inicializo as filas
		fMecanico = new Fila(0); //crescente (Tempo)
		fEvento = new Fila(0);	//crescente (Tempo)
		fTorno = new Fila(1); //drecrescente (Prioridade)
		fFresa = new Fila(1); //drecrescente (Prioridade)
		fMandril = new Fila(1); //drecrescente (Prioridade)
		//crio mecanico
		mecanico = new Mecanico(this, fMecanico);
	}
	
	public void endProcess(){
		//calculo a media dos tempos
		tEsferico = tEsferico / qEsferico;
		tConico = tConico / qConico;
		tCilindrico = tCilindrico / qCilindrico;
		//gero a mensgem na tela
		System.out.println("Tempo de processamento: "+timeSimulate);
		System.out.println("Rolamentos Esfericos-> Quant: "+qEsferico+" TMedio: "+tEsferico);
		System.out.println("Rolamentos Cilindricos-> Quant: "+qCilindrico+" TMedio: "+tCilindrico);
		System.out.println("Rolamentos Conico-> Quant: "+qConico+" TMedio: "+tConico);
	}
	
	//seta o tempo maximo da simulação
	public void setTimeSimulate(float timeSimulate){
		this.timeSimulate = timeSimulate;
	}
	
	public void thisMachineBraked(Maquina quebrou){
		//insere na fila de maquinas quebradas
		fMecanico.insere(quebrou, currentTime);
		
		//crio o evento de proxima quebra	
		float timeBroke = (currentTime + (2.0f * 10080.0f /quebrou.getFreqQuebra() ) * new Random().nextFloat()); //tempo atual+tempo chegada
		insertEvent(new EvMaqQuebrada(timeBroke,quebrou));
	}
	
	//===== Finalizações de rolamentos ======
	public void finishedItem(Conico fim){
		tConico = fim.totalTime() + tConico;
		qConico++;
	}

	public void finishedItem(Esferico fim){
		tEsferico = fim.totalTime() + tEsferico;
		qEsferico++;
	}
	
	public void finishedItem(Cilindrico fim){
		 tCilindrico = fim.totalTime() + tCilindrico;
		 qCilindrico++;
	}
	
	//===== Criação de rolamentos ======
	
	public void createRolamento(float time,Evento evento,Rolamento rolamento){
		rolamento.initializeQeue(fTorno, fFresa, fMandril);
		rolamento.goToNext(time);
		//Realoca evento de chegada
		evento.setTime(time + Gera_Exponencial(rolamento.getAvg())); //tempo atual + tempo para chegada
		insertEvent(evento);
	}
	
	//===== Criação de maquinas =====
	
	public void createMachine(Maquina maquina){
		//adiciona a maquina na lista
		maquinas.add(maquina);
		
		//crio evento de quebra da mesma
		float timeBroke = (currentTime + (2.0f * 10080.0f /maquina.getFreqQuebra() ) * new Random().nextFloat()); //tempo atual + tempo chegada
		insertEvent(new EvMaqQuebrada(timeBroke,maquina));
	}
	
	//===== Atualização das maquinas =====
	
	private void updateMachines(){
		//percorro e atualizo todas as maquinas
		for(Maquina cur: maquinas){
			cur.Update(currentTime);
		}
		//atualizo mecanico
		mecanico.Update(currentTime);
	}
	
	//chegada de peças
	private float Gera_Exponencial (float avg){
		float u=0; /* Gera randomicamente um numero entre 0 e 1 */
		do u = (new Random().nextFloat());
		while ((u==0) || (u==1));
		return (float) (-avg * Math.log(u));
	}
	
	
	//====== EVENTOS ======
	public void insertEvent(Evento e){
		//insere o evento na lista
		fEvento.insere(e, e.getTime());
	}
	
	public void listenEvents(){
		Evento ev;
		//loop pricipal
		while(true){
			//Navega para o proximo evento da lista
			ev = (Evento) fEvento.remove();
			if(ev != null){
				//currentTime recebe o tempo do evento atual 
				currentTime = ev.getTime();
				//verifica se o tempo estourou o tempo maximo (currentTime > timeSimulate)
				if(currentTime > timeSimulate){
					endProcess();
					return;
				}
				//processa o evento
				ev.processa();
				//atualizo as maquinas =>sempre que terminar de processar qualquer evento
				updateMachines();
			}
		}
	}
	
	//====== Filas ======
	
	public Fila getFMandril(){
		return fMandril;
	}
	
	public Fila getFTorno(){
		return fTorno;
	}
	
	public Fila getFFresa(){
		return fFresa;
	}
	
}
