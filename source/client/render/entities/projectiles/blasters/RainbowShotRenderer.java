package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityRainbowShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RainbowShotRenderer extends Render<EntityRainbowShot> {
	private final ResourceLocation texture;

	public RainbowShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityRainbowShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 100, 20, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 1.0, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 100, 20, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 100, 20, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 100, 20, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY - 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 100, 20, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY - 1.0, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 100, 20, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY - 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 100, 20, 7);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRainbowShot entity) {
		return texture;
	}
}
