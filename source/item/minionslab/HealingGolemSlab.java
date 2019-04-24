package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityHealingGolem;

public class HealingGolemSlab extends BaseSlab {
	public HealingGolemSlab() {
		super("HealingGolemSlab", "healing_golem_slab", 90, 200, 90, 4000);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityHealingGolem healingGolem = new EntityHealingGolem(pl.world);

		healingGolem.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		healingGolem.setTamedBy(pl);
		pl.world.spawnEntity(healingGolem);

		return healingGolem;
	}
}
