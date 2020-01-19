package net.tslat.aoa3.client.render.entities.projectiles.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.misc.EntityTidalWave;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class TidalWaveRenderer extends Render<EntityTidalWave> {
	public TidalWaveRenderer(final RenderManager manager) {
		super(manager);
	}

	@Override
	public void doRender(EntityTidalWave entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Minecraft.getMinecraft().effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.WATER_BUBBLE);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityTidalWave entity) {
		return null;
	}
}
