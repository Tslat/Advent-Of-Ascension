package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.misc.TidalWaveEntity;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TidalGreatblade extends BaseGreatblade {
	public TidalGreatblade() {
		super(24.0f, AttackSpeed.GREATBLADE, 1750, Rarity.RARE);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown < 0.95f)
			return;

		double xOffset = Mth.cos(attacker.getYRot() / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = Mth.sin(attacker.getYRot() / 180.0F * (float)Math.PI) * 0.7F;

		attacker.level.addFreshEntity(new TidalWaveEntity(attacker.level, attacker, xOffset, zOffset));
		attacker.level.addFreshEntity(new TidalWaveEntity(attacker.level, attacker, 0, 0));
		attacker.level.addFreshEntity(new TidalWaveEntity(attacker.level, attacker, -xOffset, -zOffset));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
