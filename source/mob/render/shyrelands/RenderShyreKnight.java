package net.nevermine.mob.render.shyrelands;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.shyrelands.modelShyreKnight;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class RenderShyreKnight extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/shyreKnight.png");
	protected modelShyreKnight model;

	public RenderShyreKnight(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		this.model = ((modelShyreKnight)this.mainModel);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return EntityTexture;
	}
}
