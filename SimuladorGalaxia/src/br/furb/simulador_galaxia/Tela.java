package br.furb.simulador_galaxia;

import java.nio.FloatBuffer;
import java.util.concurrent.locks.Lock;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;

public class Tela extends GLCanvas implements GLEventListener {
	private static final long serialVersionUID = 1L;
	private static final int FPS = 60;

	private PontoOrtogonal[] pontos;
	private Lock lock;

	private FPSAnimator animator;
	private GLU glu;

	public Tela(int width, int height, GLCapabilities capabilities) {
		super(capabilities);
		setSize(width, height);
		addGLEventListener(this);
	}

	private void setCamera(GL gl) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		float widthHeightRatio = (float) getWidth() / (float) getHeight();
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(-17, 50, 70, 0, 0, 0, 0, 1, 0);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	// Método chamado pelo 'animator' a cada 1/60 de segundo para desenhar na
	// tela
	@Override
	public void display(GLAutoDrawable drawable) {

		GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		setCamera(gl);

		// Este array terá de ser convertido a partir de this.pontos
		float[] pontos = new float[432];		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 36; j++) {
				pontos[108*i + 3*j] = (float) (Math.cos(j*10*Math.PI / 180) * (i * 10));
				pontos[108*i + 3*j + 1] = (float) (Math.sin(j*10*Math.PI / 180) * (i * 10));
				pontos[108*i + 3*j + 2] = 0;
			}
		}
		
		FloatBuffer pontosBuffer = BufferUtil.newFloatBuffer(pontos.length);
		pontosBuffer.put(pontos);
		pontosBuffer.rewind();
		
		gl.glColor3f(1f, 1f, 1f);
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, pontosBuffer);
		gl.glDrawArrays(GL.GL_POINTS, 0, pontos.length / 3);
	}

	// Método chamado na inicialização da tela
	@Override
	public void init(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		drawable.setGL(new DebugGL(gl));

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glClearColor(0f, 0f, 0f, 1f);
		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);

		animator = new FPSAnimator(this, FPS);
		animator.start();

		glu = new GLU();
	}

	// Método chamado quando a tela muda de tamanho
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL gl = drawable.getGL();
		gl.glViewport(0, 0, width, height);
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// Faz nada
	}
}
