package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.smartbrainlib.util.RandomUtil;

public class ArmyBlock extends BossAltarBlock {
	public ArmyBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected boolean checkActivationConditions(Player player, InteractionHand hand, BlockState state, BlockPos pos) {
		/*if (!player.level.isClientSide && WorldUtil.isWorld(player.level, AoADimensions.PRECASIA) && player.level.getEntitiesOfClass(Monster.class, new AABB(pos).inflate(100), entity -> entity instanceof SkeleElderEntity || entity instanceof SkeletronEntity).size() == 0) {
			SkeleElderEntity skeleElder = new SkeleElderEntity(player.level, pos, 0);

			skeleElder.setPos(pos.getX(), pos.getY() + 1, pos.getZ());

			player.level.addFreshEntity(skeleElder);
		}*/

		return false;
	}
/*
	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (!world.isClientSide && WorldUtil.isWorld((ServerLevelAccessor)world, AoADimensions.PRECASIA) && rand.nextBoolean() && world.getEntitiesOfClass(SkeleElderEntity.class, new AABB(pos).inflate(100)).isEmpty())
			world.addFreshEntity(new SkeleElderEntity(world, pos, 0));
	}*/

	@Override
	protected void doActivationEffect(Player player, InteractionHand hand, BlockState state, BlockPos blockPos) {
		//spawnWave(player.level(), blockPos, 1);
		//sendSpawnMessage(player, LocaleUtil.getLocaleMessage(AoAMobs.SKELETRON.get().getDescriptionId() + ".spawn", player.getDisplayName()), blockPos);
	}

	private static void spawnWaveEntities(Level world, BlockPos armyBlockPos, Entity... entities) {
		for (Entity entity : entities) {
			int posX = armyBlockPos.getX() - 17 + RandomUtil.randomNumberUpTo(23);
			int posZ = armyBlockPos.getZ() - 11 + RandomUtil.randomNumberUpTo(19);
			BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(posX, armyBlockPos.getY(), posZ);

			while (checkPos.getY() < world.getMaxBuildHeight() && !world.isEmptyBlock(checkPos.move(Direction.UP))) {
				;
			}

			entity.setPos(posX, checkPos.getY(), posZ);
			world.addFreshEntity(entity);
		}
	}


	/*public static void spawnWave(Level world, BlockPos blockPos, int wave) {
		if (!world.isClientSide) {
			switch (wave) {
				case 1:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 2:
					spawnWaveEntities(world, blockPos,
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 3:
					spawnWaveEntities(world, blockPos,
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 4:
					spawnWaveEntities(world, blockPos,
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 5:
					spawnWaveEntities(world, blockPos,
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 6:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 7:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 8:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 9:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 10:
					spawnWaveEntities(world, blockPos,
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 11:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 12:
					spawnWaveEntities(world, blockPos,
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 13:
					spawnWaveEntities(world, blockPos,
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 14:
					spawnWaveEntities(world, blockPos,
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 15:
					spawnWaveEntities(world, blockPos,
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 16:
					spawnWaveEntities(world, blockPos,
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 17:
					spawnWaveEntities(world, blockPos,
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 18:
					spawnWaveEntities(world, blockPos,
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 19:
					spawnWaveEntities(world, blockPos,
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkelePigEntity(AoAMobs.SKELE_PIG.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkeleHopperEntity(AoAMobs.SKELE_HOPPER.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new SkelemanEntity(AoAMobs.SKELEMAN.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 20:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 21:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 22:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 23:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 24:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 25:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 26:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 27:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 28:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 29:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 30:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 31:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 32:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 33:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 34:
					spawnWaveEntities(world, blockPos,
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 35:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 36:
					spawnWaveEntities(world, blockPos,
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),  
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkelePigEntity(AoAMobs.STRONG_SKELE_PIG.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkeleHopperEntity(AoAMobs.STRONG_SKELE_HOPPER.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new StrongSkelemanEntity(AoAMobs.STRONG_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 37:
					spawnWaveEntities(world, blockPos,
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 38:
					spawnWaveEntities(world, blockPos, 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world), 
							new EliteSkelePigEntity(AoAMobs.ELITE_SKELE_PIG.get(), world),
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world), 
							new EliteSkeleHopperEntity(AoAMobs.ELITE_SKELE_HOPPER.get(), world),
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world), 
							new EliteSkelemanEntity(AoAMobs.ELITE_SKELEMAN.get(), world),
							new SkeleElderEntity(world, blockPos, wave));
					break;
				case 39:
					//world.addFreshEntity(new SkeletronEntity(world, blockPos));
					break;
				default:
					break;
			}
		}
	}*/

	@Override
	protected Item getActivationItem() {
		return null;
	}
}
