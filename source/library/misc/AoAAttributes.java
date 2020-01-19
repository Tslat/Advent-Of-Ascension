package net.tslat.aoa3.library.misc;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.UUID;

public final class AoAAttributes {
	public static final UUID ATTACK_SPEED_MAINHAND = UUID.fromString("99fdc256-279e-4c8e-b1c6-9209571f134e");
	public static final UUID ATTACK_SPEED_OFFHAND = UUID.fromString("63f030a6-7269-444d-b26c-ae3514b36e1b");
	public static final UUID MAX_HEALTH = UUID.fromString("00e6648a-d6ee-4894-95e3-f9668d58339d");
	public static final UUID MOVEMENT_SPEED = UUID.fromString("a1371c64-c09e-4ed6-adfd-5afbaea79369");
	public static final UUID VANILLA_ATTACK_DAMAGE = ObfuscationReflectionHelper.getPrivateValue(Item.class, Items.AIR, "ATTACK_DAMAGE_MODIFIER", "field_111210_e");
	public static final UUID VANILLA_ATTACK_SPEED = ObfuscationReflectionHelper.getPrivateValue(Item.class, Items.AIR, "ATTACK_SPEED_MODIFIER", "field_185050_h");
	public static final UUID INNERVATION_HEALTH_BUFF = UUID.fromString("81746891-97cf-4eef-9c67-13c120f40032");
	public static final UUID BATTLEBORN_ARMOUR_BUFF = UUID.fromString("5cf50cfa-4298-46d1-b7ec-c648f8e8d5c9");

	public static final AttributeModifier BLOODTHIRSTY_BUFF = new AttributeModifier(UUID.fromString("2803f9b4-57ed-471f-8a0e-7a41fa100608"), "AoABloodthirstyBuff", 1.05, 1);
	public static final AttributeModifier BRACE_DEBUFF = new AttributeModifier(MOVEMENT_SPEED, "AoABraceDebuff", -0.35, 1);
	public static final AttributeModifier GARDENCIA_CANDIED_WATER_BUFF = new AttributeModifier(UUID.fromString("d5356e33-40b6-4515-a37b-4377f911f703"), "AoAGardenciaCandiedWaterBuff", 50, 0);
	public static final AttributeModifier LUXON_SCYTHE_LUCK = new AttributeModifier(UUID.fromString("e446949b-1792-4a66-8f83-5037d6dcce9b"), "AoALuxonScytheLuckBuff", 2, 0);
	public static final AttributeModifier SEALORD_ATTACK_BUFF = new AttributeModifier(UUID.fromString("027744fa-e85d-4d1e-946a-747739900753"), "AoASealordMovementBuff", 2, 0);
	public static final AttributeModifier MECHA_STAFF_DEBUFF = new AttributeModifier(UUID.fromString("3a1413c7-055b-405c-8d89-d3270c94f133"), "AoAMechaStaffDebuff", -0.5, 1);

	public static final AttributeModifier INNERVATION_ARMOUR_SET = new AttributeModifier(UUID.fromString("bc07e37c-9b4b-4bc3-8aa5-a613b8d3c257"), "AoAInnervationArmourSet", 10d, 0);

	public static final AttributeModifier KNIGHT_ARMOUR_BOOTS = new AttributeModifier(UUID.fromString("9283b669-bc04-4055-a165-73e3a2b5ab7e"), "AoAKnightArmourBoots", 1.5d, 0);
	public static final AttributeModifier KNIGHT_ARMOUR_LEGS = new AttributeModifier(UUID.fromString("e60b8cda-a196-4922-b867-01d2422a9e8c"), "AoAKnightArmourLegs", 1.5d, 0);
	public static final AttributeModifier KNIGHT_ARMOUR_BODY = new AttributeModifier(UUID.fromString("8ecbc122-563a-4de5-8f27-3f461ad2fb5c"), "AoAKnightArmourBody", 1.5d, 0);
	public static final AttributeModifier KNIGHT_ARMOUR_HELMET = new AttributeModifier(UUID.fromString("673ef5d8-9df5-4dbb-84f0-1da677d59f05"), "AoAKnightArmourHelmet", 1.5d, 0);

	public static final AttributeModifier SPEED_ARMOUR_SET = new AttributeModifier(UUID.fromString("748347d0-6ed4-4917-9495-0e7137fcf61a"), "AoASpeedArmourBoots", 0.1d, 2);
	public static final AttributeModifier SPEED_ARMOUR_BOOTS = new AttributeModifier(UUID.fromString("d0a8b22e-8c0e-42a0-be57-284110170f8c"), "AoASpeedArmourBoots", 0.1d, 2);
	public static final AttributeModifier SPEED_ARMOUR_LEGS = new AttributeModifier(UUID.fromString("31c9600f-b10f-48f0-8acb-2a3009fb3466"), "AoASpeedArmourLegs", 0.1d, 2);
	public static final AttributeModifier SPEED_ARMOUR_BODY = new AttributeModifier(UUID.fromString("0f20e0f4-a909-409d-99cf-65fd80f516c7"), "AoASpeedArmourBody", 0.1d, 2);
	public static final AttributeModifier SPEED_ARMOUR_HELMET = new AttributeModifier(UUID.fromString("6d13cd91-39d8-4e68-8c25-b9b45bb729d9"), "AoASpeedArmourHelmet", 0.1d, 2);

	public static final AttributeModifier HUNTER_ARMOUR_KNOCKBACK = new AttributeModifier(UUID.fromString("a794717e-8b9b-4d20-b224-0a7571ddd012"), "AoAHunterArmourBuff", 0.5, 0);

	public static final AttributeModifier SUBTERRANEAN_ARMOUR_ATTACK_SPEED_DEBUFF = new AttributeModifier(UUID.fromString("d4631555-8ceb-490d-9066-fb4188560b15"), "AoASubterraneanAttackSpeedDebuff", -0.16666667, 2);

	public static AttributeModifier battlebornArmourBuff(double currentValue) {
		return new AttributeModifier(BATTLEBORN_ARMOUR_BUFF, "AoABattlebornArmour", currentValue, 2);
	}

	public static AttributeModifier gunMainHandSpeedModifier(double currentValue) {
		return new AttributeModifier(ATTACK_SPEED_MAINHAND, "AoAGunMainHand", currentValue, 2);
	}

	public static AttributeModifier gunOffHandSpeedModifier(double currentValue) {
		return new AttributeModifier(ATTACK_SPEED_OFFHAND, "AoAGunOffHand", currentValue, 2);
	}

	public static AttributeModifier vanillaWeaponDamageModifier(double currentValue) {
		return new AttributeModifier(VANILLA_ATTACK_DAMAGE, "Weapon modifier", currentValue, 0);
	}

	public static AttributeModifier vanillaWeaponSpeedModifier(double currentValue) {
		return new AttributeModifier(VANILLA_ATTACK_SPEED, "Weapon modifier", currentValue, 0);
	}

	public static AttributeModifier innervationHealthBuff(double currentValue) {
		return new AttributeModifier(INNERVATION_HEALTH_BUFF, "AoAInnervationHealthBuff", currentValue, 0);
	}

	/* Spares
		9c59eceb-dcd0-40e0-a608-a46d3794b1c3
		a7cd0b89-ca94-4e54-a0c4-f56e8cb70bb0
		93bb7485-ce86-4e78-ab50-26f53d78ad9d
		678cb085-1367-42c3-8437-d07ade6201d0
		f2b95d53-fb23-47af-8770-2a63e55e1444
		1c7aee89-904d-492e-a5d0-56c1268bf77d
		0aae7f65-5bfe-4283-aa23-a07f97cbdefa
		a09fbfb7-a94a-4b55-a453-29b4fbb23433
		373966e9-8a4b-4d8c-90a0-14c1e3509584
		2c2aa6f0-7491-4401-8889-4efba30d3a45
		a3f0e220-23a2-415f-840d-86e61d675e6f
		d823fe6f-7ced-4a2b-8eea-5cc8d2ed3977
		8a5dddcb-f9ed-4e1d-8320-6fb1773fc09a
		b3204407-5dcf-4d27-a745-3bd724cf1e60
		a3c37ac3-be0d-4f6d-8b70-b7026afdd947
		95618e9b-b509-472e-891f-34e5f8c25dab
		6e283b27-cadb-48be-b935-16957141828a
		33b440b3-365d-4d85-b026-77bd7434f338
		30547c93-9607-43ca-a525-1e2e73682054
		1ae6e5a6-01fb-4af4-9717-84781184d5b9
		e30b49fd-1453-4dda-9503-626a67592396
		634901a8-3ba5-47cf-bcab-1432141f6f17
		285a09fd-86d6-4538-a239-7d6cac0709d1
		012a8820-5694-4ec2-a095-1eca6d28a7ef
		ee50e8a8-e2a0-43f0-91e0-f33115bb16e1
		144c86d3-65a1-4282-a93a-61e5dc199f70
		2b5e7969-7b18-49b5-849d-b5a084bed369
	 */
}
