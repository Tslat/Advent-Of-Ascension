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
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

public class PureGoldAccumulator extends Block {
	public PureGoldAccumulator() {
		super(Material.ROCK);
		setUnlocalizedName("PureGoldAccumulator");
		setRegistryName("aoa3:pure_gold_accumulator");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(null);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() == ItemRegister.pureGold) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand	).shrink(1);

			cap.addTribute(Enums.Deities.PLUTON, 20);

			if (cap.getTribute(Enums.Deities.PLUTON) == 200)
				cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.immortallisProgression.pureGoldEnd", player.getDisplayNameString()));
		}

		return true;
	}
}
