package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.KrorEntity;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class KrorSpawnTask implements Runnable {
	private final ServerPlayerEntity player;
	private final World world;
	private final BlockPos chargingTablePos;
	private int chargedAmount = 0;

	public KrorSpawnTask(ServerPlayerEntity player, BlockPos pos) {
		this.player = player;
		this.world = player.world;
		this.chargingTablePos = pos;
	}

	@Override
	public void run() {
		if (player.world != world || player.getDistanceSq(chargingTablePos.getX(), chargingTablePos.getY(), chargingTablePos.getZ()) > 100) {
			player.sendMessage(LocaleUtil.getLocaleMessage("message.mob.kror.tooFar"));

			return;
		}

		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.spawnBoss.difficultyFail"));

			return;
		}

		if (world.getBlockState(chargingTablePos).getBlock() != AoABlocks.CHARGING_TABLE.get())
			return;

		if (chargedAmount < 200) {
			if (PlayerUtil.consumeResource(player, Resources.ENERGY, 20, false)) {
				chargedAmount += 20;

				((ServerWorld)world).spawnParticle(ParticleTypes.END_ROD, chargingTablePos.getX() + 0.5d, chargingTablePos.getY() + 0.9, chargingTablePos.getZ() + 0.5d, 1, 0, 0, 0, 0d);
				((ServerWorld)world).spawnParticle(ParticleTypes.END_ROD, player.getPosX(), player.getBoundingBox().maxY + 0.5d, player.getPosZ(), 1, 0, 0, 0, 0d);

				schedule(1, TimeUnit.SECONDS);
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientResources(player, Resources.ENERGY, 20);
			}
		}
		else {
			KrorEntity kror = new KrorEntity(AoAEntities.Mobs.KROR.get(), world);
			int i = 0;

			kror.setPosition(chargingTablePos.getX() - 0.5d, chargingTablePos.getY(), chargingTablePos.getZ() - 0.5 + 10);

			while (world.checkBlockCollision(kror.getBoundingBox())) {
				i++;
				Random rand = kror.getRNG();

				kror.setPosition( kror.getPosX() + rand.nextInt(20) - 10, kror.getPosY(), kror.getPosZ() + rand.nextInt(20) - 10);

				if (i > 64) {
					player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.spawnBoss.noSpace"));

					return;
				}
			}

			PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.kror.spawn", player.getDisplayName().getFormattedText()), world, player.getPosition(), 50);
			world.addEntity(kror);
			kror.setAttackTarget(player);
		}
	}

	public void schedule(Integer time, TimeUnit units) {
		AoAScheduler.scheduleAsyncTask(this, time, units);
	}
}
