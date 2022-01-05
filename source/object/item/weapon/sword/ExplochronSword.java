package net.tslat.aoa3.object.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class ExplochronSword extends BaseSword {
	public ExplochronSword() {
		super(ItemUtil.customItemTier(1800, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.75f) {
			double offset = target.getBbWidth() / 1.99d;
			double offsetX = MathHelper.clamp(attacker.getX() - target.getX(), -offset, offset);
			double offsetY = MathHelper.clamp(attacker.getY() + attacker.getEyeHeight() - target.getY(), -0.1, target.getBbHeight() + 0.1);
			double offsetZ = MathHelper.clamp(attacker.getZ() - target.getZ(), -offset, offset);

			WorldUtil.createExplosion(attacker, attacker.level, target.getX() + offsetX, target.getY() + offsetY, target.getZ() + offsetZ, 1.75f);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
