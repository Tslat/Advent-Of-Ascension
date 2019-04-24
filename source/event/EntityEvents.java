package net.tslat.aoa3.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

public class EntityEvents {
	@SubscribeEvent
	public void onMobDeath(LivingDeathEvent ev) {
		EntityLivingBase entity = ev.getEntityLiving();

		if (!entity.world.isRemote && !(entity instanceof EntityPlayer) && entity.getMaxHealth() > 1) {
			if (ev.getSource().getTrueSource() instanceof EntityPlayer && AdventOfAscension.rand.nextInt(8 * (entity instanceof IMob ? 1 : 3)) == 0) {
				entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundsRegister.heartStoneSpawn, SoundCategory.MASTER, 1.0f, 1.0f);
				entity.dropItem(ItemRegister.heartStone, 1);
			}
		}
	}
}
