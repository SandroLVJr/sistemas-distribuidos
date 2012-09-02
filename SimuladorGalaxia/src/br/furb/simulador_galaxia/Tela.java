package br.furb.simulador_galaxia;

import java.util.concurrent.locks.Lock;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

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
        glu.gluLookAt(0, 0, 100, 0, 0, 0, 0, 1, 0);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	// Método chamado pelo 'animator' a cada 1/60 de segundo para desenhar na tela
	@Override
	public void display(GLAutoDrawable drawable) {
		
		GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        setCamera(gl);
        
        gl.glColor4f(1f, 1f, 1f, 0.9f);
        
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex3f(-20, -20, 0);
        gl.glVertex3f(+20, -20, 0);
        gl.glVertex3f(0, 20, 0);
        gl.glEnd();
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
