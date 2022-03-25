package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.boss.SkeletronEntity;
import net.tslat.aoa3.content.entity.mob.precasia.*;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.Random;

public class ArmyBlock extends BossAltarBlock {
	public ArmyBlock() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.TERRACOTTA_GREEN).stats(35f, 1000f).randomTicks().get());
	}

	@Override
	protected boolean checkActivationConditions(PlayerEntity player, Hand hand, BlockState state, BlockPos pos) {
		if (!player.level.isClientSide && WorldUtil.isWorld(player.level, AoADimensions.PRECASIA.key) && player.level.getEntitiesOfClass(MonsterEntity.class, new AxisAlignedBB(pos).inflate(100), entity -> entity instanceof SkeleElderEntity || entity instanceof SkeletronEntity).size() == 0) {
			SkeleElderEntity skeleElder = new SkeleElderEntity(player.level, pos, 0);

			skeleElder.setPos(pos.getX(), pos.getY() + 1, pos.getZ());

			player.level.addFreshEntity(skeleElder);
		}

		return false;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (!world.isClientSide && WorldUtil.isWorld((IServerWorld)world, AoADimensions.PRECASIA.key) && rand.nextBoolean() && world.getEntitiesOfClass(SkeleElderEntity.class, new AxisAlignedBB(pos).inflate(100)).isEmpty())
			world.addFreshEntity(new SkeleElderEntity(world, pos, 0));
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		spawnWave(player.level, blockPos, 1);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAEntities.Mobs.SKELETRON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	private static void spawnWaveEntities(World world, BlockPos armyBlockPos, Entity... entities) {
		for (Entity entity : entities) {
			int posX = armyBlockPos.getX() - 17 + RandomUtil.randomNumberUpTo(23);
			int posZ = armyBlockPos.getZ() - 11 + RandomUtil.randomNumberUpTo(19);
			BlockPos.Mutable checkPos = new BlockPos.Mutable(posX, armyBlockPos.getY(), posZ);

			while (checkPos.getY() < world.getMaxBuildHeight() && !world.isEmptyBlock(checkPos.move(Direction.UP))) {
				;
			}

			entity.setPos(posX, checkPos.getY(), posZ);
			world.addFreshEntity(entity);
		}
	}


	public static void spawnWave(World world, BlockPos blockPos, int wave) {
		if (!world.isClientSide) {
			switch (wave) {
				case 1:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 2:
					spawnWaveEntities(world, blockPos,
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 3:
					spawnWaveEntities(world, blockPos,
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 4:
					spawnWaveEntities(world, blockPos,
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 5:
					spawnWaveEntities(world, blockPos,
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 6:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 7:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 8:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 9:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 10:
					spawnWaveEntities(world, blockPos,
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 11:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 12:
					spawnWaveEntities(world, blockPos,
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 13:
					spawnWaveEntities(world, blockPos,
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 14:
					spawnWaveEntities(world, blockPos,
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 15:
					spawnWaveEntities(world, blockPos,
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 16:
					spawnWaveEntities(world, blockPos,
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 17:
					spawnWaveEntities(world, blockPos,
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 18:
					spawnWaveEntities(world, blockPos,
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 19:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAEntities.Mobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAEntities.Mobs.SKELE_HOPPER.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAEntities.Mobs.SKELEMAN.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 20:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 21:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 22:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 23:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 24:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 25:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 26:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 27:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 28:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 29:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 30:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 31:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 32:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 33:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 34:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 35:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 36:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),  
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAEntities.Mobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAEntities.Mobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAEntities.Mobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 37:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 38:
					spawnWaveEntities(world, blockPos, 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAEntities.Mobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAEntities.Mobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAEntities.Mobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 39:
					world.addFreshEntity(new SkeletronEntity(world, blockPos));
					break;
				default:
					break;
			}
		}
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}
