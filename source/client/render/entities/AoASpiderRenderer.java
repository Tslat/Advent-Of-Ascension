package net.tslat.aoa3.client.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

@SideOnly(Side.CLIENT)
public class AoASpiderRenderer extends AoAMeleeMobRenderer {
	public AoASpiderRenderer(RenderManager renderManager, ModelBase model, float entityWidth, float scale, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth, scale, textureResource);
	}

	@Override
	protected float getDeathMaxRotation(AoAMeleeMob entityLivingBaseIn) {
		return 180f;
	}
}