package net.nevermine.event.dimensional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.gui.MobHitPacket;

import java.util.Random;

public class ShyrelandsAtmosphereEvent {
	private World world;
	private static int lastTicksExisted = 0;
	private Random rand = new Random();

	@SubscribeEvent
	public void onTickEvent(TickEvent.PlayerTickEvent ev) {
		if (ev.player.capabilities.isCreativeMode)
			return;

		world = ev.player.worldObj;

		if (ev.player.dimension == ConfigurationHelper.shyrelands) {
			if (ev.player.ticksExisted % 80 == 0 && rand.nextInt(3) == 1) {
				if (world.getEntitiesWithinAABB(EntityBoss.class, ev.player.boundingBox.expand(40.0D, 40.0D, 40.0D)).size() == 0) {
					if (!ev.player.worldObj.isRemote) {
						if (checkHeight(ev.player.posY)) {
							world.playSoundAtEntity(ev.player, "nevermine:ShyreGust", 2.85F, 2.0F);
							ev.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 25));

							if (lastTicksExisted != ev.player.ticksExisted)
								ev.player.addChatMessage(StringUtil.getLocale("message.event.shyreWind"));

							lastTicksExisted = ev.player.ticksExisted;
						}
					}
				}

				if (!ev.player.worldObj.isRemote && ev.player.ticksExisted % 1200 == 0) {
					switch (rand.nextInt(3)) {
						case 0:
							world.playSoundAtEntity(ev.player, "nevermine:ShyreDizzyness", 2.85F, 2.0F);

							if (lastTicksExisted != ev.player.ticksExisted)
								ev.player.addChatMessage(StringUtil.getLocale("message.event.shyreDizzy"));

							if ((ev.player instanceof EntityPlayerMP))
								AddPackets.network.sendTo(new MobHitPacket(200, 11), (EntityPlayerMP)ev.player);

							ev.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
							break;
						case 1:
							world.playSoundAtEntity(ev.player, "nevermine:ShyreAtmosphere", 2.85F, 2.0F);

							if (lastTicksExisted != ev.player.ticksExisted)
								ev.player.addChatMessage(StringUtil.getLocale("message.event.shyreWeakness"));

							ev.player.addPotionEffect(new PotionEffect(Potion.weakness.id, 160, 0));
							ev.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 160, 0));
							break;
						case 2:
							world.playSoundAtEntity(ev.player, "nevermine:ShyreBlind", 2.85F, 2.0F);

							if (lastTicksExisted != ev.player.ticksExisted)
								ev.player.addChatMessage(StringUtil.getLocale("message.event.shyreBlind"));

							if ((ev.player instanceof EntityPlayerMP))
								AddPackets.network.sendTo(new MobHitPacket(180, 12), (EntityPlayerMP)ev.player);
							break;
						default:
							break;
					}

					lastTicksExisted = ev.player.ticksExisted;
				}
			}
		}
	}

	private boolean checkHeight(double pos) {
		if (pos > 55) {
			if (pos > 61) {
				if (pos < 73) {
					return false;
				}
			}
			return true;
		}

		return false;
	}
}
