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
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class ErebonScythe extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public ErebonScythe(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("ErebonScythe");
		setRegistryName("aoa3:erebon_scythe");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (attacker instanceof EntityPlayer && target instanceof EntityLivingBase) {
			if (((EntityPlayer)attacker).getCooledAttackStrength(0.0f) > 0.75f) {
				if (target.isBurning()) {
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 2, true, true));
				}
				else {
					target.setFire(3);
				}
			}
		}
		else {
			target.setFire(10);
		}

		if (attacker instanceof EntityPlayer && !attacker.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null).consumeResource(Enums.Resources.ENERGY, 15, true))
			EntityUtil.dealSelfHarmDamage(attacker, 4);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.fire", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.ErebonScythe.desc.1", TextFormatting.AQUA));
		tooltip.add(StringUtil.getColourLocaleString("item.ErebonScythe.desc.2", TextFormatting.AQUA));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.energy", TextFormatting.DARK_GREEN, "15"));
	}
}
