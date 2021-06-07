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
import net.tslat.aoa3.entity.boss.MechbotEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class MechBotAltar extends BossAltarBlock {
	public MechBotAltar() {
		super(MaterialColor.COLOR_YELLOW);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		MechbotEntity mechbot = new MechbotEntity(AoAEntities.Mobs.MECHBOT.get(), player.level);

		mechbot.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(mechbot);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.MECHBOT.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.IROMINE.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.GOLD_SPRING.get();
	}
}
