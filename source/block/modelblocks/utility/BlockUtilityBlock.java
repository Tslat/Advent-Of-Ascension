package net.nevermine.block.modelblocks.utility;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.nevermine.block.modelblocks.BlockMod;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import net.nevermine.block.modelblocks.model.modelHydroTable;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.mob.entity.lborean.EntityHydroliskShield;

import java.util.Random;

public class BlockUtilityBlock extends BlockMod {
	public ModelEternalBlock model;
	public ResourceLocation texture;
	private Random rand;

	public BlockUtilityBlock(final ResourceLocation texture, final ModelEternalBlock model, Material mat) {
		super(mat);
		rand = new Random();
		this.texture = texture;
		this.model = model;
		if (model instanceof modelHydroTable) {
			setHardness(-1.0f);
			setResistance(999999999f);
		}
		else {
			setHardness(5.0f);
			setResistance(0.5f);
		}
		setCreativeTab(Itemizer.TablesTab);
	}

	public boolean hasTileEntity(final int metadata) {
		return true;
	}

	public TileEntity createTileEntity(final World world, final int metadata) {
		return new TileEntityUtilityBlock(texture, model);
	}

	public int getRenderType() {
		return -1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public Item getItemDropped(final int par1, final Random par2, final int par3) {
		if (this == SpecialBlockizer.TeasinkFull) {
			return Item.getItemFromBlock(SpecialBlockizer.Teasink);
		}
		if (this == SpecialBlockizer.VoxLog1 || this == SpecialBlockizer.VoxLog2) {
			return Item.getItemFromBlock(Blockizer.WoodToxic);
		}
		return Item.getItemFromBlock(this);
	}

	public void onBlockPlacedBy(final World w, final int x, final int y, final int z, final EntityLivingBase entity, final ItemStack item) {
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
		final int i1 = w.getBlockMetadata(x, y, z) >> 2;
		l = ++l % 4;
		if (l == 0) {
			w.setBlockMetadataWithNotify(x, y, z, 0x2 | i1 << 2, 2);
		}
		if (l == 1) {
			w.setBlockMetadataWithNotify(x, y, z, 0x3 | i1 << 2, 2);
		}
		if (l == 2) {
			w.setBlockMetadataWithNotify(x, y, z, 0x0 | i1 << 2, 2);
		}
		if (l == 3) {
			w.setBlockMetadataWithNotify(x, y, z, 0x1 | i1 << 2, 2);
		}
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking()) {
			if (w.getBlock(x, y, z) == SpecialBlockizer.ChargingTable) {
				if (p.inventory.hasItem(Itemizer.HeavyBoulder) && p.inventory.hasItem(Itemizer.PrimordialDust) && p.inventory.consumeInventoryItem(Itemizer.HeavyBoulder) && p.inventory.consumeInventoryItem(Itemizer.PrimordialDust) && !p.worldObj.isRemote) {
					p.dropItem(Itemizer.BoulderDash, 1);
				}
			}
			else if (w.getBlock(x, y, z) == SpecialBlockizer.HydroTable) {
				if (p.inventory.consumeInventoryItem(Itemizer.PureWaterStone) && !p.worldObj.isRemote) {
					final EntityHydroliskShield var10 = new EntityHydroliskShield(w);
					var10.setLocationAndAngles((double)x, (double)(y + 3), (double)z, 0.0f, 0.0f);
					w.spawnEntityInWorld(var10);
				}
			}
			else if (w.getBlock(x, y, z) == SpecialBlockizer.FiltrationSystemOff) {
				if (p.getHeldItem() != null && p.getHeldItem().getItem() == Items.lava_bucket) {
					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Items.bucket))) {
						p.dropItem(Items.bucket, 1);
					}
					final ItemStack heldItem = p.getHeldItem();
					--heldItem.stackSize;
					w.playSoundAtEntity(p, "nevermine:FiltrationLava", 3.85f, 1.0f);
					w.setBlock(x, y, z, SpecialBlockizer.FiltrationSystemOn, w.getBlockMetadata(x, y, z), 2);
				}
			}
			else if (w.getBlock(x, y, z) == SpecialBlockizer.FiltrationSystemOn) {
				if (p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.MetalTub && p.inventory.consumeInventoryItem(Itemizer.MagicMendingCompound)) {
					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.MagicMendingSolution))) {
						p.dropItem(Itemizer.MagicMendingSolution, 1);
					}
					final ItemStack heldItem2 = p.getHeldItem();
					--heldItem2.stackSize;
					w.playSoundAtEntity(p, "nevermine:FiltrationUse", 3.85f, 1.0f);
					w.setBlock(x, y, z, SpecialBlockizer.FiltrationSystemOff, w.getBlockMetadata(x, y, z), 2);
				}
			}
			else if (w.getBlock(x, y, z) == SpecialBlockizer.Teasink) {
				if (p.getHeldItem() != null && p.getHeldItem().getItem() == Items.water_bucket) {
					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Items.bucket))) {
						p.dropItem(Items.bucket, 1);
					}
					final ItemStack heldItem3 = p.getHeldItem();
					--heldItem3.stackSize;
					w.playSoundAtEntity(p, "nevermine:TeaSink", 3.85f, 1.0f);
					w.setBlock(x, y, z, SpecialBlockizer.TeasinkFull, w.getBlockMetadata(x, y, z), 2);
				}
			}
			else if (w.getBlock(x, y, z) == SpecialBlockizer.TeasinkFull && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.Cup && p.inventory.hasItem(Itemizer.TeaShreddings)) {
				if (p.inventory.consumeInventoryItem(Itemizer.HavenShrooms)) {
					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.FungalTea))) {
						p.dropItem(Itemizer.FungalTea, 1);
					}
				}
				else if (p.inventory.consumeInventoryItem(Itemizer.NatureMelonSlice)) {
					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.NaturalTea))) {
						p.dropItem(Itemizer.NaturalTea, 1);
					}
				}
				else if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.Tea))) {
					p.dropItem(Itemizer.Tea, 1);
				}
				w.playSoundAtEntity(p, "nevermine:TeaMake", 3.85f, 1.0f);
				p.inventory.consumeInventoryItem(Itemizer.TeaShreddings);
				final ItemStack heldItem4 = p.getHeldItem();
				--heldItem4.stackSize;
				if (rand.nextInt(7) == 2) {
					w.setBlock(x, y, z, SpecialBlockizer.Teasink, w.getBlockMetadata(x, y, z), 2);
				}
			}
			if (p instanceof EntityPlayerMP) {
				((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World p_149734_1_, final int p_149734_2_, final int p_149734_3_, final int p_149734_4_, final Random p_149734_5_) {
		if (this == SpecialBlockizer.FiltrationSystemOn) {
			final int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
			final double d0 = p_149734_2_ + 0.5f;
			final double d2 = p_149734_3_ + 0.7f;
			final double d3 = p_149734_4_ + 0.5f;
			final double d4 = 0.3099999988079071;
			final double d5 = 1.072883606E-8;
			final double d3b = 0.07099999988079071;
			final double d4b = 0.3599999988079071;
			p_149734_1_.spawnParticle("smoke", d0 + d5, d2 + d4, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0 + d5, d2 + d4, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("smoke", d0 + d4b, d2 + d3b, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0 + d4b, d2 + d3b, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("smoke", d0 - d4b, d2 + d3b, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0 - d4b, d2 + d3b, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("smoke", d0, d2 + d3b, d3 + d4b, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0, d2 + d3b, d3 + d4b, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("smoke", d0, d2 + d3b, d3 - d4b, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0, d2 + d3b, d3 - d4b, 0.0, 0.0, 0.0);
		}
	}

	@Override
	public BlockUtilityBlock setName(final String name) {
		if (name == "CharingTable" || name == "HydroTable") {
			setCreativeTab(Itemizer.TablesTab);
		}
		setBlockTextureName("cobblestone");
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
