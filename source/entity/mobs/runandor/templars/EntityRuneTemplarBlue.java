package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

public class EntityRuneTemplarBlue extends EntityRuneTemplar {
	public EntityRuneTemplarBlue(World world) {
		super(world);
	}

	@Override
	protected Item getRuneFragment() {
		return ItemRegister.megaRuneFragmentBlue;
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformBlue(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runeWater;
	}
}
