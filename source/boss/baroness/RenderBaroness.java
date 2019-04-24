package net.nevermine.boss.baroness;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBaroness extends RenderLiving {
	private static final ResourceLocation EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/baroness.png");
	private static final ResourceLocation EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/baronessInvuln.png");
	private static final ResourceLocation armoredWingedCreeperTextures = new ResourceLocation("nevermine:textures/mobs/baronArmor.png");
	protected modelBaroness model;
	private ModelBase BaronessModel = new modelBaroness();

	public RenderBaroness(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = ((modelBaroness)mainModel);
	}

	public void renderBaroness(EntityBaroness var1, double var2, double var4, double var6, float var8, float var9) {
		EternalBossStatus.setBossStatus(var1, true, 45);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	@Override
	public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		renderBaroness((EntityBaroness)var1, var2, var4, var6, var8, var9);
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		renderBaroness((EntityBaroness)var1, var2, var4, var6, var8, var9);
	}

	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
		return shouldRenderPass((EntityBaroness)p_77032_1_, p_77032_2_, p_77032_3_);
	}

	protected int inheritRenderPass(EntityBaroness p_77035_1_, int p_77035_2_, float p_77035_3_) {
		return -1;
	}

	protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
		return getColorMultiplier((EntityBaroness)p_77030_1_, p_77030_2_, p_77030_3_);
	}

	protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_) {
		return inheritRenderPass((EntityBaroness)p_77035_1_, p_77035_2_, p_77035_3_);
	}

	protected int getColorMultiplier(EntityBaroness p_77030_1_, float p_77030_2_, float p_77030_3_) {
		float f2 = 1;

		if ((int)(f2 * 10.0F) % 2 == 0) {
			return 0;
		}
		else {
			int i = (int)(f2 * 0.2F * 255.0F);

			if (i < 0) {
				i = 0;
			}

			if (i > 255) {
				i = 255;
			}

			short short1 = 255;
			short short2 = 255;
			short short3 = 255;
			return i << 24 | short1 << 16 | short2 << 8 | short3;
		}
	}

	protected int shouldRenderPass(EntityBaroness p_77032_1_, int p_77032_2_, float p_77032_3_) {
		if (p_77032_1_.getInvulnTicks() > 0) {
			if (p_77032_1_.isInvisible()) {
				GL11.glDepthMask(false);
			}
			else {
				GL11.glDepthMask(true);
			}

			if (p_77032_2_ == 1) {
				float f1 = (float)p_77032_1_.ticksExisted + p_77032_3_;
				bindTexture(armoredWingedCreeperTextures);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				float f2 = f1 * 0.01F;
				float f3 = f1 * 0.01F;
				GL11.glTranslatef(f2, f3, 0.0F);
				setRenderPassModel(BaronessModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				float f4 = 0.5F;
				GL11.glColor4f(f4, f4, f4, 3.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				return 1;
			}

			if (p_77032_2_ == 2) {
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}

		return -1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityBaroness thisentity = (EntityBaroness)entity;
		if (thisentity.isEntityInvulnerable()) {
			return EntityTexture2;
		}
		else {
			return EntityTexture1;
		}

	}

}
