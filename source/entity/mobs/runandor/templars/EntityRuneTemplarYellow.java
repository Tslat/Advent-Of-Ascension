package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

public class EntityRuneTemplarYellow extends EntityRuneTemplar {
	public EntityRuneTemplarYellow(World world) {
		super(world);
	}

	@Override
	protected Item getRuneFragment() {
		return ItemRegister.megaRuneFragmentYellow;
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformYellow(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runeEnergy;
	}
}
