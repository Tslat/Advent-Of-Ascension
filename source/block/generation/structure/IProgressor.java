package net.nevermine.block.generation.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.immortal.EntityFlash;
import net.nevermine.boss.immortal.EntityKlobber;
import net.nevermine.boss.immortal.EntityMirage;
import net.nevermine.boss.immortal.EntityProshield;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Deities.Erebon;
import static net.nevermine.container.PlayerContainer.Deities.Pluton;

public class IProgressor extends Block {
	private String name;

	public IProgressor(final Material Mtr) {
		super(Mtr);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (w.isRemote || p.isSneaking())
			return true;

		Block bl = w.getBlock(x, y, z);
		PlayerContainer cont = PlayerContainer.getProperties(p);

		switch (bl.getUnlocalizedName()) {
			case "tile.progressorImmortallis1":
				if (p.inventory.consumeInventoryItem(Itemizer.StartingCoin)) {
					if (!p.inventory.addItemStackToInventory(new ItemStack(Itemizer.ReturnCrystal))) {
						p.addChatMessage(StringUtil.getLocale("message.feedback.immortallisProgression.returnCrystal.fail"));
						p.dropItem(Itemizer.StartingCoin, 1);
					}
					else {
						p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.goldStart.0", EnumChatFormatting.GOLD));
						p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.goldStart.1", EnumChatFormatting.GOLD));
						p.setPositionAndUpdate(28.0, 20.0, 2.0);
					}
				}
				break;
			case "tile.progressorImmortallis2":
				if (cont.getTribute(Pluton) >= 100) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.klobberStart", EnumChatFormatting.DARK_AQUA));
					p.setPositionAndUpdate(67.0, 21.0, 2.0);

					final EntityKlobber var10 = new EntityKlobber(w);

					var10.setLocationAndAngles(69.0, 21.0, 4.0, 0.0f, 0.0f);

					if (var10.worldObj.getEntitiesWithinAABB(EntityKlobber.class, var10.boundingBox.expand(15, 5, 15)).size() == 0)
						w.spawnEntityInWorld(var10);
				}
				break;
			case "tile.progressorImmortallis3":
				if (p.inventory.consumeInventoryItem(Itemizer.ProgressCoin1)) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.skeletalSpiritsStart", EnumChatFormatting.RED));
					p.setPositionAndUpdate(81.0, 21.0, 2.0);
				}
				break;
			case "tile.progressorImmortallis4":
				if (cont.getTribute(Erebon) >= 100) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.proshieldStart", EnumChatFormatting.DARK_AQUA));
					p.setPositionAndUpdate(122.0, 21.0, 2.0);

					final EntityProshield var11 = new EntityProshield(w);

					var11.setLocationAndAngles(123.0, 21.0, 6.0, 0.0f, 0.0f);

					if (var11.worldObj.getEntitiesWithinAABB(EntityProshield.class, var11.boundingBox.expand(15, 5, 15)).size() == 0)
						w.spawnEntityInWorld(var11);
				}
				break;
			case "tile.progressorImmortallis5":
				if (p.inventory.consumeInventoryItem(Itemizer.ProgressCoin2)) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.pureGoldStart.0", EnumChatFormatting.GOLD));
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.pureGoldStart.1", EnumChatFormatting.GOLD));
					p.setPositionAndUpdate(141.0, 24.0, 2.0);
				}
				break;
			case "tile.progressorImmortallis6":
				if (cont.getTribute(Pluton) == 200) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.mirageStart", EnumChatFormatting.DARK_AQUA));
					p.setPositionAndUpdate(168.0, 24.0, 7.0);

					final EntityMirage var12 = new EntityMirage(w);

					var12.setLocationAndAngles(177.0, 24.0, -2.0, 0.0f, 0.0f);

					if (var12.worldObj.getEntitiesWithinAABB(EntityKlobber.class, var12.boundingBox.expand(15, 5, 15)).size() == 0)
						w.spawnEntityInWorld(var12);
				}
				break;
			case "tile.progressorImmortallis7":
				if (p.inventory.consumeInventoryItem(Itemizer.ProgressCoin3)) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.evilSpiritsStart", EnumChatFormatting.RED));
					p.setPositionAndUpdate(189.0, 20.0, 2.0);
				}
				break;
			case "tile.progressorImmortallis8":
				if (cont.getTribute(Erebon) == 200) {
					p.addChatMessage(StringUtil.getColourLocale("message.feedback.immortallisProgression.flashStart", EnumChatFormatting.DARK_AQUA));
					p.setPositionAndUpdate(233.0, 21.0, 3.0);

					final EntityFlash var13 = new EntityFlash(w);

					var13.setLocationAndAngles(235.0, 22.0, 10.0, 0.0f, 0.0f);

					if (var13.worldObj.getEntitiesWithinAABB(EntityKlobber.class, var13.boundingBox.expand(15, 5, 15)).size() == 0)
						w.spawnEntityInWorld(var13);
				}
				break;
			case "tile.progressorImmortallis9":
				if (p.inventory.consumeInventoryItem(Itemizer.ProgressCoin4)) {
					p.setPositionAndUpdate(0.0, 20.0, 0.0);
					p.inventory.consumeInventoryItem(Itemizer.ReturnCrystal);
				}
				break;
			default:
				break;
		}

		if (p instanceof EntityPlayerMP)
			((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);

		return true;
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
