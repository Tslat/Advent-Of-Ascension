package net.nevermine.block.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Deities.Luxon;
import static net.nevermine.container.PlayerContainer.Skills.Augury;

public class AscensionShrine extends Block {
	private static Block.SoundType rck = Block.soundTypeGlass;

	public AscensionShrine(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.FurnitureTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(AscensionShrine.rck);
	}

	public int getRenderBlockPass() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
		final Block i1 = blockAccess.getBlock(x, y, z);
		return i1 != this && super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null) {
			PlayerContainer cont = PlayerContainer.getProperties(p);
			int level = cont.getLevel(Augury);
			boolean success = false;

			switch (p.getHeldItem().getItem().getUnlocalizedName()) {
				case "item.EssenceWeak":
					cont.addExperience(4.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceWeak);
					success = true;
					break;
				case "item.EssenceMolten":
					if (level < 10)
						break;

					cont.addExperience(7.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceMolten);
					success = true;
					break;
				case "item.EssenceCharged":
					if (level < 18)
						break;

					cont.addExperience(10.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceCharged);
					success = true;
					break;
				case "item.EssenceOminous":
					if (level < 27)
						break;

					cont.addExperience(12.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceOminous);
					success = true;
					break;
				case "item.EssenceEmpowered":
					if (level < 36)
						break;

					cont.addExperience(15.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceEmpowered);
					success = true;
					break;
				case "item.EssenceLuminate":
					if (level < 45)
						break;

					cont.addExperience(20.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceLuminate);
					success = true;
					break;
				case "item.EssenceAncient":
					if (level < 58)
						break;

					cont.addExperience(120.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceAncient);
					success = true;
					break;
				case "item.EssenceDark":
					if (level < 66)
						break;

					cont.addExperience(250.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceDark);
					success = true;
					break;
				case "item.EssenceEthereal":
					if (level < 75)
						break;

					cont.addExperience(400.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceEthereal);
					success = true;
					break;
				case "item.EssenceDivine":
					if (level < 84)
						break;

					cont.addExperience(600.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.EssenceDivine);
					success = true;
					break;
				case "item.GhostStone":
					if (level < 50)
						break;

					cont.addExperience(40.0f, Augury);

					if (!p.capabilities.isCreativeMode)
						p.inventory.consumeInventoryItem(Itemizer.GhostStone);
					success = true;
					break;
				default:
					break;
			}

			if (success) {
				w.playSoundAtEntity(p, "nevermine:Augury", 1.0f, 1.0f);

				if (w.isDaytime() && p.dimension == 0)
					cont.adjustTribute(Luxon, 4);

				if (p instanceof EntityPlayerMP)
					((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}
}
