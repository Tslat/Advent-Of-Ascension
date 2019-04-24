package net.tslat.aoa3.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXFluffyTrail extends Particle {
	public static final int particleId = 0;

	public static final ResourceLocation texture = new ResourceLocation("aoa3", "fx/fluffy_trail");

	public final int textureOffsetIndex;

	public FXFluffyTrail(World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int colour, int textureOffsetIndex, float... scale) {
		super(world, posX, posY, posZ, speedX, speedY, speedZ);
		this.textureOffsetIndex = textureOffsetIndex;
		this.motionX = speedX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionY = speedY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionZ = speedZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.particleScale = rand.nextFloat() * rand.nextFloat() * 6.0f + 1.0f;

		if (scale.length > 0)
			particleScale *= scale[0];

		particleMaxAge = (int)(3.0 / (rand.nextFloat() * 0.8 + 0.2));
		particleRed = (colour >> 16) / 255.0f;
		particleGreen = ((colour >> 8) & 0xff) / 255.0f;
		particleBlue = (colour & 0xff) / 255.0f;
		particleAlpha = 1.0f;
		setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString()));
	}

	@Override
	public int getFXLayer() {
		return particleTexture == null ? 1 : 0;
	}

	@Override
	public boolean shouldDisableDepth() {
		return false;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge)
			setExpired();

		int textureIndex = 7 - particleAge * textureOffsetIndex / particleMaxAge;
		particleTextureIndexX = textureIndex % 16;
		particleTextureIndexY = textureIndex / 16;

		motionY += 0.004;
		motionX *= 0.8999999761581421;
		motionY *= 0.8999999761581421;
		motionZ *= 0.8999999761581421;

		if (onGround) {
			motionX *= 0.699999988079071;
			motionZ *= 0.699999988079071;
		}
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entity, float partialTick, float edgeLRdirectionX, float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
		double minU = particleTextureIndexX / 16.0f;
		double maxU = minU + 0.0624375F;
		double minV = particleTextureIndexY / 16.0f;
		double maxV = minV + 0.0624375F;
		double x = prevPosX + (posX - prevPosX) * partialTick - interpPosX;
		double y = prevPosY + (posY - prevPosY) * partialTick - interpPosY;
		double z = prevPosZ + (posZ - prevPosZ) * partialTick - interpPosZ;
		double scale = 0.1F * particleScale;
		double scaledUDDirX = edgeUDdirectionX * scale;
		double scaledUDDirY = edgeUDdirectionY * scale;
		double scaledUDDirZ = edgeUDdirectionZ * scale;
		double scaledLRDirX = edgeLRdirectionX * scale;
		double scaledLRDirZ = edgeLRdirectionZ * scale;
		int combinedBrightness = this.getBrightnessForRender(partialTick);
		int skyLightTimes16 = combinedBrightness >> 16 & 65535;
		int blockLightTimes16 = combinedBrightness & 65535;

		buffer.pos(x - scaledLRDirX - scaledUDDirX, y - scaledUDDirY, z - scaledLRDirZ - scaledUDDirZ)
				.tex(maxU, maxV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16)
				.endVertex();
		buffer.pos(x - scaledLRDirX + scaledUDDirX,y + scaledUDDirY,z - scaledLRDirZ + scaledUDDirZ)
				.tex(maxU, minV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16)
				.endVertex();
		buffer.pos(x + scaledLRDirX + scaledUDDirX,y + scaledUDDirY,z + scaledLRDirZ + scaledUDDirZ)
				.tex(minU, minV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16)
				.endVertex();
		buffer.pos(x + scaledLRDirX - scaledUDDirX,y - scaledUDDirY,z + scaledLRDirZ - scaledUDDirZ)
				.tex(minU, maxV)
				.color(particleRed, particleGreen, particleBlue, particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16)
				.endVertex();
	}

	public void create() {
		Minecraft.getMinecraft().effectRenderer.addEffect(this);
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int id, World world, double x, double y, double z, double vX, double vY, double vZ, int... args) {
			Particle particle = new FXFluffyTrail(world, x, y, z, vX, vY, vZ, args[0], args[1], args[2]);

			Minecraft.getMinecraft().effectRenderer.addEffect(particle);
			return particle;
		}
	}
}
