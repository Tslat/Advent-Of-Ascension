package net.tslat.aoa3.item.weapon.maul;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class VulcammerMaul extends BaseMaul {
	public VulcammerMaul(Float dmg, Double speed, double knockback, int durability) {
		super(dmg, speed, knockback, durability);
		setTranslationKey("VulcammerMaul");
		setRegistryName("aoa3:vulcammer_maul");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityPlayer attacker, Entity target, float finalDmg, float attackCooldown) {
		if (attackCooldown > 0.85f) {
			boolean doWorldDamage = WorldUtil.checkGameRule(attacker.world, "destructiveWeaponPhysics");

			WorldUtil.createExplosion(attacker, attacker.world, (attacker.posX + target.posX) / 2d, (attacker.posY + target.posY) / 2d, (attacker.posZ + target.posZ) / 2d, 2f, doWorldDamage, doWorldDamage);

			if (!doWorldDamage) {
				for (EntityLivingBase entity : attacker.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(2), PredicateUtil.IS_HOSTILE_MOB)) {
					entity.setFire(3);
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.VulcammerMaul.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
