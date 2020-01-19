package net.tslat.aoa3.client.render.entities.boss.clunkhead;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.clunkhead.ModelClunkhead;
import net.tslat.aoa3.entity.boss.clunkhead.EntityClunkhead;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ClunkheadRenderer extends RenderLiving<EntityClunkhead> {
	private final ResourceLocation texture;

	public ClunkheadRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelClunkhead(), EntityClunkhead.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityClunkhead entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityClunkhead clunkhead) {
		return texture;
	}
}