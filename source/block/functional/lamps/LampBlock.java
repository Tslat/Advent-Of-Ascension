package net.tslat.aoa3.block.functional.lamps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.block.CustomStateMapperBlock;
import net.tslat.aoa3.block.functional.lights.LightBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class LampBlock extends LightBlock implements CustomStateMapperBlock {
	public static final PropertyBool FIXED_LAMP = PropertyBool.create("fixed_state");

	protected boolean turnedOn = true;
	protected LampBlock offLamp;
	protected LampBlock onLamp;

	public LampBlock(String name, String registryName, Material material, float luminosity, float hardness, float resistance) {
		super(name, registryName, material, luminosity, hardness, resistance);
		setTickRandomly(true);
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
		setDefaultState(getDefaultState().withProperty(FIXED_LAMP, false));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public StateMap getStateMapper() {
		return (new StateMap.Builder().ignore(FIXED_LAMP).build());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote) {
			if (turnedOn && !world.isBlockPowered(pos)) {
				world.setBlockState(pos, offLamp.getDefaultState(), 2);
			}
			else if (!turnedOn && world.isBlockPowered(pos)) {
				world.setBlockState(pos, onLamp.getDefaultState(), 2);
			}

		}
	}

	public LampBlock getOffLamp() {
		if (offLamp != null)
			return offLamp;

		LampBlock offLamp = new LampBlock(getTranslationKey().replace("tile.", "") + "Off", getRegistryName().getPath() + "_off", getDefaultState().getMaterial(), 0.0f, blockHardness, blockResistance);
		offLamp.turnedOn = false;
		this.offLamp = offLamp;
		this.onLamp = this;
		offLamp.offLamp = offLamp;
		offLamp.onLamp = onLamp;

		onLamp.setCreativeTab(null);

		return offLamp;
	}

	public LampBlock getOnLamp() {
		return onLamp;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!world.isRemote && !state.getValue(FIXED_LAMP)) {
			if (turnedOn && !world.isBlockPowered(pos)) {
				world.scheduleUpdate(pos, this, 4);
			}
			else if (!turnedOn && world.isBlockPowered(pos)) {
				world.setBlockState(pos, onLamp.getDefaultState(), 2);
			}
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && !state.getValue(FIXED_LAMP)) {
			if (turnedOn && !world.isBlockPowered(pos))
				world.setBlockState(pos, offLamp.getDefaultState(), 2);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(offLamp);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(offLamp);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0) {
			return getDefaultState();
		}
		else {
			return getDefaultState().withProperty(FIXED_LAMP, true);
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (state.getValue(FIXED_LAMP))
			return 1;

		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FIXED_LAMP);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(offLamp);
	}
}
