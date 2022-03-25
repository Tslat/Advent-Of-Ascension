package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.content.entity.boss.ElusiveEntity;
import net.tslat.aoa3.content.entity.mob.misc.ElusiveCloneEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class IllusionAltar extends BossAltarBlock {
	public IllusionAltar() {
		super(MaterialColor.COLOR_PURPLE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		ElusiveEntity elusive = new ElusiveEntity(AoAEntities.Mobs.ELUSIVE.get(), player.level);
		ElusiveCloneEntity clone = new ElusiveCloneEntity(elusive);
		double posX = (int)(blockPos.getX() + player.getLookAngle().x * -10);
		double posZ = (int)(blockPos.getZ() + player.getLookAngle().z * -10);

		clone.moveTo(posX, player.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, 64, posZ)).getY(), posZ, player.yHeadRot, 0);

		posX += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);
		posZ += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);

		elusive.moveTo(posX, player.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, 64, posZ)).getY(), posZ, 0, 0);
		player.level.addFreshEntity(elusive);
		player.level.addFreshEntity(clone);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.ELUSIVE.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		return WorldUtil.isWorld(player.level, AoADimensions.ABYSS.key);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.STARING_EYE.get();
	}
}
