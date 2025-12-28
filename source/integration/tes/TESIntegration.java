package net.tslat.aoa3.integration.tes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.tes.api.TESAPI;
import net.tslat.tes.api.TESConstants;
import net.tslat.tes.api.TESParticle;
import net.tslat.tes.api.util.TESClientUtil;
import net.tslat.tes.core.particle.type.DamageParticle;
import net.tslat.tes.core.state.EntityState;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class TESIntegration {
	private static final ResourceLocation GUN_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_gun.png");
	private static final ResourceLocation ENERGY_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_energy.png");
	private static final ResourceLocation MAGIC_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_magic.png");
	private static final ResourceLocation FIRE_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_fire.png");
	private static final ResourceLocation ICE_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_freeze.png");
	private static final ResourceLocation MELEE_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_melee.png");
	private static final ResourceLocation ARROW_ICON = AdventOfAscension.id("textures/gui/misc/damage_indicator_arrow.png");

	public static void clientInit() {
		TESAPI.registerParticleSourceHandler(TESIntegration::handleTypedDamage);
	}

	public static void sendParticle(LivingEntity targetedEntity, double value, int colour) {
		TESConstants.NETWORKING.sendParticle(targetedEntity, value, colour);
	}

	private static boolean handleTypedDamage(EntityState entityState, float damageAmount, DamageSource damageSource, Consumer<TESParticle<?>> particleAdder) {
		ResourceLocation icon  = null;

		if (DamageUtil.isMeleeDamage(damageSource)) {
			icon = MELEE_ICON;
		}
		else if (damageSource.is(AoATags.DamageTypes.GUN)) {
			icon = GUN_ICON;
		}
		else if (damageSource.is(AoATags.DamageTypes.ENERGY)) {
			icon = ENERGY_ICON;
		}
		else if (damageSource.is(Tags.DamageTypes.IS_MAGIC)) {
			icon = MAGIC_ICON;
		}
		else if (damageSource.is(DamageTypeTags.IS_FIRE)) {
			icon = FIRE_ICON;
		}
		else if (damageSource.is(DamageTypeTags.IS_FREEZING)) {
			icon = ICE_ICON;
		}
		else if (damageSource.getDirectEntity() instanceof AbstractArrow) {
			icon = ARROW_ICON;
		}

		if (icon != null) {
			particleAdder.accept(new IconDamageParticle(entityState, entityState.getEntity().getEyePosition().toVector3f(), damageAmount, icon));

			return true;
		}

		return false;
	}

	static class IconDamageParticle extends DamageParticle {
		private final ResourceLocation icon;

		public IconDamageParticle(EntityState entityState, Vector3f position, double amount, ResourceLocation icon) {
			super(entityState, position, amount);

			this.icon = icon;
		}

		public IconDamageParticle(EntityState entityState, Vector3f position, Animation animation, double amount, ResourceLocation icon) {
			super(entityState, position, animation, amount);

			this.icon = icon;
		}

		public IconDamageParticle(EntityState entityState, Vector3f position, Animation animation, double amount, int lifespan, ResourceLocation icon) {
			super(entityState, position, animation, amount, lifespan);

			this.icon = icon;
		}

		@Override
		public void render(GuiGraphics guiGraphics, Minecraft mc, Font fontRenderer, float partialTick) {
			defaultedTextRender(mc, guiGraphics.pose(), this.prevPos, this.pos, partialTick, () -> {
				ColourUtil.Colour colour = new ColourUtil.Colour(getColour());
				int xOffset = (int)(-fontRenderer.width(Component.literal(this.text)) / 2f - 14f);

				guiGraphics.pose().translate(1, 5, 0);
				guiGraphics.pose().scale(0.75f, 0.75f, 0.75f);

				if (TESConstants.CONFIG.particleFontStyle() == TESClientUtil.TextRenderType.OUTLINED) {
					RenderUtil.prepRenderTexture(this.icon);
					guiGraphics.setColor(0, 0, 0, colour.alpha());

					for (float deltaX = -1; deltaX <= 1; deltaX++) {
						for (float deltaY = -1; deltaY <= 1; deltaY++) {
							if (deltaX == 0 ^ deltaY == 0)
								RenderUtil.renderTexture(guiGraphics.pose(), xOffset + deltaX * 0.75f, deltaY * 0.75f, 8, 8, 8, 8);
						}
					}
				}

				guiGraphics.pose().translate(0, 0, -0.001);
				guiGraphics.setColor(colour.red(), colour.green(), colour.blue(), colour.alpha());
				guiGraphics.blit(this.icon, xOffset, 0, 0, 0, 8, 8, 8, 8);
			});

			super.render(guiGraphics, mc, fontRenderer, partialTick);
		}
	}
}
