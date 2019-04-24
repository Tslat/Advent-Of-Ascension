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
import net.tslat.aoa3.block.generation.misc.RunePostBlock;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

public class RuneShrine extends Block {
	public RuneShrine() {
		super(Material.ROCK);
		setUnlocalizedName("RuneShrine");
		setRegistryName("aoa3:rune_shrine");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() != ItemRegister.runeUnpowered && heldStack.getItem() != ItemRegister.runeCharged)
			return true;

		BlockPos basePos = pos.up(2);
		Block post1 = world.getBlockState(basePos.north(3).east(3)).getBlock();
		AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

		if (post1 instanceof RunePostBlock) {
			if (((RunePostBlock)post1).getLevelReq() <= cap.getLevel(Enums.Skills.RUNATION)) {
				RuneItem rune = ((RunePostBlock)post1).getRune();

				if (rune.isChargedRune() && heldStack.getItem() != ItemRegister.runeCharged)
					return false;

				Block post2 = world.getBlockState(basePos.north(3).west(3)).getBlock();
				Block post3 = world.getBlockState(basePos.south(3).east(3)).getBlock();
				Block post4 = world.getBlockState(basePos.south(3).west(3)).getBlock();

				if (post1 == post2 && post2 == post3 && post3 == post4) {
					ItemStack loot = new ItemStack(((RunePostBlock)post1).getRune(), 1);

					if (cap.getArmourSetType() == Enums.ArmourSets.RUNATION)
						loot.setCount(2);

					if (!player.capabilities.isCreativeMode)
						heldStack.shrink(1);

					cap.addXp(Enums.Skills.RUNATION, ((RunePostBlock)post1).getXpGain(), false);
					ItemUtil.givePlayerItemOrDrop(player, loot);
					player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.makeRunes, SoundCategory.BLOCKS, 1.0f, 1.0f);
				}
			}
		}
		else {
			if (!player.capabilities.isCreativeMode)
				heldStack.shrink(1);

			cap.sendPlayerMessage(StringUtil.getLocale("message.feedback.runeShrine.practice"));
			cap.addXp(Enums.Skills.RUNATION, 2, false);
			player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.makeRunes, SoundCategory.BLOCKS, 1.0f, 1.0f);
			player.inventoryContainer.detectAndSendChanges();
		}

		return true;
	}


}
