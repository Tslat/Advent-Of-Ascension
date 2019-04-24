package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.precasia.EntityTerradon;
import net.nevermine.mob.model.precasia.modelTerradon;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTerradon extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	private static final ResourceLocation EntityTexture2;
	private float scale;
	protected modelTerradon model;

	public RenderTerradon(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelTerradon)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityTerradon par1EntityTerradon, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityTerradon thisentity = (EntityTerradon)entity;
		if (thisentity.isEntityInvulnerable()) {
			return RenderTerradon.EntityTexture2;
		}
		return RenderTerradon.EntityTexture1;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityTerradon)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/terradon.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/terradonChanged.png");
	}
}
