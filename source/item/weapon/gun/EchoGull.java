package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.scheduling.async.EchoGullTask;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EchoGull extends BaseGun {
	public EchoGull(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("EchoGull");
		setRegistryName("aoa3:echo_gull");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunFastRifle;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		ArrayList<Tuple<EntityLivingBase, Integer>> entityList = new ArrayList<Tuple<EntityLivingBase, Integer>>();

		for (EntityLivingBase entity : bullet.world.getEntitiesWithinAABB(EntityLivingBase.class, bullet.getEntityBoundingBox().grow(30), PredicateUtil.IS_HOSTILE_MOB)) {
			int distance = (int)entity.getDistance(bullet);

			if (entityList.isEmpty()) {
				entityList.add(new Tuple<EntityLivingBase, Integer>(entity, distance));
			}
			else {
				for (int i = 0; i < entityList.size(); i++) {
					int dist = entityList.get(i).getSecond();

					if (dist == distance || dist > distance) {
						entityList.add(i, new Tuple<EntityLivingBase, Integer>(entity, distance));

						break;
					}
				}
			}
		}

		ModUtil.scheduleSyncronisedTask(new EchoGullTask(bullet.world, entityList), 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EchoGull.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
