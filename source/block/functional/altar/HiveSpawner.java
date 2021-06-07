package net.tslat.aoa3.block.functional.altar;

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
import net.tslat.aoa3.entity.boss.HiveKingEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class HiveSpawner extends BossAltarBlock {
	public HiveSpawner() {
		super(MaterialColor.TERRACOTTA_RED);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		HiveKingEntity hiveKing = new HiveKingEntity(player.level, 0);

		hiveKing.moveTo(blockPos.getX() + 0.99, blockPos.getY() + 1.050000001d, blockPos.getZ() + 0.99, 0, 0);
		player.level.addFreshEntity(hiveKing);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.HIVE_KING.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.BARATHOS.key) && player.level.getEntitiesOfClass(HiveKingEntity.class, new AxisAlignedBB(pos).inflate(20)).isEmpty();
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.HIVE_EGG.get();
	}
}
