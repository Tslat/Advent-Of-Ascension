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
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

public class RuneRandomizer extends Block {
	public RuneRandomizer() {
		super(Material.ROCK);
		setUnlocalizedName("RuneRandomizer");
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

		AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

		if (cap.getLevel(Enums.Skills.RUNATION) < 20) {
			cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.runeRandomizer.fail", "20"));
			return false;
		}


		ItemStack runeStack = ItemStack.EMPTY;

		switch (AdventOfAscension.rand.nextInt(heldItem.getItem() == ItemRegister.runeCharged ? 14 : 8)) {
			case 0:
				runeStack = new ItemStack(ItemRegister.runeEnergy, 2);
				break;
			case 1:
				runeStack = new ItemStack(ItemRegister.runeFire, 2);
				break;
			case 2:
				runeStack = new ItemStack(ItemRegister.runePoison, 2);
				break;
			case 3:
				runeStack = new ItemStack(ItemRegister.runePower, 2);
				break;
			case 4:
				runeStack = new ItemStack(ItemRegister.runeStrike, 2);
				break;
			case 5:
				runeStack = new ItemStack(ItemRegister.runeWater, 2);
				break;
			case 6:
				runeStack = new ItemStack(ItemRegister.runeWind, 2);
				break;
			case 7:
				runeStack = new ItemStack(ItemRegister.runeWind, 2);
				break;
			case 8:
				runeStack = new ItemStack(ItemRegister.runeCompass, 2);
				break;
			case 9:
				runeStack = new ItemStack(ItemRegister.runeDistortion, 2);
				break;
			case 10:
				runeStack = new ItemStack(ItemRegister.runeKinetic, 2);
				break;
			case 11:
				runeStack = new ItemStack(ItemRegister.runeLife, 2);
				break;
			case 12:
				runeStack = new ItemStack(ItemRegister.runeLunar, 2);
				break;
			case 13:
				runeStack = new ItemStack(ItemRegister.runeStorm, 2);
				break;
		}

		if (!player.capabilities.isCreativeMode)
			heldItem.shrink(1);

		ItemUtil.givePlayerItemOrDrop(player, runeStack);
		cap.addXp(Enums.Skills.RUNATION, 5, false);
		player.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.runeRandomizer, SoundCategory.BLOCKS, 1.0f, 1.0f);
		return true;
	}
}
