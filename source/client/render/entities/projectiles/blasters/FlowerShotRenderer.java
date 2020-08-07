package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityFlowerShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class FlowerShotRenderer extends Render<EntityFlowerShot> {
	private final ResourceLocation texture;

	public FlowerShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityFlowerShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (AdventOfAscension.rand.nextBoolean()) {
			for (int i = 0; i < 3; i++) {
				entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 100, 3, 7);
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 100, 3, 7);
			}
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityFlowerShot entity) {
		return texture;
	}
}
