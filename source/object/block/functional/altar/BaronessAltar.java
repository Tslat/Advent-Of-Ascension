package net.tslat.aoa3.object.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.boss.BaronessEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class BaronessAltar extends BossAltarBlock {
	public BaronessAltar() {
		super(MaterialColor.COLOR_PURPLE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		BaronessEntity baroness = new BaronessEntity(AoAEntities.Mobs.BARONESS.get(), player.level);

		for (PlayerEntity pl : player.level.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB(blockPos).inflate(5), entity -> entity != null && entity.isAlive())) {

			pl.push(Math.signum(pl.getX() - ((double)blockPos.getX() + 0.5d)) * 10, 0.1, Math.signum(pl.getZ() - ((double)blockPos.getZ() + 0.5d)) * 10);
			pl.hurtMarked = true;
		}

		baroness.teleportTo(blockPos.getX() + 0.5, blockPos.above().getY(), blockPos.getZ() + 0.5);
		player.level.addFreshEntity(baroness);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.BARONESS.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.BARATHOS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.WARLOCK_GEM.get();
	}
}
