package net.tslat.aoa3.common.registration;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public final class AoAAttributes {
	public static void init() {}

	public static final RegistryObject<Attribute> RANGED_ATTACK_DAMAGE = register("ranged_attack_damage", "aoa3.rangedAttackDamage", 0, 0, Double.MAX_VALUE, false);
	public static final RegistryObject<Attribute> AGGRO_RANGE = register("aggro_range", "aoa3.aggroRange", 8, 0, Double.MAX_VALUE, false);

	public static final AttributeModifier NIGHT_AGGRO_MODIFIER = new AttributeModifier(UUID.fromString("48b802a7-2926-4f89-8e0a-1c44205c18b0"), "Nighttime Aggro Reduction", -0.6d, AttributeModifier.Operation.MULTIPLY_TOTAL);

	private static RegistryObject<Attribute> register(String id, String name, double defaultValue, double minValue, double maxValue, boolean syncedWithClient) {
		return AoARegistries.ENTITY_ATTRIBUTES.register(id, () -> new RangedAttribute(name, defaultValue, minValue, maxValue).setSyncable(syncedWithClient));
	}
}

/*
Spare UUIDS
68f3cddf-305f-4e81-b9d6-f16445d0d33d
9bd244e4-83e1-494f-b055-55c7f88b9ff4
c535c9df-280b-436c-9e4d-7eb4a877a702
628c1a4d-5f9d-4227-bf93-2c5f55844d7e
160cb8bc-320d-472d-9c91-9b2ad4b4a69b
fbbb86ae-8625-4d2b-bec5-7946ad51ccfe
56d22281-5223-48d0-9fb3-7c79f978cd4a
0b14711b-02f1-4bb2-8048-8e7c47f9aa1c
 */
