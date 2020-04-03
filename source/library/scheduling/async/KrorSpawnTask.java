package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.boss.kror.EntityKror;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class KrorSpawnTask implements Runnable {
	private final EntityPlayerMP player;
	private final World world;
	private final BlockPos chargingTablePos;
	private int chargedAmount = 0;

	public KrorSpawnTask(EntityPlayerMP player, BlockPos pos) {
		this.player = player;
		this.world = player.world;
		this.chargingTablePos = pos;
	}

	@Override
	public void run() {
		if (player.world != world || player.getDistance(chargingTablePos.getX(), chargingTablePos.getY(), chargingTablePos.getZ()) > 10) {
			player.sendMessage(StringUtil.getLocale("message.mob.kror.tooFar"));

			return;
		}

		if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			player.sendMessage(StringUtil.getLocale("message.feedback.spawnBoss.difficultyFail"));

			return;
		}

		if (world.getBlockState(chargingTablePos).getBlock() != BlockRegister.chargingTable)
			return;

		if (chargedAmount < 200) {
			if (PlayerUtil.consumeResource(player, Enums.Resources.ENERGY, 20, false)) {
				chargedAmount += 20;

				((WorldServer)world).spawnParticle(EnumParticleTypes.END_ROD, chargingTablePos.getX() + 0.5d, chargingTablePos.getY() + 0.9, chargingTablePos.getZ() + 0.5d, 1, 0, 0, 0, 0d);
				((WorldServer)world).spawnParticle(EnumParticleTypes.END_ROD, player.posX, player.getEntityBoundingBox().maxY + 0.5d, player.posZ, 1, 0, 0, 0, 0d);

				schedule(1, TimeUnit.SECONDS);
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientResources(player, Enums.Resources.ENERGY, 20);
			}
		}
		else {
			EntityKror kror = new EntityKror(world);
			int i = 0;

			kror.setPosition(chargingTablePos.getX() - 0.5d, chargingTablePos.getY(), chargingTablePos.getZ() - 0.5 + 10);

			while (world.checkBlockCollision(kror.getEntityBoundingBox())) {
				i++;
				Random rand = kror.getRNG();

				kror.setPosition( kror.posX + rand.nextInt(20) - 10, kror.posY, kror.posZ + rand.nextInt(20) - 10);

				if (i > 64) {
					player.sendMessage(StringUtil.getLocale("message.feedback.spawnBoss.noSpace"));

					return;
				}
			}

			StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.kror.spawn", player.getDisplayNameString()), player, 50);
			world.spawnEntity(kror);
			kror.setAttackTarget(player);
		}
	}

	public void schedule(Integer time, TimeUnit units) {
		ModUtil.scheduleRequiredAsyncTask(this, time, units);
	}
}
