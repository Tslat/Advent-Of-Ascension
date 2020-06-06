package net.tslat.aoa3.block.generation.wood;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class LogBlock extends BlockLog {
	public LogBlock(String name, String registryName) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3", registryName);
		setCreativeTab(CreativeTabsRegister.GENERATION_BLOCKS);
		setResistance(0.5f);
		Blocks.FIRE.setFireInfo(this, 5, 5);
		setDefaultState(getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		setSoundType(SoundType.WOOD);
	}

	public LogBlock(String name, String registryName, Material material, float hardness, float resistance) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3", registryName);
		setCreativeTab(CreativeTabsRegister.GENERATION_BLOCKS);
		setHardness(hardness);
		setResistance(resistance);
		setDefaultState(getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		doMaterialCheck(material);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();

		switch (meta & 0b1100) {
			case 0b0000:
				return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			case 0b0100:
				return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			case 0b1000:
				return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			case 0b1100:
			default:
				return state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		switch (state.getValue(LOG_AXIS)) {
			case X:
				return 0b0100;
			case Y:
				return 0b0000;
			case NONE:
				return 0b1100;
			case Z:
			default:
				return 0b1000;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LOG_AXIS);
	}

	private void doMaterialCheck(Material material) {
		if (material != Material.ROCK) {
			if (material == Material.GRASS || material == Material.GROUND) {
				setSoundType(SoundType.GROUND);
			}
			else if (material == Material.GLASS) {
				setSoundType(SoundType.GLASS);
			}
			else if (material == Material.CLOTH) {
				setSoundType(SoundType.CLOTH);
				Blocks.FIRE.setFireInfo(this, 30, 60);
			}
			else if (material == Material.IRON) {
				setSoundType(SoundType.METAL);
			}
			else if (material == Material.WOOD) {
				setSoundType(SoundType.WOOD);
				Blocks.FIRE.setFireInfo(this, 5, 20);
			}
		}
	}
}
