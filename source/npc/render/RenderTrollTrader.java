package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelSeaTroll;

@SideOnly(Side.CLIENT)
public class RenderTrollTrader extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSeaTroll model;

	public RenderTrollTrader(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSeaTroll)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderTrollTrader.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/trolltrader.png");
	}
}
