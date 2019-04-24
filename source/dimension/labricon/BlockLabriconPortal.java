package net.nevermine.dimension.labricon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.block.functional.AdventPortalBlock;
import net.nevermine.container.PlayerContainer;
import net.nevermine.container.PortalCoordinatesContainer;
import net.nevermine.fx.portal.EntityPrecasiaPortalFX;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BlockLabriconPortal extends Block implements AdventPortalBlock {
	public static final int[][] sides;
	public String name;

	public BlockLabriconPortal() {
		super(Material.portal);
		setTickRandomly(true);
		setResistance(999999999f);
		setHardness(-1f);
		setBlockName("labriconPortal");
		setBlockTextureName("nevermine:animated/labriconPortal");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public Item getItem(final World par1World, final int par2, final int par3, final int par4) {
		return Item.getItemFromBlock(this);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void updateTick(final World world, final int x, final int y, final int z, final Random rand) {
		super.updateTick(world, x, y, z, rand);
	}

	public void onEntityCollidedWithBlock(final World par1World, final int par2, final int par3, final int par4, final Entity par5Entity) {
		if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null && par5Entity instanceof EntityPlayerMP) {
			final int dim = ConfigurationHelper.labricon;
			final EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;

			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			}
			else if (thePlayer.dimension != dim) {
				if (thePlayer.dimension == 0) {
					PortalCoordinatesContainer coords = new PortalCoordinatesContainer(thePlayer.posX, thePlayer.posY, thePlayer.posZ);

					PlayerContainer.getProperties(thePlayer).setPortalReturnLocation(coords);
				}

				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, dim, new TeleporterLabricon(thePlayer.mcServer.worldServerForDimension(dim)));
			}
			else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, ConfigurationHelper.deeplands, new TeleporterLabricon(thePlayer.mcServer.worldServerForDimension(ConfigurationHelper.deeplands)));
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
		return null;
	}

	public void setBlockBoundsBasedOnState(final IBlockAccess blockAccess, final int xPos, final int yPos, final int zPos) {
		int l = getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));
		if (l == 0) {
			if (blockAccess.getBlock(xPos - 1, yPos, zPos) != this && blockAccess.getBlock(xPos + 1, yPos, zPos) != this) {
				l = 2;
			}
			else {
				l = 1;
			}
			if (blockAccess instanceof World && !((World)blockAccess).isRemote) {
				((World)blockAccess).setBlockMetadataWithNotify(xPos, yPos, zPos, l, 2);
			}
		}
		float f = 0.125f;
		float f2 = 0.125f;
		if (l == 1) {
			f = 0.5f;
		}
		if (l == 2) {
			f2 = 0.5f;
		}
		setBlockBounds(0.5f - f, 0.0f, 0.5f - f2, 0.5f + f, 1.0f, 0.5f + f2);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean hasPortalSizeChanged(final World world, final int xPos, final int yPos, final int zPos) {
		final Size size = new Size(world, xPos, yPos, zPos, 1);
		final Size size2 = new Size(world, xPos, yPos, zPos, 2);
		if (size.func_150860_b() && size.value == 0) {
			size.func_150859_c();
			return true;
		}
		if (size2.func_150860_b() && size2.value == 0) {
			size2.func_150859_c();
			return true;
		}
		return false;
	}

	public void onNeighborBlockChange(final World world, final int xPos, final int yPos, final int zPos, final Block block) {
		final int l = getMeta(world.getBlockMetadata(xPos, yPos, zPos));
		final Size size = new Size(world, xPos, yPos, zPos, 1);
		final Size size2 = new Size(world, xPos, yPos, zPos, 2);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(final IBlockAccess blockAccess, final int xPos, final int yPos, final int zPos, final int side) {
		return true;

		/*int i1 = 0;
		if (blockAccess.getBlock(xPos, yPos, zPos) == this) {
			i1 = getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));
			if (i1 == 0) {
				return false;
			}
			if (i1 == 2 && side != 5 && side != 4) {
				return false;
			}
			if (i1 == 1 && side != 3 && side != 2) {
				return false;
			}
		}
		final boolean flag = blockAccess.getBlock(xPos - 1, yPos, zPos) == this && blockAccess.getBlock(xPos - 2, yPos, zPos) != this;
		final boolean flag2 = blockAccess.getBlock(xPos + 1, yPos, zPos) == this && blockAccess.getBlock(xPos + 2, yPos, zPos) != this;
		final boolean flag3 = blockAccess.getBlock(xPos, yPos, zPos - 1) == this && blockAccess.getBlock(xPos, yPos, zPos - 2) != this;
		final boolean flag4 = blockAccess.getBlock(xPos, yPos, zPos + 1) == this && blockAccess.getBlock(xPos, yPos, zPos + 2) != this;
		final boolean flag5 = flag || flag2 || i1 == 1;
		final boolean flag6 = flag3 || flag4 || i1 == 2;
		return (flag5 && side == 4) || (flag5 && side == 5) || (flag6 && side == 2) || (flag6 && side == 3);*/
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World world, final int xPos, final int yPos, final int zPos, final Random rand) {
		for (int l = 0; l < 4; ++l) {
			double d0 = xPos + rand.nextFloat();
			final double d2 = yPos + rand.nextFloat();
			double d3 = zPos + rand.nextFloat();
			double d4 = 0.0;
			double d5 = 0.0;
			double d6 = 0.0;
			final int i1 = rand.nextInt(2) * 2 - 1;
			d4 = (rand.nextFloat() - 0.5) * 0.5;
			d5 = (rand.nextFloat() - 0.5) * 0.5;
			d6 = (rand.nextFloat() - 0.5) * 0.5;
			if (world.getBlock(xPos - 1, yPos, zPos) != this && world.getBlock(xPos + 1, yPos, zPos) != this) {
				d0 = xPos + 0.5 + 0.25 * i1;
				d4 = rand.nextFloat() * 2.0f * i1;
			}
			else {
				d3 = zPos + 0.5 + 0.25 * i1;
				d6 = rand.nextFloat() * 2.0f * i1;
			}
			final EntityPrecasiaPortalFX var20 = new EntityPrecasiaPortalFX(world, d0, d2, d3, d4, d5, d6);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	public static int getMeta(final int meta) {
		return meta & 0x3;
	}

	static {
		sides = new int[][] {new int[0], {3, 1}, {2, 0}};
	}

	public static class Size {
		private final World world;
		private final int side1;
		private final int side1_1;
		private final int side1_0;
		private int value;
		private ChunkCoordinates chunkCoords;
		private int directionOrSideMaybe;
		private int rotationOrMaybeNumSides;
		private static final String __OBFID = "CL_00000285";

		public Size(final World world, final int x, int y, final int z, final int side1) {
			value = 0;
			this.world = world;
			this.side1 = side1;
			side1_0 = BlockLabriconPortal.sides[side1][0];
			side1_1 = BlockLabriconPortal.sides[side1][1];
			for (int i1 = y; y > i1 - 21 && y > 0 && func_150857_a(world.getBlock(x, y - 1, z)); --y) {
			}
			final int j1 = getValueBasedOnBlock(x, y, z, side1_0) - 1;
			if (j1 >= 0) {
				chunkCoords = new ChunkCoordinates(x + j1 * Direction.offsetX[side1_0], y, z + j1 * Direction.offsetZ[side1_0]);
				rotationOrMaybeNumSides = getValueBasedOnBlock(chunkCoords.posX, chunkCoords.posY, chunkCoords.posZ, side1_1);
				if (rotationOrMaybeNumSides < 2 || rotationOrMaybeNumSides > 21) {
					chunkCoords = null;
					rotationOrMaybeNumSides = 0;
				}
			}
			if (chunkCoords != null) {
				directionOrSideMaybe = getDirectionMaybe();
			}
		}

		protected int getValueBasedOnBlock(final int x, final int y, final int z, final int side) {
			final int j1 = Direction.offsetX[side];
			final int k1 = Direction.offsetZ[side];
			int i1;
			for (i1 = 0; i1 < 22; ++i1) {
				final Block block = world.getBlock(x + j1 * i1, y, z + k1 * i1);
				if (!func_150857_a(block)) {
					break;
				}
				final Block block2 = world.getBlock(x + j1 * i1, y - 1, z + k1 * i1);
				if (block2 != Blockizer.PlantStem) {
					break;
				}
			}
			final Block block = world.getBlock(x + j1 * i1, y, z + k1 * i1);
			return (block == Blockizer.PlantStem) ? i1 : 0;
		}

		protected int getDirectionMaybe() {
			directionOrSideMaybe = 0;
			Label_0272:
			while (directionOrSideMaybe < 21) {
				final int i = chunkCoords.posY + directionOrSideMaybe;
				for (int j = 0; j < rotationOrMaybeNumSides; ++j) {
					final int k = chunkCoords.posX + j * Direction.offsetX[BlockLabriconPortal.sides[side1][1]];
					final int l = chunkCoords.posZ + j * Direction.offsetZ[BlockLabriconPortal.sides[side1][1]];
					Block block = world.getBlock(k, i, l);
					if (!func_150857_a(block)) {
						break Label_0272;
					}
					if (block == Blockizer.labriconPortal) {
						++value;
					}
					if (j == 0) {
						block = world.getBlock(k + Direction.offsetX[BlockLabriconPortal.sides[side1][0]], i, l + Direction.offsetZ[BlockLabriconPortal.sides[side1][0]]);
						if (block != Blockizer.PlantStem) {
							break Label_0272;
						}
					}
					else if (j == rotationOrMaybeNumSides - 1) {
						block = world.getBlock(k + Direction.offsetX[BlockLabriconPortal.sides[side1][1]], i, l + Direction.offsetZ[BlockLabriconPortal.sides[side1][1]]);
						if (block != Blockizer.PlantStem) {
							break Label_0272;
						}
					}
				}
				++directionOrSideMaybe;
			}
			for (int i = 0; i < rotationOrMaybeNumSides; ++i) {
				final int j = chunkCoords.posX + i * Direction.offsetX[BlockLabriconPortal.sides[side1][1]];
				final int k = chunkCoords.posY + directionOrSideMaybe;
				final int l = chunkCoords.posZ + i * Direction.offsetZ[BlockLabriconPortal.sides[side1][1]];
				if (world.getBlock(j, k, l) != Blockizer.PlantStem) {
					directionOrSideMaybe = 0;
					break;
				}
			}
			if (directionOrSideMaybe <= 21 && directionOrSideMaybe >= 3) {
				return directionOrSideMaybe;
			}
			chunkCoords = null;
			rotationOrMaybeNumSides = 0;
			return directionOrSideMaybe = 0;
		}

		protected boolean func_150857_a(final Block block) {
			return block.getMaterial() == Material.air || block == Blockizer.ToxicBlock || block == Blockizer.labriconPortal;
		}

		public boolean func_150860_b() {
			return chunkCoords != null && rotationOrMaybeNumSides >= 2 && rotationOrMaybeNumSides <= 21 && directionOrSideMaybe >= 3 && directionOrSideMaybe <= 21;
		}

		public void func_150859_c() {
			for (int i = 0; i < rotationOrMaybeNumSides; ++i) {
				final int j = chunkCoords.posX + Direction.offsetX[side1_1] * i;
				final int k = chunkCoords.posZ + Direction.offsetZ[side1_1] * i;
				for (int l = 0; l < directionOrSideMaybe; ++l) {
					final int i2 = chunkCoords.posY + l;
					world.setBlock(j, i2, k, Blockizer.labriconPortal, side1, 2);
				}
			}
		}
	}
}
