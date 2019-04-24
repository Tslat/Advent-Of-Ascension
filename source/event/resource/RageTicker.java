package net.nevermine.event.resource;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.block.functional.HeatLamp;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.resource.rage.rageHelper;
import net.nevermine.skill.butchery.butcheryHelper;

import java.util.Random;

public class RageTicker {
	@SubscribeEvent
	public void onPlayerDoingDamageEvent(final LivingHurtEvent e) {
		if (e.source.getEntity() != null && e.source.getEntity() instanceof EntityPlayer && !e.source.isProjectile() && !e.source.isMagicDamage()) {
			final DamageSource s = e.source;
			final EntityPlayer pl = (EntityPlayer)e.source.getEntity();
			Random r = new Random();
			rageHelper helper = rageHelper.getProperties(pl);

			if (r.nextInt(100) + 1 <= helper.getRageValue()) {
				e.ammount *= butcheryHelper.getDamageMultiplier(pl);

				if (isWearingArmour(pl)) {
					e.ammount *= 4.0f;

					if (!pl.capabilities.isCreativeMode) {
						if (pl.getHealth() - 4.0f > 0.0f) {
							pl.setHealth(pl.getHealth() - 4.0f);
						}
						else {
							pl.attackEntityFrom(DamageSource.magic, 4.0f);
						}
					}
				}

				if (helper.getRageValue() >= 150.0f)
					e.ammount *= 1.3f;

				helper.setBarValue(0.0f);
			}
		}
	}

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		if (!ev.player.worldObj.isRemote && ev.phase == TickEvent.Phase.START && ev.player.ticksExisted % 60 == 0) {
			final double posx = ev.player.posX;
			final double posy = ev.player.posY;
			final double posz = ev.player.posZ;
			for (int i = (int)(posx - 2.0); i <= (int)(posx + 2.0); ++i) {
				for (int j = (int)(posy - 2.0); j <= (int)(posy + 2.0); ++j) {
					for (int k = (int)(posz - 2.0); k <= (int)(posz + 2.0); ++k) {
						if (ev.player.worldObj.getBlock(i, j, k) == SpecialBlockizer.Campfire) {
							ev.player.heal(0.5f);
						}
						if (ev.player.worldObj.getBlock(i, j, k) instanceof HeatLamp) {
							ev.player.heal(0.75f);
						}
					}
				}
			}
		}
	}

	private boolean isWearingArmour(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return ArmorUtil.isButcheryArmor(boots, legs, body, helmet);
	}
}
