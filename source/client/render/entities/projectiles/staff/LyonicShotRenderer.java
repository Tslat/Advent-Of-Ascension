package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.staff.EntityLyonicShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class LyonicShotRenderer extends Render<EntityLyonicShot> {
	private final ResourceLocation texture;

	public LyonicShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityLyonicShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, AdventOfAscension.rand.nextBoolean() ? Enums.RGBIntegers.DARK_GRAY : Enums.RGBIntegers.GOLD, 100, 3, 3);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityLyonicShot entity) {
		return texture;
	}
}
