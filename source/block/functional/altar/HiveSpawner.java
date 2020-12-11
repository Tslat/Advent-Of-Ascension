package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.HiveKingEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class HiveSpawner extends BossAltarBlock {
	public HiveSpawner() {
		super(MaterialColor.RED_TERRACOTTA);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		HiveKingEntity hiveKing = new HiveKingEntity(player.world, 0);

		hiveKing.setLocationAndAngles(blockPos.getX() + 0.99, blockPos.getY() + 1.050000001d, blockPos.getZ() + 0.99, 0, 0);
		player.world.addEntity(hiveKing);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.hiveKing.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return player.world.getEntitiesWithinAABB(HiveKingEntity.class, new AxisAlignedBB(pos).grow(20)).isEmpty();
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.HIVE_EGG.get();
	}
}
