package br.furb.simulador_galaxia;

import java.util.ArrayList;

public class Reposicionador extends Thread {
	private BufferPontos buffer;
	private FilaPontos fila;
	
	private ArrayList<PontoCilindrico> pontos = new ArrayList<PontoCilindrico>();

	public Reposicionador(BufferPontos buffer, FilaPontos fila) {
		this.buffer = buffer;
		this.fila = fila;
		
		pontos.ensureCapacity(buffer.getSize());
	}
	
	public void run() {
		carregaPontos();
		
		for(;;) {
			produz();
		}
	}
	
	private void carregaPontos() {
		PontoCilindrico ponto;

		while((ponto = buffer.nextPonto()) != null) {
			pontos.add(ponto);
		}
	}
	
	private void produz() {
		for(PontoCilindrico p : pontos) {
			p.t += 0.005;
			fila.insere(p);
		}
	}
}
