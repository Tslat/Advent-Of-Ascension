package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

public class CraexxeusAltar extends BossAltarBlock {
	public CraexxeusAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*CraexxeusEntity craexxeus = new CraexxeusEntity(AoAMobs.CRAEXXEUS.get(), player.level);

		int offsetX = player.getRandom().nextBoolean() ? -11 : 11;
		int offsetZ = player.getRandom().nextBoolean() ? -11 : 11;

		craexxeus.moveTo(blockPos.getX() + offsetX, blockPos.getY() + 5, blockPos.getZ() + offsetZ, 0, 0);
		player.level.addFreshEntity(craexxeus);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.CRAEXXEUS.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.SHYRELANDS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.ANCIENT_RING.get();
	}
}
