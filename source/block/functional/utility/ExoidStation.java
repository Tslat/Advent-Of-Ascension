package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.mobs.voxponds.EntityExoid;

public class ExoidStation extends Block {
	public ExoidStation() {
		super(Material.ROCK);
		setUnlocalizedName("ExoidStation");
		setRegistryName("aoa3:exoid_station");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() == ItemRegister.apocoStone) {
			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			EntityExoid exoid = new EntityExoid(world, 0);

			exoid.setLocationAndAngles(pos.getX(), pos.getY() + 3, pos.getZ(), 0, 0);
			world.spawnEntity(exoid);

			return true;
		}

		return true;
	}
}
