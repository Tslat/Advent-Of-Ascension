package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURHUNTER;

public class HunterArmour extends AdventArmour implements SkillItem {
	public HunterArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURHUNTER, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.HUNTER;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.HUNTER;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();

		pl.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 2, true, false));

		if (pl.motionY == 0.0 && pl.isSneaking()) {
			if (pl.motionX < 1.25 && pl.motionX > -1.25)
				pl.motionX *= 1.2000000476837158;

			if (pl.motionZ < 1.25 && pl.motionZ > -1.25)
				pl.motionZ *= 1.2000000476837158;
		}

		if (pl.motionY > 0.0 && pl.motionY < 0.4)
			pl.motionY *= 1.150000023841858;

		if (pl.motionY < 0.0)
			pl.motionY *= 0.8999999761581421;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.HunterArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HunterArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HunterArmour.desc.3", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.HunterArmour.desc.4", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 100) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, Integer.toString(100), StringUtil.getLocaleString("skills.hunter.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, Integer.toString(100), StringUtil.getLocaleString("skills.hunter.name")));
		}
	}
}
