package net.tslat.aoa3.common.registration.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.RegistryObject;
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

	public static final EnchantmentCategory GUN = EnchantmentCategory.create("GUN", item -> item instanceof BaseGun);
	public static final EnchantmentCategory GREATBLADE = EnchantmentCategory.create("GREATBLADE", item -> item instanceof BaseGreatblade);
	public static final EnchantmentCategory SHOTGUN = EnchantmentCategory.create("SHOTGUN", item -> item instanceof BaseShotgun);
	public static final EnchantmentCategory MAUL = EnchantmentCategory.create("MAUL", item -> item instanceof BaseMaul);
	public static final EnchantmentCategory MELEE_WEAPON = EnchantmentCategory.create("MELEE_WEAPON", item -> item instanceof SwordItem || item instanceof BaseMaul);
	public static final EnchantmentCategory STAFF = EnchantmentCategory.create("STAFF", item -> item instanceof BaseStaff);
	public static final EnchantmentCategory AMMO_CONSUMING = EnchantmentCategory.create("AMMO_CONSUMING", item -> item instanceof BaseGun || item instanceof BaseBlaster || item instanceof BaseStaff);
	public static final EnchantmentCategory BULLET_FIRING = EnchantmentCategory.create("BULLET_FIRING", item -> item instanceof BaseGun && !(item instanceof BaseCannon));
	public static final EnchantmentCategory BLASTER = EnchantmentCategory.create("BLASTER", item -> item instanceof BaseBlaster);
	public static final EnchantmentCategory UNSTACKABLE = EnchantmentCategory.create("UNSTACKABLE", item -> item != null && item.getMaxStackSize(new ItemStack(item)) == 0);
	public static final EnchantmentCategory LIGHT_GUN = EnchantmentCategory.create("DUAL_WIELDABLE_GUN", item -> item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon));

	public static final RegistryObject<Enchantment> ARCHMAGE = registerEnchantment("archmage", ArchmageEnchantment::new);
	public static final RegistryObject<Enchantment> BRACE = registerEnchantment("brace", BraceEnchantment::new);
	public static final RegistryObject<Enchantment> CONTROL = registerEnchantment("control", ControlEnchantment::new);
	public static final RegistryObject<Enchantment> FORM = registerEnchantment("form", FormEnchantment::new);
	public static final RegistryObject<Enchantment> GREED = registerEnchantment("greed", GreedEnchantment::new);
	public static final RegistryObject<Enchantment> INTERVENTION = registerEnchantment("intervention", InterventionEnchantment::new);
	public static final RegistryObject<Enchantment> RECHARGE = registerEnchantment("recharge", RechargeEnchantment::new);
	public static final RegistryObject<Enchantment> SEVER = registerEnchantment("sever", SeverEnchantment::new);
	public static final RegistryObject<Enchantment> SHELL = registerEnchantment("shell", ShellEnchantment::new);

	private static RegistryObject<Enchantment> registerEnchantment(String id, Supplier<Enchantment> enchantment) {
		return AoARegistries.ENCHANTMENTS.register(id, enchantment);
	}
}
