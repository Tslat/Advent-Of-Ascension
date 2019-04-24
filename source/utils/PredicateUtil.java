package net.tslat.aoa3.utils;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;

public class PredicateUtil {
	public static final Predicate<EntityPlayer> IS_VULNERABLE_PLAYER = pl -> pl != null && !pl.isSpectator() && !pl.isCreative();

	public static final Predicate<EntityLivingBase> NOT_UNDEAD = entity -> entity != null && entity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
}
