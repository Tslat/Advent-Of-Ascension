package net.tslat.aoa3.library.scheduling.async;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.tslat.aoa3.utils.ModUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class EchoGullTask implements Runnable {
	private final World world;
	private final ArrayList<Tuple<EntityLivingBase, Integer>> entityList;

	public EchoGullTask(World world, ArrayList<Tuple<EntityLivingBase, Integer>> entities) {
		this.world = world;
		this.entityList = entities;
	}

	@Override
	public void run() {
		if (!entityList.isEmpty()) {
			Iterator<Tuple<EntityLivingBase, Integer>> it = entityList.iterator();
			int distance = 0;

			while (it.hasNext()) {
				Tuple<EntityLivingBase, Integer> entry = it.next();

				if (distance == 0 || entry.getSecond() <= distance + 1) {
					distance = entry.getSecond();

					entry.getFirst().addPotionEffect(new PotionEffect(MobEffects.GLOWING, 7, 0, true, false));
					it.remove();
				}
				else {
					if (!entityList.isEmpty())
						ModUtil.scheduleSyncronisedTask(this, 1);

					return;
				}
			}
		}
	}
}
