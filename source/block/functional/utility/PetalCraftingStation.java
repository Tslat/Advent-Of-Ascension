package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class PetalCraftingStation extends Block {
	public PetalCraftingStation() {
		super(Material.ROCK);
		setUnlocalizedName("PetalCraftingStation");
		setRegistryName("aoa3:petal_crafting_station");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (ItemUtil.consumeMultipleItemsSafely(player, new ItemStack(ItemRegister.smallPetalBlue), new ItemStack(ItemRegister.smallPetalGreen), new ItemStack(ItemRegister.smallPetalOrange),
					new ItemStack(ItemRegister.smallPetalRed), new ItemStack(ItemRegister.smallPetalPurple))) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.petals));
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.petalCraftingStationSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);

				return true;
			}
		}

		return true;
	}
}
