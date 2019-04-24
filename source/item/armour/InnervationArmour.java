package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURINNERVATION;

public class InnervationArmour extends AdventArmour implements SkillItem {
	public InnervationArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURINNERVATION, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.INNERVATION;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.INNERVATION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void handleAttackImmunities(LivingAttackEvent event, AdventPlayerCapability cap) {
		if (itemRand.nextInt(4) == 0 && EntityUtil.isPhysicalDamage(event.getSource(), event.getEntity(), event.getAmount())) {
			EntityPlayer pl = cap.getPlayer();

			pl.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.dodge, SoundCategory.PLAYERS, 1.0f, 1.0f);
			event.setCanceled(true);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.InnervationArmour.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 100) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, Integer.toString(100), StringUtil.getLocaleString("skills.innervation.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, Integer.toString(100), StringUtil.getLocaleString("skills.innervation.name")));
		}
	}
}
