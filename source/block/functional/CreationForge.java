package net.nevermine.block.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Slabizer;

import static net.nevermine.container.PlayerContainer.Skills.Creation;

public class CreationForge extends Block {
	private static Block.SoundType rck = Block.soundTypeStone;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public CreationForge(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.FurnitureTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(CreationForge.rck);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:creationForge_top");
		side = icon.registerIcon("nevermine:creationForge_side");
		bottom = icon.registerIcon("nevermine:creationForge_top");
		blockIcon = icon.registerIcon("nevermine:creationForge_side");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null) {
			PlayerContainer cont = PlayerContainer.getProperties(p);
			final int level = cont.getLevel(Creation);
			float xp = 0;

			switch (p.getHeldItem().getItem().getUnlocalizedName()) {
				case "item.PenguinSlab":
					xp = 9.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.PenguinSlab);
					break;
				case "item.DraggySlab":
					xp = 20.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.DraggySlab);
					break;
				case "item.CompeerSlab":
					if (level < 10)
						return true;

					xp = 40.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.CompeerSlab);
					break;
				case "item.WaggySlab":
					if (level < 15)
						return true;

					xp = 50.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.WaggySlab);
					break;
				case "item.SpikebackSlab":
					if (level < 16)
						return true;

					xp = 12.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.SpikebackSlab);
					break;
				case "item.RammerhornSlab":
					if (level < 27)
						return true;

					xp = 200.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.RammerhornSlab);
					break;
				case "item.SpraggySlab":
					if (level < 30)
						return true;

					xp = 200.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.SpraggySlab);
					break;
				case "item.PlateosaurSlab":
					if (level < 33)
						return true;

					xp = 250.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.PlateosaurSlab);
					break;
				case "item.CraggySlab":
					if (level < 45)
						return true;

					xp = 400.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.CraggySlab);
					break;
				case "item.HellquinSlab":
					if (level < 45)
						return true;

					xp = 500.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.HellquinSlab);
					break;
				case "item.ShaddySlab":
					if (level < 60)
						return true;

					xp = 850.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.ShaddySlab);
					break;
				case "item.GnawerSlab":
					if (level < 60)
						return true;

					xp = 2000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.GnawerSlab);
					break;
				case "item.CorbySlab":
					if (level < 63)
						return true;

					xp = 3000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.CorbySlab);
					break;
				case "item.BlissardSlab":
					if (level < 70)
						return true;

					xp = 2500.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.BlissardSlab);
					break;
				case "item.EnderCarrierSlab":
					if (level < 70)
						return true;

					xp = 1000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.EnderCarrierSlab);
					break;
				case "item.GooferSlab":
					if (level < 74)
						return true;

					xp = 500.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.GooferSlab);
					break;
				case "item.HorntailSlab":
					if (level < 54)
						return true;

					xp = 2000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.HorntailSlab);
					break;
				case "item.AlluricornSlab":
					if (level < 80)
						return true;

					xp = 4000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.AlluricornSlab);
					break;
				case "item.ConstructServilitySlab":
					if (level < 83)
						return true;

					xp = 3000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.ConstructServilitySlab);
					break;
				case "item.MechaCyclopsSlab":
					if (level < 78)
						return true;

					xp = 4500.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.MechaCyclopsSlab);
					break;
				case "item.HealingGolemSlab":
					if (level < 90)
						return true;

					xp = 4000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.HealingGolemSlab);
					break;
				case "item.MechaSkelloxSlab":
					if (level < 92)
						return true;

					xp = 8000.0f;

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Slabizer.MechaSkelloxSlab);
					break;
				default:
					break;
			}

			if (xp > 0.0f) {
				cont.addExperience(xp, Creation);
				w.playSoundAtEntity(p, "nevermine:CreationForge", 1.85f, 1.0f);
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
