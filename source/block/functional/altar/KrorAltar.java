package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.kror.EntityKror;
import net.tslat.aoa3.utils.StringUtil;

public class KrorAltar extends BossAltarBlock {
	public KrorAltar() {
		super("KrorAltar", "kror_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityKror kror = new EntityKror(player.world);

		kror.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(kror);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.kror.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.boulderDash;
	}
}
