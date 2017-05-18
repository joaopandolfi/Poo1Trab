
public class Nodo {
	private Nodo prox;
	private Object value;
	private float priority;
	
	Nodo(Object value, float priority){
		this.value = value;
		this.priority = priority;
		this.prox = null;
	}
	
	public void setProx(Nodo prox){
		this.prox = prox;
	}
	
	public Nodo getProx(){
		return prox;
	}
	
	public Object getVal(){
		return value;
	}
	
	public float getPriority(){
		return priority;
	}
}
