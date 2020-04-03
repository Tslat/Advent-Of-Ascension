package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.ExtractionUtil;

import java.util.List;
import java.util.Random;

public class ExtractionDevice extends Block {
	private final boolean full;

	public ExtractionDevice(boolean full) {
		super(Material.ROCK);
		this.full = full;
		setTranslationKey(full ? "ExtractionDeviceOn" : "ExtractionDevice");
		setRegistryName("aoa3:" + (full ? "extraction_device_on" : "extraction_device"));
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setTickRandomly(true);

		if (!full) {
			setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
		}
		else {
			setCreativeTab(null);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			IBlockState topBlock = world.getBlockState(pos.up());

			if (!full && (topBlock.getBlock() == Blocks.LAVA || (topBlock.getBlock() == Blocks.FLOWING_LAVA && topBlock.getBlock().getMetaFromState(topBlock) == 0))) {
				world.setBlockState(pos, BlockRegister.extractionDeviceOn.getDefaultState());
				world.setBlockToAir(pos.up());
			}
		}
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockRegister.extractionDevice);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if (!world.isRemote) {
			IBlockState topBlock = world.getBlockState(pos.up());

			if (!full && (topBlock.getBlock() == Blocks.LAVA || (topBlock.getBlock() == Blocks.FLOWING_LAVA && topBlock.getBlock().getMetaFromState(topBlock) == 0))) {
				world.setBlockState(pos, BlockRegister.extractionDeviceOn.getDefaultState());
				world.setBlockToAir(pos.up());
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!full)
			return false;

		if (!world.isRemote && player.getHeldItem(hand).getItem() instanceof InfusionBowl) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			if (world.getBlockState(pos.down()).getMaterial().isReplaceable()) {
				int lvl = plData.stats().getLevel(Enums.Skills.EXTRACTION);

				if (plData.equipment().getCurrentFullArmourSet() != Enums.ArmourSets.EXTRACTION)
					world.setBlockState(pos.down(), Blocks.OBSIDIAN.getDefaultState());

				if (ExtractionUtil.shouldGetLoot(lvl)) {
					if (!player.capabilities.isCreativeMode)
						player.getHeldItem(hand).damageItem(1, player);

					List<ItemStack> loot = ExtractionUtil.getLoot(player);

					for (ItemStack stack : loot) {
						if (!stack.isEmpty()) {
							player.sendMessage(StringUtil.getLocale("message.feedback.extraction").appendSibling(new TextComponentTranslation(stack.getTranslationKey() + ".name")));
						}
						else {
							player.sendMessage(StringUtil.getLocale("message.feedback.extraction.empty"));
						}

						ItemHandlerHelper.giveItemToPlayer(player, stack);
					}

					plData.stats().addXp(Enums.Skills.EXTRACTION, PlayerUtil.getXpRequiredForNextLevel(lvl) / ExtractionUtil.getXpDenominator(lvl), false, false);
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.extractionDeviceSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);
					world.setBlockState(pos, BlockRegister.extractionDevice.getDefaultState());

					if (player.dimension == 0)
						plData.stats().addTribute(Enums.Deities.PLUTON, 4);
				}
				else {
					player.sendMessage(StringUtil.getLocale("message.feedback.extraction.fail"));
				}
			}
			else {
				player.sendMessage(StringUtil.getColourLocale("message.feedback.extraction.noSpace", TextFormatting.RED));
			}
		}

		return true;
	}
}
