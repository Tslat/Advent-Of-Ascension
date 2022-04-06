package net.tslat.aoa3.client.model.misc.fullbright;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;

import javax.annotation.Nullable;

public class FullbrightCubeDefinition extends CubeDefinition {
	protected FullbrightCubeDefinition(@Nullable String comment, float u, float v, float x, float y, float z, float width, float height, float depth, CubeDeformation deformation, boolean mirror, float uScale, float vScale) {
		super(comment, u, v, x, y, z, width, height, depth, deformation, mirror, uScale, vScale);
	}

	@Override
	public ModelPart.Cube bake(int uWidth, int vHeight) {
		return new FullbrightCube((int)this.texCoord.u(), (int)this.texCoord.v(), this.origin.x(), this.origin.y(), this.origin.z(), this.dimensions.x(), this.dimensions.y(), this.dimensions.z(), this.grow.growX, this.grow.growY, this.grow.growZ, this.mirror, (float)uWidth * this.texScale.u(), (float)vHeight * this.texScale.v());
	}
}
