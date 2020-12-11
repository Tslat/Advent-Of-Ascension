package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.ElusiveEntity;
import net.tslat.aoa3.entity.mob.misc.ElusiveCloneEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class IllusionAltar extends BossAltarBlock {
	public IllusionAltar() {
		super(MaterialColor.PURPLE);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		ElusiveEntity elusive = new ElusiveEntity(AoAEntities.Mobs.ELUSIVE.get(), player.world);
		ElusiveCloneEntity clone = new ElusiveCloneEntity(elusive);
		double posX = (int)(blockPos.getX() + player.getLookVec().x * -10);
		double posZ = (int)(blockPos.getZ() + player.getLookVec().z * -10);

		clone.setLocationAndAngles(posX, player.world.getHeight(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, 64, posZ)).getY(), posZ, player.rotationYawHead, 0);

		posX += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);
		posZ += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);

		elusive.setLocationAndAngles(posX, player.world.getHeight(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, 64, posZ)).getY(), posZ, 0, 0);
		player.world.addEntity(elusive);
		player.world.addEntity(clone);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.elusive.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.STARING_EYE.get();
	}
}
