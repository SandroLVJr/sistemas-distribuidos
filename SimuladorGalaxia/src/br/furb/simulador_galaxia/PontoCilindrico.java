package br.furb.simulador_galaxia;

public class PontoCilindrico {
	public float r, t, z;
	
	public PontoCilindrico(float r, float t, float z) {
		this.r = r;
		this.t = t;
		this.z = z;
	}

	public PontoOrtogonal toOrtogonal() {
		return new PontoOrtogonal((float) (r * Math.cos(t)), (float) (r * Math.sin(t)), z);
	}
}
