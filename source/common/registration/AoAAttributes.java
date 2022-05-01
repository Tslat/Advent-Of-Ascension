package net.tslat.aoa3.common.registration;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.RegistryObject;

public final class AoAAttributes {
	public static void init() {}

	public static final RegistryObject<Attribute> RANGED_ATTACK_DAMAGE = register("ranged_attack_damage", "aoa3.rangedAttackDamage", 0, 0, Double.MAX_VALUE, true);

	private static RegistryObject<Attribute> register(String id, String name, double defaultValue, double minValue, double maxValue, boolean syncedWithClient) {
		return AoARegistries.ENTITY_ATTRIBUTES.register(id, () -> new RangedAttribute(name, defaultValue, minValue, maxValue).setSyncable(syncedWithClient));
	}
}
