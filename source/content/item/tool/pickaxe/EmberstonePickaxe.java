package net.tslat.aoa3.content.item.tool.pickaxe;

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
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class EmberstonePickaxe extends BasePickaxe implements LootModifyingItem {
	public EmberstonePickaxe() {
		super(AoATiers.EMBERSTONE.adjusted(AdventOfAscension.id("emberstone_pickaxe")).damage(7.5f));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1)
			return;

		ServerLevel level = lootContext.getLevel();
		Vec3 pos = lootContext.getParamOrNull(LootContextParams.ORIGIN);

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);
			Optional<SmeltingRecipe> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem(level.registryAccess()).copy();

				smeltedStack.setCount(smeltedStack.getCount() * stack.getCount());
				existingLoot.set(i, smeltedStack);
				block.popExperience(level, BlockPos.containing(pos), (int)smeltRecipe.get().getExperience());

				ServerParticlePacket particlePacket = new ServerParticlePacket(
						ParticleBuilder.forPos(ParticleTypes.FLAME,
								() -> new Vec3(pos.x + RandomUtil.randomValueUpTo(1), pos.y + RandomUtil.randomValueUpTo(1), pos.z + RandomUtil.randomValueUpTo(1)))
								.spawnNTimes(5));

				AoAPackets.messageNearbyPlayers(particlePacket, level, pos.add(0.5f, 0.5f, 0.5f), 32);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
