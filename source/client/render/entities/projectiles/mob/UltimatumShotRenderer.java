package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.staff.EntityUltimatumShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class UltimatumShotRenderer extends Render<EntityUltimatumShot> {
	private final ResourceLocation texture;

	public UltimatumShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityUltimatumShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 25, 3, 0);
		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 100, 3, 3);
		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, true, entity.posX + AdventOfAscension.rand.nextDouble() - 0.5d, entity.posY + AdventOfAscension.rand.nextDouble() - 0.5d, entity.posZ + AdventOfAscension.rand.nextDouble() - 0.5d, 0, 0, 0, Enums.RGBIntegers.YELLOW, 50, 3, 3);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityUltimatumShot entity) {
		return texture;
	}
}
