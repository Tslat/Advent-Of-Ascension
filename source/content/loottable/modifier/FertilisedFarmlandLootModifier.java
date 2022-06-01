package net.tslat.aoa3.content.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.functional.misc.FertilisedFarmland;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.ListIterator;

public class FertilisedFarmlandLootModifier extends LootModifier {
	public FertilisedFarmlandLootModifier(ILootCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (generatedLoot.isEmpty())
			return generatedLoot;

		BlockState state = context.getParamOrNull(LootParameters.BLOCK_STATE);
		Vector3d pos = context.getParamOrNull(LootParameters.ORIGIN);

		if (state != null && pos != null && state.getBlock() instanceof CropsBlock && state.getBlock() != AoABlocks.GREEN_MANURE.get()) {
			BlockPos farmlandPos = new BlockPos(pos).below();
			BlockState belowBlock = context.getLevel().getBlockState(farmlandPos);

			if (belowBlock.getBlock() instanceof FertilisedFarmland && belowBlock.getBlock().canSustainPlant(belowBlock, context.getLevel(), farmlandPos, Direction.UP, (IPlantable)state.getBlock())) {
				int increaseAmount = belowBlock.getValue(FertilisedFarmland.WELL_FERTILISED) ? 2 : 1;
				Item seeds = ((CropsBlock)state.getBlock()).getCloneItemStack(context.getLevel(), new BlockPos(pos), state).getItem();

				for (ListIterator<ItemStack> iterator = generatedLoot.listIterator(); iterator.hasNext();) {
					ItemStack itStack = iterator.next();

					if (itStack.getItem() == seeds)
						continue;

					ItemUtil.increaseStackSize(itStack, increaseAmount).forEach(iterator::add);
				}

				context.getLevel().setBlock(farmlandPos, (increaseAmount == 2 ? AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState() : Blocks.FARMLAND.defaultBlockState()).setValue(FarmlandBlock.MOISTURE, belowBlock.getValue(FarmlandBlock.MOISTURE)), Constants.BlockFlags.DEFAULT);
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<FertilisedFarmlandLootModifier> {
		@Override
		public FertilisedFarmlandLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			return new FertilisedFarmlandLootModifier(lootConditions);
		}

		@Override
		public JsonObject write(FertilisedFarmlandLootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);

			json.addProperty("type", getRegistryName().toString());

			return json;
		}
	}
}
