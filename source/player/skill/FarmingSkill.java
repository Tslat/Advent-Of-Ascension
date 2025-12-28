package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class FarmingSkill extends AoASkill.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(BlockEvent.BreakEvent.class, BlockEvent.BreakEvent::getPlayer, serverOnly(this::handleBlockBreak)),
			listener(BabyEntitySpawnEvent.class, BabyEntitySpawnEvent::getCausedByPlayer, serverOnly(this::handleAnimalBreed)));

	public FarmingSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.FARMING.get(), plData, jsonData);
	}

	public FarmingSkill(CompoundTag nbtData) {
		super(AoASkills.FARMING.get(), nbtData);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleBlockBreak(final BlockEvent.BreakEvent ev) {
		final BlockState state = ev.getState();

		if (canGainXp(true) && BlockUtil.canPlayerHarvest(state, ev.getPlayer(), ev.getLevel(), ev.getPos())) {
			int xpTime = switch (state.getBlock()) {
				case CropBlock crop -> crop.isMaxAge(state) ? 7 * crop.getMaxAge() : 0;
				case NetherWartBlock netherWart -> state.getValue(NetherWartBlock.AGE) == 3 ? 21 : 0;
				default -> state.is(BlockTags.CROPS) && !(state.getBlock() instanceof StemBlock) ? 12 : 0;
			};

			if (xpTime > 0)
				PlayerUtil.giveTimeBasedXpToPlayer((ServerPlayer)ev.getPlayer(), type(), xpTime,  false);
		}
	}

	private void handleAnimalBreed(final BabyEntitySpawnEvent ev) {
		if (!canGainXp(true))
			return;

		PlayerUtil.giveTimeBasedXpToPlayer((ServerPlayer)getPlayer(), type(), 600, false);
	}
}
