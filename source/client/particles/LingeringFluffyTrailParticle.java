package net.tslat.aoa3.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class LingeringFluffyTrailParticle extends Particle {
	private static final ResourceLocation texture = new ResourceLocation("aoa3", "particles/fluffy_trail");
	private static TextureAtlasSprite sprite = null;

	public final int textureOffsetIndex;

	public LingeringFluffyTrailParticle(World world, double posX, double posY, double posZ, int colour) {
		this(world, posX, posY, posZ, 0, 0, 0, colour, 0, 1);
	}

	public LingeringFluffyTrailParticle(World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int colour, int textureOffsetIndex, float scale) {
		super(world, posX, posY, posZ, speedX, speedY, speedZ);
		this.textureOffsetIndex = textureOffsetIndex;
		this.motionX = speedX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionY = speedY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.motionZ = speedZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
		this.particleScale = (rand.nextFloat() * rand.nextFloat() * 6.0f + 1.0f) * scale;
		this.particleMaxAge = (int)(20.0 / (rand.nextFloat() * 0.8 + 0.2));
		this.particleRed = (colour >> 16) / 255.0f;
		this.particleGreen = ((colour >> 8) & 0xff) / 255.0f;
		this.particleBlue = (colour & 0xff) / 255.0f;
		this.particleAlpha = (colour >> 24) / 255.0f;

		if (particleAlpha == 0)
			particleAlpha = 1f;

		setParticleTexture(sprite);
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

	public static class Factory implements IParticleFactory {
		@Nullable
		@Override
		public Particle createParticle(int id, World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int... args) {
			return new LingeringFluffyTrailParticle(Minecraft.getMinecraft().world, posX, posY, posZ, velocityX, velocityY, velocityZ, args.length > 0 ? args[0] : Enums.RGBIntegers.WHITE, args.length > 1 ? args[1] : 0, args.length > 2 ? args[2] / 100f : 1f);
		}
	}
}
