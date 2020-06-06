package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class EnigmaTable extends Block {
	public EnigmaTable() {
		super(Material.ROCK);
		setTranslationKey("EnigmaTable");
		setRegistryName("aoa3:enigma_table");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			for (int i = 0; i < 4; i++) {
				EntityItem unchargedStone = new EntityItem(world, pos.getX(), pos.getY() + 0.2, pos.getZ(), new ItemStack(ItemRegister.UNCHARGED_STONE));

				unchargedStone.setPickupDelay(10);
				unchargedStone.addVelocity(AdventOfAscension.rand.nextGaussian(), 1 + AdventOfAscension.rand.nextDouble(), AdventOfAscension.rand.nextGaussian());
				world.spawnEntity(unchargedStone);
			}

			world.setBlockToAir(pos);
		}

		return true;
	}
}
