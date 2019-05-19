package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.client.gui.KeyBinder;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import org.lwjgl.opengl.GL11;

import java.util.TreeSet;

public class EntityPropertiesRenderer {
	@SubscribeEvent
	public void renderIcons(final RenderLivingEvent.Specials.Pre ev) {
		if (!KeyBinder.statusCreatureStats || !(ev.getEntity() instanceof SpecialPropertyEntity))
				return;

		EntityLivingBase entity = ev.getEntity();

		if (entity.posX == 0 && entity.posY == 0 && entity.posZ == 0)
			return;

		TreeSet<Enums.MobProperties> mobProps = ((SpecialPropertyEntity)entity).getMobProperties();
		float yOffset = 0;
		float xOffset = Math.max(-5, -mobProps.size()) * 0.45f / 2.0f + 0.2f;
		float rowMax = 0.875f;

		for (Enums.MobProperties prop : mobProps) {
			switch (prop) {
				case GUN_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/gun_immunity.png"), xOffset, yOffset, ev);
					break;
				case FIRE_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/fire_immunity.png"), xOffset, yOffset, ev);
					break;
				case RANGED_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/ranged_immunity.png"), xOffset, yOffset, ev);
					break;
				case MAGIC_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/magic_immunity.png"), xOffset, yOffset, ev);
					break;
				case MELEE_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/melee_immunity.png"), xOffset, yOffset, ev);
					break;
				case HUNTER_ENTITY:
					if (AdventGuiTabPlayer.getSkillLevel(Enums.Skills.HUNTER) >= ((HunterEntity)entity).getHunterReq()) {
						renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/hunter_entity_available.png"), xOffset, yOffset, ev);
					}
					else {
						renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/hunter_entity_unavailable.png"), xOffset, yOffset, ev);
					}
					break;
				case STATUS_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/status_immunity.png"), xOffset, yOffset, ev);
					break;
				case BLASTER_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/blaster_immunity.png"), xOffset, yOffset, ev);
					break;
				case EXPLOSION_IMMUNE:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/explosion_immunity.png"), xOffset, yOffset, ev);
					break;
				case SPECIAL_COMBAT_ENTITY:
					renderIcon(new ResourceLocation("aoa3", "textures/gui/mobproperties/special_combat_entity.png"), xOffset, yOffset, ev);
					break;
			}

			xOffset += 0.45f;

			if (xOffset > rowMax) {
				yOffset += 0.45f;
				xOffset = Math.max(-5, -mobProps.size()) * 0.45f / 2.0f + 0.2f;
			}
		}
	}

	private void renderIcon(ResourceLocation texture, float xOffset, float yOffset, RenderLivingEvent.Specials.Pre event, String... msg) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buffer = tess.getBuffer();
		Minecraft mc = Minecraft.getMinecraft();

		GlStateManager.translate(event.getX(), 0.2d + event.getY() + event.getEntity().getEntityBoundingBox().maxY - event.getEntity().getEntityBoundingBox().minY, event.getZ());
		GlStateManager.rotate(180f - mc.getRenderManager().playerViewY, 0, 1, 0);
		GlStateManager.rotate(-mc.getRenderManager().playerViewX, 1, 0, 0);
		GlStateManager.translate(xOffset, yOffset, 0);
		GlStateManager.scale(0.45f, 0.45f, 0.45f);
		GL11.glDisable(GL11.GL_LIGHTING);
		buffer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		buffer.pos(-0.5d, -0.25d, 0).tex(0, 1).normal(0.0f, 1.0f, 0.0f).endVertex();
		buffer.pos(0.5d, -0.25d, 0).tex(1, 1).normal(0.0f, 1.0f, 0.0f).endVertex();
		buffer.pos(0.5d, 0.75d, 0).tex(1, 0).normal(0.0f, 1.0f, 0.0f).endVertex();
		buffer.pos(-0.5d, 0.75d, 0).tex(0, 0).normal(0.0f, 1.0f, 0.0f).endVertex();
		tess.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.popMatrix();
	}
}
