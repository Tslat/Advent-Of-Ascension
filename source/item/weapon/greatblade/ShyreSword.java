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

public class ShyreSword extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public ShyreSword(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("ShyreSword");
		setRegistryName("aoa3:shyre_sword");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (!attacker.world.isRemote) {
			if (!EntityUtil.isSpecExempt(target, attacker) && ((EntityPlayer)attacker).getCooledAttackStrength(0.0f) > 0.75f) {
				double x = attacker.posX;
				double y = attacker.posY;
				double z = attacker.posZ;

				attacker.dismountRidingEntity();
				attacker.setPositionAndUpdate(target.posX, target.posY, target.posZ);
				target.setPositionAndUpdate(x, y, z);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.ShyreSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
