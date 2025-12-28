package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.tool.artifice.AmmoVoidPouch;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Sublimus extends AoAGun {
	public Sublimus(Item.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public WeaponProjectile findAndConsumeAmmo(Level level, WeaponFiringContext context) {
		if (context.getShooter() instanceof Player pl && !pl.hasInfiniteMaterials()) {
			int cost = AoAEnchantments.modifyAmmoCost(pl.level(), context.weaponStack(), 1);

			if (!InventoryUtil.findItemForConsumption(pl, item -> isAmmoStack(context.weaponStack(), item), cost, !pl.level().isDay() || !pl.level().canSeeSky(pl.blockPosition()))) {
				MutableBoolean foundAmmo = new MutableBoolean(false);

				AmmoVoidPouch.tryProduceProjectile(pl, this, context.weaponStack(), cost, stack -> foundAmmo.setTrue());

				if (foundAmmo.isFalse())
					return null;
			}
		}

		return createProjectileEntity(level, context);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
