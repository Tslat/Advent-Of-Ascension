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

public class UnderworldGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public UnderworldGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("UnderworldGreatblade");
		setRegistryName("aoa3:underworld_greatblade");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (attacker instanceof EntityPlayer) {
			target.setFire((int)(8 * ((EntityPlayer)attacker).getCooledAttackStrength(0.0f)));
		}
		else {
			target.setFire(8);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.fire", TextFormatting.DARK_GREEN));
	}
}
