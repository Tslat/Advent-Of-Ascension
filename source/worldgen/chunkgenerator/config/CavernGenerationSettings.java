package net.tslat.aoa3.worldgen.chunkgenerator.config;

import net.minecraft.world.gen.GenerationSettings;

public class CavernGenerationSettings extends GenerationSettings {
	private int ceilingHeight = 128;
	private int floorHeight = 20;

	public void setCeilingHeight(int ceilingHeight) {
		this.ceilingHeight = ceilingHeight;
	}

	public void setFloorHeight(int floorHeight) {
		this.floorHeight = floorHeight;
	}

	public int getCeilingHeight() {
		return this.ceilingHeight;
	}

	public int getFloorHeight() {
		return this.floorHeight;
	}
}
