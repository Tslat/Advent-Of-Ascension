package net.tslat.aoa3.content.loottable.function;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.loot.AoALootFunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnchantSpecific extends LootItemConditionalFunction {
	public static final Codec<EnchantSpecific> CODEC = RecordCodecBuilder.create(builder -> commonFields(builder).and(
			Codec.simpleMap(
					AoARegistries.ENCHANTMENTS.lookupCodec(),
					Codec.INT,
					AoARegistries.ENCHANTMENTS.registry().get()).fieldOf("enchantments").forGetter(EnchantSpecific::getEnchantments))
			.apply(builder, EnchantSpecific::new));

	private final Map<Enchantment, Integer> enchants;

	protected EnchantSpecific(List<LootItemCondition> lootConditions, Map<Enchantment, Integer> enchantments) {
		super(lootConditions);

		this.enchants = enchantments;
	}

	@Override
	public LootItemFunctionType getType() {
		return AoALootFunctions.ENCHANT_SPECIFIC.get();
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		EnchantmentHelper.setEnchantments(this.enchants, stack);

		return stack;
	}

	public Map<Enchantment, Integer> getEnchantments() {
		return ImmutableMap.copyOf(this.enchants);
	}

	public static Builder<?> builder(EnchantmentInstance... enchantments) {
		return simpleBuilder((conditions) -> new EnchantSpecific(conditions, Arrays.stream(enchantments).collect(Collectors.toMap(instance -> instance.enchantment, instance -> instance.level))));
	}
}
