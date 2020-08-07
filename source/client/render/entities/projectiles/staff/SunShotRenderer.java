package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.staff.EntitySunShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class SunShotRenderer extends Render<EntitySunShot> {
	private final ResourceLocation texture;

	public SunShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntitySunShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 200, 3, 3);
		entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 200, 3, 3);

		double posX = entity.posX + AdventOfAscension.rand.nextGaussian() * 0.5;
		double posY = entity.posY + AdventOfAscension.rand.nextGaussian() * 0.5;
		double posZ = entity.posZ + AdventOfAscension.rand.nextGaussian() * 0.5;

		if (!Minecraft.getMinecraft().isGamePaused())
			entity.world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, 0, 0, 0);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySunShot entity) {
		return texture;
	}
}
