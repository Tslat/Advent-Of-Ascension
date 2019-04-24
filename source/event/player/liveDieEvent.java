package net.nevermine.event.player;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.ItemUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.entity.night.EntityHeadlessHunter;
import net.nevermine.mob.entity.overworld.EntityHeadlessDestroyer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class liveDieEvent {
	private static HashMap<UUID, HashSet<ItemStack>> respawnItems = new HashMap<UUID, HashSet<ItemStack>>();
	Random r = new Random();

	@SubscribeEvent
	public void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent evt) {
		PlayerContainer advPl = PlayerContainer.getProperties(evt.player);
		advPl.loadNBTData(evt.player.getEntityData());
		advPl.saveNBTData(evt.player.getEntityData());

		if (respawnItems.containsKey(evt.player.getUniqueID())) {
			for (ItemStack st : respawnItems.get(evt.player.getUniqueID())) {
				evt.player.inventory.addItemStackToInventory(st);
			}

			respawnItems.remove(evt.player.getUniqueID());
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDeath(final LivingDeathEvent evt) {
		if (evt.entity instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)evt.entity;
			PlayerContainer advPl = PlayerContainer.getProperties(pl);

			advPl.resetAllTribute();
			advPl.saveNBTData(pl.getEntityData());

			if (evt.source instanceof EntityDamageSource) {
				Entity mob = evt.source.getSourceOfDamage();

				if (mob instanceof EntityHeadlessHunter) {
					if (r.nextInt(100) < 5) {
						ItemStack headStack = new ItemStack(Items.skull, 1, 3);
						headStack.setTagCompound(new NBTTagCompound());
						headStack.getTagCompound().setTag("SkullOwner", new NBTTagString(pl.getGameProfile().getName()));

						EntityItem playerHead = new EntityItem(pl.worldObj, pl.posX, pl.posY, pl.posZ, headStack);
						pl.worldObj.spawnEntityInWorld(playerHead);
					}
				}
				else if (mob instanceof EntityHeadlessDestroyer) {
					if (r.nextInt(100) < 10) {
						ItemStack headStack = new ItemStack(Items.skull, 1, 3);
						headStack.setTagCompound(new NBTTagCompound());
						headStack.getTagCompound().setTag("SkullOwner", new NBTTagString(pl.getGameProfile().getName()));

						EntityItem playerHead = new EntityItem(pl.worldObj, pl.posX, pl.posY, pl.posZ, headStack);
						pl.worldObj.spawnEntityInWorld(playerHead);
					}
				}
			}

			if (!evt.entity.worldObj.isRemote && !MinecraftServer.getServer().worldServerForDimension(evt.entity.worldObj.provider.dimensionId).getGameRules().getGameRuleBooleanValue("keepInventory")) {
				HashSet<ItemStack> stacks = new HashSet<ItemStack>();

				for (int i = 0; i < 36; i++) {
					ItemStack stack = pl.inventory.getStackInSlot(i);

					if (stack != null) {
						if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eintervention, stack) > 0) {

							stacks.add(ItemUtil.removeEnchantment(stack, ConfigurationHelper.eintervention));
							pl.inventory.setInventorySlotContents(i, null);
						}
					}
				}
				for (int i = 0; i < 4; i++) {
					ItemStack stack = pl.inventory.armorItemInSlot(i);

					if (stack != null) {
						if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eintervention, stack) > 0) {
							stacks.add(ItemUtil.removeEnchantment(stack, ConfigurationHelper.eintervention));
							pl.inventory.armorInventory[i] = null;
						}
					}
				}

				if (!stacks.isEmpty()) {
					respawnItems.put(pl.getUniqueID(), stacks);
				}
			}
		}
	}
}
