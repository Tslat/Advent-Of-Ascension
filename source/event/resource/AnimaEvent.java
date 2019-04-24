package net.nevermine.event.resource;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockVine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.nevermine.block.generation.plant.Leaves;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.resource.creation.creationHelper;
import net.nevermine.resource.soulpower.soulPowerHelper;
import net.nevermine.skill.anima.animaHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Anima;

public class AnimaEvent {
	Random rand = new Random();

	@SubscribeEvent
	public void onJoin(final EntityJoinWorldEvent e) {
		if (e.entity instanceof EntityPlayer) {
			soulPowerHelper.getProperties((EntityPlayer)e.entity).useBar(0.001f);
			creationHelper.getProperties((EntityPlayer)e.entity).useBar(0.001f);
		}
	}

	@SubscribeEvent
	public void stoneToss(final ItemTossEvent e) {
		if (!e.player.worldObj.isRemote && e.entityItem.getEntityItem().getItem() == Itemizer.AnimaStone) {
			e.entityItem.setDead();
		}
	}

	@SubscribeEvent
	public void onHarvestBlock(final BlockEvent.HarvestDropsEvent e) {
		if (e.harvester != null) {
			if ((e.block instanceof BlockCrops || e.block instanceof BlockFlower || e.block instanceof BlockVine)) {
				if (rand.nextInt(8) != 0)
					return;

				e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:HeartstoneDrop", 2.85f, 1.0f);
				e.drops.add(new ItemStack(Itemizer.AnimaStone));
			}
			else if ((e.block instanceof Leaves)) {
				if (rand.nextInt(30) != 0)
					return;

				e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:HeartstoneDrop", 2.85f, 1.0f);
				e.drops.add(new ItemStack(Itemizer.AnimaStone));
			}
		}
	}

	@SubscribeEvent
	public void render(final PlayerEvent.ItemPickupEvent e) {
		if (!e.player.worldObj.isRemote && e.pickedUp.getEntityItem().getItem() == Itemizer.AnimaStone) {
			PlayerContainer cont = PlayerContainer.getProperties(e.player);

			while (e.player.inventory.consumeInventoryItem(Itemizer.AnimaStone)) {
				cont.addExperience(cont.getExpRequired(Anima) / animaHelper.getExpDenominator(cont.getLevel(Anima)), Anima);
			}

			e.pickedUp.setDead();
			e.player.worldObj.playSoundAtEntity(e.player, "nevermine:HeartPickup", 2.85f, 1.0f);
		}
	}
}
