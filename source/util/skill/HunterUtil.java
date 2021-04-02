package net.tslat.aoa3.util.skill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Tuple;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public class HunterUtil {
	private static final HashMap<EntityType<? extends MobEntity>, Tuple<Integer, Float>> hunterCreatureMap = new HashMap<EntityType<? extends MobEntity>, Tuple<Integer, Float>>();

	static {
		hunterCreatureMap.put(AoAEntities.Mobs.VOID_WALKER.get(), new Tuple<Integer, Float>(1, 6.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.MOTHER_VOID_WALKER.get(), new Tuple<Integer, Float>(5, 9.9f));
		hunterCreatureMap.put(AoAEntities.Mobs.ICE_GIANT.get(), new Tuple<Integer, Float>(9, 38.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.LEAFY_GIANT.get(), new Tuple<Integer, Float>(9, 37.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.SAND_GIANT.get(), new Tuple<Integer, Float>(9, 40.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.STONE_GIANT.get(), new Tuple<Integer, Float>(9, 45.5f));
		hunterCreatureMap.put(AoAEntities.Mobs.WOOD_GIANT.get(), new Tuple<Integer, Float>(9, 42.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.SKELETAL_COWMAN.get(), new Tuple<Integer, Float>(13, 12.8f));
		hunterCreatureMap.put(AoAEntities.Mobs.KAIYU.get(), new Tuple<Integer, Float>(17, 10.5f));
		hunterCreatureMap.put(AoAEntities.Mobs.HOST.get(), new Tuple<Integer, Float>(18, 25f));
		hunterCreatureMap.put(AoAEntities.Mobs.ZHINX.get(), new Tuple<Integer, Float>(19, 17.8f));
		hunterCreatureMap.put(AoAEntities.Mobs.EXOHEAD.get(), new Tuple<Integer, Float>(23, 15.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.NETHENGEIC_BEAST.get(), new Tuple<Integer, Float>(24, 16.3f));
		hunterCreatureMap.put(AoAEntities.Mobs.DIOCUS.get(), new Tuple<Integer, Float>(27, 29.9f));
		hunterCreatureMap.put(AoAEntities.Mobs.PARAVITE.get(), new Tuple<Integer, Float>(28, 21.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.SPINOLEDON.get(), new Tuple<Integer, Float>(28, 21.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.IOSAUR.get(), new Tuple<Integer, Float>(29, 24.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.MUSHROOM_SPIDER.get(), new Tuple<Integer, Float>(29, 21.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.RAWBONE.get(), new Tuple<Integer, Float>(30, 24.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.ENFORCER.get(), new Tuple<Integer, Float>(31, 26.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.UNDEAD_TROLL.get(), new Tuple<Integer, Float>(32, 28.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.JAWE.get(), new Tuple<Integer, Float>(33, 30.5f));
		hunterCreatureMap.put(AoAEntities.Mobs.NIGHTMARE_SPIDER.get(), new Tuple<Integer, Float>(34, 26.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.NIGHTWING.get(), new Tuple<Integer, Float>(39, 47.8f));
		hunterCreatureMap.put(AoAEntities.Mobs.FLESH_EATER.get(), new Tuple<Integer, Float>(40, 51.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.JUMBO.get(), new Tuple<Integer, Float>(42, 90.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.CANE_BUG.get(), new Tuple<Integer, Float>(47, 88.9f));
		hunterCreatureMap.put(AoAEntities.Mobs.GINGERBIRD.get(), new Tuple<Integer, Float>(50, 112.8f));
		hunterCreatureMap.put(AoAEntities.Mobs.GINGERBREAD_MAN.get(), new Tuple<Integer, Float>(53, 143.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.ANEMIA.get(), new Tuple<Integer, Float>(55, 595.3f));
		hunterCreatureMap.put(AoAEntities.Mobs.POD_PLANT.get(), new Tuple<Integer, Float>(56, 183.1f));
		hunterCreatureMap.put(AoAEntities.Mobs.VINE_WIZARD.get(), new Tuple<Integer, Float>(59, 233.9f));
		hunterCreatureMap.put(AoAEntities.Mobs.AMPHIBIYTE.get(), new Tuple<Integer, Float>(61, 275.7f));
		hunterCreatureMap.put(AoAEntities.Mobs.SPECTRAL_WIZARD.get(), new Tuple<Integer, Float>(64, 353.3f));
		hunterCreatureMap.put(AoAEntities.Mobs.RUNIC_GUARDIAN.get(), new Tuple<Integer, Float>(66, 417.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.MERMAGE.get(), new Tuple<Integer, Float>(67, 453.5f));
		hunterCreatureMap.put(AoAEntities.Mobs.AMPHIBIOR.get(), new Tuple<Integer, Float>(69, 536f));
		hunterCreatureMap.put(AoAEntities.Mobs.FACELESS_FLOATER.get(), new Tuple<Integer, Float>(71, 634f));
		hunterCreatureMap.put(AoAEntities.Mobs.BANSHEE.get(), new Tuple<Integer, Float>(73, 796.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.ZORP.get(), new Tuple<Integer, Float>(75, 888.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.ZARG.get(), new Tuple<Integer, Float>(76, 966.9f));
		hunterCreatureMap.put(AoAEntities.Mobs.BAUMBA.get(), new Tuple<Integer, Float>(78, 1145.8f));
		hunterCreatureMap.put(AoAEntities.Mobs.REFLUCT.get(), new Tuple<Integer, Float>(79, 1247.6f));
		hunterCreatureMap.put(AoAEntities.Mobs.INMATE_X.get(), new Tuple<Integer, Float>(81, 1443.4f));
		hunterCreatureMap.put(AoAEntities.Mobs.INMATE_Y.get(), new Tuple<Integer, Float>(81, 1640.8f));
		hunterCreatureMap.put(AoAEntities.Mobs.MERKYRE.get(), new Tuple<Integer, Float>(83, 1755.3f));
		hunterCreatureMap.put(AoAEntities.Mobs.CRUSILISK.get(), new Tuple<Integer, Float>(84, 1912.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.ARKZYNE.get(), new Tuple<Integer, Float>(86, 2270.2f));
		hunterCreatureMap.put(AoAEntities.Mobs.LIGHTWALKER.get(), new Tuple<Integer, Float>(94, 4527.5f));
		hunterCreatureMap.put(AoAEntities.Mobs.LUXOCRON.get(), new Tuple<Integer, Float>(97, 5873.8f));
	}
	
	public static boolean canAttackTarget(@Nonnull LivingEntity target, @Nullable Entity attacker, boolean notifyPlayer) {
		if (attacker == null || !hunterCreatureMap.containsKey(target.getType()))
			return true;

		int lvl = hunterCreatureMap.get(target.getType()).getA();
		PlayerEntity player = attacker instanceof PlayerEntity ? (PlayerEntity)attacker : attacker instanceof TameableEntity ? (((TameableEntity)attacker).getOwner() instanceof PlayerEntity ? (PlayerEntity)((TameableEntity)attacker).getOwner() : null) : null;

		if (player == null)
			return false;

		boolean success = player.isCreative() || (!target.level.isClientSide && PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)player, Skills.HUNTER, lvl));

		if (!success && notifyPlayer && !player.level.isClientSide)
			PlayerUtil.notifyPlayerOfInsufficientLevel((ServerPlayerEntity)player, Skills.HUNTER, lvl);

		return success;
	}

	public static boolean isHunterCreature(@Nonnull LivingEntity entity) {
		return hunterCreatureMap.containsKey(entity.getType());
	}

	public static int getHunterLevel(@Nullable LivingEntity entity) {
		if (entity == null || !hunterCreatureMap.containsKey(entity.getType()))
			return -1;

		return hunterCreatureMap.get(entity.getType()).getA();
	}

	public static float getHunterXp(@Nullable LivingEntity entity) {
		if (entity == null || !hunterCreatureMap.containsKey(entity.getType()))
			return -1;

		return hunterCreatureMap.get(entity.getType()).getB();
	}
}
