package net.tslat.aoa3.content.entity.mob.gardencia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.boss.VinocorneEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

public class YellowFlowerEntity extends AoAMeleeMob {
	public YellowFlowerEntity(VinocorneEntity vinocorne) {
		this(AoAEntities.Mobs.YELLOW_FLOWER.get(), vinocorne.level);

		moveTo(vinocorne.getX(), vinocorne.getY(), vinocorne.getZ(), random.nextFloat() * 360, 0);
	}

	public YellowFlowerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.5f;
	}

	@Override
	public void tick() {
		super.tick();

		if (random.nextInt(100) == 0)
			EntityUtil.applyPotions(this, new EffectBuilder(Effects.INVISIBILITY, 35));
	}
}
