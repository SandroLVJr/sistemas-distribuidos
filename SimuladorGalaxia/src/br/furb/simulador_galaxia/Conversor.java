package br.furb.simulador_galaxia;

import java.util.concurrent.locks.Lock;

public class Conversor extends Thread {
	private FilaPontos fila;
	private PontoOrtogonal[] pontos;
	private Lock lock;
	
	public Conversor(FilaPontos fila, PontoOrtogonal[] pontos, Lock lock) {
		this.fila = fila;
		this.pontos = pontos;
		this.lock = lock;
	}

	public void run() {
		for(;;) {
			converte();
		}
	}
	
	private void converte() {
		lock.lock();
		
		for(int i = 0; i < 1000; i++) {
			pontos[i] = fila.remove().toOrtogonal();
		}
		
		lock.unlock();
	}
}
