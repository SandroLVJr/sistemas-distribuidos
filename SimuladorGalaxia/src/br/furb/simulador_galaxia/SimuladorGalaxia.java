package br.furb.simulador_galaxia;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

public class SimuladorGalaxia {

	public static void main(String[] args) {
		GLCanvas canvas = new GLCanvas();
		canvas.setSize(600, 400);

		JFrame frame = new JFrame("Simulador Galáxia");
		frame.getContentPane().add(canvas);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}

}
