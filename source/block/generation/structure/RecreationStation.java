package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;

public class RecreationStation extends Block {
	private static Block.SoundType rck;

	public RecreationStation(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(RecreationStation.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking()) {
			if (p.inventory.hasItem(Itemizer.RealmstoneFragment1) && p.inventory.hasItem(Itemizer.RealmstoneFragment2) && p.inventory.hasItem(Itemizer.RealmstoneFragment3) && p.inventory.hasItem(Itemizer.RealmstoneFragment4) && p.inventory.hasItem(Itemizer.RealmstoneFragment5)) {
				p.inventory.consumeInventoryItem(Itemizer.RealmstoneFragment1);
				p.inventory.consumeInventoryItem(Itemizer.RealmstoneFragment2);
				p.inventory.consumeInventoryItem(Itemizer.RealmstoneFragment3);
				p.inventory.consumeInventoryItem(Itemizer.RealmstoneFragment4);
				p.inventory.consumeInventoryItem(Itemizer.RealmstoneFragment5);
				if (!w.isRemote) {
					p.inventory.addItemStackToInventory(new ItemStack(Itemizer.RealmstoneLabricon));
					p.addChatMessage(StringUtil.getColourLocaleWithArguments("message.feedback.item.labriconRealmstone.create", EnumChatFormatting.LIGHT_PURPLE, p.getDisplayName()));
				}
			}
			if (p instanceof EntityPlayerMP) {
				((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		RecreationStation.rck = Block.soundTypeStone;
	}
}
