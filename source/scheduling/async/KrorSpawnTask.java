package net.tslat.aoa3.scheduling.async;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.concurrent.TimeUnit;

public class KrorSpawnTask implements Runnable {
	private final ServerPlayer player;
	private final Level world;
	private final BlockPos chargingTablePos;
	private int chargedAmount = 0;

	public KrorSpawnTask(ServerPlayer player, BlockPos pos) {
		this.player = player;
		this.world = player.level();
		this.chargingTablePos = pos;
	}

	@Override
	public void run() {
		if (player.level() != world || player.distanceToSqr(chargingTablePos.getX(), chargingTablePos.getY(), chargingTablePos.getZ()) > 100) {
			//player.sendSystemMessage(LocaleUtil.getLocaleMessage(AoAMobs.KROR.get().getDescriptionId() + "tooFar"));

			return;
		}

		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.spawnBoss.difficultyFail"));

			return;
		}

		if (world.getBlockState(chargingTablePos).getBlock() != AoABlocks.CHARGING_TABLE.get())
			return;

		if (chargedAmount < 200) {
			if (PlayerUtil.consumeResource(player, AoAResources.SPIRIT.get(), 20, false)) {
				chargedAmount += 20;

				((ServerLevel)world).sendParticles(ParticleTypes.END_ROD, chargingTablePos.getX() + 0.5d, chargingTablePos.getY() + 0.9, chargingTablePos.getZ() + 0.5d, 1, 0, 0, 0, 0d);
				((ServerLevel)world).sendParticles(ParticleTypes.END_ROD, player.getX(), player.getBoundingBox().maxY + 0.5d, player.getZ(), 1, 0, 0, 0, 0d);

				schedule(1, TimeUnit.SECONDS);
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientResources(player, AoAResources.SPIRIT.get(), 20);
			}
		}
		else {
			/*KrorEntity kror = new KrorEntity(AoAMobs.KROR.get(), world);
			int i = 0;

			kror.setPos(chargingTablePos.getX() - 0.5d, chargingTablePos.getY(), chargingTablePos.getZ() - 0.5 + 10);

			while (!world.noCollision(kror.getBoundingBox())) {
				i++;
				Random rand = kror.getRandom();

				kror.setPos( kror.getX() + rand.nextInt(20) - 10, kror.getY(), kror.getZ() + rand.nextInt(20) - 10);

				if (i > 64) {
					player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.spawnBoss.noSpace"));

					return;
				}
			}

			PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.KROR.get().getDescriptionId() + ".spawn", player.getDisplayName()), world, player.blockPosition(), 50);
			world.addFreshEntity(kror);
			kror.setTarget(player);*/
		}
	}

	public void schedule(Integer time, TimeUnit units) {
		AoAScheduler.scheduleAsyncTask(this, time, units);
	}
}
