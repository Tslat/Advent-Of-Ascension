package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.LootUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class RuneRandomizer extends Block {
	public RuneRandomizer() {
		super(Material.ROCK);
		setTranslationKey("RuneRandomizer");
		setRegistryName("aoa3:rune_randomizer");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking())
			return false;

		if (world.isRemote)
			return true;

		ItemStack heldItem = player.getHeldItem(hand);

		if (heldItem.getItem() != ItemRegister.runeUnpowered && heldItem.getItem() != ItemRegister.runeCharged)
			return true;

		PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		if (!player.capabilities.isCreativeMode && plData.stats().getLevel(Enums.Skills.RUNATION) < 20) {
			if (player instanceof EntityPlayerMP)
				PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.RUNATION, 20);

			return false;
		}

		if (!player.capabilities.isCreativeMode)
			heldItem.shrink(1);

		LootUtil.generateAndProvideLootDirectly((EntityPlayerMP)player, LootSystemRegister.blockRuneRandomizer);

		if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.RUNATION)
			LootUtil.generateAndProvideLootDirectly((EntityPlayerMP)player, LootSystemRegister.blockRuneRandomizer);

		plData.stats().addXp(Enums.Skills.RUNATION, 5, false);
		player.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.runeRandomizer, SoundCategory.BLOCKS, 1.0f, 1.0f);
		return true;
	}
}
