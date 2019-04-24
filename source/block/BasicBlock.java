package net.tslat.aoa3.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class BasicBlock extends Block {
	public BasicBlock(String name, String registryName, Material material, float hardness, float resistance) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(hardness);
		setResistance(resistance);
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
		doMaterialCheck(material);
	}

	public BasicBlock(String name, String registryName, Material material) {
		this(name, registryName, material, 1.5f, 10.0f);
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
