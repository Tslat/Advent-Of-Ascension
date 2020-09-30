package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityVortexBlaster;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class VortexBlasterRenderer extends Render<EntityVortexBlaster> {
	private final ResourceLocation texture;

	public VortexBlasterRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityVortexBlaster entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (AdventOfAscension.rand.nextBoolean()) {
			for (int i = 0; i < 3; i++) {
				entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 25, 3, 3);
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 100, 3, 3);
			}
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityVortexBlaster entity) {
		return texture;
	}
}
