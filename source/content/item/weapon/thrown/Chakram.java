package net.tslat.aoa3.content.item.weapon.thrown;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.thrown.ChakramEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Chakram extends AoAThrowableWeapon {
	public Chakram(Item.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public SoundEvent getThrowSound() {
		return SoundEvents.WITCH_THROW;
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new ChakramEntity(level, context);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
