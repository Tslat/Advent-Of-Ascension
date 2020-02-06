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
import net.tslat.aoa3.utils.PredicateUtil;

import java.util.List;

public class CrystalGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public CrystalGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("CrystalGreatblade");
		setRegistryName("aoa3:crystal_greatblade");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		for (EntityLivingBase entity : target.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(2.0f), PredicateUtil.IS_HOSTILE_MOB)) {
			EntityUtil.dealAoeDamage(null, attacker, entity, itemRand.nextFloat() * 2f * (dmgDealt / (float)getDamage()), false);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CrystalGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
