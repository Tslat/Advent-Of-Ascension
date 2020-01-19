package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

import javax.annotation.Nullable;

public class EntityRuneTemplarBlue extends EntityRuneTemplar {
	public EntityRuneTemplarBlue(World world) {
		super(world);
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformBlue(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runeWater;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.runeTemplarBlue;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return null;
	}
}
