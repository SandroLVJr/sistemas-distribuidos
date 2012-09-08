package br.furb.simulador_galaxia;

import java.util.Random;

public class BufferPontos {
	PontoCilindrico[] pontos = new PontoCilindrico[1000];
	int utilizados = 0;

	public BufferPontos() {
		Random r = new Random();
		
		for (int i = 0; i < pontos.length; i++) {
			pontos[i] = new PontoCilindrico(r.nextFloat() * 30,
					(float) (r.nextFloat() * Math.PI * 2),
					r.nextFloat() * 10);
		}
	}

	public synchronized boolean hasNext() {
		return utilizados < pontos.length;
	}

	public synchronized PontoCilindrico nextPonto() {
		PontoCilindrico ponto = pontos[utilizados];
		utilizados++;

		return ponto;
	}

}
