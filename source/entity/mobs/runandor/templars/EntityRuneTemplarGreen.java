package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

public class EntityRuneTemplarGreen extends EntityRuneTemplar {
	public EntityRuneTemplarGreen(World world) {
		super(world);
	}

	@Override
	protected Item getRuneFragment() {
		return ItemRegister.megaRuneFragmentGreen;
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformGreen(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runePoison;
	}
}
