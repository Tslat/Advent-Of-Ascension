package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.boss.DracyonEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class DracyonAltar extends BossAltarBlock {
	public DracyonAltar() {
		super(MaterialColor.COLOR_BLUE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		DracyonEntity dracyon = new DracyonEntity(AoAEntities.Mobs.DRACYON.get(), player.level);

		dracyon.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(dracyon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.DRACYON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.LBOREAN.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.CALL_OF_THE_DRAKE.get();
	}
}
