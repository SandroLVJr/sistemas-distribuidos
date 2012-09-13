package br.furb.simulador_galaxia;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Reposicionador extends Thread {
	private BufferPontos buffer;
	private FilaPontos fila;
	private CyclicBarrier barreira;
	
	private ArrayList<PontoCilindrico> pontos = new ArrayList<PontoCilindrico>();

	public Reposicionador(BufferPontos buffer, FilaPontos fila, CyclicBarrier barreira) {
		this.buffer = buffer;
		this.fila = fila;
		this.barreira = barreira;
		
		pontos.ensureCapacity(buffer.getSize());
	}
	
	public void run() {
		carregaPontos();
		
		for(;;) {
			produz();
			
			try {
				barreira.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
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
