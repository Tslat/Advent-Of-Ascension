package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nullable;

public class EntityRunicLifeformYellow extends EntityRunicLifeform {
	public EntityRunicLifeformYellow(EntityRuneTemplar templar) {
		super(templar);
	}

	public EntityRunicLifeformYellow(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityYellowRunicLifeform;
	}
}
