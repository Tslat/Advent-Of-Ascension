package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.ExtractionUtil;

import java.util.List;
import java.util.Random;

public class ExtractionDevice extends Block {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");

	public ExtractionDevice() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.OBSIDIAN, 5, 10, SoundType.STONE).notSolid());

		setDefaultState(getDefaultState().with(FILLED, false));
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!world.isRemote) {
			BlockState topBlock = world.getBlockState(pos.up());

			if (!state.get(FILLED) && topBlock.getFluidState().isSource() && topBlock.getFluidState().isTagged(FluidTags.LAVA)) {
				world.setBlockState(pos, getDefaultState().with(FILLED, true));
				world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
		if (world instanceof ServerWorld)
			tick(state, (ServerWorld)world, pos, world.rand);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (state.get(FILLED) && player.getHeldItem(hand).getItem() instanceof InfusionBowl) {
			if (player instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
				ItemStack heldStack = player.getHeldItem(hand);

				if (world.getBlockState(pos.down()).getMaterial().isReplaceable() || world.isOutsideBuildHeight(pos.down())) {
					int lvl = plData.stats().getLevel(Skills.EXTRACTION);

					world.setBlockState(pos, getDefaultState());

					if (plData.equipment().getCurrentFullArmourSet() != AdventArmour.Type.EXTRACTION)
						world.setBlockState(pos.down(), Blocks.OBSIDIAN.getDefaultState());

					if (ExtractionUtil.shouldGetLoot(lvl)) {
						if (!player.isCreative())
							ItemUtil.damageItem(heldStack, player, hand);

						List<ItemStack> loot = ExtractionUtil.getLoot((ServerPlayerEntity)player, pos);

						for (ItemStack stack : loot) {
							if (!stack.isEmpty()) {
								player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction").appendSibling(new TranslationTextComponent(stack.getTranslationKey())));
							}
							else {
								player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.nothing"));
							}

							ItemHandlerHelper.giveItemToPlayer(player, stack);
						}

						player.addStat(Stats.ITEM_USED.get(heldStack.getItem()));
						plData.stats().addXp(Skills.EXTRACTION, PlayerUtil.getXpRequiredForNextLevel(lvl) / ExtractionUtil.getXpDenominator(lvl), false, false);
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_EXTRACTION_DEVICE_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);

						if (player.world.getDimension().getType() == DimensionType.OVERWORLD && player.world.isDaytime())
							plData.stats().addTribute(Deities.PLUTON, 4);
					}
					else {
						player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.fail"));
					}
				}
				else {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.noSpace", TextFormatting.RED));
				}
			}

			return ActionResultType.PASS;
		}

		return ActionResultType.FAIL;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FILLED);
	}
}
