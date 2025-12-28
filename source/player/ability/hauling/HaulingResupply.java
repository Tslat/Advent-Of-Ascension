package net.tslat.aoa3.player.ability.hauling;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.content.skill.hauling.HaulingEntity;
import net.tslat.aoa3.event.custom.events.HaulingSpawnEntityEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class HaulingResupply extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(HaulingSpawnEntityEvent.class, serverOnly(this::handleHaulingEntitySpawn)));

	public HaulingResupply(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HAULING_RESUPPLY.get(), skill, data);
	}

	public HaulingResupply(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HAULING_RESUPPLY.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	protected MutableComponent getScalingDescriptionComponent(int precision) {
		return LocaleUtil.getAbilityValueDesc(baseValue != 0, perLevelMod != 0, isPercent(),
				NumberUtil.roundToNthDecimalPlace(baseValue * (isPercent() ? 100 : 1), precision),
				NumberUtil.roundToNthDecimalPlace(perLevelMod * (isPercent() ? 100 : 1), precision),
				NumberUtil.roundToNthDecimalPlace(Math.max(0, getScaledValue() * (isPercent() ? 100 : 1)), precision));
	}

	private void handleHaulingEntitySpawn(final HaulingSpawnEntityEvent ev) {
		if (ev.getSpawnPool() != null && testAsChance()) {
			List<Supplier<ItemStack>> repairIngredients = new ObjectArrayList<>();

			InventoryUtil.findItem(ev.getEntity(), stack -> {
				checkRepairable(stack, repairIngredients::add);

				return false;
			});

			if (!repairIngredients.isEmpty()) {
				repairIngredients.add(() -> new ItemStack(Items.EXPERIENCE_BOTTLE, RandomUtil.numberBetween(1, 2)));
				repairIngredients.add(() -> new ItemStack(AoAItems.CHUM.get(), RandomUtil.numberBetween(1, 3)));

				ev.setNewEntity(new HaulingEntity(Either.left(RandomUtil.selection(repairIngredients).get()), Optional.empty(), 0, 0, 0).apply(ev.getEntity().level(), ev.getSpawnPool().getFluidType() == NeoForgeMod.LAVA_TYPE.value()));
			}
		}
	}

	private static void checkRepairable(ItemStack inventoryStack, Consumer<Supplier<ItemStack>> repairStackConsumer) {
		if (inventoryStack.isDamaged() && inventoryStack.isRepairable()) {
			if (inventoryStack.getItem() instanceof TieredItem tieredItem) {
				repairStackConsumer.accept(() -> RandomUtil.selection(tieredItem.getTier().getRepairIngredient().getItems()));
			}
			else {
				repairStackConsumer.accept(AoAItems.MAGIC_REPAIR_DUST::toStack);
			}
		}

		switch (inventoryStack.getItem()) {
			case AoAGun gun when gun.getDefaultAmmo(inventoryStack).filter(set -> set.size() > 0).isPresent() -> repairStackConsumer.accept(() -> gun.getDefaultAmmo(inventoryStack).get().getRandomElement(RandomUtil.RANDOM).get().value().getDefaultInstance());
			case ProjectileWeaponItem projectileWeapon -> repairStackConsumer.accept(() -> projectileWeapon.getDefaultCreativeAmmo(null, inventoryStack));
			case AoAStaff<?> staff -> repairStackConsumer.accept(() -> {
				Reference2IntMap<Item> runes = staff.getRuneCost(inventoryStack);
				Item rune = RandomUtil.selection(runes.keySet().toArray(new Item[0]));

				return new ItemStack(rune, RandomUtil.numberBetween(1, runes.getInt(rune) * 2));
			});
			default -> {}
		}
	}
}
