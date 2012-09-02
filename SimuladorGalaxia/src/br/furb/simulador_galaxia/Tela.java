package br.furb.simulador_galaxia;

import java.util.concurrent.locks.Lock;

// Pro import abaixo funcionar, é necessário que o JOGL esteja instalado. Acho
// melhor instalá-lo no sistema em vez de colocá-lo diretamente no projeto por
// causa de seu tamanho.
import javax.media.opengl.*;

public class Tela {
	private PontoOrtogonal[] pontos;
	private Lock lock;
}
