package net.nevermine.event.equipment;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.nevermine.container.PlayerContainer;
import net.nevermine.item.armor.artistry.ArtistryArmor;

public class ArtistryRequirement {

	@SubscribeEvent
	public void onTickEvent(final LivingEvent.LivingUpdateEvent ev) {
		if (ev.entity instanceof EntityPlayer && !ev.entity.worldObj.isRemote) {
			if (((EntityPlayer)ev.entity).capabilities.isCreativeMode)
				return;

			EntityPlayer player = (EntityPlayer)ev.entity;

			final ItemStack stackBoots = player.inventory.armorItemInSlot(0);
			final ItemStack stackLegs = player.inventory.armorItemInSlot(1);
			final ItemStack stackBody = player.inventory.armorItemInSlot(2);
			final ItemStack stackHelmet = player.inventory.armorItemInSlot(3);

			final Item boots = stackBoots != null ? stackBoots.getItem() : null;
			final Item legs = stackLegs != null ? stackLegs.getItem() : null;
			final Item body = stackBody != null ? stackBody.getItem() : null;
			final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

			if (boots != null && boots instanceof ArtistryArmor) {
				if (PlayerContainer.getProperties(player).getLevel(((ArtistryArmor)boots).getAssociatedSkill()) < 100) {
					player.entityDropItem(player.inventory.armorItemInSlot(0).copy(), 1.0f);
					player.inventory.armorInventory[0] = null;

					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}

			if (legs != null && legs instanceof ArtistryArmor) {
				if (PlayerContainer.getProperties(player).getLevel(((ArtistryArmor)legs).getAssociatedSkill()) < 100) {
					player.entityDropItem(player.inventory.armorItemInSlot(1).copy(), 1.0f);
					player.inventory.armorInventory[1] = null;

					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}

			if (body != null && body instanceof ArtistryArmor) {
				if (PlayerContainer.getProperties(player).getLevel(((ArtistryArmor)body).getAssociatedSkill()) < 100) {
					player.entityDropItem(player.inventory.armorItemInSlot(2).copy(), 1.0f);
					player.inventory.armorInventory[2] = null;

					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}

			if (helmet != null && helmet instanceof ArtistryArmor) {
				if (PlayerContainer.getProperties(player).getLevel(((ArtistryArmor)helmet).getAssociatedSkill()) < 100) {
					player.entityDropItem(player.inventory.armorItemInSlot(3).copy(), 1.0f);
					player.inventory.armorInventory[3] = null;

					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
					}
				}
			}
		}
	}
}
