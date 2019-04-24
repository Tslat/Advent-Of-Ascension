package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.projectiles.ModelCorallusShot;
import net.tslat.aoa3.entity.projectiles.mob.EntityCorallusShot;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CorallusShotRenderer extends RenderLiving<EntityCorallusShot> {
	private final ResourceLocation texture;

	public CorallusShotRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelCorallusShot(), 0);
		texture = resource;
	}

	@Override
	public void doRender(EntityCorallusShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCorallusShot entity) {
		return texture;
	}
}