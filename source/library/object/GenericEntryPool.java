package net.tslat.aoa3.library.object;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.apache.commons.lang3.mutable.MutableInt;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class GenericEntryPool<T, P> {
	protected final RandomUtil.EasyRandom random = new RandomUtil.EasyRandom();
	protected final List<GenericEntry<T, P>> entries;
	protected Pair<MutableInt, ImmutableList<GenericEntry<T, P>>> lastCache;

	protected final boolean cacheLast;
	protected boolean useCache = false;

	public GenericEntryPool() {
		this(false);
	}

	public GenericEntryPool(boolean cacheLast) {
		this.entries = new ArrayList<GenericEntry<T, P>>();
		this.cacheLast = cacheLast;
	}

	public GenericEntryPool<T, P> startCache() {
		if (this.cacheLast)
			this.useCache = true;

		return this;
	}

	public GenericEntryPool<T, P> stopCache() {
		if (this.cacheLast)
			this.useCache = false;

		return this;
	}

	public GenericEntryPool<T, P> add(T entry, int weight) {
		return add(entry, obj -> true, weight);
	}

	public GenericEntryPool<T, P> add(T entry, Predicate<P> validationPredicate, int weight) {
		return add(entry, validationPredicate, weight, 0);
	}

	public GenericEntryPool<T, P> add(T entry, Predicate<P> validationPredicate, int weight, float weightMod) {
		return add(new GenericEntry<T, P>(entry, validationPredicate, weight, weightMod));
	}

	public GenericEntryPool<T, P> add(GenericEntry<T, P> entry) {
		this.entries.add(entry);

		return this;
	}

	protected Pair<MutableInt, ImmutableList<GenericEntry<T, P>>> filterFor(P predicateObj, float weightModValue) {
		if (this.useCache && this.lastCache != null)
			return this.lastCache;

		MutableInt totalWeight = new MutableInt();
		ArrayList<GenericEntry<T, P>> filtered = new ArrayList<GenericEntry<T, P>>(this.entries.size());

		for (GenericEntry<T, P> entry : this.entries) {
			int weight = entry.getEffectiveWeight(weightModValue);

			if (weight <= 0)
				continue;

			if (!entry.test(predicateObj))
				continue;

			totalWeight.add(weight);
			filtered.add(entry);
		}

		Pair<MutableInt, ImmutableList<GenericEntry<T, P>>> result = Pair.of(totalWeight, ImmutableList.copyOf(filtered));

		if (cacheLast)
			this.lastCache = result;

		return result;
	}

	@Nullable
	public GenericEntry<T, P> getRandomEntry(P predicateObj, float weightModValue) {
		Pair<MutableInt, ImmutableList<GenericEntry<T, P>>> filtered = filterFor(predicateObj, weightModValue);

		return getRandomEntry(filtered.getFirst().getValue(), filtered.getSecond(), weightModValue);
	}

	@Nullable
	protected GenericEntry<T, P> getRandomEntry(int totalWeight, List<GenericEntry<T, P>> list, float weightModValue) {
		if (list.size() == 0)
			return null;

		if (list.size() == 1)
			return list.get(0);

		int selection = random.randomNumberUpTo(totalWeight);

		for (GenericEntry<T, P> entry : list) {
			selection -= entry.getEffectiveWeight(weightModValue);

			if (selection <= 0)
				return entry;
		}

		return null;
	}

	@Nullable
	public T getRandomElement(P predicateObj, float weightModValue) {
		GenericEntry<T, P> entry = getRandomEntry(predicateObj, weightModValue);

		return entry == null ? null : entry.get();
	}

	@Nullable
	public GenericEntry<T, P> getRandomEntryUnweighted(P predicateObj) {
		Pair<MutableInt, ImmutableList<GenericEntry<T, P>>> filtered = filterFor(predicateObj, 0);

		return random.getRandomSelection(filtered.getSecond());
	}

	@Nullable
	public T getRandomElementUnweighted(P predicateObj) {
		GenericEntry<T, P> entry = getRandomEntryUnweighted(predicateObj);

		return entry == null ? null : entry.get();
	}

	public Stream<GenericEntry<T, P>> getRandomEntries(P predicateObj, float weightModValue) {
		Pair<MutableInt, ImmutableList<GenericEntry<T, P>>> filtered = filterFor(predicateObj, 0);
		int totalWeight = filtered.getFirst().getValue();
		List<GenericEntry<T, P>> list = filtered.getSecond();

		return Stream.generate(() -> getRandomEntry(totalWeight, list, weightModValue));
	}

	@Nullable
	public Stream<T> getRandomElements(P predicateObj, float weightModValue) {
		return getRandomEntries(predicateObj, weightModValue).map(entry -> entry == null ? null : entry.get());
	}

	public boolean isEmpty() {
		return this.entries.isEmpty();
	}

	public void clear() {
		this.entries.clear();
	}

	@Override
	public String toString() {
		return "GenericEntryPool{" + "entries=" + entries + '}';
	}

	public static class GenericEntry<T, P> {
		private final T obj;
		private final int weight;
		private final float weightMod;
		private final Predicate<P> validationPredicate;

		protected GenericEntry(T obj, Predicate<P> predicate, int weight, float weightMod) {
			this.obj = obj;
			this.validationPredicate = predicate;
			this.weight = weight;
			this.weightMod = weightMod;
		}

		public T get() {
			return this.obj;
		}

		public int getWeight() {
			return this.weight;
		}

		public int getEffectiveWeight(float weightModValue) {
			return this.weight + (int)(this.weightMod * weightModValue);
		}

		public boolean test(P predicateObj) {
			return this.validationPredicate.test(predicateObj);
		}
	}
}
