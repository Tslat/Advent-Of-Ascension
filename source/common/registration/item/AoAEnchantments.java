package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.enchantment.*;
import net.tslat.aoa3.content.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.content.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.content.item.weapon.greatblade.BaseGreatblade;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.content.item.weapon.maul.BaseMaul;
import net.tslat.aoa3.content.item.weapon.shotgun.BaseShotgun;
import net.tslat.aoa3.content.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.content.item.weapon.staff.BaseStaff;

import java.util.function.Supplier;

public final class AoAEnchantments {
	public static void init() {}

	public static final EnchantmentCategory GUN = EnchantmentCategory.create("AOA_GUN", item -> item instanceof BaseGun);
	public static final EnchantmentCategory GREATBLADE = EnchantmentCategory.create("AOA_GREATBLADE", item -> item instanceof BaseGreatblade);
	public static final EnchantmentCategory SHOTGUN = EnchantmentCategory.create("AOA_SHOTGUN", item -> item instanceof BaseShotgun);
	public static final EnchantmentCategory MAUL = EnchantmentCategory.create("AOA_MAUL", item -> item instanceof BaseMaul);
	public static final EnchantmentCategory MELEE_WEAPON = EnchantmentCategory.create("AOA_MELEE_WEAPON", item -> item instanceof SwordItem || item instanceof BaseMaul);
	public static final EnchantmentCategory STAFF = EnchantmentCategory.create("AOA_STAFF", item -> item instanceof BaseStaff);
	public static final EnchantmentCategory AMMO_CONSUMING = EnchantmentCategory.create("AOA_AMMO_CONSUMING", item -> item instanceof BaseGun || item instanceof BaseBlaster || item instanceof BaseStaff);
	public static final EnchantmentCategory BULLET_FIRING = EnchantmentCategory.create("AOA_BULLET_FIRING", item -> item instanceof BaseGun && !(item instanceof BaseCannon));
	public static final EnchantmentCategory BLASTER = EnchantmentCategory.create("AOA_BLASTER", item -> item instanceof BaseBlaster);
	public static final EnchantmentCategory UNSTACKABLE = EnchantmentCategory.create("AOA_UNSTACKABLE", item -> item != null && item.getMaxStackSize() <= 1);
	public static final EnchantmentCategory LIGHT_GUN = EnchantmentCategory.create("AOA_DUAL_WIELDABLE_GUN", item -> item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon));

	public static final DeferredHolder<Enchantment, Enchantment> GREED = registerEnchantment("greed", GreedEnchantment::new);

	public static final DeferredHolder<Enchantment, ArchmageEnchantment> ARCHMAGE = registerEnchantment("archmage", ArchmageEnchantment::new);
	public static final DeferredHolder<Enchantment, BraceEnchantment> BRACE = registerEnchantment("brace", BraceEnchantment::new);
	public static final DeferredHolder<Enchantment, ControlEnchantment> CONTROL = registerEnchantment("control", ControlEnchantment::new);
	public static final DeferredHolder<Enchantment, FormEnchantment> FORM = registerEnchantment("form", FormEnchantment::new);
	public static final DeferredHolder<Enchantment, InterventionEnchantment> INTERVENTION = registerEnchantment("intervention", InterventionEnchantment::new);
	public static final DeferredHolder<Enchantment, RechargeEnchantment> RECHARGE = registerEnchantment("recharge", RechargeEnchantment::new);
	public static final DeferredHolder<Enchantment, SeverEnchantment> SEVER = registerEnchantment("sever", SeverEnchantment::new);
	public static final DeferredHolder<Enchantment, ShellEnchantment> SHELL = registerEnchantment("shell", ShellEnchantment::new);

	private static <T extends Enchantment> DeferredHolder<Enchantment, T> registerEnchantment(String id, Supplier<T> enchantment) {
		return AoARegistries.ENCHANTMENTS.register(id, enchantment);
	}
}
