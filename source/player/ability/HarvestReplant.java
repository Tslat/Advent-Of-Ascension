package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.util.Constants;
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

	public HarvestReplant(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.HARVEST_REPLANT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		BlockState state = ev.getState();

		if (state.getBlock() instanceof CropsBlock && testAsChance()) {
			IWorld world = ev.getWorld();
			BlockPos pos = ev.getPos();

			if (ItemUtil.findInventoryItem(ev.getPlayer(), ((CropsBlock)state.getBlock()).getCloneItemStack(world, pos, state), true, 1))
				AoAScheduler.scheduleSyncronisedTask(() -> {
					if (world.getBlockState(pos).isAir(world, pos)) {
						world.setBlock(pos, state.setValue(((CropsBlock)state.getBlock()).getAgeProperty(), 0), Constants.BlockFlags.DEFAULT);

						if (!world.isClientSide())
							PlayerUtil.giveXpToPlayer((ServerPlayerEntity)ev.getPlayer(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getPlayer(), AoASkills.FARMING.get()), 3), false);
					}
				}, 1);
		}
	}
}
