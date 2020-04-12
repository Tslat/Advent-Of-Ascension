package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class RockbasherSword extends BaseSword {
	public RockbasherSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("RockbasherSword");
		setRegistryName("aoa3:rockbasher_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		double armour = target.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue();

		if (armour > 0)
			WorldUtil.createExplosion(attacker, attacker.world, target.posX, target.posY + target.height / 1.5, target.posZ, 0.5f + (float)(3 * armour / 30f));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RockbasherSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
