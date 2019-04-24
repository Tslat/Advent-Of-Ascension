package net.tslat.aoa3.common.registration;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.item.enchantment.*;

@Mod.EventBusSubscriber
public class EnchantmentsRegister {
	public static final BaseEnchantment brace = new EnchantmentBrace();
	public static final BaseEnchantment intervention = new EnchantmentIntervention();
	public static final BaseEnchantment archmage = new EnchantmentArchmage();
	public static final BaseEnchantment sever = new EnchantmentSever();
	public static final BaseEnchantment recharge = new EnchantmentRecharge();
	public static final BaseEnchantment control = new EnchantmentControl();
	public static final BaseEnchantment shell = new EnchantmentShell();
	public static final BaseEnchantment crush = new EnchantmentCrush();
	public static final BaseEnchantment form = new EnchantmentForm();
	public static final BaseEnchantment greed = new EnchantmentGreed();

	@SubscribeEvent
	public static void registerEnchantment(final RegistryEvent.Register<Enchantment> ev) {
		final IForgeRegistry<Enchantment> registry = ev.getRegistry();

		registry.registerAll(
			brace,
			intervention,
			archmage,
			sever,
			recharge,
			control,
			shell,
			crush,
			form,
			greed
		);
	}
}
