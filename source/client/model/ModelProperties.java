package net.tslat.aoa3.client.model;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.content.item.weapon.bow.BaseBow;
import net.tslat.aoa3.content.item.weapon.crossbow.BaseCrossbow;

public final class ModelProperties {
	public static void init() {
		registerBows(
				(BaseBow)AoAWeapons.ALACRITY_BOW.get(),
				(BaseBow)AoAWeapons.ANCIENT_BOW.get(),
				(BaseBow)AoAWeapons.ATLANTIC_BOW.get(),
				(BaseBow)AoAWeapons.BARON_BOW.get(),
				(BaseBow)AoAWeapons.BOREIC_BOW.get(),
				(BaseBow)AoAWeapons.DAYBREAKER_BOW.get(),
				(BaseBow)AoAWeapons.DEEP_BOW.get(),
				(BaseBow)AoAWeapons.EXPLOSIVE_BOW.get(),
				(BaseBow)AoAWeapons.HAUNTED_BOW.get(),
				(BaseBow)AoAWeapons.ICE_BOW.get(),
				(BaseBow)AoAWeapons.INFERNAL_BOW.get(),
				(BaseBow)AoAWeapons.JUSTICE_BOW.get(),
				(BaseBow)AoAWeapons.LUNAR_BOW.get(),
				(BaseBow)AoAWeapons.MECHA_BOW.get(),
				(BaseBow)AoAWeapons.NIGHTMARE_BOW.get(),
				(BaseBow)AoAWeapons.POISON_BOW.get(),
				(BaseBow)AoAWeapons.PREDATIOUS_BOW.get(),
				(BaseBow)AoAWeapons.PRIMORDIAL_BOW.get(),
				(BaseBow)AoAWeapons.ROSIDIAN_BOW.get(),
				(BaseBow)AoAWeapons.RUNIC_BOW.get(),
				(BaseBow)AoAWeapons.SCREAMER_BOW.get(),
				(BaseBow)AoAWeapons.SHYREGEM_BOW.get(),
				(BaseBow)AoAWeapons.SKELETAL_BOW.get(),
				(BaseBow)AoAWeapons.SKYDRIVER_BOW.get(),
				(BaseBow)AoAWeapons.SLINGSHOT.get(),
				(BaseBow)AoAWeapons.SOULFIRE_BOW.get(),
				(BaseBow)AoAWeapons.SPECTRAL_BOW.get(),
				(BaseBow)AoAWeapons.SPEED_BOW.get(),
				(BaseBow)AoAWeapons.SUNSHINE_BOW.get(),
				(BaseBow)AoAWeapons.TOXIN_BOW.get(),
				(BaseBow)AoAWeapons.VOID_BOW.get(),
				(BaseBow)AoAWeapons.WEAKEN_BOW.get(),
				(BaseBow)AoAWeapons.WITHER_BOW.get()
		);

		registerCrossbows(
				(BaseCrossbow)AoAWeapons.CORAL_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.LUNAR_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.MECHA_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.PYRO_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.ROSIDIAN_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.SKELETAL_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.SPECTRAL_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.TROLLS_CROSSBOW.get(),
				(BaseCrossbow)AoAWeapons.VIRAL_CROSSBOW.get()
		);

		registerExpFlask();
		registerParalyzer();
		registerKnightsGuard();
		registerGuardiansSword();
		registerRods();
	}

	private static void registerBows(BaseBow... bows) {
		for (BaseBow bow : bows) {
			registerItemProperty(bow, "pull", (stack, world, entity, seed) -> {
				if (entity == null || entity.getUseItem() != stack)
					return 0;

				return ((BaseBow)stack.getItem()).getDrawSpeedMultiplier() * (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
			});
			registerItemProperty(bow, "pulling", (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
		}
	}

	private static void registerCrossbows(BaseCrossbow... crossbows) {
		for (BaseCrossbow crossbow : crossbows) {
			registerItemProperty(crossbow, "pull", (stack, world, entity, seed) -> {
				if (entity == null || CrossbowItem.isCharged(stack))
					return 0;

				return (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(stack);
			});
			registerItemProperty(crossbow, "pulling", (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !CrossbowItem.isCharged(stack) ? 1 : 0);
			registerItemProperty(crossbow, "charged", (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) ? 1 : 0);
			registerItemProperty(crossbow, "firework", (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
		}
	}

	private static void registerItemProperty(Item item, String propertyName, ItemPropertyFunction propertyProvider) {
		ItemProperties.register(item, new ResourceLocation(propertyName), propertyProvider);
	}

	private static void registerExpFlask() {
		registerItemProperty(AoATools.EXP_FLASK.get(), "filled", (stack, world, entity, seed) -> {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			return cap.getValue() <= 0 ? 0 : 1;
		});
	}

	private static void registerParalyzer() {
		registerItemProperty(AoAWeapons.PARALYZER.get(), "firing", (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
		registerItemProperty(AoAWeapons.PARALYZER.get(), "firing_tick_modulo", (stack, world, entity, seed) -> {
			if (entity == null || stack != entity.getUseItem())
				return 0;

			return entity.getUseItemRemainingTicks() % 3;
		});
	}

	private static void registerKnightsGuard() {
		registerItemProperty(AoAWeapons.KNIGHTS_GUARD.get(), "blocking", (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
	}

	private static void registerGuardiansSword() {
		registerItemProperty(AoAWeapons.GUARDIANS_SWORD.get(), "charged", (stack, world, entity, seed) -> {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			return cap.getValue() <= 0 ? 0 : 1;
		});
	}

	private static void registerRods() {
		ItemPropertyFunction predicateHandler = (stack, world, entity, seed) -> {
			if (entity == null)
				return 0;

			return (entity.getMainHandItem() == stack || entity.getOffhandItem() == stack && !(entity.getMainHandItem().getItem() instanceof FishingRodItem)) && entity instanceof Player && ((Player)entity).fishing != null ? 1 : 0;
		};

		registerItemProperty(AoATools.HAULING_ROD.get(), "cast", predicateHandler);
		registerItemProperty(AoATools.GOLDEN_ROD.get(), "cast", predicateHandler);
		registerItemProperty(AoATools.LIGHT_ROD.get(), "cast", predicateHandler);
		registerItemProperty(AoATools.THERMALLY_INSULATED_ROD.get(), "cast", predicateHandler);
	}
}
