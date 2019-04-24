package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURMERCURIAL;

public class MercurialArmour extends AdventArmour {
	public MercurialArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURMERCURIAL, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.MERCURIAL;
	}

	@Override
	public void handleAttackImmunities(LivingAttackEvent event, AdventPlayerCapability cap) {
		if (!event.getEntity().world.isRemote && event.getSource().isExplosion()) {
			event.setCanceled(true);
			cap.getPlayer().inventory.damageArmor(10 + itemRand.nextInt(10) + event.getAmount());
		}
	}

	@Override
	public void handleDamageTriggers(LivingDamageEvent event, AdventPlayerCapability cap) {
		if (cap.isCooledDown(Enums.Counters.MERCURIAL)) {
			EntityPlayer pl = cap.getPlayer();

			pl.world.createExplosion(pl, pl.posX, pl.posY + pl.getEyeHeight(), pl.posZ, 6.0f, false);
			cap.setCooldown(Enums.Counters.MERCURIAL, 100 + (int)(itemRand.nextDouble() * 400));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.MercurialArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.MercurialArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}
