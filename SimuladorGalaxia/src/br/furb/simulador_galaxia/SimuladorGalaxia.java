package br.furb.simulador_galaxia;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

public class SimuladorGalaxia {

	public static void main(String[] args) {
		BufferPontos buffer = new BufferPontos();
		
		FilaPontos fila = new FilaPontos();
		CyclicBarrier barreira = new CyclicBarrier(buffer.getSize() / 500);

		Reposicionador[] reposicionadores = new Reposicionador[buffer.getSize() / 500];
		for(int i = 0; i < reposicionadores.length; i++) {
			reposicionadores[i] = new Reposicionador(buffer, fila, barreira);
			reposicionadores[i].start();
		}
		
		PontoOrtogonal[] pontos = new PontoOrtogonal[buffer.getSize()];
		for(int i = 0; i < pontos.length; i++)
			pontos[i] = new PontoOrtogonal(0, 0, 0);
		
		Lock lock = new ReentrantLock();
		Condition desenhando = lock.newCondition();
		
		Conversor conversor = new Conversor(fila, pontos, lock, desenhando);
		conversor.start();
		
		Tela tela = new Tela(600, 400, getCapabilities(), pontos, lock, desenhando);
		JFrame frame = new JFrame("Simulador Galáxia");
		frame.getContentPane().add(tela);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		for(Reposicionador r : reposicionadores) {
			try {
				r.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static GLCapabilities getCapabilities() {
		GLCapabilities capabilities = new GLCapabilities();
		capabilities.setRedBits(8);
		capabilities.setGreenBits(8);
		capabilities.setBlueBits(8);
		capabilities.setAlphaBits(8);
		
		return capabilities;
	}

}
