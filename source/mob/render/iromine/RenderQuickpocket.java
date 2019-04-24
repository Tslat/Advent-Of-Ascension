package net.nevermine.mob.render.iromine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.iromine.modelQuickpocket;

@SideOnly(Side.CLIENT)
public class RenderQuickpocket extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelQuickpocket model;

	public RenderQuickpocket(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelQuickpocket)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderQuickpocket.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/quickpocket.png");
	}
}
