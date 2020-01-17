package net.tslat.aoa3.item.misc;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.corallus.EntityCorallus;
import net.tslat.aoa3.entity.misc.EntityBossItem;
import net.tslat.aoa3.item.misc.summon.BossSpawningItem;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class AmphibiyteLung extends BossSpawningItem {
	public AmphibiyteLung() {
		super("AmphibiyteLung", "amphibiyte_lung", null, EnumParticleTypes.WATER_BUBBLE);
	}

	@Override
	public void handleTimerParticles(EntityItem entityItem, double posX, double posY, double posZ, int lifespan, int ticksExisted) {
		if (entityItem.isInWater() && entityItem.getItem().getCount() == 5) {
			super.handleTimerParticles(entityItem, posX, posY, posZ, lifespan, ticksExisted);
		}
		else if (entityItem.getItem().getCount() != 5 || (entityItem.ticksExisted > 40 && lifespan != EntityBossItem.lifetime)) {
			entityItem.lifespan = 6000;
		}
	}

	@Override
	public void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ) {
		EntityCorallus corallus = new EntityCorallus(world);

		corallus.setLocationAndAngles(posX, posY, posZ, itemRand.nextFloat() * 360f, 0f);
		world.spawnEntity(corallus);
		StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.corallus.spawn", summoner.getDisplayNameString()), corallus, 50);
	}

	@Override
	public boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ) {
		if (world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != Blocks.WATER)
			return false;

		if (world.collidesWithAnyBlock(new AxisAlignedBB(posX - 0.5d, posY, posZ - 0.5d, posX + 0.5d, posY + 3d, posZ + 0.5d))) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}
}
