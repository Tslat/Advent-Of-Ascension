package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.content.entity.misc.BossItemEntity;
import net.tslat.aoa3.util.PlayerUtil;

public class AmphibiyteLung extends BossSpawningItem {
	public AmphibiyteLung() {
		super(() -> null, ParticleTypes.BUBBLE);
	}

	@Override
	public void handleTimerParticles(ItemEntity entityItem, double posX, double posY, double posZ, int lifespan, int ticksExisted) {
		if (entityItem.isInWater() && entityItem.getItem().getCount() == 5) {
			super.handleTimerParticles(entityItem, posX, posY, posZ, lifespan, ticksExisted);
		}
		else if (entityItem.getItem().getCount() != 5 || (entityItem.tickCount > 40 && lifespan != BossItemEntity.lifetime)) {
			entityItem.lifespan = 6000;
		}
	}

	@Override
	public void spawnBoss(Level world, ServerPlayer summoner, double posX, double posY, double posZ) {
		/*CorallusEntity corallus = new CorallusEntity(AoAMobs.CORALLUS.get(), world);

		corallus.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(corallus);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.CORALLUS.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, new BlockPos(posX, posY, posZ), 50);*/
	}

	@Override
	public boolean canSpawnHere(Level world, ServerPlayer player, double posX, double posY, double posZ) {
		if (world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != Blocks.WATER)
			return false;

		if (!checkSpawnArea(world, new AABB(posX - 0.5d, posY, posZ - 0.5d, posX + 0.5d, posY + 3d, posZ + 0.5d))) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.noSpace"));

			return false;
		}

		return true;
	}

	private boolean checkSpawnArea(Level world, AABB boundingBox) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

		for(int x = Mth.floor(boundingBox.minX); x < Mth.ceil(boundingBox.maxX); ++x) {
			for(int y = Mth.floor(boundingBox.minY); y < Mth.ceil(boundingBox.maxY); ++y) {
				for(int z = Mth.floor(boundingBox.minZ); z < Mth.ceil(boundingBox.maxZ); ++z) {
					BlockState state = world.getBlockState(checkPos.set(x, y, z));

					if (!state.isAir() && state.getFluidState().getType() == Fluids.EMPTY)
						return false;
				}
			}
		}

		return true;
	}
}
