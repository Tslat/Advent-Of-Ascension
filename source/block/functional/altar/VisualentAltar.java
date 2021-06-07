package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.VisualentEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class VisualentAltar extends BossAltarBlock {
	public VisualentAltar() {
		super(MaterialColor.TERRACOTTA_PURPLE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		VisualentEntity visualent = new VisualentEntity(AoAEntities.Mobs.VISUALENT.get(), player.level);

		visualent.teleportTo(blockPos.getX() + 0.5, blockPos.above().getY(), blockPos.getZ() + 0.5);
		player.level.addFreshEntity(visualent);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VISUALENT.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.LUNALUS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.OBSERVING_EYE.get();
	}
}
