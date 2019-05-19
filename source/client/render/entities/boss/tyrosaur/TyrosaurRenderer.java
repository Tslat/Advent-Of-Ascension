package net.tslat.aoa3.client.render.entities.boss.tyrosaur;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.tyrosaur.ModelTyrosaur;
import net.tslat.aoa3.entity.boss.tyrosaur.EntityTyrosaur;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class TyrosaurRenderer extends RenderLiving<EntityTyrosaur> {
	private final ResourceLocation texture;

	public TyrosaurRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelTyrosaur(), EntityTyrosaur.entityWidth / 3);
		texture = resource;
	}

	@Override
	public void doRender(EntityTyrosaur entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityTyrosaur entity) {
		return texture;
	}
}