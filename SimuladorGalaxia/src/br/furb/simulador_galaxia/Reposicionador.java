package br.furb.simulador_galaxia;

import java.util.ArrayList;

public class Reposicionador extends Thread {
	private BufferPontos buffer;
	private ArrayList<PontoCilindrico> pontos = new ArrayList<PontoCilindrico>();

	public Reposicionador(BufferPontos buffer) {
		this.buffer = buffer;
		pontos.ensureCapacity(buffer.getSize());
	}
	
	public void run() {
		carregaPontos();
		
		for(;;) {
			produz(0);
			produz(1);
		}
	}
	
	private void carregaPontos() {
		PontoCilindrico ponto;
		while((ponto = buffer.nextPonto()) != null) {
			pontos.add(ponto);
		}
	}
	
	private void produz(int fila) {
		for(PontoCilindrico p : pontos) {
			p.t += 0.1;
			// jogar na fila
		}
	}
}
