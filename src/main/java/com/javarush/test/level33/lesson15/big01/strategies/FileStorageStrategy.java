package com.javarush.test.level33.lesson15.big01.strategies;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = 10000;

    int hash(Long k) {
        return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        if (size == 0) {
            return null;
        }

        int hash = (key == null) ? 0 : hash(key);
        FileBucket b = table[indexFor(hash, table.length)];
        if (b != null) {
            Entry e = b.getEntry();
            while (e != null) {
                if (key.equals(e.getKey())) {
                    return e;
                }
                e = e.next;
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        for (FileBucket b : table) {
            if (b == null) continue;
            Entry e = b.getEntry();
            while (null != e) {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                if (newTable[i] == null) {
                    newTable[i] = new FileBucket();
                    e.next = null;
                } else {
                    e.next = newTable[i].getEntry();
                }
                newTable[i].putEntry(e);
                e = next;
            }
            b.remove();
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;

        FileBucket[] tab = table;
        for (FileBucket b : tab) {
            if (b == null) continue;
            for (Entry e = b.getEntry(); e != null; e = e.next)
                if (value.equals(e.value)) return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null)
            return;
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        if (table[i] != null) {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Long k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    e.value = value;
                    return;
                }
            }
            addEntry(hash, key, value, i);
        } else {
            createEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return null;
        FileBucket[] tab = table;
        for (FileBucket b : tab) {
            if (b == null) continue;
            for (Entry e = b.getEntry(); e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }
}
