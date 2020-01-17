package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.InfusionStone;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class InfusionTable extends Block {
	public InfusionTable() {
		super(Material.ROCK);
		setTranslationKey("InfusionTable");
		setRegistryName("aoa3:infusion_table");
		setHardness(10.0f);
		setResistance(15.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			Item item = stack.getItem();

			if (item instanceof InfusionStone) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
				InfusionStone stone = (InfusionStone)item;
				int count = stack.getCount();

				if (player.capabilities.isCreativeMode || plData.stats().getLevel(Enums.Skills.INFUSION) >= stone.getLvl()) {
					plData.stats().addXp(Enums.Skills.INFUSION, stone.getXp() * count, false);
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.infusionSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);

					int chanceMod = plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.INFUSION ? 33 : 100;
					int powerStoneCount = 0;

					for (int i = 0; i < count; i++) {
						if (AdventOfAscension.rand.nextInt(chanceMod) == 0)
							powerStoneCount++;
					}

					if (!player.capabilities.isCreativeMode) {
						if (powerStoneCount > 0) {
							player.setHeldItem(hand, new ItemStack(stone.getPowerStone(), powerStoneCount));
						}
						else {
							player.setHeldItem(hand, ItemStack.EMPTY);
						}
					}
					else {
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(stone.getPowerStone(), powerStoneCount));
					}
				}
			}
			else {
				player.openGui(AdventOfAscension.instance(), Enums.ModGuis.INFUSION_TABLE.guiId, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}

		return true;
	}
}
