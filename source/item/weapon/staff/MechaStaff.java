package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityLyonicShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class MechaStaff extends BaseStaff {
	public MechaStaff(int durability) {
		super(durability);
		setTranslationKey("MechaStaff");
		setRegistryName("aoa3:mecha_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.staffBasic;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runeDistortion, 1);
		runes.put(ItemRegister.runePower, 1);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityLyonicShot(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase)target;
			IAttributeInstance armour = entity.getEntityAttribute(SharedMonsterAttributes.ARMOR);

			if (armour != null && armour.getAttributeValue() > 0 && !armour.hasModifier(AoAAttributes.MECHA_STAFF_DEBUFF)) {
				EntityUtil.applyAttributeModifierSafely(entity, SharedMonsterAttributes.ARMOR, AoAAttributes.MECHA_STAFF_DEBUFF);

				if (!entity.world.isRemote) {
					AxisAlignedBB bounds = entity.getEntityBoundingBox();

					for (int i = 0; i < 8; i++) {
						((WorldServer)entity.world).spawnParticle(EnumParticleTypes.TOTEM, bounds.minX + itemRand.nextDouble() * entity.width, bounds.maxY + 0.1d, bounds.minZ + itemRand.nextDouble() * entity.width, 1, 0, 0, 0, (double)0);
					}
				}
			}

			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MechaStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
