package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.library.scheduling.async.EchoGullTask;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EchoGull extends BaseGun {
	public EchoGull(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_FAST_RIFLE_FIRE.get();
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		ArrayList<Tuple<LivingEntity, Integer>> entityList = new ArrayList<Tuple<LivingEntity, Integer>>();

		for (LivingEntity entity : bullet.world.getEntitiesWithinAABB(LivingEntity.class, bullet.getBoundingBox().grow(30), EntityUtil.Predicates.HOSTILE_MOB)) {
			int distance = (int)entity.getDistance(bullet);

			if (entityList.isEmpty()) {
				entityList.add(new Tuple<LivingEntity, Integer>(entity, distance));
			}
			else {
				for (int i = 0; i < entityList.size(); i++) {
					int dist = entityList.get(i).getB();

					if (dist == distance || dist > distance) {
						entityList.add(i, new Tuple<LivingEntity, Integer>(entity, distance));

						break;
					}
				}
			}
		}

		AoAScheduler.scheduleSyncronisedTask(new EchoGullTask(bullet.world, entityList), 1);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
