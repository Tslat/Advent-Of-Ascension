package net.tslat.aoa3.client.render.entities.boss.corallus;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.corallus.ModelCorallus;
import net.tslat.aoa3.entity.boss.corallus.EntityCorallus;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CorallusRenderer extends RenderLiving<EntityCorallus> {
	private final ResourceLocation texture;
	private final ResourceLocation enragedTexture;

	public CorallusRenderer(RenderManager renderManager, final ResourceLocation texture, final ResourceLocation enragedTexture) {
		super(renderManager, new ModelCorallus(), EntityCorallus.entityWidth / 3);
		this.texture = texture;
		this.enragedTexture = enragedTexture;
	}

	@Override
	public void doRender(EntityCorallus entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCorallus corallus) {
		return corallus.isEnraged() ? enragedTexture : texture;
	}
}