package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class GoofyGreatblade extends BaseGreatblade {
	public GoofyGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("GoofyGreatblade");
		setRegistryName("aoa3:goofy_greatblade");
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, EntityLivingBase attacker, double baseDmg) {
		return (float)dmg + (float)(itemRand.nextGaussian() * 5f);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.GoofyGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE, String.valueOf(dmg - 5), String.valueOf(dmg + 5)));
	}
}
