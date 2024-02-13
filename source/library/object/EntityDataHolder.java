package net.tslat.aoa3.library.object;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.attachment.AttachmentHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class EntityDataHolder<T> {
	private final EntityDataAccessor<T> key;
	private final T defaultValue;
	private final Function<Entity, T> getter;
	private final BiConsumer<Entity, T> setter;

	private EntityDataHolder(EntityDataAccessor<T> key, T defaultValue, Function<Entity, T> getter, BiConsumer<Entity, T> setter) {
		this.key = key;
		this.defaultValue = defaultValue;
		this.getter = getter;
		this.setter = setter;
	}

	public static <E extends Entity, T> EntityDataHolder<T> register(Class<E> clazz, EntityDataSerializer<T> serializer, T defaultValue, Function<E, T> getter, BiConsumer<E, T> setter) {
		return new EntityDataHolder<T>(register(clazz, serializer), defaultValue, (Function<Entity, T>)getter, (BiConsumer<Entity, T>)setter);
	}

	public void defineDefault(Entity entity) {
		entity.getEntityData().define(this.key, this.defaultValue);
	}

	public boolean checkSync(Entity entity, EntityDataAccessor<?> updatedKey) {
		if (this.key.equals(updatedKey)) {
			this.setter.accept(entity, entity.getEntityData().get(this.key));

			return true;
		}

		return false;
	}

	public T get(Entity entity) {
		return this.getter.apply(entity);
	}

	public void setRaw(Entity entity, T value) {
		entity.getEntityData().set(this.key, value);
	}

	public void set(Entity entity, T value) {
		setRaw(entity, value);
		this.setter.accept(entity, value);
	}

	public T defaultValue() {
		return this.defaultValue;
	}

	public boolean is(Entity entity, @NotNull T value) {
		return value.equals(get(entity));
	}

	public boolean isAny(Entity entity, T... values) {
		T value = get(entity);

		for (T val : values) {
			if (val.equals(value))
				return true;
		}

		return false;
	}

	private static <T> EntityDataAccessor<T> register(Class<? extends Entity> clazz, EntityDataSerializer<T> serializer) {
		int id = 0;

		if (SynchedEntityData.ENTITY_ID_POOL.containsKey(clazz)) {
			id = SynchedEntityData.ENTITY_ID_POOL.getInt(clazz) + 1;
		}
		else {
			for (Class<?> parentClass = clazz.getSuperclass(); parentClass != AttachmentHolder.class; parentClass = parentClass.getSuperclass()) {
				if (SynchedEntityData.ENTITY_ID_POOL.containsKey(parentClass)) {
					id = SynchedEntityData.ENTITY_ID_POOL.getInt(parentClass) + 1;

					break;
				}
			}
		}

		if (id >= 255)
			throw new IllegalArgumentException("Too many data parameters registered for " + clazz + "!. Max is 254");

		SynchedEntityData.ENTITY_ID_POOL.put(clazz, id);

		return serializer.createAccessor(id);
	}
}
