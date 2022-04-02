package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.util.BlockUtil;

import java.util.Random;

public class ExtractionDevice extends Block {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");

	public ExtractionDevice() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.PODZOL).stats(5f, 10f).noOcclusion().get());

		registerDefaultState(defaultBlockState().setValue(FILLED, false));
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (!world.isClientSide) {
			BlockState topBlock = world.getBlockState(pos.above());

			if (!state.getValue(FILLED) && topBlock.getFluidState().isSource() && topBlock.getFluidState().is(FluidTags.LAVA)) {
				world.setBlockAndUpdate(pos, defaultBlockState().setValue(FILLED, true));
				world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
		if (world instanceof ServerLevel)
			tick(state, (ServerLevel)world, pos, world.random);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		/*if (state.getValue(FILLED) && player.getItemInHand(hand).getItem() instanceof InfusionBowl) {
			if (player instanceof ServerPlayer) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)player);
				ItemStack heldStack = player.getItemInHand(hand);

				if (world.getBlockState(pos.below()).getMaterial().isReplaceable() || world.isOutsideBuildHeight(pos.below())) {
					int lvl = plData.stats().getLevel(Skills.EXTRACTION);

					world.setBlockAndUpdate(pos, defaultBlockState());

					if (plData.equipment().getCurrentFullArmourSet() != AdventArmour.Type.EXTRACTION)
						world.setBlockAndUpdate(pos.below(), Blocks.OBSIDIAN.defaultBlockState());

					if (ExtractionUtil.shouldGetLoot(lvl)) {
						if (!player.isCreative())
							ItemUtil.damageItem(heldStack, player, hand);

						List<ItemStack> loot = ExtractionUtil.getLoot((ServerPlayer)player, new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));

						for (ItemStack stack : loot) {
							if (!stack.isEmpty()) {
								player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction").append(new TranslatableComponent(stack.getDescriptionId())), Util.NIL_UUID);
							}
							else {
								player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.nothing"), Util.NIL_UUID);
							}

							ItemHandlerHelper.giveItemToPlayer(player, stack);
						}

						player.awardStat(Stats.ITEM_USED.get(heldStack.getItem()));
						plData.stats().addXp(Skills.EXTRACTION, PlayerUtil.getXpRequiredForNextLevel(lvl) / ExtractionUtil.getXpDenominator(lvl), false, false);
						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_EXTRACTION_DEVICE_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

						if (WorldUtil.isWorld(player.level, Level.OVERWORLD) && player.level.isDay())
							plData.stats().addTribute(Deities.PLUTON, 4);
					}
					else {
						player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.fail"), Util.NIL_UUID);
					}
				}
				else {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.extraction.noSpace", ChatFormatting.RED), Util.NIL_UUID);
				}
			}

			return InteractionResult.PASS;
		}*/ // TODO

		return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FILLED);
	}
}
