package net.tslat.aoa3.block.decoration.gates;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.block.CustomStateMapperBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class GateBlock extends BlockFenceGate implements CustomStateMapperBlock {
	public GateBlock(String name, String registryName) {
		super(BlockPlanks.EnumType.DARK_OAK);
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(2.0f);
		setResistance(5.0f);
		Blocks.FIRE.setFireInfo(this, 5, 20);
		setCreativeTab(CreativeTabsRegister.DECORATION_BLOCKS);
		setSoundType(SoundType.WOOD);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public StateMap getStateMapper() {
		return (new StateMap.Builder().ignore(BlockFenceGate.POWERED).build());
	}
}
