package profiluefter.fez2.rendering.models;

import profiluefter.fez2.rendering.Loader;

public class ModelTexture {
	private int textureID;

	public ModelTexture(int textureID) {
		this.textureID = textureID;
	}

	public ModelTexture(Loader loader, String name) {
		this(loader.loadTexture(name));
	}

	public int getTextureID() {
		return textureID;
	}
}
