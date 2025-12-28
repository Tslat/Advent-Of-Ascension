package net.tslat.aoa3.player.ability.farming;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class HarvestReplant extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(BlockEvent.BreakEvent.class, BlockEvent.BreakEvent::getPlayer, serverOnly(this::handleBlockBreak)));

	public HarvestReplant(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HARVEST_REPLANT.get(), skill, data);
	}

	public HarvestReplant(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HARVEST_REPLANT.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleBlockBreak(BlockEvent.BreakEvent ev) {
		BlockState state = ev.getState();

		if (state.getBlock() instanceof CropBlock crop && !state.is(AoATags.Blocks.NO_HARVEST_REPLANT) && testAsChance()) {
			LevelAccessor level = ev.getLevel();
			BlockPos pos = ev.getPos();

			if (InventoryUtil.findItemForConsumption(ev.getPlayer(), stack -> ItemStack.isSameItemSameComponents(stack, crop.getCloneItemStack(level, pos, state)), 1, true))
				AoAScheduler.schedule(1, tick -> {
					if (level.getBlockState(pos).isAir()) {
						level.setBlock(pos, state.setValue(crop.getAgeProperty(), 0), Block.UPDATE_ALL);

						if (!level.isClientSide())
							PlayerUtil.giveXpToPlayer((ServerPlayer)ev.getPlayer(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getPlayer(), AoASkills.FARMING.get()), 3), false);
					}
				});
		}
	}
}
