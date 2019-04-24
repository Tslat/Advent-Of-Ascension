package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class MineralizationStation extends Block {
	public MineralizationStation() {
		super(Material.ROCK);
		setUnlocalizedName("MineralizationStation");
		setRegistryName("aoa3:mineralization_station");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = player.getHeldItem(hand);

			switch (stack.getItem().getUnlocalizedName()) {
				case "item.BaronyteIngot":
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);

					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.coinSilver, 3));
					return true;
				case "item.ElecaniumIngot":
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);

					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.coinSilver, 2));
					return true;
				case "item.VarsiumIngot":
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);

					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.coinSilver, 1));
					return true;
				case "item.BlaziumIngot":
					if (!player.capabilities.isCreativeMode)
						stack.shrink(1);

					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.coinSilver, 20));
					return true;
			}
		}

		return true;
	}
}
