package net.tslat.aoa3.integration.tes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.tes.api.TESAPI;
import net.tslat.tes.api.TESParticle;
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

	private static boolean handleTypedDamage(EntityState entityState, float damageAmount, DamageSource damageSource, Consumer<TESParticle<?>> particleAdder) {
		if (AoAConfigs.INTEGRATIONS.tesEnabled.get()) {
			Vec3 entityPos = entityState.getEntity().getEyePosition();
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
			else if (damageSource.is(AoATags.DamageTypes.MAGIC)) {
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
				particleAdder.accept(new IconDamageParticle(entityState, new Vector3f((float)entityPos.x(), (float)entityPos.y(), (float)entityPos.z()), damageAmount, icon));

				return true;
			}
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

				guiGraphics.setColor(colour.red(), colour.green(), colour.blue(), colour.alpha());
				guiGraphics.pose().translate(0, 4, 0);
				guiGraphics.blit(this.icon, -16, 0, 0, 0, 8, 8, 8, 8);
			});

			super.render(guiGraphics, mc, fontRenderer, partialTick);
		}
	}
}
