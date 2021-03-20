package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CrystalGreatblade extends BaseGreatblade {
	public CrystalGreatblade() {
		super(22.0f, AttackSpeed.GREATBLADE, 1480);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		for (LivingEntity entity : target.level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(2.0f), EntityUtil.Predicates.HOSTILE_MOB)) {
			DamageUtil.dealAoeDamage(null, attacker, entity, random.nextFloat() * 1.5f * (dmgDealt / (float)getAttackDamage()), false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
