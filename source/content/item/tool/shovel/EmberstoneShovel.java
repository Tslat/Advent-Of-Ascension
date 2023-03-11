package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class EmberstoneShovel extends BaseShovel implements LootModifyingItem {
	public EmberstoneShovel() {
		super(ItemUtil.customItemTier(2000, 10.0f, 5.5f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || !harvestedBlock.isToolEffective(ToolType.SHOVEL))
			return;

		ServerWorld world = lootContext.getLevel();
		BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootParameters.ORIGIN));

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);
			Optional<FurnaceRecipe> smeltRecipe = world.getRecipeManager().getRecipeFor(IRecipeType.SMELTING, new Inventory(stack), world);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem().copy();

				smeltedStack.setCount(smeltedStack.getCount() * stack.getCount());
				existingLoot.set(i, smeltedStack);
				block.popExperience(world, pos, (int)smeltRecipe.get().getExperience());

				ServerParticlePacket particlePacket = new ServerParticlePacket();

				for (int x = 0; x < 5; x++) {
					particlePacket.particle(ParticleTypes.FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + RandomUtil.randomValueUpTo(1), pos.getZ() + RandomUtil.randomValueUpTo(1));
				}

				AoAPackets.messageNearbyPlayers(particlePacket, world, Vector3d.atCenterOf(pos), 32);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
