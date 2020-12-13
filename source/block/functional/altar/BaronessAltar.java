package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.BaronessEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class BaronessAltar extends BossAltarBlock {
	public BaronessAltar() {
		super(MaterialColor.PURPLE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		BaronessEntity baroness = new BaronessEntity(AoAEntities.Mobs.BARONESS.get(), player.world);

		for (PlayerEntity pl : player.world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(blockPos).grow(5), entity -> entity != null && entity.isAlive())) {

			pl.addVelocity(Math.signum(pl.getPosX() - ((double)blockPos.getX() + 0.5d)) * 10, 0.1, Math.signum(pl.getPosZ() - ((double)blockPos.getZ() + 0.5d)) * 10);
			pl.velocityChanged = true;
		}

		baroness.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.addEntity(baroness);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.baroness.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.WARLOCK_GEM.get();
	}
}
