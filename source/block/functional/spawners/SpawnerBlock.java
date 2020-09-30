package net.tslat.aoa3.block.functional.spawners;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class SpawnerBlock extends BlockMobSpawner {
	private final ResourceLocation mob;

	public SpawnerBlock(String name, String registryName, String mobName, boolean unbreakable) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);

		this.mob = new ResourceLocation("aoa3", mobName);

		if (unbreakable) {
			setHardness(-1f);
			setResistance(999999999f);
		}
		else {
			setHardness(10.0f);
			setResistance(2.0f);
		}
	}

	public SpawnerBlock(String name, String registryName, String mobName) {
		this(name, registryName, mobName, false);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (world.isRemote || stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemMonsterPlacer))
			return true;

		if (!player.canPlayerEdit(pos, facing, stack))
			return false;

		TileEntity tileEntity = world.getTileEntity(pos);

		if (!(tileEntity instanceof TileEntityMobSpawner))
			return true;

		((TileEntityMobSpawner)tileEntity).getSpawnerBaseLogic().setEntityId(ItemMonsterPlacer.getNamedIdFrom(stack));
		tileEntity.markDirty();
		world.notifyBlockUpdate(pos, state, state, 3);

		if (!player.capabilities.isCreativeMode)
			stack.shrink(1);

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		TileEntity spawner = ((BlockMobSpawner)Blocks.MOB_SPAWNER).createNewTileEntity(world, meta);

		if (spawner instanceof TileEntityMobSpawner)
			((TileEntityMobSpawner)spawner).getSpawnerBaseLogic().setEntityId(mob);

		return spawner;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return (int)(super.getExpDrop(state, world, pos, fortune) * 1.5);
	}
}
