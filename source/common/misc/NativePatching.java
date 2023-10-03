package net.tslat.aoa3.common.misc;

import it.unimi.dsi.fastutil.floats.FloatObjectPair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.tslat.aoa3.common.registration.item.AoAItems;

import java.util.List;
import java.util.function.Supplier;

public final class NativePatching {
	private static final List<FloatObjectPair<Supplier<Item>>> BLOCK_COMPOSTABLES = new ObjectArrayList<>(250);

	public static void init() {
		((RangedAttribute)Attributes.MAX_HEALTH).maxValue = Double.MAX_VALUE;
		((RangedAttribute)Attributes.ATTACK_KNOCKBACK).maxValue = Double.MAX_VALUE;
		((RangedAttribute)Attributes.ARMOR_TOUGHNESS).maxValue = Double.MAX_VALUE;
	}

	public static void addCompostableBlock(Supplier<? extends Block> block, float chance) {
		BLOCK_COMPOSTABLES.add(FloatObjectPair.of(chance, () -> block.get().asItem()));
	}

	public static void lateInit() {
		patchInComposterSupport();
	}

	private static void patchInComposterSupport() {
		BLOCK_COMPOSTABLES.forEach(pair -> ComposterBlock.add(pair.leftFloat(), pair.right().get()));

		ComposterBlock.add(0.3f, AoAItems.BUBBLE_BERRY_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.CHILLI_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.FLORACLE_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.GOLDICAP_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.HEART_FRUIT_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.LUNACRIKE_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.LUNALON_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.LUNA_GLOBE_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.ROSIDON_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.TEA_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.THORNY_PLANT_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.TRILLIAD_SEEDS.get());
		ComposterBlock.add(0.3f, AoAItems.GREEN_MANURE_SEEDS.get());

		ComposterBlock.add(0.65f, AoAItems.TRILLIAD_LEAVES.get());
		ComposterBlock.add(0.65f, AoAItems.ROSIDONS.get());
		ComposterBlock.add(0.65f, AoAItems.LUNACRIKE.get());
		ComposterBlock.add(0.65f, AoAItems.LUNA_GLOBE.get());
		ComposterBlock.add(0.65f, AoAItems.LUNALONS.get());
		ComposterBlock.add(0.65f, AoAItems.MAGIC_MARANG.get());
		ComposterBlock.add(0.65f, AoAItems.MYSTIC_SHROOMS.get());
		ComposterBlock.add(0.65f, AoAItems.GOLDICAP_PETALS.get());
		ComposterBlock.add(0.65f, AoAItems.HEART_FRUIT.get());
		ComposterBlock.add(0.65f, AoAItems.CHILLI.get());
		ComposterBlock.add(0.65f, AoAItems.BUBBLE_BERRIES.get());
	}
}
