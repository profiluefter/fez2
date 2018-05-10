package profiluefter.fez2.rendering.shader;

import org.joml.Matrix4f;
import profiluefter.fez2.entities.Camera;
import profiluefter.fez2.utils.ProfiMaths;

public class StaticShader extends Shader {

	private static final String VERTEX_FILE = "vertex.glsl";
	private static final String FRAGMENT_FILE = "fragment.glsl";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;

	public StaticShader() {
		super(StaticShader.class.getResourceAsStream(VERTEX_FILE), StaticShader.class.getResourceAsStream(FRAGMENT_FILE));
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(location_viewMatrix, ProfiMaths.createViewMatrix(camera));
	}

	public void loadProjectionMatrix(Matrix4f projectionMatrix) {
		super.loadMatrix(location_projectionMatrix, projectionMatrix);
	}
}