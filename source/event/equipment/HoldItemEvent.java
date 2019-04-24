package net.nevermine.event.equipment;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.item.weapon.energy.BaseEnergy;
import net.nevermine.item.weapon.energy.BaseEnergyRapid;
import net.nevermine.item.weapon.gun.BaseGun;
import net.nevermine.item.weapon.scythe.BaseScythe;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.Random;

public class HoldItemEvent {
	private Random rand;
	private World world;
	private Item held;
	private ItemStack heldStack;

	public HoldItemEvent() {
		rand = new Random();
		held = null;
		heldStack = null;
	}

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		world = ev.player.worldObj;
		final ItemStack weildedItem = ev.player.getHeldItem();
		if (weildedItem != null) {
			held = weildedItem.getItem();
		}
		else {
			held = null;
		}
		if (weildedItem != null) {
			heldStack = ev.player.getHeldItem();
		}
		if (heldStack != null) {
			if (held instanceof BaseScythe) {
				if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.ewindfury, heldStack) == 1) {
					ev.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, -1, 0));
				}
				else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.ewindfury, heldStack) == 2) {
					ev.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, -1, 1));
				}
				else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.ewindfury, heldStack) == 3) {
					ev.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, -1, 2));
				}
			}
			if (held == Weaponizer.Gravitator) {
				if (ev.player.motionY < 0.0) {
					final EntityPlayer player = ev.player;
					player.motionY *= 0.800000011920929;
				}
				ev.player.fallDistance = -0.5f;
			}
			if (held instanceof BaseGun || held instanceof BaseEnergy || held instanceof BaseEnergyRapid) {
				ev.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, -1, 0));
			}
		}
	}
}
