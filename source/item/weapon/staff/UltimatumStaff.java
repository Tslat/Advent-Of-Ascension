package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityUltimatumShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.scheduling.sync.UltimatumStaffTask;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.ModUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class UltimatumStaff extends BaseStaff {
	public UltimatumStaff(int durability) {
		super(durability);
		setTranslationKey("UltimatumStaff");
		setRegistryName("aoa3:ultimatum_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.ULTIMATUM_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.LIFE_RUNE, 5);
		runes.put(ItemRegister.POWER_RUNE, 3);
		runes.put(ItemRegister.DISTORTION_RUNE, 8);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityUltimatumShot(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target instanceof EntityLivingBase && !EntityUtil.isSpecExempt(target, shooter)) {
			Vec3d lookVec = shooter.getLookVec();

			double posX = shooter.posX + lookVec.x * 4;
			double posZ = shooter.posZ + lookVec.z * 4;

			target.setPositionAndRotation(posX, shooter.posY, posZ, (shooter.rotationYawHead + 180) % 360, 0);
			target.setRotationYawHead((shooter.rotationYawHead + 180) % 360);
			target.setPositionAndUpdate(posX, shooter.posY, posZ);
			ModUtil.scheduleSyncronisedTask(new UltimatumStaffTask(shooter, (EntityLivingBase)target), 2);

			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.UltimatumStaff.desc.1", Enums.ItemDescriptionType.UNIQUE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.specImmune", Enums.ItemDescriptionType.NEGATIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
