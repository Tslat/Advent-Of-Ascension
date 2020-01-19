package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class SubterraneanGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public SubterraneanGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("SubterraneanGreatblade");
		setRegistryName("aoa3:subterranean_greatblade");
	}

	@Override
	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (super.attackEntity(stack, target, attacker, dmg)) {
			if (target instanceof EntityLivingBase && ItemUtil.checkCooledItemProc(attacker, 0.20f))
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 50, true, false));

			return true;
		}
		else {
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SubterraneanGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
