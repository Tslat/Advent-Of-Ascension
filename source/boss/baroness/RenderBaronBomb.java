package net.nevermine.boss.baroness;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBaronBomb extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/baronBomb.png");
	protected modelBaronBomb model;

	public RenderBaronBomb(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = ((modelBaronBomb)mainModel);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return EntityTexture;
	}

}