package net.tslat.aoa3.client.model;

import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.item.weapon.bow.BaseBow;
import net.tslat.aoa3.item.weapon.crossbow.BaseCrossbow;

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
		);registerCrossbows(
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

		registerItemProperty(AoAItems.EXP_FLASK.get(), "filled", (stack, world, entity) -> {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			return cap.getValue() <= 0 ? 0 : 1;
		});

		registerItemProperty(AoAWeapons.PARALYZER.get(), "firing", (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
		registerItemProperty(AoAWeapons.PARALYZER.get(), "firing_tick_modulo", (stack, world, entity) -> {
			if (entity == null || stack != entity.getUseItem())
				return 0;

			return entity.getUseItemRemainingTicks() % 3;
		});

		registerItemProperty(AoAWeapons.KNIGHTS_GUARD.get(), "blocking", (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
	}

	private static void registerBows(BaseBow... bows) {
		for (BaseBow bow : bows) {
			registerItemProperty(bow, "pull", (stack, world, entity) -> {
				if (entity == null || entity.getUseItem() != stack)
					return 0;

				return ((BaseBow)stack.getItem()).getDrawSpeedMultiplier() * (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
			});
			registerItemProperty(bow, "pulling", (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
		}
	}

	private static void registerCrossbows(BaseCrossbow... crossbows) {
		for (BaseCrossbow crossbow : crossbows) {
			registerItemProperty(crossbow, "pull", (stack, world, entity) -> {
				if (entity == null || CrossbowItem.isCharged(stack))
					return 0;

				return (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(stack);
			});
			registerItemProperty(crossbow, "pulling", (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !CrossbowItem.isCharged(stack) ? 1 : 0);
			registerItemProperty(crossbow, "charged", (stack, world, entity) -> entity != null && CrossbowItem.isCharged(stack) ? 1 : 0);
			registerItemProperty(crossbow, "firework", (stack, world, entity) -> entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
		}
	}

	private static void registerItemProperty(Item item, String propertyName, IItemPropertyGetter propertyProvider) {
		ItemModelsProperties.register(item, new ResourceLocation(propertyName), propertyProvider);
	}
}
