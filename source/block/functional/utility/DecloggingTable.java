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
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.WaterloggedItem;
import net.tslat.aoa3.utils.ItemUtil;

public class DecloggingTable extends Block {
	public DecloggingTable() {
		super(Material.ROCK);
		setTranslationKey("DecloggingTable");
		setRegistryName("aoa3:declogging_Table");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() instanceof WaterloggedItem) {
			ItemStack stack = player.getHeldItem(hand);
			ItemUtil.givePlayerItemOrDrop(player, new ItemStack(((WaterloggedItem)stack.getItem()).getFixedItem()));
			stack.shrink(1);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.decloggingTableUse, SoundCategory.BLOCKS, 1.0f, 1.0f);

			return true;
		}

		return true;
	}
}
