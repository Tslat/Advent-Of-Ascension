package net.tslat.aoa3.common.registration;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.item.enchantment.*;
import net.tslat.aoa3.item.weapon.archergun.BaseArchergun;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.item.weapon.greatblade.BaseGreatblade;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.maul.BaseMaul;
import net.tslat.aoa3.item.weapon.shotgun.BaseShotgun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;

@Mod.EventBusSubscriber
public class EnchantmentsRegister {
	public static final EnumEnchantmentType GUN = EnumHelper.addEnchantmentType("GUN", item -> item instanceof BaseGun);
	public static final EnumEnchantmentType GREATBLADE = EnumHelper.addEnchantmentType("GREATBLADE", item -> item instanceof BaseGreatblade);
	public static final EnumEnchantmentType SHOTGUN = EnumHelper.addEnchantmentType("SHOTGUN", item -> item instanceof BaseShotgun);
	public static final EnumEnchantmentType MAUL = EnumHelper.addEnchantmentType("MAUL", item -> item instanceof BaseMaul);
	public static final EnumEnchantmentType MELEE_WEAPON = EnumHelper.addEnchantmentType("MELEE_WEAPON", item -> item instanceof ItemSword || item instanceof BaseGreatblade || item instanceof BaseMaul);
	public static final EnumEnchantmentType STAFF = EnumHelper.addEnchantmentType("STAFF", item -> item instanceof BaseStaff);
	public static final EnumEnchantmentType AMMO_CONSUMING = EnumHelper.addEnchantmentType("AMMO_CONSUMING", item -> item instanceof BaseGun || item instanceof BaseBlaster || item instanceof BaseStaff);
	public static final EnumEnchantmentType BULLET_FIRING = EnumHelper.addEnchantmentType("BULLET_FIRING", item -> item instanceof BaseGun && !(item instanceof BaseCannon) && !(item instanceof BaseArchergun));
	public static final EnumEnchantmentType BLASTER = EnumHelper.addEnchantmentType("BLASTER", item -> item instanceof BaseBlaster);
	public static final EnumEnchantmentType UNSTACKABLE = EnumHelper.addEnchantmentType("UNSTACKABLE", item -> item != null && item.getItemStackLimit(new ItemStack(item)) == 0);
	public static final EnumEnchantmentType LIGHT_GUN = EnumHelper.addEnchantmentType("DUAL_WIELDABLE_GUN", item -> item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon));

	public static final BaseEnchantment ARCHMAGE = new EnchantmentArchmage();
	public static final BaseEnchantment BRACE = new EnchantmentBrace();
	public static final BaseEnchantment CONTROL = new EnchantmentControl();
	public static final BaseEnchantment CRUSH = new EnchantmentCrush();
	public static final BaseEnchantment FORM = new EnchantmentForm();
	public static final BaseEnchantment GREED = new EnchantmentGreed();
	public static final BaseEnchantment INTERVENTION = new EnchantmentIntervention();
	public static final BaseEnchantment RECHARGE = new EnchantmentRecharge();
	public static final BaseEnchantment SEVER = new EnchantmentSever();
	public static final BaseEnchantment SHELL = new EnchantmentShell();

	@SubscribeEvent
	public static void registerEnchantments(final RegistryEvent.Register<Enchantment> ev) {
		final IForgeRegistry<Enchantment> registry = ev.getRegistry();

		registry.registerAll(
				BRACE,
				INTERVENTION,
				ARCHMAGE,
				SEVER,
				RECHARGE,
				CONTROL,
				SHELL,
				CRUSH,
				FORM,
				GREED
		);
	}
}
