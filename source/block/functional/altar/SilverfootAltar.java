package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.silverfoot.EntitySilverfoot;
import net.tslat.aoa3.utils.StringUtil;

public class SilverfootAltar extends BossAltarBlock {
	public SilverfootAltar() {
		super("SilverfootAltar", "silverfoot_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntitySilverfoot silverfoot = new EntitySilverfoot(player.world);

		silverfoot.setLocationAndAngles(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, -90, 0);
		player.world.spawnEntity(silverfoot);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.silverfoot.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.SILVRO_COIN;
	}
}
