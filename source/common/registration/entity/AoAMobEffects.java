package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.mobeffect.BleedingEffect;
import net.tslat.aoa3.content.mobeffect.BurnedEffect;
import net.tslat.aoa3.content.mobeffect.NethengeicCurseEffect;

import java.util.function.Supplier;

public final class AoAMobEffects {
	public static void init() {}

	public static final RegistryObject<MobEffect> BLEEDING = register("bleeding", BleedingEffect::new);
	public static final RegistryObject<MobEffect> BURNED = register("burned", BurnedEffect::new);
	public static final RegistryObject<MobEffect> NETHENGEIC_CURSE = register("nethengeic_curse", NethengeicCurseEffect::new);

	private static RegistryObject<MobEffect> register(String id, Supplier<MobEffect> effect) {
		return AoARegistries.MOB_EFFECTS.register(id, effect);
	}
}
