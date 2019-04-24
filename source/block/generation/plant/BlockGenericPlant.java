package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;
import java.util.Random;

public class BlockGenericPlant extends Block implements IShearable {
	private String name;
	private boolean isDown = false;
	private boolean isStonePlant = false;
	private boolean isTallPlant = false;
	private Block childPlant = this;
	private boolean isBidirectional = false;

	public BlockGenericPlant(final Material Rck) {
		super(Rck);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.4f);
		setResistance(0.5f);

		if (Rck != Material.glass) {
			setStepSound(BlockGenericPlant.soundTypeGrass);
		}
		else {
			setStepSound(BlockGenericPlant.soundTypeGlass);
		}
	}

	public int getRenderType() {
		return 1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	public BlockGenericPlant setDown() {
		isDown = true;
		return this;
	}

	public BlockGenericPlant setTallPlant() {
		isTallPlant = true;
		return this;
	}

	public BlockGenericPlant isBidirectional() {
		isBidirectional = true;
		return this;
	}

	public BlockGenericPlant setStonePlant() {
		isStonePlant = true;
		return this;
	}

	public BlockGenericPlant setChildPlant(Block pl) {
		childPlant = pl;
		return this;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ) {
		Block bl = isDown ? world.getBlock(posX, posY + 1, posZ) : world.getBlock(posX, posY - 1, posZ);
		String harvestTool = bl.getHarvestTool(0);

		if (harvestTool == null)
			harvestTool = "";
		if (!isBidirectional) {
			return (((bl == this || bl == childPlant) && isTallPlant) || ((isStonePlant ? (harvestTool.equals("pickaxe") || bl.getMaterial() == Material.rock) : harvestTool.equals("shovel") || bl.getMaterial() == Material.ground) && bl.isOpaqueCube()));
		}
		else {
			if ((((bl == this || bl == childPlant) && isTallPlant) || ((isStonePlant ? (harvestTool.equals("pickaxe") || bl.getMaterial() == Material.rock) : harvestTool.equals("shovel") || bl.getMaterial() == Material.ground) && bl.isOpaqueCube()))) {
				return true;
			}
			else {
				bl = world.getBlock(posX, posY + 1, posZ);

				if ((((bl == this || bl == childPlant) && isTallPlant) || ((isStonePlant ? (harvestTool.equals("pickaxe") || bl.getMaterial() == Material.rock) : harvestTool.equals("shovel") || bl.getMaterial() == Material.ground) && bl.isOpaqueCube()))) {
					return true;
				}
			}

			return false;
		}
	}

	@Override
	public void breakBlock(World world, int posX, int posY, int posZ, Block block, int meta) {
		super.breakBlock(world, posX, posY, posZ, block, meta);

		boolean up = !isDown;

		switch (block.getUnlocalizedName()) {
			case "tile.lelyetianWiggler":
			case "tile.lelyetianStem":
			case "tile.shyreStock":
				int offset = -1;

				Block hatBlock = world.getBlock(posX, posY + offset, posZ);

				while (hatBlock instanceof BlockGenericPlant) {
					offset--;
					hatBlock = world.getBlock(posX, posY + offset, posZ);
				}

				if (hatBlock instanceof BlockAir)
					up = false;
				break;
		}

		if (up) {
			int offset = 1;
			boolean hatBlock = world.getBlock(posX, posY + offset, posZ) instanceof BlockGenericPlant;

			while (hatBlock) {
				world.setBlock(posX, posY + offset, posZ, Blocks.air);
				offset++;
				hatBlock = world.getBlock(posX, posY + offset, posZ) instanceof BlockGenericPlant;
			}
		}
		else {
			int offset = -1;

			boolean hatBlock = world.getBlock(posX, posY + offset, posZ) instanceof BlockGenericPlant;

			while (hatBlock) {
				world.setBlock(posX, posY + offset, posZ, Blocks.air);
				offset--;
				hatBlock = world.getBlock(posX, posY + offset, posZ) instanceof BlockGenericPlant;
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block) {
		int y = isDown ? posY + 1 : posY - 1;

		if (world.getBlock(posX, y, posZ) == Blocks.air) {
			breakBlock(world, posX, y, posZ, this, 0);
		}
	}

	private boolean tracePathUp(World w, int x, int y, int z) {
		int offset = -1;

		Block hatBlock = w.getBlock(x, y + offset, z);

		while (hatBlock instanceof BlockGenericPlant) {
			offset--;
			hatBlock = w.getBlock(x, y + offset, z);
		}

		if (hatBlock instanceof BlockAir)
			return false;

		return true;
	}

	public void onEntityCollidedWithBlock(final World p_149670_1_, final int p_149670_2_, final int p_149670_3_, final int p_149670_4_, final Entity p_149670_5_) {
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
		return null;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		breakBlock((World)world, x, y, z, this, world.getBlockMetadata(x, y, z));
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Item.getItemFromBlock(this), 1));

		return stack;
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
