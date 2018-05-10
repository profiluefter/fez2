package profiluefter.fez2.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import profiluefter.fez2.entities.Camera;
import profiluefter.fez2.rendering.Display;

import java.nio.IntBuffer;

public class ProfiMaths {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0));
		matrix.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0));
		matrix.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1));
		matrix.scale(scale);
		return matrix;
	}

	public static Matrix4f createProjectionMatrix() {
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(Display.getWindow(), w, h);
		int width = w.get(0);
		int height = h.get(0);

		return new Matrix4f().perspective((float) Math.toRadians(FOV), ((float) width)/((float) height), NEAR_PLANE, FAR_PLANE);
	}

	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotateX((float) Math.toRadians(camera.getPitch()),viewMatrix);
		viewMatrix.rotateY((float) Math.toRadians(camera.getYaw()),viewMatrix);
		viewMatrix.rotateZ((float) Math.toRadians(camera.getRoll()),viewMatrix);

		viewMatrix.translate(camera.getPosition().negate());
		return viewMatrix;
	}
}
