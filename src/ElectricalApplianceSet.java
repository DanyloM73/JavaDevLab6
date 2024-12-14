import electrical_appliances.ElectricalAppliance;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A custom implementation of the {@link Set} interface to handle collections of electrical appliances.
 * This set does not allow duplicate elements or null values.
 *
 * @param <T> A type parameter that extends {@link electrical_appliances.ElectricalAppliance}.
 */
public class ElectricalApplianceSet<T extends ElectricalAppliance> implements Set<T> {
    /**
     * The initial capacity of the set.
     */
    private static final int INITIAL_CAPACITY = 15;

    /**
     * The capacity increment factor used to increase the array size.
     */
    private static final double CAPACITY_INCREMENT = 0.3;

    /**
     * The internal array to store elements in the set.
     */
    private T[] elements;

    /**
     * The current size of the set.
     */
    private int size;

    /**
     * Default constructor to initialize the set with the default capacity.
     */
    @SuppressWarnings("unchecked")
    public ElectricalApplianceSet() {
        this.elements = (T[]) new ElectricalAppliance[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructor to create a set and add an initial appliance.
     *
     * @param appliance The initial appliance to add to the set.
     */
    public ElectricalApplianceSet(T appliance) {
        this();
        add(appliance);
    }

    /**
     * Constructor to create a set from a collection of appliances.
     *
     * @param appliances A collection of appliances to add to the set.
     */
    public ElectricalApplianceSet(Collection<? extends T> appliances) {
        this();
        addAll(appliances);
    }

    /**
     * Ensures there is enough capacity in the internal array to add more elements.
     * If the current capacity is insufficient, the array is resized.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length + elements.length * CAPACITY_INCREMENT);
            T[] newElements = (T[]) new ElectricalAppliance[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    /**
     * @return the number of elements in the set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return {@code true} if the set contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return {@code true} if the set contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return an iterator over the elements in the set.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            /**
             * @return {@code true} if next element is available.
             */
            @Override
            public boolean hasNext() {
                return index < size;
            }

            /**
             * @return the next element.
             * @throws NoSuchElementException if no more elements are available.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index++];
            }
        };
    }

    /**
     * @return an array containing all the elements in the set.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        T[] result = (T[]) new ElectricalAppliance[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    /**
     * @return an array containing all the elements in the set;
     * the runtime type of the returned array is that of the specified array.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <U> U[] toArray(U[] a) {
        if (a.length < size) {
            return (U[]) toArray();
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * Adds the specified appliance to the set if it is not present.
     *
     * @param appliance The appliance to be added.
     * @return {@code true} if the appliance was added.
     */
    @Override
    public boolean add(T appliance) {
        if (appliance == null || contains(appliance)) {
            return false;
        }
        ensureCapacity();
        elements[size++] = appliance;
        return true;
    }

    /**
     * Removes the specified element from the set if it is present.
     *
     * @return {@code true} if the specified element was removed.
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * @return {@code true} if the set contains all the elements of the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object appliance : c) {
            if (!contains(appliance)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all the elements in the specified collection to this set.
     *
     * @return {@code true} if all the elements from the specified collection was added.
     */
    @Override
    public boolean addAll(Collection<? extends T> appliances) {
        boolean modified = false;
        for (T appliance : appliances) {
            if (add(appliance)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes from the set all of its elements that are not contained in the specified collection.
     *
     * @return {@code true} if all the elements not from the specified collection was removed.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(elements[i--]);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes from the set all of its elements that are contained in the specified collection.
     *
     * @return {@code true} if all the elements from the specified collection was removed.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object appliance : c) {
            if (remove(appliance)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all the elements from the set.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (T[]) new ElectricalAppliance[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * @return a string representation of the set in the format "{ element1; element2; ... }".
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n{ ");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append("; ");
            }
        }
        sb.append(" }");
        return sb.toString();
    }

}
