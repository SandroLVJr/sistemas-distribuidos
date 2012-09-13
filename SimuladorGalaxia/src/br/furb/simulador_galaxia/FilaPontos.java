package br.furb.simulador_galaxia;

import java.util.concurrent.Semaphore;

public class FilaPontos {
	private Semaphore semaforo = new Semaphore(1, true);
	
	private PontoCilindrico[] fila = new PontoCilindrico[10];
	private int tamanho = 0;
	
	public void insere(PontoCilindrico ponto) {
		try {
			semaforo.acquire();
			
			while(tamanho >= fila.length) {
				// permite que outra thread tente usar a fila
				semaforo.release();
				semaforo.acquire();
			}
			
			fila[tamanho] = ponto;
			tamanho++;
			
			semaforo.release();
		} catch(Exception ex) { // pattern pokemon dos bróder
			ex.printStackTrace();
		}
	}
	
	public PontoCilindrico remove() {
		try {
			semaforo.acquire();
			
			while(tamanho <= 0) {
				// permite que outra thread tente usar a fila
				semaforo.release();
				semaforo.acquire();
			}
			
			tamanho--;
			PontoCilindrico ponto = fila[tamanho];
			
			semaforo.release();
			return ponto;
		} catch(Exception ex) { // pattern pokemon dos bróder
			ex.printStackTrace();
			return null;
		}
	}
}
