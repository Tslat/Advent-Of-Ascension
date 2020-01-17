package net.tslat.aoa3.client.render.entities.mobs.abyss;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.abyss.ModelWebReaper;
import net.tslat.aoa3.client.render.AoARangedMobRenderer;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.mobs.abyss.EntityWebReaper;

@SideOnly(Side.CLIENT)
public class WebReaperRenderer extends AoARangedMobRenderer {
	public WebReaperRenderer(RenderManager renderManager, final ResourceLocation textureResource) {
		super(renderManager, new ModelWebReaper(), EntityWebReaper.entityWidth, 1.0f, textureResource);
	}

	@Override
	protected void preRenderCallback(AoARangedMob mob, float partialTickTime) {
		if (mob instanceof EntityWebReaper) {
			float scale = ((EntityWebReaper)mob).getStageMod();

			GlStateManager.scale(scale, scale, scale);
		}
	}
}