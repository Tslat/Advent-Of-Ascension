package net.tslat.aoa3.client.render.entities.boss.primordialfive;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.primordialfive.ModelOkazor;
import net.tslat.aoa3.entity.boss.primordialfive.EntityOkazor;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class OkazorRenderer extends RenderLiving<EntityOkazor> {
	private final ResourceLocation texture;

	public OkazorRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelOkazor(), EntityOkazor.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityOkazor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityOkazor Okazor) {
		return texture;
	}
}