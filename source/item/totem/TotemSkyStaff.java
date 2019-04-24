package net.nevermine.item.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.npc.totem.EntityIdolSkyStaff;

public class TotemSkyStaff extends net.minecraft.item.Item {
	public TotemSkyStaff() {
		setCreativeTab(Itemizer.TotemsTab);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		int var4 = 0;
		if (!par3World.isRemote) {
			if (player.capabilities.isCreativeMode) {
				while (var4 < 1) {
					EntityIdolSkyStaff var5 = new EntityIdolSkyStaff(par3World);
					var5.setPosition(par4, par5 + 1, par6);
					par3World.spawnEntityInWorld(var5);
					player.worldObj.playSoundAtEntity(player, "nevermine:TotemPlace", 1.0F, 1.0F);
					var4++;
				}
			}
			while (var4 < 1) {
				EntityIdolSkyStaff var5 = new EntityIdolSkyStaff(par3World);
				var5.setPosition(par4, par5 + 1, par6);
				par3World.spawnEntityInWorld(var5);
				player.worldObj.playSoundAtEntity(player, "nevermine:TotemPlace", 1.0F, 1.0F);
				var4++;
				par1ItemStack.stackSize -= 1;
			}
		}

		return true;
	}
}
