package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class HarvestReplant extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK};

	public HarvestReplant(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HARVEST_REPLANT.get(), skill, data);
	}

	public HarvestReplant(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HARVEST_REPLANT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		BlockState state = ev.getState();

		if (state.getBlock() instanceof CropBlock crop && testAsChance()) {
			LevelAccessor world = ev.getWorld();
			BlockPos pos = ev.getPos();

			if (ItemUtil.findInventoryItem(ev.getPlayer(), crop.getCloneItemStack(world, pos, state), true, 1))
				AoAScheduler.scheduleSyncronisedTask(() -> {
					if (world.getBlockState(pos).isAir()) {
						world.setBlock(pos, state.setValue(CropBlock.AGE, 0), Block.UPDATE_ALL);

						if (!world.isClientSide())
							PlayerUtil.giveXpToPlayer((ServerPlayer)ev.getPlayer(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getPlayer(), AoASkills.FARMING.get()), 3), false);
					}
				}, 1);
		}
	}
}
