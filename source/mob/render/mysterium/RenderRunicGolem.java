package net.nevermine.mob.render.mysterium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.mysterium.EntityRunicGolem;
import net.nevermine.mob.model.mysterium.modelRunicGolem;

@SideOnly(Side.CLIENT)
public class RenderRunicGolem extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	protected modelRunicGolem model;

	public RenderRunicGolem(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelRunicGolem)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityRunicGolem thisentity = (EntityRunicGolem)entity;
		return RenderRunicGolem.EntityTexture1;
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/runicgolem.png");
	}
}
