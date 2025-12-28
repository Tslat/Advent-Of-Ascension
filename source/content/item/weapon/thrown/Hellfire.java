package net.tslat.aoa3.content.item.weapon.thrown;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.thrown.HellfireEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hellfire extends AoAThrowableWeapon {
	public Hellfire(Item.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public SoundEvent getThrowSound() {
		return SoundEvents.EXPERIENCE_BOTTLE_THROW;
	}

	@Override
	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createFiringContext(stack, shooter, hand).velocity(1.5f);
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new HellfireEntity(level, context);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
