package net.tslat.aoa3.block.decoration.fences;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class FenceBlock extends BlockFence {
	public FenceBlock(String name, String registryName, Material material) {
		super(material, BlockPlanks.EnumType.DARK_OAK.getMapColor());
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(2.0f);
		setResistance(5.0f);
		doMaterialCheck(material);
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
	}

	public FenceBlock(String name, String registryName) {
		this(name, registryName, Material.WOOD);
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
