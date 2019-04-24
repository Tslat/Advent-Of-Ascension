package net.nevermine.projectiles.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.projectiles.enemy.EntityNethengeicWitherShot;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNethengeicWitherShot extends Render {
	private static final ResourceLocation invulnerableWitherTextures;
	private static final ResourceLocation witherTextures;
	private final ModelSkeletonHead skeletonHeadModel;
	private static final String __OBFID = "CL_00001035";

	public RenderNethengeicWitherShot() {
		skeletonHeadModel = new ModelSkeletonHead();
	}

	private float func_82400_a(final float par1, final float par2, final float par3) {
		float f3;
		for (f3 = par2 - par1; f3 < -180.0f; f3 += 360.0f) {
		}
		while (f3 >= 180.0f) {
			f3 -= 360.0f;
		}
		return par1 + par3 * f3;
	}

	public void doRender(final EntityNethengeicWitherShot par1EntityWitherSkull, final double par2, final double par4, final double par6, final float par8, final float par9) {
		GL11.glPushMatrix();
		GL11.glDisable(2884);
		final float f2 = func_82400_a(par1EntityWitherSkull.prevRotationYaw, par1EntityWitherSkull.rotationYaw, par9);
		final float f3 = par1EntityWitherSkull.prevRotationPitch + (par1EntityWitherSkull.rotationPitch - par1EntityWitherSkull.prevRotationPitch) * par9;
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		final float f4 = 0.0625f;
		GL11.glEnable(32826);
		GL11.glScalef(-1.0f, -1.0f, 1.0f);
		GL11.glEnable(3008);
		bindEntityTexture(par1EntityWitherSkull);
		skeletonHeadModel.render(par1EntityWitherSkull, 0.0f, 0.0f, 0.0f, f2, f3, f4);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getEntityTexture(final EntityNethengeicWitherShot par1EntityWitherSkull) {
		return par1EntityWitherSkull.isInvulnerable() ? RenderNethengeicWitherShot.invulnerableWitherTextures : RenderNethengeicWitherShot.witherTextures;
	}

	protected ResourceLocation getEntityTexture(final Entity par1Entity) {
		return getEntityTexture((EntityNethengeicWitherShot)par1Entity);
	}

	public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
		doRender((EntityNethengeicWitherShot)par1Entity, par2, par4, par6, par8, par9);
	}

	static {
		invulnerableWitherTextures = new ResourceLocation("nevermine:textures/projectiles/nethengeicskull.png");
		witherTextures = new ResourceLocation("nevermine:textures/projectiles/nethengeicskull.png");
	}
}
