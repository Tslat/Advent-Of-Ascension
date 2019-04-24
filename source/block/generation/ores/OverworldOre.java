package net.tslat.aoa3.block.generation.ores;

import net.minecraft.block.BlockOre;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

@Mod.EventBusSubscriber
public class OverworldOre extends BlockOre {
	private static final PropertyEnum<OreType> variant = PropertyEnum.create("ore", OreType.class);

	public OverworldOre() {
		setUnlocalizedName("OverworldOre");
		setRegistryName("aoa3:overworld_ore");
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
		setDefaultState(blockState.getBaseState().withProperty(variant, OreType.LIMONITE));
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, variant);
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState().withProperty(variant, OreType.getByMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(variant).getMeta();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	public String getName(ItemStack stack) {
		return OreType.getByMeta(stack.getMetadata()).getName();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> blocks) {
		for (OreType ore : OreType.values()) {
			blocks.add(new ItemStack(this, 1, ore.getMeta()));
		}
	}

	public enum OreType implements IStringSerializable {
		LIMONITE(0, "limonite"),
		SAPPHIRE(1, "sapphire"),
		JADE(2, "jade"),
		ROSITE(3, "rosite"),
		AMETHYST(4, "amethyst"),
		RUNIUM(5, "runium");

		private final int meta;
		private final String oreName;

		OreType(int meta, String name) {
			this.meta = meta;
			this.oreName = name;
		}

		public static OreType getByMeta(int meta) {
			switch (meta) {
				case 0:
					return LIMONITE;
				case 1:
					return SAPPHIRE;
				case 2:
					return JADE;
				case 3:
					return ROSITE;
				case 4:
					return AMETHYST;
				case 5:
					return RUNIUM;
				default:
					return null;
			}
		}

		@Override
		public String getName() {
			return oreName;
		}

		public int getMeta() {
			return meta;
		}

		public static String[] getAllNames() {
			String[] names = new String[values().length];

			for (OreType ore : values()) {
				names[ore.getMeta()] = ore.getName();
			}

			return names;
		}
	}
}
