package br.furb.simulador_galaxia;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

public class SimuladorGalaxia {

	public static void main(String[] args) {
		Tela tela = new Tela(600, 400, getCapabilities());
		JFrame frame = new JFrame("Simulador Galáxia");
		frame.getContentPane().add(tela);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
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
