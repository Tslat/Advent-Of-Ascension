package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import java.util.List;

public class Ultraflame extends BaseSword {
	public Ultraflame(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("Ultraflame");
		setRegistryName("aoa3:ultraflame");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (attackCooldown > 0.75) {
			for (EntityLivingBase entity : target.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(3), PredicateUtil.IS_HOSTILE_MOB)) {
				entity.setFire(3);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Ultraflame.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
