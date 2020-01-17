package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nullable;

public class EntityRunicLifeformRed extends EntityRunicLifeform {
	public EntityRunicLifeformRed(EntityRuneTemplar templar) {
		super(templar);
	}

	public EntityRunicLifeformRed(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRedRunicLifeform;
	}
}
