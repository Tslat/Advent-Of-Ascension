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

public class CottonCrusher extends BaseGreatblade {
	private double dmg;

	public CottonCrusher(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		this.dmg = dmg;
		setTranslationKey("CottonCrusher");
		setRegistryName("aoa3:cotton_crusher");
	}

	@Override
	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (super.attackEntity(stack, target, attacker, dmg)) {
			if (attacker instanceof EntityPlayer && ((EntityPlayer)attacker).getCooledAttackStrength(0) > 0.95f)
				((EntityPlayer)attacker).getFoodStats().addStats(1, 0);

			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CottonCrusher.desc.1", TextFormatting.DARK_GREEN));
	}
}
