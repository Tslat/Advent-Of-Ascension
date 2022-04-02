package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.common.registration.item.AoAItems;

public class HiveSpawner extends BossAltarBlock {
	public HiveSpawner() {
		super(MaterialColor.TERRACOTTA_RED);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*HiveKingEntity hiveKing = new HiveKingEntity(player.level, 0);

		hiveKing.moveTo(blockPos.getX() + 0.99, blockPos.getY() + 1.050000001d, blockPos.getZ() + 0.99, 0, 0);
		player.level.addFreshEntity(hiveKing);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.HIVE_KING.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return false;//return WorldUtil.isWorld(player.level, AoADimensions.BARATHOS.key) && player.level.getEntitiesOfClass(HiveKingEntity.class, new AABB(pos).inflate(20)).isEmpty();
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.HIVE_EGG.get();
	}
}
