package net.tslat.aoa3.client.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npcs.trader.EntityCorruptedTraveller;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class AoATraderRenderer extends RenderLiving<AoATrader> {
	private final ResourceLocation texture;
	private final float scale;

	public AoATraderRenderer(RenderManager renderManager, ModelBase model, float entityWidth, float scale, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth / 3);
		this.texture = textureResource;
		this.scale = scale;
	}

	@Override
	protected void preRenderCallback(AoATrader entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected int getTeamColor(AoATrader trader) {
		if (trader instanceof EntityCorruptedTraveller) {
			return Enums.RGBIntegers.BLACK;
		}
		else {
			return super.getTeamColor(trader);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoATrader entity) {
		return texture;
	}
}
