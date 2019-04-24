package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.clunkhead.EntityClunkhead;
import net.tslat.aoa3.utils.StringUtil;

public class ClunkheadAltar extends BossAltarBlock {
	public ClunkheadAltar() {
		super("ClunkheadAltar", "clunkhead_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityClunkhead clunkhead = new EntityClunkhead(player.world);

		clunkhead.setPositionAndUpdate(blockPos.getX() - 4, blockPos.up().getY(), blockPos.getZ() + 5);
		player.world.spawnEntity(clunkhead);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.clunkhead.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.megaRuneStone;
	}
}
