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

public class IllusionAltar extends BossAltarBlock {
	public IllusionAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		/*ElusiveEntity elusive = new ElusiveEntity(AoAMobs.ELUSIVE.get(), player.level);
		ElusiveCloneEntity clone = new ElusiveCloneEntity(elusive);
		double posX = (int)(blockPos.getX() + player.getLookAngle().x * -10);
		double posZ = (int)(blockPos.getZ() + player.getLookAngle().z * -10);

		clone.moveTo(posX, player.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, 64, posZ)).getY(), posZ, player.yHeadRot, 0);

		posX += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);
		posZ += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);

		elusive.moveTo(posX, player.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, 64, posZ)).getY(), posZ, 0, 0);
		player.level.addFreshEntity(elusive);
		player.level.addFreshEntity(clone);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.ELUSIVE.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);*/
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level(), AoADimensions.ABYSS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.STARING_EYE.get();
	}
}
