package net.nevermine.item.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.npc.totem.EntityIdolTidalGreatblade;

public class TotemTidalGreatblade extends Item {
	public TotemTidalGreatblade() {
		setCreativeTab(Itemizer.TotemsTab);
	}

	public boolean onItemUse(final ItemStack par1ItemStack, final EntityPlayer player, final World par3World, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
		int var4 = 0;
		if (!par3World.isRemote) {
			if (player.capabilities.isCreativeMode) {
				while (var4 < 1) {
					final EntityIdolTidalGreatblade var5 = new EntityIdolTidalGreatblade(par3World);
					var5.setPosition((double)par4, (double)(par5 + 1), (double)par6);
					par3World.spawnEntityInWorld(var5);
					player.worldObj.playSoundAtEntity(player, "nevermine:TotemPlace", 1.0f, 1.0f);
					++var4;
				}
			}
			else {
				while (var4 < 1) {
					final EntityIdolTidalGreatblade var5 = new EntityIdolTidalGreatblade(par3World);
					var5.setPosition((double)par4, (double)(par5 + 1), (double)par6);
					par3World.spawnEntityInWorld(var5);
					player.worldObj.playSoundAtEntity(player, "nevermine:TotemPlace", 1.0f, 1.0f);
					++var4;
					--par1ItemStack.stackSize;
				}
			}
		}
		return true;
	}
}
