package net.nevermine.block.functional;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.nevermine.assist.AscensionEnchants;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

public class DivineStation extends Block {
	private static Block.SoundType rck = Block.soundTypeStone;

	public DivineStation(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(DivineStation.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getMaxStackSize() == 1) {
			PlayerContainer cont = PlayerContainer.getProperties(p);
			if (cont.getLevel(PlayerContainer.Skills.Augury) >= 20 && p.inventory.consumeInventoryItem(Itemizer.pStoneAmbient)) {
				p.getHeldItem().addEnchantment(AscensionEnchants.Intervention, 1);
				w.playSoundAtEntity(p, "nevermine:Infusion", 1.85f, 1.0f);
			}

			if (p instanceof EntityPlayerMP)
				((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
		}

		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}
}
