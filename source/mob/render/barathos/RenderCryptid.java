package net.nevermine.mob.render.barathos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.barathos.modelCryptid;

@SideOnly(Side.CLIENT)
public class RenderCryptid extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCryptid model;

	public RenderCryptid(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCryptid)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCryptid.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/cryptid.png");
	}
}
