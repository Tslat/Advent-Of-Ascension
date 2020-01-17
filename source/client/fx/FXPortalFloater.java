package net.tslat.aoa3.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.render.FXRenders;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class FXPortalFloater extends Particle {
	public static final int particleId = 5;

	public static final ResourceLocation texture = new ResourceLocation("aoa3", "fx/portal_floater");

	private final double originPosX;
	private final double originPosY;
	private final double originPosZ;

	public FXPortalFloater(World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int colour, float... scale) {
		super(world, posX, posY, posZ, speedX, speedY, speedZ);

		this.originPosX = posX;
		this.originPosY = posY;
		this.originPosZ = posZ;
		this.motionX = speedX;
		this.motionY = speedY;
		this.motionZ = speedZ;
		this.particleScale = rand.nextFloat() * 0.07F;

		if (scale.length > 0)
			particleScale *= scale[0];

		this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
		this.particleRed = (colour >> 16) / 255.0f;
		this.particleGreen = ((colour >> 8) & 0xff) / 255.0f;
		this.particleBlue = (colour & 0xff) / 255.0f;
		this.particleAlpha = 1.0f;
		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(texture.toString()));
		this.setParticleTextureIndex((int)(Math.random() * 8d));
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
		double minU = particleTextureIndexX / 16.0f;
		double maxU = minU + 0.0624375F;
		double minV = particleTextureIndexY / 16.0f;
		double maxV = minV + 0.0624375F;
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

	public void create() {
		Minecraft.getMinecraft().effectRenderer.addEffect(this);
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements FXRenders.FXFactory {
		@Nullable
		@Override
		public Particle createParticle(double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, int textureOffsetIndex, float scale, int... args) {
			Particle particle = new FXPortalFloater(Minecraft.getMinecraft().world, posX, posY, posZ, velocityX, velocityY, velocityZ, textureOffsetIndex, scale);

			Minecraft.getMinecraft().effectRenderer.addEffect(particle);

			return particle;
		}
	}
}
