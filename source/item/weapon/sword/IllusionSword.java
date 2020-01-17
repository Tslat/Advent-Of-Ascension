package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class IllusionSword extends BaseSword implements AdventWeapon {
	public IllusionSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("IllusionSword");
		setRegistryName("aoa3:illusion_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (!attacker.world.isRemote && !EntityUtil.isSpecExempt(target, attacker) && itemRand.nextFloat() < 0.1 * attackCooldown) {
			List<EntityLivingBase> nearbyMobs = target.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(5), mob -> mob instanceof IMob);

			if (nearbyMobs.size() > 1) {
				EntityLivingBase newTarget = null;

				for (EntityLivingBase nearbyMob : nearbyMobs) {
					if ((newTarget = nearbyMob) != target)
						break;
				}

				if (newTarget == null)
					return;

				target.setRevengeTarget(newTarget);

				if (target instanceof EntityCreature)
					((EntityCreature)target).setAttackTarget(newTarget);

				target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0, false, true));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.IllusionSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
