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
import net.tslat.aoa3.block.generation.misc.RunePostBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class RuneShrine extends Block {
	public RuneShrine() {
		super(Material.ROCK);
		setTranslationKey("RuneShrine");
		setRegistryName("aoa3:rune_shrine");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}
	// TODO Fix level distribution across dimensions
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() != ItemRegister.UNPOWERED_RUNE && heldStack.getItem() != ItemRegister.CHARGED_RUNE)
			return true;

		BlockPos basePos = pos.up(2);
		Block post1 = world.getBlockState(basePos.north(3).east(3)).getBlock();
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		if (post1 instanceof RunePostBlock) {
			if (((RunePostBlock)post1).getLevelReq() <= plData.stats().getLevel(Enums.Skills.RUNATION)) {
				RuneItem rune = ((RunePostBlock)post1).getRune();

				if (rune.isChargedRune() && heldStack.getItem() != ItemRegister.CHARGED_RUNE)
					return false;

				Block post2 = world.getBlockState(basePos.north(3).west(3)).getBlock();
				Block post3 = world.getBlockState(basePos.south(3).east(3)).getBlock();
				Block post4 = world.getBlockState(basePos.south(3).west(3)).getBlock();

				if (post1 == post2 && post2 == post3 && post3 == post4) {
					int runeCount = heldStack.getCount();

					if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.RUNATION)
						runeCount *= 2;

					plData.stats().addXp(Enums.Skills.RUNATION, ((RunePostBlock)post1).getXpGain() * heldStack.getCount(), false, false);
					player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.RUNES_CRAFT, SoundCategory.BLOCKS, 1.0f, 1.0f);

					if (!player.capabilities.isCreativeMode) {
						int handCount = Math.min(64, runeCount);

						player.setHeldItem(hand, new ItemStack(rune, handCount));

						runeCount -= handCount;

						if (runeCount > 0)
							ItemUtil.givePlayerItemOrDrop(player, new ItemStack(rune, runeCount));
					}
					else {
						while (runeCount > 64) {
							ItemUtil.givePlayerItemOrDrop(player, new ItemStack(rune, 64));

							runeCount -= 64;
						}

						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(rune, runeCount));
					}

					player.inventoryContainer.detectAndSendChanges();
				}
			}
			else if (player instanceof EntityPlayerMP) {
				PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.RUNATION, ((RunePostBlock)post1).getLevelReq());
			}
		}
		else {
			int count = heldStack.getCount();


			if (!player.capabilities.isCreativeMode)
				player.setHeldItem(hand, ItemStack.EMPTY);

			plData.sendThrottledChatMessage("message.feedback.runeShrine.practice");
			plData.stats().addXp(Enums.Skills.RUNATION, 2 * count, false, false);
			player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsRegister.RUNES_CRAFT, SoundCategory.BLOCKS, 1.0f, 1.0f);
			player.inventoryContainer.detectAndSendChanges();
		}

		return true;
	}


}
