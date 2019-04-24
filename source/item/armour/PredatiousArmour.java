package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.gun.EntityHollyArrowShot;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURPREDATIOUS;

public class PredatiousArmour extends AdventArmour {
	public PredatiousArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURPREDATIOUS, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.PREDATIOUS;
	}

	@Override
	public void handleAttackImmunities(LivingAttackEvent event, AdventPlayerCapability cap) {
		if (itemRand.nextInt(10) == 0 && EntityUtil.isRangedDamage(event.getSource(), event.getEntity(), event.getAmount())) {
			EntityPlayer pl = cap.getPlayer();

			pl.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.dodge, SoundCategory.PLAYERS, 1.0f, 1.0f);
			event.setCanceled(true);
		}
	}

	@Override
	public void handleAttackBuffs(LivingHurtEvent event, AdventPlayerCapability cap) {
		Entity projectile = event.getSource().getImmediateSource();

		if (projectile instanceof EntityHollyArrow || projectile instanceof EntityHollyArrowShot) {
			event.setAmount(event.getAmount() * 1.5f);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.PredatiousArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.PredatiousArmour.desc.2", TextFormatting.DARK_GREEN));
	}
}
