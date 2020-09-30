package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.baroness.EntityBaroness;
import net.tslat.aoa3.utils.StringUtil;

public class BaronessAltar extends BossAltarBlock {
	public BaronessAltar() {
		super("BaronessAltar", "baroness_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityBaroness baroness = new EntityBaroness(player.world);

		for (EntityPlayer pl : player.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(blockPos).grow(5), entity -> entity != null && entity.isEntityAlive())) {

			pl.addVelocity(Math.signum(pl.posX - ((double)blockPos.getX() + 0.5d)) * 10, 0.1, Math.signum(pl.posZ - ((double)blockPos.getZ() + 0.5d)) * 10);
			pl.velocityChanged = true;
		}

		baroness.setPositionAndUpdate(blockPos.getX() + 0.5, blockPos.up().getY(), blockPos.getZ() + 0.5);
		player.world.spawnEntity(baroness);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.baroness.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.WARLOCK_GEM;
	}
}
