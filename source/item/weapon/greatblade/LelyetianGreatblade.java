package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class LelyetianGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public LelyetianGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("LelyetianGreatblade");
		setRegistryName("aoa3:lelyetian_greatblade");
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, EntityLivingBase attacker, double baseDmg) {
		return target instanceof EntityLiving && (target instanceof EntityFlying || ((EntityLiving)target).getNavigator() instanceof PathNavigateFlying) ? (float)this.dmg * 1.25f : (float)this.dmg;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LelyetianGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
