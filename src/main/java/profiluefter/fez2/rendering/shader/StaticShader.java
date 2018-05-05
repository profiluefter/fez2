package profiluefter.fez2.rendering.shader;

public class StaticShader extends Shader{

	private static final String VERTEX_FILE = "vertex.glsl";
	private static final String FRAGMENT_FILE = "fragment.glsl";

	public StaticShader() {
		super(StaticShader.class.getResourceAsStream(VERTEX_FILE), StaticShader.class.getResourceAsStream(FRAGMENT_FILE));
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}



}