package profiluefter.fez2;

import profiluefter.fez2.rendering.Display;
import profiluefter.fez2.rendering.Loader;
import profiluefter.fez2.rendering.Renderer;
import profiluefter.fez2.rendering.models.ModelTexture;
import profiluefter.fez2.rendering.models.RawModel;
import profiluefter.fez2.rendering.models.TexturedModel;
import profiluefter.fez2.rendering.shader.StaticShader;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Starter {
	public static void main(String[] args) {
		Display.create();

		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();

		float[] vertices = {
				-0.5f, 0.5f, 0f,//v0
				-0.5f, -0.5f, 0f,//v1
				0.5f, -0.5f, 0f,//v2
				0.5f, 0.5f, 0f,//v3
		};

		int[] indices = {
				0,1,3,//top left triangle (v0, v1, v3)
				3,1,2//bottom right triangle (v3, v1, v2)
		};

		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};

		RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
		ModelTexture texture = new ModelTexture(loader,"fez_original");
		TexturedModel texturedModel = new TexturedModel(model,texture);

		while (!glfwWindowShouldClose(Display.getWindow())) {
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			Display.update();
		}

		shader.cleanUp();
		loader.cleanUp();
		Display.destroy();
	}
}