package net.nevermine.mob.render.shyrelands;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.shyrelands.modelSysker;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class RenderSysker extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/sysker.png");
	protected modelSysker model;

	public RenderSysker(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		this.model = ((modelSysker)this.mainModel);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return EntityTexture;
	}
}
