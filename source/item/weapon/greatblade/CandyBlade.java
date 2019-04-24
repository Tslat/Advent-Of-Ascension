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
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class CandyBlade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	private double dmg;

	public CandyBlade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		this.dmg = dmg;
		setUnlocalizedName("CandyBlade");
		setRegistryName("aoa3:candy_blade");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (attacker instanceof EntityPlayer) {
			if (itemRand.nextInt((int)(20 * ((EntityPlayer)attacker).getCooledAttackStrength(0.0f))) == 0)
				EntityUtil.dealSelfHarmDamage(attacker, (float)this.dmg);
		}
		else {
			if (itemRand.nextInt(20) == 0)
				EntityUtil.dealSelfHarmDamage(attacker, Math.min((float)this.dmg * 0.25f, 10));
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CandyBlade.desc.1", TextFormatting.DARK_GREEN));
	}
}
