package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;

public class ExtractionDevice extends Block {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");

	public ExtractionDevice() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.PODZOL).stats(5f, 10f).noOcclusion().get());

		registerDefaultState(defaultBlockState().setValue(FILLED, false));
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!world.isClientSide) {
			BlockState topBlock = world.getBlockState(pos.above());

			if (!state.getValue(FILLED) && topBlock.getFluidState().isSource() && topBlock.getFluidState().is(FluidTags.LAVA)) {
				world.setBlockAndUpdate(pos, defaultBlockState().setValue(FILLED, true));
				world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
		if (world instanceof ServerWorld)
			tick(state, (ServerWorld)world, pos, world.random);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		/*if (state.getValue(FILLED) && player.getItemInHand(hand).getItem() instanceof InfusionBowl) {
			if (player instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
				ItemStack heldStack = player.getItemInHand(hand);

				if (world.getBlockState(pos.below()).getMaterial().isReplaceable() || world.isOutsideBuildHeight(pos.below())) {
					int lvl = plData.stats().getLevel(Skills.EXTRACTION);

					world.setBlockAndUpdate(pos, defaultBlockState());

					if (plData.equipment().getCurrentFullArmourSet() != AdventArmour.Type.EXTRACTION)
						world.setBlockAndUpdate(pos.below(), Blocks.OBSIDIAN.defaultBlockState());

					if (ExtractionUtil.shouldGetLoot(lvl)) {
						if (!player.isCreative())
							ItemUtil.damageItem(heldStack, player, hand);

						List<ItemStack> loot = ExtractionUtil.getLoot((ServerPlayerEntity)player, new Vector3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));

						for (ItemStack stack : loot) {
							if (!stack.isEmpty()) {
								player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction").append(new TranslationTextComponent(stack.getDescriptionId())), Util.NIL_UUID);
							}
							else {
								player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.nothing"), Util.NIL_UUID);
							}

							ItemHandlerHelper.giveItemToPlayer(player, stack);
						}

						player.awardStat(Stats.ITEM_USED.get(heldStack.getItem()));
						plData.stats().addXp(Skills.EXTRACTION, PlayerUtil.getXpRequiredForNextLevel(lvl) / ExtractionUtil.getXpDenominator(lvl), false, false);
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_EXTRACTION_DEVICE_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);

						if (WorldUtil.isWorld(player.level, World.OVERWORLD) && player.level.isDay())
							plData.stats().addTribute(Deities.PLUTON, 4);
					}
					else {
						player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.fail"), Util.NIL_UUID);
					}
				}
				else {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.noSpace", TextFormatting.RED), Util.NIL_UUID);
				}
			}

			return ActionResultType.PASS;
		}*/ // TODO

		return ActionResultType.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FILLED);
	}
}
