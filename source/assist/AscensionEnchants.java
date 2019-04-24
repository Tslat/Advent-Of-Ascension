package net.nevermine.assist;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class AscensionEnchants {
	public static Enchantment Control;
	public static Enchantment Shell;
	public static Enchantment Recharge;
	public static Enchantment Overpower;
	public static Enchantment Crush;
	public static Enchantment Sever;
	public static Enchantment Archmage;
	public static Enchantment Windfury;
	public static Enchantment Slice;
	public static Enchantment Intervention;

	public static void init() {
		AscensionEnchants.Control = new RestrictedEnchant(ConfigurationHelper.econtrol, 1, EnumEnchantmentType.all, 2) {
		}.setName("control");
		AscensionEnchants.Shell = new RestrictedEnchant(ConfigurationHelper.eshell, 1, EnumEnchantmentType.all, 3) {
		}.setName("shell");
		AscensionEnchants.Recharge = new RestrictedEnchant(ConfigurationHelper.erecharge, 1, EnumEnchantmentType.all, 2) {
		}.setName("recharge");
		AscensionEnchants.Overpower = new RestrictedEnchant(ConfigurationHelper.eoverpower, 1, EnumEnchantmentType.all, 2) {
		}.setName("overpower");
		AscensionEnchants.Crush = new RestrictedEnchant(ConfigurationHelper.ecrush, 1, EnumEnchantmentType.all, 2) {
		}.setName("crush");
		AscensionEnchants.Sever = new RestrictedEnchant(ConfigurationHelper.esever, 1, EnumEnchantmentType.all, 3) {
		}.setName("sever");
		AscensionEnchants.Archmage = new RestrictedEnchant(ConfigurationHelper.earchmage, 1, EnumEnchantmentType.all, 1) {
		}.setName("archmage");
		AscensionEnchants.Windfury = new RestrictedEnchant(ConfigurationHelper.ewindfury, 1, EnumEnchantmentType.all, 3) {
		}.setName("windfury");
		AscensionEnchants.Slice = new RestrictedEnchant(ConfigurationHelper.eslice, 1, EnumEnchantmentType.all, 3) {
		}.setName("slice");
		AscensionEnchants.Intervention = new RestrictedEnchant(ConfigurationHelper.eintervention, 1, EnumEnchantmentType.all, 1) {
		}.setName("intervention");
	}
}
