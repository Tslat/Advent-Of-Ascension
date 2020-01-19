package net.tslat.aoa3.utils;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

public class PredicateUtil {
	public static final Predicate<EntityPlayer> IS_VULNERABLE_PLAYER = pl -> pl != null && !pl.isSpectator() && !pl.isCreative() && !pl.capabilities.disableDamage;

	public static final Predicate<EntityLivingBase> NOT_UNDEAD = entity -> entity != null && entity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;

	public static final Predicate<EntityLivingBase> IS_HOSTILE_MOB = entity -> entity instanceof IMob;

	public static final Predicate<EntityLivingBase> VULNERABLE_TO_MAGIC = entity -> !(entity instanceof SpecialPropertyEntity) || !((SpecialPropertyEntity)entity).getMobProperties().contains(Enums.MobProperties.MAGIC_IMMUNE);

	public static final Predicate<EntityLivingBase> MAGIC_VULNERABLE_MOB = entity -> entity instanceof IMob && (!(entity instanceof SpecialPropertyEntity) || !((SpecialPropertyEntity)entity).getMobProperties().contains(Enums.MobProperties.MAGIC_IMMUNE));
}
