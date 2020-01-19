package net.tslat.aoa3.client.render.entities.boss.primordialfive;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.primordialfive.ModelRaxxan;
import net.tslat.aoa3.entity.boss.primordialfive.EntityRaxxan;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RaxxanRenderer extends RenderLiving<EntityRaxxan> {
	private final ResourceLocation texture;

	public RaxxanRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelRaxxan(), EntityRaxxan.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityRaxxan entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRaxxan Raxxan) {
		return texture;
	}
}