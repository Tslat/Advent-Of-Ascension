package net.nevermine.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.gui.pouch.ContainerRunePouch;
import net.nevermine.gui.pouch.GuiRunePouch;
import net.nevermine.gui.pouch.InventoryRunePouch;
import net.nevermine.gui.pouch.ItemRunePouch;
import net.nevermine.gui.trader.*;
import net.nevermine.izer.Itemizer;

public class GuiHandler implements IGuiHandler {
	public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		if (ID <= 499) {
			return new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world);
		}
		if (ID == 500 && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemRunePouch) {
			return new ContainerRunePouch(player.inventory, new InventoryRunePouch(player.getHeldItem()));
		}
		return null;
	}

	public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		switch (ID) {
			case 1:
				return new GuiTrollTrader(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 2:
				return new GuiGorbArmsDealer(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 3:
				return new GuiGorbEngineer(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 4:
				return new GuiPortalMaster(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 5:
				return new GuiLottoman(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 6:
				return new GuiPrimordialBanker(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 7:
				return new GuiPrimordialSpellbinder(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 8:
				return new GuiPrimordialWizard(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 9:
				return new GuiPrimordialMerchant(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 10:
				return new GuiProfessor(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 11:
				return new GuiStoreKeeper(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 12:
				return new GuiZalBanker(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 13:
				return new GuiZalGrocer(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 14:
				return new GuiZalHerbalist(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 15:
				return new GuiZalSpellbinder(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 16:
				return new GuiZalVendor(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 17:
				return new GuiSkillMaster(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 22:
				return new GuiLelyetianBanker(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 23:
				return new GuiLelyetianTrader(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 28:
				return new GuiToyMerchant(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 29:
				return new GuiCrystalTrader(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 30:
				return new GuiAssassin(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 31:
				return new GuiNaturalist(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 32:
				return new GuiMetalloid(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 33:
				return new GuiSoulAgent(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 34:
				return new GuiRealmShifter(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 35:
				return new GuiExplosivesExpert(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 36:
				return new GuiCreepBanker(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 37:
				return new GuiDungeonKeeper(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 38:
				return new GuiTokenCollector(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 39:
				return new GuiCorruptedTraveller(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 40:
				return new GuiShyreBanker(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 41:
				return new GuiShyreArcher(new ContainerEternalMerchant(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
			case 42:
				return new GuiCustomBook(player, new ItemStack(Itemizer.WornBook, 1), false);
			case 500:
				if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemRunePouch) {
					return new GuiRunePouch(player.inventory, new InventoryRunePouch(player.getHeldItem()));
				}
			default:
				return null;
		}
	}

	private Entity getEntityByID(final int entityID, final World world) {
		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
			if (((Entity)world.loadedEntityList.get(i)).getEntityId() == entityID) {
				return (Entity)world.loadedEntityList.get(i);
			}
		}
		return null;
	}
}
