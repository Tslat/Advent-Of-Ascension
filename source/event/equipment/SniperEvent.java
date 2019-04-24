package net.nevermine.event.equipment;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.common.nevermine;
import net.nevermine.item.weapon.sniper.SniperInstance;

public class SniperEvent {
	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		final ItemStack weildedItem = ev.player.getHeldItem();

		if (weildedItem == null || !(weildedItem.getItem() instanceof SniperInstance)) {
			nevermine.proxy.displayScopeScreen(false, 1, ev.player);
			return;
		}

		final ItemStack stackBoots = ev.player.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = ev.player.inventory.armorItemInSlot(1);
		final ItemStack stackBody = ev.player.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = ev.player.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		if (!ArmorUtil.isSharpshotArmor(boots, legs, body, helmet)) {
			ev.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, -1, 15, true));

			if (ev.player.isInWater()) {
				ev.player.motionY -= 0.025;
			}
		}

		if (!ev.player.worldObj.isRemote)
			return;

		switch (weildedItem.getItem().getUnlocalizedName()) {
			case "item.Duster":
			case "item.BoltRifle":
			case "item.BayonetteSR":
				nevermine.proxy.displayScopeScreen(true, 1, ev.player);
				break;
			case "item.RosidRifle":
			case "item.SludgeSniper":
			case "item.Terminator":
			case "item.Ka500":
			case "item.CamoRifle":
			case "item.Decimator":
			case "item.HeadHunter":
			case "item.ClownCracker":
			case "item.HiveCracker":
			case "item.DualSight":
			case "item.Clownimator":
				nevermine.proxy.displayScopeScreen(true, 2, ev.player);
				break;
			case "item.Viper1":
			case "item.Sabbath":
				nevermine.proxy.displayScopeScreen(true, 3, ev.player);
				break;
			case "item.BaronSSR":
				nevermine.proxy.displayScopeScreen(true, 4, ev.player);
				break;
			case "item.Floro500":
				nevermine.proxy.displayScopeScreen(true, 5, ev.player);
				break;
			case "item.MarkMaker":
				nevermine.proxy.displayScopeScreen(true, 6, ev.player);
				break;
			case "item.Monster":
			case "item.DarkBeast":
				nevermine.proxy.displayScopeScreen(true, 7, ev.player);
				break;
			case "item.DischargeSniper":
				nevermine.proxy.displayScopeScreen(true, 8, ev.player);
				break;
			case "item.MoonMaker":
				nevermine.proxy.displayScopeScreen(true, 9, ev.player);
				break;
			case "item.Crystaneer":
				nevermine.proxy.displayScopeScreen(true, 10, ev.player);
				break;
			case "item.SweetTooth":
				nevermine.proxy.displayScopeScreen(true, 11, ev.player);
				break;
			case "item.Deadlock":
				nevermine.proxy.displayScopeScreen(true, 12, ev.player);
				break;
			case "item.Mineral":
				nevermine.proxy.displayScopeScreen(true, 13, ev.player);
			default:
				break;
		}
	}

	@SubscribeEvent
	public void LivingJumpEvent(final LivingEvent.LivingJumpEvent e) {
		if (e.entity instanceof EntityPlayer) {
			EntityPlayer pl = ((EntityPlayer)e.entity);

			if (pl.getHeldItem() != null && pl.getHeldItem().getItem() instanceof SniperInstance && !ArmorUtil.isSharpshotArmor(pl))
				--pl.motionY;
		}
	}
}
