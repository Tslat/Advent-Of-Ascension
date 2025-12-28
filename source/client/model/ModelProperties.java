package net.tslat.aoa3.client.model;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.tslat.aoa3.common.registration.item.*;
import net.tslat.aoa3.content.item.weapon.bow.AoABow;
import net.tslat.aoa3.content.item.weapon.crossbow.AoACrossbow;
import net.tslat.aoa3.library.object.container.AmmoVoidPouchComponent;
import net.tslat.aoa3.library.object.container.CachedEntity;

public final class ModelProperties {
	public static void init() {
		registerBows(
				(AoABow)AoAWeapons.ALACRITY_BOW.get(),
				(AoABow)AoAWeapons.ANCIENT_BOW.get(),
				(AoABow)AoAWeapons.ATLANTIC_BOW.get(),
				(AoABow)AoAWeapons.BARON_BOW.get(),
				(AoABow)AoAWeapons.BOREIC_BOW.get(),
				(AoABow)AoAWeapons.DAYBREAKER_BOW.get(),
				(AoABow)AoAWeapons.DEEP_BOW.get(),
				(AoABow)AoAWeapons.EXPLOSIVE_BOW.get(),
				(AoABow)AoAWeapons.HAUNTED_BOW.get(),
				(AoABow)AoAWeapons.ICE_BOW.get(),
				(AoABow)AoAWeapons.INFERNAL_BOW.get(),
				(AoABow)AoAWeapons.JUSTICE_BOW.get(),
				(AoABow)AoAWeapons.LUNAR_BOW.get(),
				(AoABow)AoAWeapons.MECHA_BOW.get(),
				(AoABow)AoAWeapons.NIGHTMARE_BOW.get(),
				(AoABow)AoAWeapons.POISON_BOW.get(),
				(AoABow)AoAWeapons.PREDATIOUS_BOW.get(),
				(AoABow)AoAWeapons.PRIMORDIAL_BOW.get(),
				(AoABow)AoAWeapons.ROSIDIAN_BOW.get(),
				(AoABow)AoAWeapons.RUNIC_BOW.get(),
				(AoABow)AoAWeapons.SCREAMER_BOW.get(),
				(AoABow)AoAWeapons.SHYREGEM_BOW.get(),
				(AoABow)AoAWeapons.SKELETAL_BOW.get(),
				(AoABow)AoAWeapons.SKYDRIVER_BOW.get(),
				(AoABow)AoAWeapons.SLINGSHOT.get(),
				(AoABow)AoAWeapons.SOULFIRE_BOW.get(),
				(AoABow)AoAWeapons.SPECTRAL_BOW.get(),
				(AoABow)AoAWeapons.SPEED_BOW.get(),
				(AoABow)AoAWeapons.SUNSHINE_BOW.get(),
				(AoABow)AoAWeapons.TOXIN_BOW.get(),
				(AoABow)AoAWeapons.VOID_BOW.get(),
				(AoABow)AoAWeapons.WEAKEN_BOW.get(),
				(AoABow)AoAWeapons.WITHER_BOW.get());

		registerCrossbows(
				(AoACrossbow)AoAWeapons.CORAL_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.LUNAR_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.MECHA_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.PYRO_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.ROSIDIAN_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.SKELETAL_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.SPECTRAL_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.TROLLS_CROSSBOW.get(),
				(AoACrossbow)AoAWeapons.VIRAL_CROSSBOW.get()
		);

		registerExpFlask();
		registerAmmoVoidPouch();
		registerStasisCapsule();
		registerParalyzer();
		registerKnightsGuard();
		registerGuardiansSword();
		registerRods();
		registerHorns();
	}

	private static void registerBows(AoABow... bows) {
		for (AoABow bow : bows) {
			registerItemProperty(bow, "pull", (stack, world, entity, seed) -> {
				if (entity == null || entity.getUseItem() != stack)
					return 0;

				return ((AoABow)stack.getItem()).getDrawSpeedMultiplier(stack) * (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
			});
			registerItemProperty(bow, "pulling", (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
		}
	}

	private static void registerCrossbows(AoACrossbow... crossbows) {
		for (AoACrossbow crossbow : crossbows) {
			registerItemProperty(crossbow, "pull", (stack, world, entity, seed) -> {
				if (entity == null || CrossbowItem.isCharged(stack))
					return 0;

				return (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(stack, entity);
			});
			registerItemProperty(crossbow, "pulling", (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !CrossbowItem.isCharged(stack) ? 1 : 0);
			registerItemProperty(crossbow, "charged", (stack, world, entity, seed) -> entity != null && CrossbowItem.isCharged(stack) ? 1 : 0);
			registerItemProperty(crossbow, "firework", (stack, world, entity, seed) -> {
				ChargedProjectiles loadedProjectiles = stack.get(DataComponents.CHARGED_PROJECTILES);

				return loadedProjectiles != null && loadedProjectiles.contains(Items.FIREWORK_ROCKET) ? 1 : 0;
			});

		}
	}

	private static void registerItemProperty(Item item, String propertyName, ItemPropertyFunction propertyProvider) {
		ItemProperties.register(item, ResourceLocation.read(propertyName).getOrThrow(), propertyProvider);
	}

	private static void registerExpFlask() {
		registerItemProperty(AoAArtificeDevices.EXP_FLASK.get(), "filled", (stack, world, entity, seed) -> stack.getOrDefault(AoADataComponents.CHARGE, 0f) <= 0 ? 0 : 1);
	}

	private static void registerAmmoVoidPouch() {
		registerItemProperty(AoAArtificeDevices.AMMO_VOID_POUCH.get(), "empty", (stack, world, entity, seed) -> stack.getOrDefault(AoADataComponents.AMMO_VOID_POUCH, AmmoVoidPouchComponent.EMPTY).contents().isEmpty() ? 1 : 0);
	}

	private static void registerStasisCapsule() {
		registerItemProperty(AoAArtificeDevices.STASIS_CAPSULE.get(), "filled", (stack, world, entity, seed) -> stack.getOrDefault(AoADataComponents.STORED_ENTITY, CachedEntity.EMPTY).isEmpty() ? 0 : 1);
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
		registerItemProperty(AoAWeapons.GUARDIANS_SWORD.get(), "charged", (stack, world, entity, seed) -> stack.getOrDefault(AoADataComponents.CHARGE, 0f) <= 0 ? 0 : 1);
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

	private static void registerHorns() {
		ItemPropertyFunction predicateHandler = (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0;

		registerItemProperty(AoAItems.BONE_HORN.get(), "tooting", predicateHandler);
		registerItemProperty(AoAItems.WARPED_HORN.get(), "tooting", predicateHandler);
	}
}
