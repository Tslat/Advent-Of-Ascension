package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.elusive.EntityElusive;
import net.tslat.aoa3.utils.StringUtil;

public class IllusionAltar extends BossAltarBlock {
	public IllusionAltar() {
		super("IllusionAltar", "illusion_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityElusive elusive = new EntityElusive(player.world);

		int posX = (int)(blockPos.getX() + player.getLookVec().x * -6);
		int posZ = (int)(blockPos.getZ() + player.getLookVec().z * -6);

		elusive.setLocationAndAngles(posX, player.world.getHeight(posX, posZ), posZ, 0, 0);
		player.world.spawnEntity(elusive);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.elusive.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.staringEye;
	}
}
