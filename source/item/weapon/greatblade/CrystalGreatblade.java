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
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class CrystalGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public CrystalGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setUnlocalizedName("CrystalGreatblade");
		setRegistryName("aoa3:crystal_greatblade");
	}

	@Override
	public void attackEntity(ItemStack stack, Entity target, EntityLivingBase attacker, float dmg) {
		super.attackEntity(stack, target, attacker, dmg);

		if (ItemUtil.checkCooledItemProc(attacker, 0.05f))
			EntityUtil.healEntity(attacker, attacker.getMaxHealth());
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CrystalGreatblade.desc.1", TextFormatting.DARK_GREEN));
	}
}
