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
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class GoldAccumulator extends Block {
	public GoldAccumulator() {
		super(Material.ROCK);
		setTranslationKey("GoldAccumulator");
		setRegistryName("aoa3:gold_accumulator");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(null);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() == ItemRegister.IMPURE_GOLD) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			plData.stats().addTribute(Enums.Deities.PLUTON, Math.min(100 - plData.stats().getTribute(Enums.Deities.PLUTON), 20));

			if (plData.stats().getTribute(Enums.Deities.PLUTON) >= 100)
				plData.sendThrottledChatMessage("message.feedback.immortallisProgression.goldEnd",  player.getDisplayNameString());
		}

		return true;
	}
}
