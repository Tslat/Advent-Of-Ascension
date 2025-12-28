package net.tslat.aoa3.library.object;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.tslat.tme.api.object.EasyRandom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Generic collection that allows for native predication of valid retrievals and weighted retrievals.
 * <p>
 * The purpose of this collection is to provide a random-access pool for retrieving elements at random based on the following variables:
 * <ul>
 *     <li><b>Weight</b> - Individual entry weight, representing the likelihood of this entry being drawn compared to all others. Representable as a chance of <code>n / ∑n</code></li>
 *     <li><b>Weight Mod</b> - A per-entry modifier that allows for an entry's weight to be linearly modified at the time of retrieval based on a given input value as a function <code>weight = weight + mod * input</code></li>
 *     <li><b>Predicate</b> - A retrieval-time predicate to determine whether the given entry is eligible for retrieval for the current context. Ineligible entries are excluded from the weight pool.</li>
 * </ul>
 * @param <T> The value object type
 * @param <P> The predicate input object type
 */
public class RandomEntryPool<T, P> implements Collection<T> {
	protected final EasyRandom random;
	protected final List<PoolEntry<T, P>> entries;

	/**
	 * Create a new pool with an uncontrolled random source
	 */
	public RandomEntryPool() {
		this(RandomSource.create());
	}

	/**
	 * Create a new pool with a controlled random source
	 *
	 * @param random The random to use
	 */
	public RandomEntryPool(RandomSource random) {
		this(new ObjectArrayList<>(), random);
	}

	protected RandomEntryPool(List<PoolEntry<T, P>> entries, RandomSource random) {
		this.entries = entries;
		this.random = EasyRandom.wrap(random);
	}

	/**
	 * Add a new entry to the pool, with a given weight and no retrieval condition
	 */
	public RandomEntryPool<T, P> add(T entry, int weight) {
		return add(entry, obj -> true, weight);
	}

	/**
	 * Add a new entry to the pool, with a given weight and retrieval condition
	 */
	public RandomEntryPool<T, P> add(T entry, Predicate<P> validationPredicate, int weight) {
		return add(entry, validationPredicate, weight, 0);
	}

	/**
	 * Add a new entry to the pool, with a given weight, weight modifier, and retrieval condition
	 */
	public RandomEntryPool<T, P> add(T entry, Predicate<P> validationPredicate, int weight, float weightMod) {
		return add(new PoolEntry<>(entry, weight, weightMod, validationPredicate));
	}

	/**
	 * Add a new pre-formed entry to the pool
	 */
	public RandomEntryPool<T, P> add(PoolEntry<T, P> entry) {
		this.entries.add(entry);

		return this;
	}

	/**
	 * Get a random entry from this pool meeting the given predicate, ignoring any weight modifiers
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @return A random entry
	 */
	public Optional<T> getEntry(@NotNull P predicateInput) {
		return getEntry(predicateInput, 0);
	}

	/**
	 * Get a random entry from this pool meeting the given predicate, and applying the given weight modifier
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @param weightModifierMultiplier The weight modifier input value
	 * @return A random entry
	 */
	public Optional<T> getEntry(@NotNull P predicateInput, float weightModifierMultiplier) {
		return getEntryRaw(predicateInput, weightModifierMultiplier).map(PoolEntry::get);
	}

	/**
	 * Get a random entry from this pool in its containerised form, meeting the given predicate, and ignoring any weight modifiers
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @return A random entry
	 */
	public Optional<PoolEntry<T, P>> getEntryRaw(@NotNull P predicateInput) {
		return getEntryRaw(predicateInput, 0);
	}

	/**
	 * Get a random entry from this pool in its containerised form, meeting the given predicate, and applying the given weight modifier
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @param weightModifierMultiplier The weight modifier input value
	 * @return A random entry
	 */
	public Optional<PoolEntry<T, P>> getEntryRaw(@NotNull P predicateInput, float weightModifierMultiplier) {
		return Optional.ofNullable(selectEntry(predicateInput, weightModifierMultiplier));
	}

	/**
	 * Get an <b><u>Immutable</u></b> view of a subset of this pool containing only elements matching the given predicate
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @return A new, immutable pool containing only elements from this pool which meet the given predicate.
	 */
	public RandomEntryPool<T, P> getFilteredView(P predicateInput) {
		ImmutableList.Builder<PoolEntry<T, P>> entries = new ImmutableList.Builder<>();

		for (PoolEntry<T, P> entry : this.entries) {
			if (entry.test(predicateInput))
				entries.add(entry);
		}

		return new RandomEntryPool<>(entries.build(), RandomSource.create());
	}

	/**
	 * Create a Stream of entries for this pool.
	 * <p>
	 * Due to the lazily-computed contents of the stream, it is not known if any elements match the predicate until the first retrieval.
	 * Due to this, any stream that returns an empty optional will always return an empty optional, and vice-versa.
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @param weightModifierMultiplier The weight modifier input value
	 */
	public Stream<Optional<T>> getEntries(P predicateInput, float weightModifierMultiplier) {
		return getEntriesRaw(predicateInput, weightModifierMultiplier).map(optional -> optional.map(PoolEntry::get));
	}

	/**
	 * Create a Stream of entries for this pool in their containerised form.
	 * <p>
	 * Due to the lazily-computed contents of the stream, it is not known if any elements match the predicate until the first retrieval.
	 * Due to this, any stream that returns an empty optional will always return an empty optional, and vice-versa.
	 *
	 * @param predicateInput The input value to pass to entry predicates
	 * @param weightModifierMultiplier The weight modifier input value
	 */
	public Stream<Optional<PoolEntry<T, P>>> getEntriesRaw(P predicateInput, float weightModifierMultiplier) {
		final Supplier<RandomEntryPool<T, P>> filteredView = Suppliers.memoize(() -> getFilteredView(predicateInput));
		final Supplier<IntObjectPair<int[]>> mappedWeights = Suppliers.memoize(() -> filteredView.get().mapPoolWeights(weightModifierMultiplier));

		return Stream.generate(() -> Optional.ofNullable(filteredView.get().selectEntry(mappedWeights.get())));
	}

	@Nullable
	private PoolEntry<T, P> selectEntry(@NotNull P predicateInput, float weightModifierMultiplier) {
		final RandomEntryPool<T, P> filteredView = getFilteredView(predicateInput);

		if (filteredView.isEmpty())
			return null;

		if (filteredView.entries.size() == 1)
			return filteredView.entries.getFirst();

		return filteredView.selectEntry(filteredView.mapPoolWeights(weightModifierMultiplier));
	}

	@Nullable
	private PoolEntry<T, P> selectEntry(IntObjectPair<int[]> mappedWeights) {
		if (isEmpty())
			return null;

		if (this.entries.size() == 1)
			return this.entries.getFirst();

		int selection = this.random.numberUpTo(mappedWeights.leftInt());

		for (int i = 0; i < this.entries.size(); i++) {
			selection -= mappedWeights.right()[i];

			if (selection <= 0)
				return this.entries.get(i);
		}

		return null;
	}

	private IntObjectPair<int[]> mapPoolWeights(float weightModifierMultiplier) {
		int totalWeight = 0;
		int[] mappedWeights = new int[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			totalWeight += mappedWeights[i] = this.entries.get(i).getEffectiveWeight(weightModifierMultiplier);
		}

		return IntObjectPair.of(totalWeight, mappedWeights);
	}

	@Override
	public int size() {
		return this.entries.size();
	}

	@Override
	public boolean isEmpty() {
		return this.entries.isEmpty();
	}

	@Override
	public void clear() {
		this.entries.clear();
	}

	@Override
	public boolean contains(Object object) {
		for (PoolEntry<T, P> entry : this.entries) {
			if (entry.get().equals(object))
				return true;
		}

		return false;
	}

	@NotNull
	@Override
	public Iterator<T> iterator() {
		return new Iterator<>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return RandomEntryPool.this.entries.size() > this.index;
			}

			@Override
			public T next() {
				return RandomEntryPool.this.entries.get(this.index++).get();
			}
		};
	}

	@NotNull
	@Override
	public Object[] toArray() {
		final Object[] array = new Object[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			array[i] = this.entries.get(i).get();
		}

		return array;
	}

	@NotNull
	@Override
	public <T1> T1[] toArray(@NotNull T1[] array) {
		if (array.length < this.entries.size())
			array = (T1[])Array.newInstance(array.getClass().getComponentType(), this.entries.size());

		for (int i = 0; i < this.entries.size(); i++) {
			array[i] = (T1)this.entries.get(i).get();
		}

		return array;
	}

	@Override
	public boolean add(T entry) {
		throw new UnsupportedOperationException("Unweighted add operation not supported, use the overload that takes a weight value");
	}

	@Override
	public boolean remove(Object object) {
		for (Iterator<PoolEntry<T, P>> iterator = this.entries.iterator(); iterator.hasNext();) {
			PoolEntry<T, P> entry = iterator.next();

			if (entry.get().equals(object)) {
				iterator.remove();

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsAll(@NotNull Collection<?> collection) {
		for (Object object : collection) {
			if (!contains(object))
				return false;
		}

		return true;
	}

	@Override
	public boolean addAll(@NotNull Collection<? extends T> collection) {
		throw new UnsupportedOperationException("Unweighted add operation not supported, use the overload that takes a weight value");
	}

	@Override
	public boolean removeAll(@NotNull Collection<?> collection) {
		boolean modified = false;

		for (Object object : collection) {
			if (remove(object))
				modified = true;
		}

		return modified;
	}

	@Override
	public boolean retainAll(@NotNull Collection<?> collection) {
		final int size = size();

        this.entries.removeIf(entry -> !collection.contains(entry.get()));

		return size() != size;
	}

	@Override
	public String toString() {
		return "GenericEntryPool{" + "entries=" + entries + '}';
	}

	public record PoolEntry<T, P>(T value, int weight, float weightMod, Predicate<P> validationPredicate) {
		public T get() {
			return this.value;
		}

		public int getEffectiveWeight(float weightModValue) {
			return this.weight + (int)(this.weightMod * weightModValue);
		}

		public boolean test(P predicateObj) {
			return this.validationPredicate.test(predicateObj);
		}
	}
}
