package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.FlashEntity;
import net.tslat.aoa3.entity.boss.KlobberEntity;
import net.tslat.aoa3.entity.boss.MirageEntity;
import net.tslat.aoa3.entity.boss.ProshieldEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class ImmortallisProgressor extends Block {
	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 1, 9);

	public ImmortallisProgressor() {
		super(BlockUtil.generateBlockProperties(Material.STONE, MaterialColor.GOLD, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));

		registerDefaultState(defaultBlockState().setValue(STAGE, 1));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!(player instanceof ServerPlayerEntity))
			return ActionResultType.SUCCESS;

		PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);

		switch (state.getValue(STAGE)) {
			case 1:
				if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), true, 1)) {
					ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));

					if (!player.addItem(new ItemStack(AoAItems.RETURN_CRYSTAL.get()))) {
						plData.sendThrottledChatMessage("message.feedback.item.returnCrystal.noSpace");
						plData.stats().resetAllTribute();
						player.spawnAtLocation(new ItemStack(AoAItems.PROGRESS_TOKEN.get()), 0.5f);
					}
					else {
						player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.goldStart.0", TextFormatting.GOLD), Util.NIL_UUID);
						player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.goldStart.1", TextFormatting.GOLD), Util.NIL_UUID);
						player.teleportTo(28, 20, 2);
					}

					return ActionResultType.SUCCESS;
				}
				break;
			case 2:
				if (plData.stats().getTribute(Deities.PLUTON) >= 100) {
					KlobberEntity klobber = new KlobberEntity(AoAEntities.Mobs.KLOBBER.get(), world);

					klobber.teleportTo(69, 21, 4);
					world.addFreshEntity(klobber);
					player.teleportTo(67, 21, 2);
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.klobberStart", TextFormatting.DARK_AQUA), Util.NIL_UUID);

					return ActionResultType.SUCCESS;
				}
				break;
			case 3:
				if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), true, 1)) {
					ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()));

					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.skeletalSpiritsStart", TextFormatting.RED), Util.NIL_UUID);
					player.teleportTo(81, 21, 2);

					return ActionResultType.SUCCESS;
				}
				break;
			case 4:
				if (plData.stats().getTribute(Deities.EREBON) >= 100) {
					ProshieldEntity proshield = new ProshieldEntity(AoAEntities.Mobs.PROSHIELD.get(), world);

					proshield.teleportTo(123, 21, 6);
					world.addFreshEntity(proshield);
					player.teleportTo(122, 21, 2);
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.proshieldStart", TextFormatting.DARK_AQUA), Util.NIL_UUID);

					return ActionResultType.SUCCESS;
				}
				break;
			case 5:
				if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), true, 1)) {
					ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()));

					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.pureGoldStart.0", TextFormatting.GOLD), Util.NIL_UUID);
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.pureGoldStart.1", TextFormatting.GOLD), Util.NIL_UUID);
					player.teleportTo(141, 24, 2);

					return ActionResultType.SUCCESS;
				}
				break;
			case 6:
				if (plData.stats().getTribute(Deities.PLUTON) == 200) {
					MirageEntity mirage = new MirageEntity(AoAEntities.Mobs.MIRAGE.get(), world);

					mirage.teleportTo(177, 24, -2);
					world.addFreshEntity(mirage);
					player.teleportTo(168, 24, 7);
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.mirageStart", TextFormatting.DARK_AQUA), Util.NIL_UUID);

					return ActionResultType.SUCCESS;
				}
				break;
			case 7:
				if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), true, 1)) {
					ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()));

					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.evilSpiritsStart", TextFormatting.RED), Util.NIL_UUID);
					player.teleportTo(189, 20, 2);

					return ActionResultType.SUCCESS;
				}
				break;
			case 8:
				if (plData.stats().getTribute(Deities.EREBON) == 200) {
					FlashEntity flash = new FlashEntity(AoAEntities.Mobs.FLASH.get(), world);

					flash.teleportTo(235, 22, 10);
					world.addFreshEntity(flash);
					player.teleportTo(233, 21, 3);
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.immortallisProgression.flashStart", TextFormatting.DARK_AQUA), Util.NIL_UUID);

					return ActionResultType.SUCCESS;
				}
				break;
			case 9:
				if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), true, 1)) {
					ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));

					player.teleportTo(0, 20, 0);
					plData.stats().resetAllTribute();

					return ActionResultType.SUCCESS;
				}
				break;
		}

		return ActionResultType.PASS;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE);
	}
}
