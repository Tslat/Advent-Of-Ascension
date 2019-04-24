package net.tslat.aoa3.common.registration;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.tslat.aoa3.entity.minions.*;

public class MinionRegister {
	protected static EntityEntry[] buildMinionEntries() {
		return new EntityEntry[]{
				newEntry("alluricorn", EntityAlluricorn.class),
				newEntry("blissard", EntityBlissard.class),
				newEntry("compeer", EntityCompeer.class),
				newEntry("construct_of_servility", EntityConstructOfServility.class),
				newEntry("corby", EntityCorby.class),
				newEntry("craggy", EntityCraggy.class),
				newEntry("draggy", EntityDraggy.class),
				newEntry("ender_carrier", EntityEnderCarrier.class),
				newEntry("gnawer", EntityGnawer.class),
				newEntry("goofer", EntityGoofer.class),
				newEntry("healing_golem", EntityHealingGolem.class),
				newEntry("hellquin", EntityHellquin.class),
				newEntry("hive_soldier", EntityHiveSoldier.class),
				newEntry("horntail", EntityHorntail.class),
				newEntry("mecha_cyclops", EntityMechaCyclops.class),
				newEntry("mecha_skellox", EntityMechaSkellox.class),
				newEntry("orbling", EntityOrbling.class),
				newEntry("penguin", EntityPenguin.class),
				newEntry("plateosaur", EntityPlateosaur.class),
				newEntry("rammerhorn", EntityRammerhorn.class),
				newEntry("rosid", EntityRosid.class),
				newEntry("shaddy", EntityShaddy.class),
				newEntry("shadow_stalker", EntityShadowStalker.class),
				newEntry("spikeback", EntitySpikeback.class),
				newEntry("spraggy", EntitySpraggy.class),
				newEntry("waggy", EntityWaggy.class)
		};
	}

	private static <E extends Entity> EntityEntry newEntry(final String name, final Class<E> entityClass) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation resource = new ResourceLocation("aoa3", name);

		builder.entity(entityClass);
		builder.tracker(120, 1, true);
		builder.egg(16777215, 0);

		EntityEntryBuilder.BuiltEntityEntry entry = (EntityEntryBuilder.BuiltEntityEntry)builder.id(resource, GeneralEntityRegister.entityId++).name(resource.getResourceDomain() + "." + resource.getResourcePath()).build();
		entry.addedToRegistry();

		return entry;
	}
}
