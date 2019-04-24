package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.npc.model.modelPrimordialNPC;

@SideOnly(Side.CLIENT)
public class RenderPrimordialSpellbinder extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelPrimordialNPC model;

	public RenderPrimordialSpellbinder(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelPrimordialNPC)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderPrimordialSpellbinder.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/primordialSpellbinder.png");
	}
}
