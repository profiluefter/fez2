package profiluefter.fez2;

import org.joml.Vector3f;
import profiluefter.fez2.entities.Camera;
import profiluefter.fez2.entities.Entity;
import profiluefter.fez2.rendering.Display;
import profiluefter.fez2.rendering.Loader;
import profiluefter.fez2.rendering.Renderer;
import profiluefter.fez2.rendering.models.ModelTexture;
import profiluefter.fez2.rendering.models.RawModel;
import profiluefter.fez2.rendering.models.TexturedModel;
import profiluefter.fez2.rendering.shader.StaticShader;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Starter {
	private static boolean running = true;

	public static void stop() {
		running = false;
	}

	public static void main(String[] args) {
		Display.create();

		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		float[] vertices = {
				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,0.5f,-0.5f,

				-0.5f,0.5f,0.5f,
				-0.5f,-0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				0.5f,0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				-0.5f,-0.5f,0.5f,
				-0.5f,0.5f,0.5f,

				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f

		};

		float[] textureCoords = {

				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0


		};

		int[] indices = {
				0,1,3,
				3,1,2,
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};

		RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
		ModelTexture texture = new ModelTexture(loader,"fez_original");
		TexturedModel texturedModel = new TexturedModel(model,texture);

		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-5),0,0,0,1);
		Camera camera = new Camera();

		while (!glfwWindowShouldClose(Display.getWindow()) && running) {
			entity.increaseRotation(1,1,0);
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			Display.update();
		}

		shader.cleanUp();
		loader.cleanUp();
		Display.destroy();
	}
}
