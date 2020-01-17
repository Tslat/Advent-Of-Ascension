package net.tslat.aoa3.block;

import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface CustomStateMapperBlock {
	@SideOnly(Side.CLIENT)
	StateMap getStateMapper();
}
