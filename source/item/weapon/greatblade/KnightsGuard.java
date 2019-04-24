package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class KnightsGuard extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public KnightsGuard(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("KnightsGuard");
		setRegistryName("aoa3:knights_guard");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (!(attacker instanceof EntityPlayer) || ((EntityPlayer)attacker).getCooledAttackStrength(0.0f) > 0.75f) {
			Potion effect = null;
			switch (itemRand.nextInt(4)) {
				case 0:
					effect = MobEffects.STRENGTH;
					break;
				case 1:
					effect = MobEffects.RESISTANCE;
					break;
				case 2:
					effect = MobEffects.SPEED;
					break;
				case 3:
					effect = MobEffects.REGENERATION;
					break;
			}

			attacker.addPotionEffect(new PotionEffect(effect, 60, 0));
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.KnightsGuard.desc.1", TextFormatting.DARK_GREEN));
	}
}
