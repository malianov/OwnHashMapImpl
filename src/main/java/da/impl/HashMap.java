package da.impl;

import da.Map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {

    static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<K, V> entry = (Entry<K, V>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private Entry<K, V>[] backets;
    private int size = 0;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int capacity = INITIAL_CAPACITY;
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public HashMap() {
        this(INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        backets = new Entry[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    private int indexOfBacket(Object key) {
        return key == null ? 0 : ((key.hashCode() & 0x7fffffff) % (backets.length));
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            return backets[0] == null ? null : backets[0].value;
        }

        int index = indexOfBacket(key);
        Entry<K, V> entry = backets[index];

        if (entry == null) {
            return null;
        }

        while (entry.next != null && !Objects.equals(entry.key, key)) {
            entry = entry.next;
        }

        if (Objects.equals(entry.key, key)) {
            return entry.value;
        }

        return null;
    }

    private boolean demandForResize() {
        return this.size > this.capacity * DEFAULT_LOAD_FACTOR;
    }

    private void resizing() {
        capacity *= 2;
        int i = 0;
        Entry<K, V>[] oldTable = backets;
        backets = new Entry[capacity];
        size = 0;
        for (Entry<K, V> entry : oldTable) {
            Entry<K, V> temp = entry;
            if (temp != null) {
                while (temp != null) {
                    put(temp.key, temp.value);
                    temp = temp.next;
                }
            }
        }
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> newEntry = new Entry(key, value, null);

        if (demandForResize()) {
            resizing();
        }

        if (key == null) {
            System.out.println("backet[0] = " + backets[0]);
            if (backets[0] == null) {
                backets[0] = newEntry;
                size++;
                return null;
            } else {
                V oldValue = backets[0].getValue();
                backets[0] = newEntry;
                return oldValue;
            }
        }

        int index = indexOfBacket(key);
        Entry<K, V> existingEntry = backets[index];

        if (existingEntry != null) {
            V oldValue = existingEntry.getValue();

            if (Objects.equals(existingEntry.getKey(), key)) {
                existingEntry.value = value;
                return oldValue;
            }

            while (existingEntry.next != null) {
                if (Objects.equals(existingEntry.key, key)) {
                    existingEntry.value = value;
                    return oldValue;
                }
                existingEntry = existingEntry.next;
            }
            existingEntry.next = newEntry;
        } else {
            backets[index] = newEntry;
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            if (backets[0] == null) {
                return null;
            }

            V oldValue = backets[0].getValue();
            backets[0] = null;
            size--;
            return oldValue;
        }

        int index = indexOfBacket(key);
        Entry<K, V> entry = backets[index];

        if (entry == null) {
            return null;
        }

        V oldValue = entry.getValue();

        if (Objects.equals(entry.key, key)) {
            if (entry.next == null) {
                backets[index] = null;
            } else {
                backets[index] = entry.next;
            }
            size--;
            return oldValue;
        }

        if (entry.next == null) {
            return null;
        }

        Entry<K, V> previous = entry;

        while (entry.next != null) {
            if (Objects.equals(entry.next.key, key)) {
                oldValue = entry.next.getValue();
                if (entry.next.next != null) {
                    previous.next = entry.next.next;
                }
                entry.next = null;
                size--;
                return oldValue;
            } else {
                previous = entry;
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<K> set;
        set = (Set<K>) m.keySet();
        V value;

        for (K key : set) {
            value = m.get(key);
            this.put(key, value);
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (containsKey(key)) {
            return get(key);
        }
        return put(key, value);
    }

    @Override
    public void putAllIfAbsent(Map<K, V> map) {
        Set<K> set;
        set = (Set<K>) map.keySet();
        V value;

        for (K key : set) {
            if (!this.containsKey(key)) {
                value = map.get(key);
                this.put(key, value);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();

        for (Entry<K, V> basket : backets) {
            while (basket != null) {
                set.add(basket.getKey());
                basket = basket.next;
            }
        }
        return set;
    }

    public String toString() {
        return Arrays.toString(backets);
    }
}
