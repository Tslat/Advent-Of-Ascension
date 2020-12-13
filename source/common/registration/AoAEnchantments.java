package net.tslat.aoa3.common.registration;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.enchantment.*;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.item.weapon.crossbow.BaseCrossbow;
import net.tslat.aoa3.item.weapon.greatblade.BaseGreatblade;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.maul.BaseMaul;
import net.tslat.aoa3.item.weapon.shotgun.BaseShotgun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;

import java.util.function.Supplier;

public final class AoAEnchantments {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, AdventOfAscension.MOD_ID);

	public static final EnchantmentType GUN = EnchantmentType.create("GUN", item -> item instanceof BaseGun);
	public static final EnchantmentType GREATBLADE = EnchantmentType.create("GREATBLADE", item -> item instanceof BaseGreatblade);
	public static final EnchantmentType SHOTGUN = EnchantmentType.create("SHOTGUN", item -> item instanceof BaseShotgun);
	public static final EnchantmentType MAUL = EnchantmentType.create("MAUL", item -> item instanceof BaseMaul);
	public static final EnchantmentType MELEE_WEAPON = EnchantmentType.create("MELEE_WEAPON", item -> item instanceof SwordItem || item instanceof BaseGreatblade || item instanceof BaseMaul);
	public static final EnchantmentType STAFF = EnchantmentType.create("STAFF", item -> item instanceof BaseStaff);
	public static final EnchantmentType AMMO_CONSUMING = EnchantmentType.create("AMMO_CONSUMING", item -> item instanceof BaseGun || item instanceof BaseBlaster || item instanceof BaseStaff || item instanceof BaseCrossbow);
	public static final EnchantmentType BULLET_FIRING = EnchantmentType.create("BULLET_FIRING", item -> item instanceof BaseGun && !(item instanceof BaseCannon));
	public static final EnchantmentType BLASTER = EnchantmentType.create("BLASTER", item -> item instanceof BaseBlaster);
	public static final EnchantmentType UNSTACKABLE = EnchantmentType.create("UNSTACKABLE", item -> item != null && item.getItemStackLimit(new ItemStack(item)) == 0);
	public static final EnchantmentType LIGHT_GUN = EnchantmentType.create("DUAL_WIELDABLE_GUN", item -> item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon));

	public static final RegistryObject<Enchantment> ARCHMAGE = registerEnchantment("archmage", ArchmageEnchantment::new);
	public static final RegistryObject<Enchantment> BRACE = registerEnchantment("brace", BraceEnchantment::new);
	public static final RegistryObject<Enchantment> CONTROL = registerEnchantment("control", ControlEnchantment::new);
	public static final RegistryObject<Enchantment> CRUSH = registerEnchantment("crush", CrushEnchantment::new);
	public static final RegistryObject<Enchantment> FORM = registerEnchantment("form", FormEnchantment::new);
	public static final RegistryObject<Enchantment> GREED = registerEnchantment("greed", GreedEnchantment::new);
	public static final RegistryObject<Enchantment> INTERVENTION = registerEnchantment("intervention", InterventionEnchantment::new);
	public static final RegistryObject<Enchantment> RECHARGE = registerEnchantment("recharge", RechargeEnchantment::new);
	public static final RegistryObject<Enchantment> SEVER = registerEnchantment("sever", SeverEnchantment::new);
	public static final RegistryObject<Enchantment> SHELL = registerEnchantment("shell", ShellEnchantment::new);

	private static RegistryObject<Enchantment> registerEnchantment(String id, Supplier<Enchantment> enchantment) {
		return ENCHANTMENTS.register(id, enchantment);
	}
}
