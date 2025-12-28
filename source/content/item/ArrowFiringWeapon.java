package net.tslat.aoa3.content.item;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ArrowFiringWeapon {
    default Projectile applyArrowMods(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {
        return projectile;
    }

    default int getProjectileCount(ItemStack weaponStack, ItemStack ammoStack, LivingEntity shooter, float power) {
        return 1;
    }

    default float getArrowDamage(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float baseDamage, float velocity, boolean isCritical) {
        float damage = baseDamage * 0.5f * (velocity / 3f);

        if (isCritical)
            damage += damage + (damage * (float)RandomUtil.scaledGaussianValue(0.35f));

        return damage;
    }

    default ItemStack findAmmo(LivingEntity shooter, ItemStack weaponStack, boolean infiniteAmmo) {
        return shooter.getProjectile(weaponStack);
    }

    default Projectile makeArrow(LivingEntity shooter, ProjectileWeaponItem weaponItem, ItemStack weaponStack, ItemStack ammoStack, float power, float baseDamage, boolean infiniteAmmo) {
        Projectile projectile = weaponItem.createProjectile(shooter.level(), shooter, weaponStack, ammoStack, power == 1);

        if (projectile instanceof AbstractArrow abstractArrow)
            abstractArrow.setBaseDamage(baseDamage);

        projectile.setPos(projectile.position().equals(Vec3.ZERO) ? shooter.getEyePosition().subtract(0, 0.1f, 0) : projectile.position());

        return applyArrowMods(projectile, shooter, ammoStack);
    }

    default void fireArrows(ItemStack bowStack, ProjectileWeaponItem weaponItem, ItemStack ammoStack, List<ItemStack> projectileItems, ServerLevel level, LivingEntity shooter, float power, float baseDamage, boolean infiniteAmmo) {
        final EquipmentSlot slot = LivingEntity.getSlotForHand(shooter.getUsedItemHand());
        final float spread = EnchantmentHelper.processProjectileSpread(level, bowStack, shooter, 0);
        final float spreadFactor = projectileItems.size() == 1 ? 0f : 2f * spread / (float)(projectileItems.size() - 1);
        final float spreadOffset = (float)((projectileItems.size() - 1) % 2) * spreadFactor / 2f;
        float signum = 1f;
        int index = 0;

        for (ItemStack projectileItem : projectileItems) {
            if (projectileItem.isEmpty())
                continue;

            Projectile projectile = makeArrow(shooter, weaponItem, bowStack, projectileItem, power, baseDamage, infiniteAmmo);

            shootProjectile(shooter, projectile, index, power * 3f, 1f, 0, spreadOffset + signum * (float)((index + 1) / 2) * spreadFactor, null);
            level.addFreshEntity(projectile);
            bowStack.hurtAndBreak(weaponItem.getDurabilityUse(bowStack), shooter, slot);

            if (bowStack.isEmpty())
                break;

            signum = -signum;
            index++;
        }
    }

    default List<ItemStack> drawProjectileItems(ItemStack bowStack, ItemStack ammoStack, LivingEntity shooter, float power, boolean intangibleProjectiles) {
        if (ammoStack.isEmpty())
            return List.of();

        int projectileCount = getProjectileCount(bowStack, ammoStack, shooter, power);
        projectileCount = shooter.level() instanceof ServerLevel serverLevel ? EnchantmentHelper.processProjectileCount(serverLevel, bowStack, shooter, projectileCount) : projectileCount;
        List<ItemStack> projectileItems = new ObjectArrayList<>(projectileCount);
        ItemStack ammoStackCopy = ammoStack.copy();

        for (int i = 0; i < projectileCount; ++i) {
            ItemStack projectileStack = ProjectileWeaponItem.useAmmo(bowStack, i == 0 ? ammoStack : ammoStackCopy, shooter, intangibleProjectiles || i > 0);

            if (!projectileStack.isEmpty())
                projectileItems.add(projectileStack);
        }

        return projectileItems;
    }

    default void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float verticalOffset, float lateralOffset, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot() + verticalOffset, shooter.getYRot() + lateralOffset, 0, velocity, inaccuracy);
    }

    default void tickArrow(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {}
    default void onBlockImpact(Projectile projectile, @Nullable Entity shooter, BlockHitResult hitResult, ItemStack stack) {}
    default void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {}
}
