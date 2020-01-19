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
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class CreepoidGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public CreepoidGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("CreepoidGreatblade");
		setRegistryName("aoa3:creepoid_greatblade");
	}

	@Override
	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		if (super.attackEntity(stack, target, attacker, dmg)) {
			if (!(attacker instanceof EntityPlayer) || ((EntityPlayer)attacker).getCooledAttackStrength(0.0f) > 0.75f)
				WorldUtil.createExplosion(attacker, target.world, target.posX, target.posY + target.height, target.posZ, 1.5f);

			return true;
		}
		else {
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CreepoidGreatblade.desc.1", TextFormatting.DARK_GREEN));
	}
}
