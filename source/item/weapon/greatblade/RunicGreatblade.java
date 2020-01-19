package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class RunicGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public RunicGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("RunicGreatblade");
		setRegistryName("aoa3:runic_greatblade");
	}

	@Override
	public boolean attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		EntityUtil.dealMagicDamage(null, attacker, target, 4, false);

		return super.attackEntity(stack, target, attacker, dmg - 4);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RunicGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
