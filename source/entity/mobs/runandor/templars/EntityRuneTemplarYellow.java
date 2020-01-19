package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

import javax.annotation.Nullable;

public class EntityRuneTemplarYellow extends EntityRuneTemplar {
	public EntityRuneTemplarYellow(World world) {
		super(world);
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformYellow(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runeEnergy;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.runeTemplarYellow;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return null;
	}
}
