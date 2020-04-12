package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class UnderworldGreatblade extends BaseGreatblade {
	public UnderworldGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("UnderworldGreatblade");
		setRegistryName("aoa3:underworld_greatblade");
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, EntityLivingBase attacker, double baseDmg) {
		return target instanceof EntityLivingBase && ((EntityLivingBase)target).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? baseDmg + 5 : baseDmg;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.UnderworldGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
