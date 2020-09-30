package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityConfettiCluster;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ConfettiClusterRenderer extends Render<EntityConfettiCluster> {
	private final ResourceLocation texture;

	public ConfettiClusterRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityConfettiCluster entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX + -0.05, entity.motionY, entity.motionZ, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX + 0.05, entity.motionY, entity.motionZ, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX + -0.05, entity.motionY, entity.motionZ + -0.05, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX + -0.05, entity.motionY, entity.motionZ + 0.05, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX + 0.05, entity.motionY, entity.motionZ + 0.05, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX + 0.05, entity.motionY, entity.motionZ + -0.05, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ + -0.05, 15, 10, 3);
			entity.world.spawnParticle(ParticleRegister.RAINBOW_FLUFFY, true, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ + 0.05, 15, 10, 3);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityConfettiCluster entity) {
		return texture;
	}
}
