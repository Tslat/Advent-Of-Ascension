package net.tslat.aoa3.item.misc.summoning;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.CorallusEntity;
import net.tslat.aoa3.entity.misc.BossItemEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class AmphibiyteLung extends BossSpawningItem {
	public AmphibiyteLung() {
		super(() -> null, ParticleTypes.BUBBLE);
	}

	@Override
	public void handleTimerParticles(ItemEntity entityItem, double posX, double posY, double posZ, int lifespan, int ticksExisted) {
		if (entityItem.isInWater() && entityItem.getItem().getCount() == 5) {
			super.handleTimerParticles(entityItem, posX, posY, posZ, lifespan, ticksExisted);
		}
		else if (entityItem.getItem().getCount() != 5 || (entityItem.ticksExisted > 40 && lifespan != BossItemEntity.lifetime)) {
			entityItem.lifespan = 6000;
		}
	}

	@Override
	public void spawnBoss(World world, ServerPlayerEntity summoner, double posX, double posY, double posZ) {
		CorallusEntity corallus = new CorallusEntity(AoAEntities.Mobs.CORALLUS.get(), world);

		corallus.setLocationAndAngles(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addEntity(corallus);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("entity.aoa3.corallus.spawn", summoner.getDisplayName().getFormattedText()), world, new BlockPos(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(World world, ServerPlayerEntity player, double posX, double posY, double posZ) {
		if (world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != Blocks.WATER)
			return false;


		if (!checkSpawnArea(world, new AxisAlignedBB(posX - 0.5d, posY, posZ - 0.5d, posX + 0.5d, posY + 3d, posZ + 0.5d))) {
			PlayerUtil.notifyPlayer(player, "message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}

	private boolean checkSpawnArea(World world, AxisAlignedBB boundingBox) {
		try (BlockPos.PooledMutable checkPos = BlockPos.PooledMutable.retain()) {
			for(int x = MathHelper.floor(boundingBox.minX); x < MathHelper.ceil(boundingBox.maxX); ++x) {
				for(int y = MathHelper.floor(boundingBox.minY); y < MathHelper.ceil(boundingBox.maxY); ++y) {
					for(int z = MathHelper.floor(boundingBox.minZ); z < MathHelper.ceil(boundingBox.maxZ); ++z) {
						BlockState state = world.getBlockState(checkPos.setPos(x, y, z));

						if (!state.isAir(world, checkPos) && state.getFluidState().getFluid() == Fluids.EMPTY)
							return false;
					}
				}
			}

			return true;
		}
	}
}
