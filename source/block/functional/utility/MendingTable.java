package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class MendingTable extends Block {
	public MendingTable() {
		super(Material.ROCK);
		setUnlocalizedName("MendingTable");
		setRegistryName("aoa3:mending_table");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;

		ItemStack stack = player.getHeldItem(hand);

		if (stack.getItem() == ItemRegister.ingotRustedIron) {
			if (!player.capabilities.isCreativeMode)
				stack.shrink(1);

			ItemUtil.givePlayerItemOrDrop(player, new ItemStack(Items.IRON_INGOT));
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.mendingSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);
		}
		else if (stack.getItem() == ItemRegister.oldBoot) {
			if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.magicMendingSolution))) {
				stack.shrink(1);
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(WeaponRegister.gunShoeFlinger));

				if (!player.capabilities.isCreativeMode)
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.metalTub));

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.mendingSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);
			}
		}
		else if (stack.getItemDamage() != 0 && stack.isItemStackDamageable()) {
			if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.magicMendingSolution))) {
				stack.setItemDamage(0);

				if (!player.capabilities.isCreativeMode)
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.metalTub));

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.mendingSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);
			}
		}

		return true;
	}
}
