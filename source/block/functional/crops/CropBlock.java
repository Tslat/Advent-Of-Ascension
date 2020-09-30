package net.tslat.aoa3.block.functional.crops;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;

import java.util.Random;

public class CropBlock extends BlockCrops {
	private static final AxisAlignedBB[] GROWTH_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	private IPlantable seeds;
	private Item crop;
	private boolean dropsSeeds;

	public CropBlock(String name, String registryName, boolean harvestDropsSeeds) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0);
		setTickRandomly(true);
		disableStats();
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(AGE, 0));

		this.dropsSeeds = harvestDropsSeeds;
	}

	@Override
	protected int getBonemealAgeIncrease(World world) {
		return AdventOfAscension.rand.nextInt(3) + 1;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return GROWTH_AABB[state.getValue(AGE)];
	}

	public IPlantable getSeeds() {
		return seeds;
	}

	public void setSeeds(IPlantable seeds) {
		this.seeds = seeds;
	}

	public void setCrop(Item crop) {
		if (this.crop == null)
			this.crop = crop;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (getAge(state) >= getMaxAge()) {
			if (!world.isRemote) {
				NonNullList<ItemStack> drops = NonNullList.create();
				int fortune = Math.max(EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(EnumHand.MAIN_HAND)), EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(EnumHand.OFF_HAND)));

				getDrops(drops, world, pos, state, fortune);

				float dropChance = ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, fortune, 1, false, player);

				for (ItemStack stack : drops) {
					if (world.rand.nextFloat() <= dropChance)
						spawnAsEntity(world, pos, stack);
				}

				world.setBlockState(pos, getDefaultState(), Enums.BlockUpdateFlags.SYNC_CLIENT.value);
				ForgeEventFactory.onPlayerBlockPlace(player, BlockSnapshot.getBlockSnapshot(world, pos), facing, hand);
			}

			return true;
		}

		return false;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune + 1);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		Random rand = world instanceof World ? ((World)world).rand : RANDOM;

		int count = quantityDropped(state, fortune, rand);

		for (int i = 0; i < count; i++) {
			Item item = getItemDropped(state, rand, fortune);

			if (item != Items.AIR)
				drops.add(new ItemStack(item, 1, damageDropped(state)));
		}

		int age = getAge(state);

		if (age >= getMaxAge()) {
			for (int i = 0; i < fortune + (dropsSeeds ? 3 : 1); i++) {
				if (rand.nextInt(2 * getMaxAge()) <= age)
					drops.add(new ItemStack(getSeed(), 1, 0));
			}
		}
	}

	@Override
	public Item getCrop() {
		return crop;
	}

	public boolean dropsSeeds() {
		return dropsSeeds;
	}

	@Override
	protected Item getSeed() {
		return (Item)seeds;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AGE);
	}
}
