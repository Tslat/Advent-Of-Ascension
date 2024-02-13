package net.tslat.aoa3.common.misc;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public final class NativePatching {
	public static void init() {
		((RangedAttribute)Attributes.MAX_HEALTH).maxValue = Double.MAX_VALUE;
		((RangedAttribute)Attributes.ATTACK_KNOCKBACK).maxValue = Double.MAX_VALUE;
		((RangedAttribute)Attributes.ARMOR_TOUGHNESS).maxValue = Double.MAX_VALUE;
	}
}
