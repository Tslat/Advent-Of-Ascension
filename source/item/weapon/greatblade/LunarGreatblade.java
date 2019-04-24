package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class LunarGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	private double baseDmg;
	private double maxDmg = 33;

	public LunarGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		this.baseDmg = dmg;
		setUnlocalizedName("LunarGreatblade");
		setRegistryName("aoa3:lunar_greatblade");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (attacker instanceof EntityPlayer) {
			super.attackEntity(stack, target, attacker, (float)baseDmg + (itemRand.nextInt((int)(maxDmg - baseDmg) + 1) * ((EntityPlayer)attacker).getCooledAttackStrength(0.0f)));
		}
		else {
			super.attackEntity(stack, target, attacker, (float)baseDmg + itemRand.nextInt((int)(maxDmg - baseDmg) + 1));
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.random", TextFormatting.DARK_GREEN, Double.toString(baseDmg), Double.toString(maxDmg)));
	}
}
