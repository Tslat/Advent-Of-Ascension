package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.graw.EntityGraw;
import net.tslat.aoa3.entity.mobs.lelyetia.EntityFlye;
import net.tslat.aoa3.utils.StringUtil;

public class GrawAltar extends BossAltarBlock {
	public GrawAltar() {
		super("GrawAltar", "graw_altar");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = player.getHeldItem(hand);

		if (heldItem.getItem() == ItemRegister.orangeSpores || heldItem.getItem() == ItemRegister.yellowSpores) {
			if (!world.isRemote) {
				world.spawnEntity(new EntityFlye(world, pos));

				if (!player.capabilities.isCreativeMode)
					heldItem.shrink(1);
			}

			return true;
		}
		else {
			return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
		}
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityGraw graw = new EntityGraw(player.world);

		graw.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(graw);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.graw.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.guardiansEye;
	}
}
