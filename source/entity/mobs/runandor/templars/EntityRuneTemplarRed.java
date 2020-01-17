package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.item.misc.RuneItem;

import javax.annotation.Nullable;

public class EntityRuneTemplarRed extends EntityRuneTemplar {
	public EntityRuneTemplarRed(World world) {
		super(world);
	}

	@Override
	protected EntityRunicLifeform getLifeForm() {
		return new EntityRunicLifeformRed(this);
	}

	@Override
	protected RuneItem getActivationRune() {
		return ItemRegister.runeFire;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.runeTemplarRed;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return null;
	}
}
