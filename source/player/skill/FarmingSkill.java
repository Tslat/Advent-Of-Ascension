package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.StemGrownBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class FarmingSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK, ListenerType.ANIMAL_BREED};

	public FarmingSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.FARMING.get(), plData, jsonData);
	}

	public FarmingSkill(CompoundNBT nbtData) {
		super(AoASkills.FARMING.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		if (canGainXp(true) && BlockUtil.canPlayerHarvest(ev.getState(), ev.getPlayer(), ev.getWorld(), ev.getPos())) {
			Block block = ev.getState().getBlock();
			int xpTime = 0;

			if (block instanceof CropsBlock) {
				xpTime = ((CropsBlock)block).isMaxAge(ev.getState()) ? 7 * ((CropsBlock)block).getMaxAge() : 0;
			}
			else if (block instanceof StemGrownBlock) {
				xpTime = 12;
			}

			if (xpTime > 0)
				adjustXp(PlayerUtil.getTimeBasedXpForLevel(getLevel(true), xpTime), false, false);
		}
	}

	@Override
	public void handleAnimalBreed(BabyEntitySpawnEvent ev) {
		if (!canGainXp(true))
			return;

		adjustXp(PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 600), false, false);
	}
}
