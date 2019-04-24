package net.nevermine.dimension.shyrelands;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
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
import net.nevermine.block.functional.AdventPortalBlock;
import net.nevermine.container.PlayerContainer;
import net.nevermine.container.PortalCoordinatesContainer;
import net.nevermine.fx.portal.EntityIrominePortalFX;
import net.nevermine.izer.Blockizer;

import java.util.Random;

public class BlockShyrelandsPortal extends Block implements AdventPortalBlock {
	public static final int[][] sides = {new int[0], {3, 1}, {2, 0}};
	public String name;

	public BlockShyrelandsPortal() {
		super(Material.portal);
		setTickRandomly(true);
		setResistance(999999999f);
		setHardness(-1f);
		setBlockName("shyrelandsPortal");
		setBlockTextureName("nevermine:animated/shyrelandsPortal");
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, "shyrelandsPortal");
	}

	public Item getItem(World par1World, int par2, int par3, int par4) {
		return Item.getItemFromBlock(this);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP))) {
			int dim = net.nevermine.assist.ConfigurationHelper.shyrelands;
			EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;

			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			}
			else if (thePlayer.dimension != dim) {
				if (thePlayer.dimension == 0) {
					PortalCoordinatesContainer coords = new PortalCoordinatesContainer(thePlayer.posX, thePlayer.posY, thePlayer.posZ);

					PlayerContainer.getProperties(thePlayer).setPortalReturnLocation(coords);
				}

				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, dim, new TeleporterShyrelands(thePlayer.mcServer.worldServerForDimension(dim)));
			}
			else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterShyrelands(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int xPos, int yPos, int zPos) {
		int l = getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));

		if (l == 0) {
			if ((blockAccess.getBlock(xPos - 1, yPos, zPos) != this) && (blockAccess.getBlock(xPos + 1, yPos, zPos) != this)) {
				l = 2;
			}
			else {
				l = 1;
			}

			if (((blockAccess instanceof World)) && (!((World)blockAccess).isRemote)) {
				((World)blockAccess).setBlockMetadataWithNotify(xPos, yPos, zPos, l, 2);
			}
		}

		float f = 0.125F;
		float f1 = 0.125F;

		if (l == 1) {
			f = 0.5F;
		}

		if (l == 2) {
			f1 = 0.5F;
		}

		setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean hasPortalSizeChanged(World world, int xPos, int yPos, int zPos) {
		Size size = new Size(world, xPos, yPos, zPos, 1);
		Size size1 = new Size(world, xPos, yPos, zPos, 2);

		if ((size.func_150860_b()) && (size.value == 0)) {
			size.func_150859_c();
			return true;
		}
		if ((size1.func_150860_b()) && (size1.value == 0)) {
			size1.func_150859_c();
			return true;
		}

		return false;
	}

	public void onNeighborBlockChange(World world, int xPos, int yPos, int zPos, Block block) {
		int l = getMeta(world.getBlockMetadata(xPos, yPos, zPos));
		Size size = new Size(world, xPos, yPos, zPos, 1);
		Size size1 = new Size(world, xPos, yPos, zPos, 2);
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

	public int quantityDropped(Random p_149745_1_) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int xPos, int yPos, int zPos, Random rand) {
		for (int l = 0; l < 4; l++) {
			double d0 = xPos + rand.nextFloat();
			double d1 = yPos + rand.nextFloat();
			double d2 = zPos + rand.nextFloat();
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = (rand.nextFloat() - 0.5D) * 0.5D;
			d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			d5 = (rand.nextFloat() - 0.5D) * 0.5D;

			if ((world.getBlock(xPos - 1, yPos, zPos) != this) && (world.getBlock(xPos + 1, yPos, zPos) != this)) {
				d0 = xPos + 0.5D + 0.25D * i1;
				d3 = rand.nextFloat() * 2.0F * i1;
			}
			else {
				d2 = zPos + 0.5D + 0.25D * i1;
				d5 = rand.nextFloat() * 2.0F * i1;
			}

			EntityIrominePortalFX var20 = new EntityIrominePortalFX(world, d0, d1, d2, d3, d4, d5);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	public static int getMeta(int meta) {
		return meta & 0x3;
	}

	public static class Size {
		private final World world;
		private final int side1;
		private final int side1_1;
		private final int side1_0;
		private int value = 0;
		private ChunkCoordinates chunkCoords;
		private int directionOrSideMaybe;
		private int rotationOrMaybeNumSides;
		private static final String __OBFID = "CL_00000285";

		public Size(World world, int x, int y, int z, int side1) {
			this.world = world;
			this.side1 = side1;
			side1_0 = BlockShyrelandsPortal.sides[side1][0];
			side1_1 = BlockShyrelandsPortal.sides[side1][1];

			for (int i1 = y; (y > i1 - 21) && (y > 0) && (func_150857_a(world.getBlock(x, y - 1, z))); y--) {
			}

			int j1 = getValueBasedOnBlock(x, y, z, side1_0) - 1;

			if (j1 >= 0) {
				chunkCoords = new ChunkCoordinates(x + j1 * net.minecraft.util.Direction.offsetX[side1_0], y, z + j1 * net.minecraft.util.Direction.offsetZ[side1_0]);
				rotationOrMaybeNumSides = getValueBasedOnBlock(chunkCoords.posX, chunkCoords.posY, chunkCoords.posZ, side1_1);

				if ((rotationOrMaybeNumSides < 2) || (rotationOrMaybeNumSides > 21)) {
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
				if (block2 != Blockizer.ShyreWall) {
					break;
				}
			}
			final Block block = world.getBlock(x + j1 * i1, y, z + k1 * i1);
			return (block == Blockizer.ShyreWall) ? i1 : 0;
		}

		protected int getDirectionMaybe() {
			label272:
			for (directionOrSideMaybe = 0; directionOrSideMaybe < 21; directionOrSideMaybe += 1) {
				int i = chunkCoords.posY + directionOrSideMaybe;

				for (int j = 0; j < rotationOrMaybeNumSides; j++) {
					int k = chunkCoords.posX + j * net.minecraft.util.Direction.offsetX[BlockShyrelandsPortal.sides[side1][1]];
					int l = chunkCoords.posZ + j * net.minecraft.util.Direction.offsetZ[BlockShyrelandsPortal.sides[side1][1]];
					Block block = world.getBlock(k, i, l);

					if (!func_150857_a(block)) {
						break label272;
					}

					if (block == Blockizer.shyrelandsPortal) {
						value += 1;
					}

					if (j == 0) {
						block = world.getBlock(k + net.minecraft.util.Direction.offsetX[BlockShyrelandsPortal.sides[side1][0]], i, l + net.minecraft.util.Direction.offsetZ[BlockShyrelandsPortal.sides[side1][0]]);

						if (block != Blockizer.ShyreWall) {
							break label272;
						}

					}
					else if (j == rotationOrMaybeNumSides - 1) {
						block = world.getBlock(k + net.minecraft.util.Direction.offsetX[BlockShyrelandsPortal.sides[side1][1]], i, l + net.minecraft.util.Direction.offsetZ[BlockShyrelandsPortal.sides[side1][1]]);

						if (block != Blockizer.ShyreWall) {
							break label272;
						}
					}
				}
			}

			for (int i = 0; i < rotationOrMaybeNumSides; i++) {
				int j = chunkCoords.posX + i * net.minecraft.util.Direction.offsetX[BlockShyrelandsPortal.sides[side1][1]];
				int k = chunkCoords.posY + directionOrSideMaybe;
				int l = chunkCoords.posZ + i * net.minecraft.util.Direction.offsetZ[BlockShyrelandsPortal.sides[side1][1]];

				if (world.getBlock(j, k, l) != Blockizer.ShyreWall) {
					directionOrSideMaybe = 0;
					break;
				}
			}

			if ((directionOrSideMaybe <= 21) && (directionOrSideMaybe >= 3)) {
				return directionOrSideMaybe;
			}

			chunkCoords = null;
			rotationOrMaybeNumSides = 0;
			directionOrSideMaybe = 0;
			return 0;
		}

		protected boolean func_150857_a(Block block) {
			return (block.getMaterial() == Material.air) || (block == Blockizer.ToxicBlock) || (block == Blockizer.shyrelandsPortal);
		}

		public boolean func_150860_b() {
			return (chunkCoords != null) && (rotationOrMaybeNumSides >= 2) && (rotationOrMaybeNumSides <= 21) && (directionOrSideMaybe >= 3) && (directionOrSideMaybe <= 21);
		}

		public void func_150859_c() {
			for (int i = 0; i < rotationOrMaybeNumSides; i++) {
				int j = chunkCoords.posX + net.minecraft.util.Direction.offsetX[side1_1] * i;
				int k = chunkCoords.posZ + net.minecraft.util.Direction.offsetZ[side1_1] * i;

				for (int l = 0; l < directionOrSideMaybe; l++) {
					int i1 = chunkCoords.posY + l;
					world.setBlock(j, i1, k, Blockizer.shyrelandsPortal, side1, 2);
				}
			}
		}
	}
}
