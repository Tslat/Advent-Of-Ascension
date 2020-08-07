package net.tslat.aoa3.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class PortalFloaterParticle extends Particle {
	private final double originPosX;
	private final double originPosY;
	private final double originPosZ;

	public PortalFloaterParticle(World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int colour, float scale) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.particleTexture = ParticleRegister.getTextureAtlas();
		this.originPosX = posX;
		this.originPosY = posY;
		this.originPosZ = posZ;
		this.motionX = velocityX;
		this.motionY = velocityY;
		this.motionZ = velocityZ;
		this.particleScale = rand.nextFloat() * 0.07F * scale;
		this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
		this.particleRed = (colour >> 16) / 255.0f;
		this.particleGreen = ((colour >> 8) & 0xff) / 255.0f;
		this.particleBlue = (colour & 0xff) / 255.0f;
		this.particleAlpha = 1.0f;
		this.particleTextureIndexY = 4;
		this.particleTextureIndexX = (int)(Math.random() * 8d);
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void move(double x, double y, double z) {
		setBoundingBox(getBoundingBox().offset(x, y, z));
		resetPositionToBB();
	}

	@Override
	public void onUpdate() {
		float percentAged = (float)particleAge / (float)particleMaxAge;
		float ageModifier = 1f - (-percentAged + percentAged * percentAged * 2.0F);

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		posX = originPosX + motionX * (double)ageModifier;
		posY = originPosY + motionY * (double)ageModifier + (double)(1.0F - percentAged);
		posZ = originPosZ + motionZ * (double)ageModifier;

		if (particleAge++ >= particleMaxAge)
			setExpired();
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entity, float partialTick, float edgeLRdirectionX, float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
		double texturePixelRatio = ParticleRegister.getParticleTextureRatio() * 8;
		double minU = particleTexture.getMinU() + particleTextureIndexX * texturePixelRatio;
		double maxU = minU + texturePixelRatio;
		double minV = particleTexture.getMinV() + particleTextureIndexY * texturePixelRatio;
		double maxV = minV + texturePixelRatio;
		double x = prevPosX + (posX - prevPosX) * partialTick - interpPosX;
		double y = prevPosY + (posY - prevPosY) * partialTick - interpPosY;
		double z = prevPosZ + (posZ - prevPosZ) * partialTick - interpPosZ;
		float scaleMod = 1f - (((float)particleAge + partialTick) / (float)particleMaxAge);
		scaleMod *= scaleMod;
		scaleMod = 1 - scaleMod;
		double scale = scaleMod * particleScale;
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

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		int initialBrightness = super.getBrightnessForRender(p_189214_1_);
		double percentAged = Math.pow((float)this.particleAge / (float)this.particleMaxAge, 3);
		int brightnessLowerBits = initialBrightness & 255;
		int brightnessUpperBits = initialBrightness >> 16 & 255;

		brightnessUpperBits = Math.min(brightnessUpperBits + (int)(percentAged * 240f), 240);

		return brightnessLowerBits | brightnessUpperBits << 16;
	}

	public static class Factory implements IParticleFactory {
		@Nullable
		@Override
		public Particle createParticle(int id, World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int... args) {
			return new PortalFloaterParticle(Minecraft.getMinecraft().world, posX, posY, posZ, velocityX, velocityY, velocityZ, args.length > 0 ? args[0] : Enums.RGBIntegers.BLACK, args.length > 1 ? args[1] / 100f : 1f);
		}
	}
}
