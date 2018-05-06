package profiluefter.fez2.rendering.shader;

import org.joml.Matrix4f;

public class StaticShader extends Shader{

	private static final String VERTEX_FILE = "vertex.glsl";
	private static final String FRAGMENT_FILE = "fragment.glsl";

	private int location_transformationMatrix;

	public StaticShader() {
		super(StaticShader.class.getResourceAsStream(VERTEX_FILE), StaticShader.class.getResourceAsStream(FRAGMENT_FILE));
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

}