package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURSHARPSHOT;

public class SharpshotArmour extends AdventArmour {
	public SharpshotArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURSHARPSHOT, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.SHARPSHOT;
	}

	@Override
	public void handleAttackBuffs(LivingHurtEvent event, AdventPlayerCapability cap) {
		if (EntityUtil.isGunDamage(event.getSource()) && cap.getPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof BaseSniper)
			event.setAmount(event.getAmount() * 1.3f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.SharpshotArmour.desc.1", TextFormatting.DARK_GREEN));
	}
}
