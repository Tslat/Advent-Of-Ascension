package net.tslat.aoa3.scheduling.async;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class EchoGullTask implements Runnable {
	private final World world;
	private final ArrayList<Tuple<LivingEntity, Integer>> entityList;

	public EchoGullTask(World world, ArrayList<Tuple<LivingEntity, Integer>> entities) {
		this.world = world;
		this.entityList = entities;
	}

	@Override
	public void run() {
		if (!entityList.isEmpty()) {
			Iterator<Tuple<LivingEntity, Integer>> it = entityList.iterator();
			int distance = 0;

			while (it.hasNext()) {
				Tuple<LivingEntity, Integer> entry = it.next();

				if (distance == 0 || entry.getB() <= distance + 1) {
					distance = entry.getB();

					EntityUtil.applyPotions(entry.getA(), new PotionUtil.EffectBuilder(Effects.GLOWING, 7));
					it.remove();
				}
				else {
					if (!entityList.isEmpty())
						AoAScheduler.scheduleSyncronisedTask(this, 1);

					return;
				}
			}
		}
	}
}
