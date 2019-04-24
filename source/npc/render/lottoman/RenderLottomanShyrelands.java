package net.nevermine.npc.render.lottoman;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.npc.model.modelLottoman;

@SideOnly(Side.CLIENT)
public class RenderLottomanShyrelands extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/lottomanShyrelands.png");
	protected modelLottoman model;

	public RenderLottomanShyrelands(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		this.model = ((modelLottoman)this.mainModel);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return EntityTexture;
	}
}
