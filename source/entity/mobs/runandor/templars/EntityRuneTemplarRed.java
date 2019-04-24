package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

public class EntityRuneTemplarRed extends EntityRuneTemplar {
	public EntityRuneTemplarRed(World world) {
		super(world);
	}

	@Override
	protected Item getRuneFragment() {
		return ItemRegister.megaRuneFragmentRed;
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformRed(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runeFire;
	}
}
