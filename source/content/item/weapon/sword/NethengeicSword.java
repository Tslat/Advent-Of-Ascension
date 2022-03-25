package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.library.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class NethengeicSword extends BaseSword {
	public NethengeicSword() {
		super(ItemUtil.customItemTier(2040, AttackSpeed.NORMAL, 14.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level.isClientSide) {
			if (target.fireImmune() || target.isInvulnerableTo(DamageSource.ON_FIRE)) {

				target.addEffect(new EffectInstance(Effects.WITHER, (int)(80 * attackCooldown), 2, false, true));
			}
			else {
				target.setSecondsOnFire((int)(4 * (attacker instanceof PlayerEntity ? attackCooldown : 1)));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
