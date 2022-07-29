package br.com.academy.Flights;

public class Flights {
	

	private String destino;
	private String reservaVoo;

	
	
	

	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
	// Criar metodo cancelar 
	// Override remove - sujestão
	
	
	public String getReservaVoo() {
		return reservaVoo;
	}
	public void setReservaVoo(String reservaVoo) {
		this.reservaVoo = reservaVoo;
	}

	
	
@Override
public String toString() {
	
	return "Destino para " + destino; 
}



	
	
}

	
	      
