package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.utils.LootUtil;

public class IroCrate extends Block {
	public IroCrate() {
		super(Material.ROCK);
		setTranslationKey("IroCrate");
		setRegistryName("aoa3:iro_crate");
		setHardness(5.0f);
		setResistance(3f);
		setSoundType(SoundType.METAL);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (world instanceof WorldServer)
			drops.addAll(LootUtil.generateLootWithCustomLuck(LootSystemRegister.blockIroCrate, (WorldServer)world, fortune));
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
}
