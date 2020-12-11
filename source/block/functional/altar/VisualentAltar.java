package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.VisualentEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class VisualentAltar extends BossAltarBlock {
	public VisualentAltar() {
		super(MaterialColor.PURPLE_TERRACOTTA);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		VisualentEntity visualent = new VisualentEntity(AoAEntities.Mobs.VISUALENT.get(), player.world);

		visualent.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.addEntity(visualent);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.visualent.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.OBSERVING_EYE.get();
	}
}
