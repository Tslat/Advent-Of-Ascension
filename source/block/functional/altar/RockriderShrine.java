package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.rockrider.EntityRockRider;
import net.tslat.aoa3.utils.StringUtil;

public class RockriderShrine extends BossAltarBlock {
	public RockriderShrine() {
		super("RockriderShrine", "rockrider_shrine");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityRockRider rockRider = new EntityRockRider(player.world);

		rockRider.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(rockRider);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.rockrider.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.heavyBoulder;
	}
}
