package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nullable;

public class EntityRunicLifeformBlue extends EntityRunicLifeform {
	public EntityRunicLifeformBlue(EntityRuneTemplar templar) {
		super(templar);
	}

	public EntityRunicLifeformBlue(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBlueRunicLifeform;
	}
}
