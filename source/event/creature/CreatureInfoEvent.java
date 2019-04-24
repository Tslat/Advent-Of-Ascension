package net.nevermine.event.creature;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.nevermine.boss.EntityBoss;
import net.nevermine.event.player.KeyPressEvent;
import net.nevermine.mob.placement.*;
import org.lwjgl.opengl.GL11;

public class CreatureInfoEvent {
	@SubscribeEvent
	public void render(final RenderLivingEvent.Specials.Pre evt) {
		if (KeyPressEvent.cinfo) {
			int numthings = 0;
			if (evt.entity instanceof EntityHunter) {
				++numthings;
			}
			if (evt.entity instanceof EntityNoRange) {
				++numthings;
			}
			if (evt.entity instanceof EntityNoBows) {
				++numthings;
			}
			if (evt.entity instanceof EntityNoMelee) {
				++numthings;
			}
			if (evt.entity instanceof EntityNoFire) {
				++numthings;
			}
			if (evt.entity instanceof EntityNoMagic) {
				++numthings;
			}
			if (evt.entity instanceof EntityNoExplosions) {
				++numthings;
			}
			if (evt.entity instanceof EntityBoss || evt.entity instanceof EntityObject) {
				numthings = 0;
			}
			boolean hunter = false;
			boolean range = false;
			boolean bows = false;
			boolean melee = false;
			boolean fire = false;
			boolean magic = false;
			boolean explo = false;
			for (float size = -numthings * 0.45f / 2.0f + 0.2f; size <= numthings * 0.95f / 2.0f; size += 0.45f) {
				if (evt.entity instanceof EntityHunter && !hunter) {
					drawTexture("nevermine:textures/gui/hunterMob.png", size, evt);
					hunter = true;
				}
				else if (evt.entity instanceof EntityNoRange && !range) {
					drawTexture("nevermine:textures/gui/gunImmune.png", size, evt);
					range = true;
				}
				else if (evt.entity instanceof EntityNoBows && !bows) {
					drawTexture("nevermine:textures/gui/rangedImmune.png", size, evt);
					bows = true;
				}
				else if (evt.entity instanceof EntityNoMelee && !melee) {
					drawTexture("nevermine:textures/gui/meleeImmune.png", size, evt);
					melee = true;
				}
				else if (evt.entity instanceof EntityNoFire && !fire) {
					drawTexture("nevermine:textures/gui/fireImmune.png", size, evt);
					fire = true;
				}
				else if (evt.entity instanceof EntityNoMagic && !magic) {
					drawTexture("nevermine:textures/gui/magicImmune.png", size, evt);
					magic = true;
				}
				else if (evt.entity instanceof EntityNoExplosions && !explo) {
					drawTexture("nevermine:textures/gui/explosionImmune.png", size, evt);
					explo = true;
				}
			}
		}
	}

	private void drawTexture(final String texture, final float xoff, final RenderLivingEvent.Specials.Pre evt) {
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(texture));
		GL11.glEnable(32826);
		final Tessellator tessellator = Tessellator.instance;
		final float minU = 0.0f;
		final float maxU = 1.0f;
		final float minV = 0.0f;
		final float maxV = 1.0f;
		final float f7 = 1.0f;
		final float f8 = 0.5f;
		final float f9 = 0.25f;
		GL11.glTranslated(evt.x, 0.15 + evt.y + evt.entity.boundingBox.maxY - evt.entity.boundingBox.minY, evt.z);
		GL11.glRotatef(180.0f - RenderManager.instance.playerViewY, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(-RenderManager.instance.playerViewX, 1.0f, 0.0f, 0.0f);
		GL11.glTranslated((double)xoff, 0.0, 0.0);
		GL11.glScalef(0.45f, 0.45f, 0.45f);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0f, 1.0f, 0.0f);
		tessellator.addVertexWithUV((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)minU, (double)maxV);
		tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)maxU, (double)minV);
		tessellator.addVertexWithUV((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)minU, (double)minV);
		tessellator.draw();
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}
}
