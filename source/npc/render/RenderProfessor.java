package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.npc.model.modelProfessor;

@SideOnly(Side.CLIENT)
public class RenderProfessor extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelProfessor model;

	public RenderProfessor(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelProfessor)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderProfessor.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/professor.png");
	}
}
