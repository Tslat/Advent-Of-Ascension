package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.DracyonEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class DracyonAltar extends BossAltarBlock {
	public DracyonAltar() {
		super(MaterialColor.COLOR_BLUE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		DracyonEntity dracyon = new DracyonEntity(AoAEntities.Mobs.DRACYON.get(), player.level);

		dracyon.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(dracyon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.dracyon.spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.CALL_OF_THE_DRAKE.get();
	}
}
