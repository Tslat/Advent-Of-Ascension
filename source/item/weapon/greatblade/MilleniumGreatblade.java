package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class MilleniumGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	private double baseDmg = 0;
	private double maxDmg = 50;
	private double speed;

	public MilleniumGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("MilleniumGreatblade");
		setRegistryName("aoa3:millenium_greatblade");
		this.speed = speed;
	}

	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, itemRand.nextInt((int)(maxDmg - baseDmg) + 1));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.random", TextFormatting.DARK_GREEN, Double.toString(baseDmg), Double.toString(maxDmg)));
	}
}
