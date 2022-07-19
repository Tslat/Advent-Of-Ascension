package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.WorldUtil;

public class MechBotAltar extends BossAltarBlock {
	public MechBotAltar() {
		super(MaterialColor.COLOR_YELLOW);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*MechbotEntity mechbot = new MechbotEntity(AoAMobs.MECHBOT.get(), player.level);

		mechbot.moveTo(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.level.addFreshEntity(mechbot);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.MECHBOT.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.IROMINE.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.GOLD_SPRING.get();
	}
}
