package net.nevermine.mob.render.immortallis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.immortallis.EntityUrioh;
import net.nevermine.mob.model.immortallis.modelUrioh;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderUrioh extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelUrioh model;
	private float scale;
	float health;

	public RenderUrioh(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelUrioh)mainModel;
		scale = 1.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityUrioh)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityUrioh urioh, final float par2) {
		health = urioh.getHealth();
		if (health < 10.0f) {
			GL11.glScalef(scale * 0.1f, scale * 0.1f, scale * 0.1f);
		}
		else if (health < 25.0f) {
			GL11.glScalef(scale * 0.2f, scale * 0.2f, scale * 0.2f);
		}
		else if (health < 40.0f) {
			GL11.glScalef(scale * 0.4f, scale * 0.4f, scale * 0.4f);
		}
		else if (health < 60.0f) {
			GL11.glScalef(scale * 0.6f, scale * 0.6f, scale * 0.6f);
		}
		else {
			GL11.glScalef(scale, scale, scale);
		}
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderUrioh.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/urioh.png");
	}
}
