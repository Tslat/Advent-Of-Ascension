package net.tslat.aoa3.content.loottable.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.IPlantable;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.misc.FertilisedFarmland;
import net.tslat.aoa3.util.ItemUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;

public class FertilisedFarmlandLootModifier extends LootModifier {
	public static final Codec<FertilisedFarmlandLootModifier> CODEC = RecordCodecBuilder.create(builder -> codecStart(builder).apply(builder, FertilisedFarmlandLootModifier::new));

	public FertilisedFarmlandLootModifier(LootItemCondition[] conditions) {
		super(conditions);
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}

	@NotNull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (generatedLoot.isEmpty())
			return generatedLoot;

		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
		Vec3 pos = context.getParamOrNull(LootContextParams.ORIGIN);

		if (state != null && pos != null && state.getBlock() instanceof CropBlock && state.getBlock() != AoABlocks.GREEN_MANURE.get()) {
			BlockPos farmlandPos = BlockPos.containing(pos).below();
			BlockState belowBlock = context.getLevel().getBlockState(farmlandPos);

			if (belowBlock.getBlock() instanceof FertilisedFarmland && belowBlock.getBlock().canSustainPlant(belowBlock, context.getLevel(), farmlandPos, Direction.UP, (IPlantable)state.getBlock())) {
				int increaseAmount = belowBlock.getValue(FertilisedFarmland.WELL_FERTILISED) ? 2 : 1;
				Item seeds = ((CropBlock)state.getBlock()).getCloneItemStack(context.getLevel(), BlockPos.containing(pos), state).getItem();

				for (ListIterator<ItemStack> iterator = generatedLoot.listIterator(); iterator.hasNext();) {
					ItemStack itStack = iterator.next();

					if (itStack.getItem() == seeds)
						continue;

					ItemUtil.increaseStackSize(itStack, increaseAmount).forEach(iterator::add);
				}

				context.getLevel().setBlock(farmlandPos, (increaseAmount == 2 ? AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState() : Blocks.FARMLAND.defaultBlockState()).setValue(FarmBlock.MOISTURE, belowBlock.getValue(FarmBlock.MOISTURE)), Block.UPDATE_ALL);
			}
		}

		return generatedLoot;
	}
}
