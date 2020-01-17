package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicNonCubeBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class FiltrationSystem extends BasicNonCubeBlock {
	public final FiltrationSystem boilingState;

	public FiltrationSystem() {
		super("FiltrationSystem", "filtration_system", Material.IRON, 9.0f, 10.0f);
		setSoundType(SoundType.METAL);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
		boilingState = new FiltrationSystem(true);
	}

	public FiltrationSystem(boolean on) {
		super("FiltrationSystemOn", "filtration_system_on", Material.IRON, 9.0f, 10.0f);
		setSoundType(SoundType.METAL);
		setLightLevel(0.1f);
		setCreativeTab(null);
		boilingState = this;
	}

	public FiltrationSystem getBoilingState() {
		return boilingState;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(BlockRegister.filtrationSystem));
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO something here
		/*
		if (!world.isRemote) {
			if (state.getBlock() == boilingState) {
				if (player.getHeldItem(hand).getItem() == ItemRegister.metalTub && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.magicMendingCompound))) {
					if (!player.capabilities.isCreativeMode)
						player.getHeldItem(hand).shrink(1);

					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.magicMendingSolution));
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.filtrationSystemUse, SoundCategory.BLOCKS, 1.0f, 1.0f);
					world.setBlockState(pos, BlockRegister.filtrationSystem.getDefaultState());
				}
			}
			else {
				if (player.getHeldItem(hand).getItem() == Items.LAVA_BUCKET) {
					if (!player.capabilities.isCreativeMode) {
						player.getHeldItem(hand).shrink(1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
					}

					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.filtrationSystemActivate, SoundCategory.BLOCKS, 1.0f, 1.0f);
					world.setBlockState(pos, boilingState.getDefaultState());
				}
			}

		}*/

		return true;
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (this == boilingState) {
			double baseX = pos.getX() + 0.5f;
			double baseY = pos.getY() + 0.7f;
			double baseZ = pos.getZ() + 0.5f;
			final double offset1 = 0.3099999988079071;
			final double offset2 = 1.072883606E-8;
			final double offset3 = 0.07099999988079071;
			final double offset4 = 0.3599999988079071;

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, baseX + offset2, baseY + offset1, baseZ, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.FLAME, baseX + offset2, baseY + offset1, baseZ, 0, 0, 0);

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, baseX + offset4, baseY + offset3, baseZ, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.FLAME, baseX + offset4, baseY + offset3, baseZ, 0, 0, 0);

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, baseX - offset4, baseY + offset3, baseZ, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.FLAME, baseX - offset4, baseY + offset3, baseZ, 0, 0, 0);

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, baseX, baseY + offset3, baseZ + offset4, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.FLAME, baseX, baseY + offset3, baseZ + offset4, 0, 0, 0);

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, baseX, baseY + offset3, baseZ - offset4, 0, 0, 0);
			world.spawnParticle(EnumParticleTypes.FLAME, baseX, baseY + offset3, baseZ - offset4, 0, 0, 0);
		}
	}
}
