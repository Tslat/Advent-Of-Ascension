package net.tslat.aoa3.client.model.misc.fullbright;

import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

public class FullbrightCubes extends CubeListBuilder {
	public static FullbrightCubes create() {
		return new FullbrightCubes();
	}

	@Override
	public FullbrightCubes texOffs(int pXTexOffs, int pYTexOffs) {
		return (FullbrightCubes)super.texOffs(pXTexOffs, pYTexOffs);
	}

	@Override
	public FullbrightCubes mirror() {
		return (FullbrightCubes)super.mirror();
	}

	@Override
	public FullbrightCubes mirror(boolean pMirror) {
		return (FullbrightCubes)super.mirror(pMirror);
	}

	public FullbrightCubes addGlowBox(String comment, float x, float y, float z, int width, int height, int depth, CubeDeformation deformation, int uOffset, int vOffset) {
		this.texOffs(uOffset, vOffset);
		this.cubes.add(new FullbrightCubeDefinition(comment, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, (float)width, (float)height, (float)depth, deformation, this.mirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addGlowBox(String comment, float x, float y, float z, int width, int height, int depth, int uOffset, int vOffset) {
		this.texOffs(uOffset, vOffset);
		this.cubes.add(new FullbrightCubeDefinition(comment, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, (float)width, (float)height, (float)depth, CubeDeformation.NONE, this.mirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addGlowBox(float x, float y, float z, float width, float height, float depth) {
		this.cubes.add(new FullbrightCubeDefinition(null, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, width, height, depth, CubeDeformation.NONE, this.mirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addGlowBox(String comment, float x, float y, float z, float width, float height, float depth) {
		this.cubes.add(new FullbrightCubeDefinition(comment, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, width, height, depth, CubeDeformation.NONE, this.mirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addGlowBox(String comment, float x, float y, float z, float width, float height, float depth, CubeDeformation deformation) {
		this.cubes.add(new FullbrightCubeDefinition(comment, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, width, height, depth, deformation, this.mirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addGlowBox(float x, float y, float z, float width, float height, float depth, boolean pMirror) {
		this.cubes.add(new FullbrightCubeDefinition(null, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, width, height, depth, CubeDeformation.NONE, pMirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addGlowBox(float x, float y, float z, float width, float height, float depth, CubeDeformation deformation, float uWidth, float vHeight) {
		this.cubes.add(new FullbrightCubeDefinition(null, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, width, height, depth, deformation, this.mirror, uWidth, vHeight));

		return this;
	}

	public FullbrightCubes addGlowBox(float x, float y, float z, float width, float height, float depth, CubeDeformation deformation) {
		this.cubes.add(new FullbrightCubeDefinition(null, (float)this.xTexOffs, (float)this.yTexOffs, x, y, z, width, height, depth, deformation, this.mirror, 1.0F, 1.0F));

		return this;
	}

	public FullbrightCubes addBox(String pComment, float pOriginX, float pOriginY, float pOriginZ, int pDimensionX, int pDimensionY, int pDimensionZ, CubeDeformation pCubeDeformation, int pXTexOffs, int pYTexOffs) {
		return (FullbrightCubes)super.addBox(pComment, pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ, pCubeDeformation, pXTexOffs, pYTexOffs);
	}

	public FullbrightCubes addBox(String pComment, float pOriginX, float pOriginY, float pOriginZ, int pDimensionX, int pDimensionY, int pDimensionZ, int pXTexOffs, int pYTexOffs) {
		return (FullbrightCubes)super.addBox(pComment, pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ, pXTexOffs, pYTexOffs);
	}

	public FullbrightCubes addBox(float pOriginX, float pOriginY, float pOriginZ, float pDimensionX, float pDimensionY, float pDimensionZ) {
		return (FullbrightCubes)super.addBox(pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ);
	}

	public FullbrightCubes addBox(String pComment, float pOriginX, float pOriginY, float pOriginZ, float pDimensionX, float pDimensionY, float pDimensionZ) {
		return (FullbrightCubes)super.addBox(pComment, pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ);
	}

	public FullbrightCubes addBox(String pComment, float pOriginX, float pOriginY, float pOriginZ, float pDimensionX, float pDimensionY, float pDimensionZ, CubeDeformation pCubeDeformation) {
		return (FullbrightCubes)super.addBox(pComment, pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ, pCubeDeformation);
	}

	public FullbrightCubes addBox(float pOriginX, float pOriginY, float pOriginZ, float pDimensionX, float pDimensionY, float pDimensionZ, boolean pMirror) {
		return (FullbrightCubes)super.addBox(pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ, pMirror);
	}

	public FullbrightCubes addBox(float pOriginX, float pOriginY, float pOriginZ, float pDimensionX, float pDimensionY, float pDimensionZ, CubeDeformation pCubeDeformation, float pTexScaleU, float pTexScaleV) {
		return (FullbrightCubes)super.addBox(pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ, pCubeDeformation, pTexScaleU, pTexScaleV);
	}

	public FullbrightCubes addBox(float pOriginX, float pOriginY, float pOriginZ, float pDimensionX, float pDimensionY, float pDimensionZ, CubeDeformation pCubeDeformation) {
		return (FullbrightCubes)super.addBox(pOriginX, pOriginY, pOriginZ, pDimensionX, pDimensionY, pDimensionZ, pCubeDeformation);
	}
}
