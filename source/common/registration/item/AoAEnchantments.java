package net.tslat.aoa3.common.registration.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.enchantment.entityeffect.ShrapnelBlast;
import net.tslat.aoa3.content.enchantment.valueeffect.Clamp;
import org.apache.commons.lang3.mutable.MutableFloat;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class AoAEnchantments {
	public static void init() {}

	public static final ResourceKey<Enchantment> ARCHMAGE = key("archmage");
	public static final ResourceKey<Enchantment> BRACE = key("brace");
	public static final ResourceKey<Enchantment> CONTROL = key("control");
	public static final ResourceKey<Enchantment> FORM = key("form");
	public static final ResourceKey<Enchantment> GREED = key("greed");
	public static final ResourceKey<Enchantment> INTERVENTION = key("intervention");
	public static final ResourceKey<Enchantment> RECHARGE = key("recharge");
	public static final ResourceKey<Enchantment> SEVER = key("sever");
	public static final ResourceKey<Enchantment> SHELL = key("shell");
	public static final ResourceKey<Enchantment> FRAGMENT = key("fragment");

	public static final DeferredHolder<MapCodec<? extends EnchantmentValueEffect>, MapCodec<Clamp>> CLAMP = registerValueProvider("clamp", () -> Clamp.CODEC);
	public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<ShrapnelBlast>> SHRAPNEL_BLAST = registerEntityEffect("shrapnel_blast", () -> ShrapnelBlast.CODEC);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> CRIT_DAMAGE = registerEffectType("crit_damage", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_DAMAGE).listOf()));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> SPIRIT_CONSUMPTION = registerEffectType("spirit_consumption", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> RUNE_COST = registerEffectType("rune_cost", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> RECOIL = registerEffectType("recoil", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> PELLET_SPREAD = registerEffectType("pellet_spread", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> AMMO_COST = registerEffectType("ammo_cost", builder -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf()));

	public static float modifyCritDamage(ServerLevel level, ItemStack stack, Entity target, DamageSource source, float damage) {
		final MutableFloat mutableFloat = new MutableFloat(damage);

		EnchantmentHelper.runIterationOnItem(stack, (enchant, enchantLevel) -> enchant.value().modifyDamageFilteredValue(CRIT_DAMAGE.get(), level, enchantLevel, stack, target, source, mutableFloat));

		return mutableFloat.floatValue();
	}

	public static float modifySpiritConsumption(Level level, ItemStack stack, float spiritCost) {
		if (!(level instanceof ServerLevel serverLevel))
			return spiritCost;

		final MutableFloat mutableFloat = new MutableFloat(spiritCost);

		EnchantmentHelper.runIterationOnItem(stack, (enchant, enchantLevel) -> enchant.value().modifyItemFilteredCount(SPIRIT_CONSUMPTION.get(), serverLevel, enchantLevel, stack, mutableFloat));

		return mutableFloat.floatValue();
	}

	public static int modifyRuneCost(ServerLevel level, ItemStack stack, int runeCost) {
		final MutableFloat mutableFloat = new MutableFloat(runeCost);

		EnchantmentHelper.runIterationOnItem(stack, (enchant, enchantLevel) -> enchant.value().modifyItemFilteredCount(RUNE_COST.get(), level, enchantLevel, stack, mutableFloat));

		return Mth.ceil(mutableFloat.floatValue());
	}

	public static float modifyRecoil(ServerLevel level, ItemStack stack, float recoil) {
		final MutableFloat mutableFloat = new MutableFloat(recoil);

		EnchantmentHelper.runIterationOnItem(stack, (enchant, enchantLevel) -> enchant.value().modifyItemFilteredCount(RECOIL.get(), level, enchantLevel, stack, mutableFloat));

		return mutableFloat.floatValue();
	}

	public static float modifyPelletSpread(ServerLevel level, ItemStack stack, float spread) {
		final MutableFloat mutableFloat = new MutableFloat(spread);

		EnchantmentHelper.runIterationOnItem(stack, (enchant, enchantLevel) -> enchant.value().modifyItemFilteredCount(PELLET_SPREAD.get(), level, enchantLevel, stack, mutableFloat));

		return mutableFloat.floatValue();
	}

	public static int modifyAmmoCost(Level level, ItemStack stack, int cost) {
		if (!(level instanceof ServerLevel serverLevel))
			return cost;

		final MutableFloat mutableFloat = new MutableFloat(cost);

		EnchantmentHelper.runIterationOnItem(stack, (enchant, enchantLevel) -> enchant.value().modifyItemFilteredCount(AMMO_COST.get(), serverLevel, enchantLevel, stack, mutableFloat));

		return Mth.ceil(mutableFloat.floatValue());
	}

	private static ResourceKey<Enchantment> key(String id) {
		return ResourceKey.create(Registries.ENCHANTMENT, AdventOfAscension.id(id));
	}

	private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> registerEffectType(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
		return AoARegistries.ENCHANTMENT_EFFECT_COMPONENTS.register(id, () -> builder.apply(DataComponentType.builder()).build());
	}

	private static <T extends EnchantmentValueEffect> DeferredHolder<MapCodec<? extends EnchantmentValueEffect>, MapCodec<T>> registerValueProvider(String id, Supplier<MapCodec<T>> codec) {
		return AoARegistries.ENCHANTMENT_VALUE_EFFECTS.register(id, codec);
	}

	private static <T extends EnchantmentEntityEffect> DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<T>> registerEntityEffect(String id, Supplier<MapCodec<T>> codec) {
		return AoARegistries.ENCHANTMENT_ENTITY_EFFECTS.register(id, codec);
	}
}
