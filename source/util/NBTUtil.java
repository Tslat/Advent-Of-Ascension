package net.tslat.aoa3.util;

import net.minecraft.nbt.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class NBTUtil {
	public static class NBTBuilder<T extends INBT> {
		private final T tag;
		private HashMap<String, NBTBuilder<?>> childTags;

		public NBTBuilder(T nbtBase) {
			this.tag = nbtBase;
		}

		public NBTBuilder<T> putTag(@Nullable String key, NBTBuilder<?> tag) {
			if (childTags == null)
				childTags = new HashMap<String, NBTBuilder<?>>();

			childTags.put(key, tag);

			return this;
		}

		public NBTBuilder<T> putString(@Nullable String key, String value) {
			if (tag.getType() == CompoundNBT.TYPE && key != null) {
				((CompoundNBT)tag).put(key, StringNBT.valueOf(value));
			}
			else if (tag.getType() == ListNBT.TYPE) {
				((ListNBT)tag).add(StringNBT.valueOf(value));
			}

			return this;
		}

		public NBTBuilder<T> putInteger(@Nullable String key, int value) {
			if (tag.getType() == CompoundNBT.TYPE && key != null) {
				((CompoundNBT)tag).put(key, IntNBT.valueOf(value));
			}
			else if (tag.getType() == ListNBT.TYPE) {
				((ListNBT)tag).add(IntNBT.valueOf(value));
			}

			return this;
		}

		public NBTBuilder<T> putFloat(@Nullable String key, float value) {
			if (tag.getType() == CompoundNBT.TYPE && key != null) {
				((CompoundNBT)tag).put(key, FloatNBT.valueOf(value));
			}
			else if (tag.getType() == ListNBT.TYPE) {
				((ListNBT)tag).add(FloatNBT.valueOf(value));
			}

			return this;
		}

		public NBTBuilder<T> putLong(@Nullable String key, long value) {
			if (tag.getType() == CompoundNBT.TYPE && key != null) {
				((CompoundNBT)tag).put(key, LongNBT.valueOf(value));
			}
			else if (tag.getType() == ListNBT.TYPE) {
				((ListNBT)tag).add(LongNBT.valueOf(value));
			}

			return this;
		}

		public T build() {
			if (childTags != null && !childTags.isEmpty()) {
				if (tag.getType() == CompoundNBT.TYPE) {
					Map.Entry<String, NBTBuilder<?>> firstEntry = childTags.entrySet().stream().findFirst().get();

					((CompoundNBT)tag).put(firstEntry.getKey(), firstEntry.getValue().build());
				}
				else if (tag.getType() == ListNBT.TYPE) {
					for (Map.Entry<String, NBTBuilder<?>> childEntry : childTags.entrySet()) {
						((ListNBT)tag).add(childEntry.getValue().build());
					}
				}
			}

			return tag;
		}
	}
}
