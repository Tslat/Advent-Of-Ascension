package net.tslat.aoa3.common.registration;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;

public class AoAAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<Attribute> RANGED_ATTACK_DAMAGE = register("ranged_attack_damage", "aoa3.rangedAttackDamage", 0, 0, Double.MAX_VALUE, true);

	private static RegistryObject<Attribute> register(String id, String name, double defaultValue, double minValue, double maxValue, boolean syncedWithClient) {
		return ATTRIBUTES.register(id, () -> new RangedAttribute(name, defaultValue, minValue, maxValue).setSyncable(syncedWithClient));
	}
}
