public class Fila {
	
	private Nodo ini;
	private Nodo fim;
	private int mult; // fator multiplicador para correção da prioridade
	
	Fila(int prior){//0 - prioridade crescente # 1 -  prioridade decrescente
		ini = null;
		fim = null;
		//verifico tipo de prioridade
		if(prior == 1) //decrescente
			mult = -1;
		else //crescente
			mult = 1;
	}
	
	public void insere(Object val, float priority){
		Nodo novo = new Nodo(val,priority);
		//se a lista estiver vazia
		if(ini == null){
			ini = fim = novo;
		}
		else if(ini == fim){//só tem um elemento
			if(priority*mult < ini.getPriority()*mult){ //se a prioridade for maior
					ini.setProx(novo); //atualizo o proximo para o novo
					ini = novo; //seto o novo como inicio
			}
			else{//se a prioridade for menor
				novo.setProx(fim);  //atualizo o prox do novo com o fim
				fim = novo; // seto o novo como ultimo
			}
		}
		else{//N elementos
			Nodo aux1;
			Nodo aux2;
			aux1 = fim.getProx();
			aux2 = fim;
			while(aux1 != null){ //percorro a lista
				if(priority*mult > aux1.getPriority()*mult){ //se a prioridade do da frente for mais relevante
					if(priority*mult < aux2.getPriority()*mult){ //e o de traz menos relevante
						aux2.setProx(novo);
						novo.setProx(aux1);
						return; //nodo inserido, para função
					}
					else{//o de traz mais relevante, quer dizer que está no fim da fila
						novo.setProx(aux2);
						fim = novo;
						return;
					}
				}
				// caminho na lista
				aux2 = aux1;
				aux1 = aux1.getProx();
			}
			//se chegou aqui quer dizer que o elemento pode ser o primeiro da fila
			if(priority*mult < ini.getPriority()*mult){ //se do atual é mais relevante que o primeiro
				ini.setProx(novo);
				ini = novo;
			}
			return;
		}
		
	}
	
	public Object remove(){
		Nodo aux1;
		Nodo aux2;
		if(ini == null) //vazia
			return null;
		if(ini == fim){ //somente 1 elemento
			aux1 = ini;
			fim = ini = null;
			return aux1.getVal();
		}
		// N elementos
		aux1 = fim.getProx();
		aux2 = fim;
		//percorro a fila
		while(aux1.getProx() != null){
			aux2 = aux1;
			aux1 = aux1.getProx();
		}
		//o segundo da fila vira o primeiro 
		aux2.setProx(null);
		ini = aux2;
		//retorno o valor do primeiro
		return aux1.getVal();
	}
	
	public void imprime(){
		Nodo aux;
		aux = fim;
		while(aux != null){
			System.out.println(aux.getPriority());
			aux = aux.getProx();
		}
	}
}
