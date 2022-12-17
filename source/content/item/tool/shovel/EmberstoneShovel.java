package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class EmberstoneShovel extends BaseShovel implements LootModifyingItem {
	public EmberstoneShovel() {
		super(AoATiers.EMBERSTONE, -4f, AttackSpeed.SHOVEL);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1)
			return;

		ServerLevel level = lootContext.getLevel();
		BlockPos pos = new BlockPos(lootContext.getParamOrNull(LootContextParams.ORIGIN));

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);
			Optional<SmeltingRecipe> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem().copy();

				smeltedStack.setCount(smeltedStack.getCount() * stack.getCount());
				existingLoot.set(i, smeltedStack);
				block.popExperience(level, pos, (int)smeltRecipe.get().getExperience());

				ServerParticlePacket particlePacket = new ServerParticlePacket();

				for (int x = 0; x < 5; x++) {
					particlePacket.particle(ParticleTypes.FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + RandomUtil.randomValueUpTo(1), pos.getZ() + RandomUtil.randomValueUpTo(1));
				}

				AoAPackets.messageNearbyPlayers(particlePacket, level, Vec3.atCenterOf(pos), 32);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
