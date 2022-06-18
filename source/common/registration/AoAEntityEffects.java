package net.tslat.aoa3.common.registration;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.content.mobeffect.BleedingEffect;

import java.util.function.Supplier;

public final class AoAEntityEffects {
	public static void init() {}

	public static final RegistryObject<MobEffect> BLEEDING = register("bleeding", BleedingEffect::new);

	private static RegistryObject<MobEffect> register(String id, Supplier<MobEffect> effect) {
		return AoARegistries.ENTITY_EFFECTS.register(id, effect);
	}
}
