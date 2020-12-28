package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.util.BlockUtil;

import java.util.function.Supplier;

public class CropBlock extends CropsBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[] {
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D)),
			VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D))};
	private final Supplier<Item> seedItem;

	public CropBlock(MaterialColor colour, Supplier<Item> seedItem) {
		super(BlockUtil.generateBlockProperties(Material.PLANTS, colour, 0, 0, SoundType.CROP).doesNotBlockMovement().tickRandomly());

		this.seedItem = seedItem;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (getAge(state) >= getMaxAge()) {
			if (!world.isRemote) {
				NonNullList<ItemStack> drops = NonNullList.create();
				int fortune = Math.max(EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(Hand.MAIN_HAND)), EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(Hand.OFF_HAND)));

				drops.addAll(getDrops(state, (ServerWorld)world, pos, null, player, player.getHeldItem(hand)));

				float dropChance = ForgeEventFactory.fireBlockHarvesting(drops, world, pos, state, fortune, 1, false, player);

				for (ItemStack stack : drops) {
					if (world.rand.nextFloat() <= dropChance)
						spawnAsEntity(world, pos, stack);
				}

				world.setBlockState(pos, getDefaultState(), 2);
				ForgeEventFactory.onBlockPlace(player, BlockSnapshot.getBlockSnapshot(world, pos), hit.getFace());
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return seedItem.get();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(AGE)];
	}
}
