package br.furb.simulador_galaxia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Conversor extends Thread {
	private FilaPontos fila;
	private PontoOrtogonal[] pontos;
	private Lock lock;
	private Condition desenhando;
	
	public Conversor(FilaPontos fila, PontoOrtogonal[] pontos, Lock lock,
			Condition desenhando) {
		this.fila = fila;
		this.pontos = pontos;
		this.lock = lock;
		this.desenhando = desenhando;
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
		
		try {
			desenhando.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock.unlock();
	}
}
