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
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;

public class DivineStation extends Block {
	public DivineStation() {
		super(Material.ROCK);
		setUnlocalizedName("DivineStation");
		setRegistryName("aoa3:divine_station");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && EnchantmentsRegister.intervention.canApply(player.getHeldItem(hand))) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

			if (cap.getLevel(Enums.Skills.AUGURY) >= 20 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneAmbient))) {
				player.getHeldItem(hand).addEnchantment(EnchantmentsRegister.intervention, 1);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.infusionSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);
				player.inventoryContainer.detectAndSendChanges();

				return true;
			}
		}

		return true;
	}
}
