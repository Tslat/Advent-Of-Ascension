package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.hiveking.EntityHiveKing;
import net.tslat.aoa3.utils.StringUtil;

public class HiveSpawner extends BossAltarBlock {
	public HiveSpawner() {
		super("HiveSpawner", "hive_spawner");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityHiveKing hiveKing = new EntityHiveKing(player.world, 0);

		hiveKing.setLocationAndAngles(blockPos.getX() + 0.99, blockPos.getY() + 1.050000001d, blockPos.getZ() + 0.99, 0, 0);
		player.world.spawnEntity(hiveKing);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.hiveKing.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected boolean checkActivationConditions(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos pos) {
		return player.world.getEntitiesWithinAABB(EntityHiveKing.class, new AxisAlignedBB(pos).grow(20)).isEmpty();
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.HIVE_EGG;
	}
}
