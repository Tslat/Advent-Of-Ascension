package net.nevermine.event.creature;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Deities.Luxon;
import static net.nevermine.container.PlayerContainer.Skills.Infusion;

public class PixonCollection {
	public static void collect(final EntityAnimal e, final EntityPlayer p, final int exp, final int req, final Item i) {
		if (e.getHealth() <= 0)
			return;

		final ItemStack stack = p.inventory.getCurrentItem();

		if (!p.worldObj.isRemote && stack != null && p.ticksExisted % 15 == 0) {
			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (stack.getItem() == Itemizer.StoneBowl) {
				if (cont.getLevel(Infusion) >= req) {
					cont.addExperience(exp, Infusion);
					stack.damageItem(1, p);
					e.setHealth(e.getHealth() - 10.0f);
					p.worldObj.playSoundAtEntity(p, "nevermine:PixonCollect", 1.85f, 1.0f);

					if (!p.inventory.addItemStackToInventory(new ItemStack(i)))
						p.dropItem(i, 1);

					if (p.dimension == 0 && p.worldObj.isDaytime())
						cont.adjustTribute(Luxon, 5);
				}
			}
			else if (stack.getItem() == Itemizer.DiamondBowl) {
				if (cont.getLevel(Infusion) >= req + 10) {
					final int num = (int)Math.ceil(Math.min(e.getHealth(), 100) / 10.0f);
					stack.damageItem(1, p);
					cont.addExperience(exp * num, Infusion);
					e.setHealth(-20.0f);
					p.worldObj.playSoundAtEntity(p, "nevermine:PixonCollect", 1.85f, 1.0f);

					for (int j = 0; j < num; j++) {
						if (!p.inventory.addItemStackToInventory(new ItemStack(i)))
							p.dropItem(i, 1);
					}

					if (p.dimension == 0 && p.worldObj.isDaytime())
						cont.adjustTribute(Luxon, 5 * num);
				}
			}

			if (p instanceof EntityPlayerMP)
				((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
		}
	}
}
