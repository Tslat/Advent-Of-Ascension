package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.mobeffect.BleedingEffect;
import net.tslat.aoa3.content.mobeffect.BurnedEffect;
import net.tslat.aoa3.content.mobeffect.NethengeicCurseEffect;

import java.util.function.Supplier;

public final class AoAMobEffects {
	public static void init() {}

	public static final DeferredHolder<MobEffect, BleedingEffect> BLEEDING = register("bleeding", BleedingEffect::new);
	public static final DeferredHolder<MobEffect, BurnedEffect> BURNED = register("burned", BurnedEffect::new);
	public static final DeferredHolder<MobEffect, NethengeicCurseEffect> NETHENGEIC_CURSE = register("nethengeic_curse", NethengeicCurseEffect::new);

	private static <T extends MobEffect> DeferredHolder<MobEffect, T> register(String id, Supplier<T> effect) {
		return AoARegistries.MOB_EFFECTS.register(id, effect);
	}
}
