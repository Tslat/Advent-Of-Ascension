package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class PrimordialGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public PrimordialGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("PrimordialGreatblade");
		setRegistryName("aoa3:primordial_greatblade");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (!(target instanceof EntityLivingBase))
			return;

		if (attacker instanceof EntityPlayer) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, (int)(100 * ((EntityPlayer)attacker).getCooledAttackStrength(0.0f)), 1));
		}
		else {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1));
		}

		if (itemRand.nextBoolean())
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 0, true, true));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.wither", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.PrimordialGreatblade.desc.1", TextFormatting.DARK_GREEN));
	}
}
