package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.WorldUtil;

public class VisualentAltar extends BossAltarBlock {
	public VisualentAltar() {
		super(MaterialColor.TERRACOTTA_PURPLE);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*VisualentEntity visualent = new VisualentEntity(AoAMobs.VISUALENT.get(), player.level);

		visualent.teleportTo(blockPos.getX() + 0.5, blockPos.above().getY(), blockPos.getZ() + 0.5);
		player.level.addFreshEntity(visualent);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.VISUALENT.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.LUNALUS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.OBSERVING_EYE.get();
	}
}
