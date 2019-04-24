package net.nevermine.item.slab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.minion.entity.EntityPlateosaur;

public class PlateosaurSlab extends BaseSlab {
	public PlateosaurSlab(final int price, final int req) {
		super(price, req);
	}

	@Override
	public void useSlab(final World world, final ItemStack stack, final EntityPlayer player) {
		final EntityPlateosaur var4 = new EntityPlateosaur(player.worldObj, player);
		var4.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		player.worldObj.spawnEntityInWorld(var4);
		addBuff(var4, player);
	}
}
