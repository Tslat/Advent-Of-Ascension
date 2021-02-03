package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.ConiferonEntity;
import net.tslat.aoa3.entity.boss.GoldorthEntity;
import net.tslat.aoa3.entity.boss.HoronEntity;
import net.tslat.aoa3.entity.boss.PenumbraEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class AncientCavernShrine extends Block {
	private final Deities deity;

	public AncientCavernShrine(Deities deity) {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.GRAY, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));

		this.deity = deity;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		System.out.println(world);
		if (player instanceof ServerPlayerEntity && !player.isSneaking() && world.getDimension().getType() == AoADimensions.ANCIENT_CAVERN.type()) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			BlockPos teleportPos = new BlockPos(0, 17, 0);

			switch (deity) {
				case EREBON:
					if (pos.getX() > -20 || pos.getZ() < 60) {
						teleportPos = new BlockPos(-70, 18, 80);

						if (!player.isCreative()) {
							if (/*plData.stats().getLevel(Skills.AUGURY) < 70 || */plData.stats().getLevel(Skills.HUNTER) < 70) {
								//PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.AUGURY, 70);
								PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.HUNTER, 70);

								return ActionResultType.PASS;
							}

							if (plData.stats().getTribute(Deities.EREBON) < 200) {
								PlayerUtil.notifyPlayerOfInsufficientTribute((ServerPlayerEntity)player, Deities.EREBON, 200);

								return ActionResultType.PASS;
							}
							else {
								plData.stats().addTribute(Deities.EREBON, -20);
							}
						}

						PenumbraEntity penumbra = new PenumbraEntity(AoAEntities.Mobs.PENUMBRA.get(), world);

						penumbra.setLocationAndAngles(-51, 18, 92, 0, 0);
						world.addEntity(penumbra);
					}
					break;
				case LUXON:
					if (pos.getX() < 60 || pos.getZ() > -20) {
						teleportPos = new BlockPos(80, 18, -70);

						if (!player.isCreative()) {
							if (/*plData.stats().getLevel(Skills.AUGURY) < 70 || */plData.stats().getLevel(Skills.RUNATION) < 70) {
								//PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.AUGURY, 70);
								PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.RUNATION, 70);

								return ActionResultType.PASS;
							}

							if (plData.stats().getTribute(Deities.LUXON) < 200) {
								PlayerUtil.notifyPlayerOfInsufficientTribute((ServerPlayerEntity)player, Deities.LUXON, 200);

								return ActionResultType.PASS;
							}
							else {
								plData.stats().addTribute(Deities.LUXON, -20);
							}
						}

						HoronEntity horon = new HoronEntity(AoAEntities.Mobs.HORON.get(), world);

						horon.setLocationAndAngles(80, 18, -70, 0, 0);
						world.addEntity(horon);
					}
					break;
				case PLUTON:
					if (pos.getX() < 60 || pos.getZ() < 60) {
						teleportPos = new BlockPos(80, 18, 80);

						if (!player.isCreative()) {
							if (/*plData.stats().getLevel(Skills.AUGURY) < 70 || */plData.stats().getLevel(Skills.FORAGING) < 70) {
								//PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.AUGURY, 70);
								PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.FORAGING, 70);

								return ActionResultType.PASS;
							}

							if (plData.stats().getTribute(Deities.PLUTON) < 200) {
								PlayerUtil.notifyPlayerOfInsufficientTribute((ServerPlayerEntity)player, Deities.PLUTON, 200);

								return ActionResultType.PASS;
							}
							else {
								plData.stats().addTribute(Deities.PLUTON, -20);
							}
						}

						GoldorthEntity goldorth = new GoldorthEntity(AoAEntities.Mobs.GOLDORTH.get(), world);

						goldorth.setLocationAndAngles(80, 18, 80, 0, 0);
						world.addEntity(goldorth);
					}
					break;
				case SELYAN:
					if (pos.getX() > -20 || pos.getZ() > -20) {
						teleportPos = new BlockPos(-60, 18, -65);

						if (!player.isCreative()) {
							if (/*plData.stats().getLevel(Skills.AUGURY) < 70 || */plData.stats().getLevel(Skills.INFUSION) < 70) {
								//PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.AUGURY, 70);
								PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.INFUSION, 70);

								return ActionResultType.PASS;
							}

							if (plData.stats().getTribute(Deities.SELYAN) < 200) {
								PlayerUtil.notifyPlayerOfInsufficientTribute((ServerPlayerEntity)player, Deities.SELYAN, 200);

								return ActionResultType.PASS;
							}
							else {
								plData.stats().addTribute(Deities.SELYAN, -20);
							}
						}

						ConiferonEntity coniferon = new ConiferonEntity(AoAEntities.Mobs.CONIFERON.get(), world);

						coniferon.setLocationAndAngles(-60, 18, -65, 0, 0);
						world.addEntity(coniferon);
					}
					break;
			}

			player.setPositionAndUpdate(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
		}

		return ActionResultType.SUCCESS;
	}
}
