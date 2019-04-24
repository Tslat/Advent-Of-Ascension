package net.tslat.aoa3.common.registration;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.tslat.aoa3.entity.boss.bane.EntityBaneBig;
import net.tslat.aoa3.entity.boss.bane.EntityBaneClone;
import net.tslat.aoa3.entity.boss.baroness.EntityBaronBomb;
import net.tslat.aoa3.entity.misc.*;

public class MiscEntityRegister {
	protected static EntityEntry[] buildMiscEntityEntries() {
		return new EntityEntry[]{
				newEntry("bane_big", EntityBaneBig.class),
				newEntry("bane_clone", EntityBaneClone.class),
				newEntry("baron_bomb", EntityBaronBomb.class),
				newEntry("bloodlust", EntityBloodlust.class),
				newEntry("fake_tnt", EntityFakeTnt.class),
				newEntry("lotto_idol", EntityLottoIdol.class),
				newEntry("occult_block", EntityOccultBlock.class),
				newEntry("spawn_fluffer_ignore", EntitySpawnFluffer.class)
		};
	}

	private static <E extends Entity> EntityEntry newEntry(final String name, final Class<E> entityClass) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		builder.entity(entityClass);
		builder.tracker(40, 1, true);

		return builder.id(resource, GeneralEntityRegister.entityId++).name(resource.getResourceDomain() + "." + resource.getResourcePath()).build();
	}
}
