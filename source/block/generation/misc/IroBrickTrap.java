package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class IroBrickTrap extends BasicBlock {
	public IroBrickTrap() {
		super("IroBrickTrap", "iro_brick_trap", Material.ROCK);
		setHardness(15.0f);
		setResistance(15.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(null);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (PlayerUtil.shouldPlayerBeAffected(player))
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20, 2));

		world.setBlockToAir(pos);
	}
}
