/*
 * Copyright 2015 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.map.sorted.mutable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.gs.collections.api.block.procedure.Procedure2;
import com.gs.collections.api.collection.MutableCollection;
import com.gs.collections.api.map.sorted.MutableSortedMap;
import com.gs.collections.api.set.MutableSet;
import com.gs.collections.api.tuple.Pair;
import com.gs.collections.impl.block.factory.Functions;
import com.gs.collections.impl.block.procedure.MapCollectProcedure;
import com.gs.collections.impl.collection.mutable.CollectionAdapter;
import com.gs.collections.impl.set.mutable.SetAdapter;
import com.gs.collections.impl.utility.ArrayIterate;
import com.gs.collections.impl.utility.MapIterate;

public class TreeSortedMap<K, V>
        extends AbstractMutableSortedMap<K, V>
        implements Externalizable
{
    private static final long serialVersionUID = 1L;
    private TreeMap<K, V> treeMap;

    public TreeSortedMap()
    {
        this.treeMap = new TreeMap<K, V>();
    }

    public TreeSortedMap(Comparator<? super K> comparator)
    {
        this.treeMap = new TreeMap<K, V>(comparator);
    }

    public TreeSortedMap(Map<? extends K, ? extends V> map)
    {
        this.treeMap = new TreeMap<K, V>(map);
    }

    public TreeSortedMap(Comparator<? super K> comparator, Map<? extends K, ? extends V> map)
    {
        this.treeMap = new TreeMap<K, V>(comparator);
        this.treeMap.putAll(map);
    }

    public TreeSortedMap(SortedMap<K, ? extends V> map)
    {
        this.treeMap = new TreeMap<K, V>(map);
    }

    public TreeSortedMap(Pair<K, V>... pairs)
    {
        this.treeMap = new TreeMap<K, V>();
        ArrayIterate.forEach(pairs, new MapCollectProcedure<Pair<K, V>, K, V>(
                this.treeMap,
                Functions.<K>firstOfPair(),
                Functions.<V>secondOfPair()));
    }

    public static <K, V> TreeSortedMap<K, V> newMap()
    {
        return new TreeSortedMap<K, V>();
    }

    public static <K, V> TreeSortedMap<K, V> newMap(Comparator<? super K> comparator)
    {
        return new TreeSortedMap<K, V>(comparator);
    }

    public static <K, V> TreeSortedMap<K, V> newMap(Map<? extends K, ? extends V> map)
    {
        if (map instanceof SortedMap<?, ?>)
        {
            return new TreeSortedMap<K, V>((SortedMap<K, V>) map);
        }
        return new TreeSortedMap<K, V>(map);
    }

    public static <K, V> TreeSortedMap<K, V> newMap(Comparator<? super K> comparator, Map<? extends K, ? extends V> map)
    {
        return new TreeSortedMap<K, V>(comparator, map);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(Pair<K, V>... pairs)
    {
        return new TreeSortedMap<K, V>(pairs);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(Comparator<? super K> comparator, Pair<K, V>... pairs)
    {
        return new TreeSortedMap<K, V>(comparator).with(pairs);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(K key, V value)
    {
        return new TreeSortedMap<K, V>().with(key, value);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(K key1, V value1, K key2, V value2)
    {
        return new TreeSortedMap<K, V>().with(key1, value1, key2, value2);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new TreeSortedMap<K, V>().with(key1, value1, key2, value2, key3, value3);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return new TreeSortedMap<K, V>().with(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(Comparator<? super K> comparator, K key, V value)
    {
        return new TreeSortedMap<K, V>(comparator).with(key, value);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(Comparator<? super K> comparator, K key1, V value1, K key2, V value2)
    {
        return new TreeSortedMap<K, V>(comparator).with(key1, value1, key2, value2);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(Comparator<? super K> comparator, K key1, V value1, K key2, V value2, K key3, V value3)
    {
        return new TreeSortedMap<K, V>(comparator).with(key1, value1, key2, value2, key3, value3);
    }

    public static <K, V> TreeSortedMap<K, V> newMapWith(Comparator<? super K> comparator,
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4)
    {
        return new TreeSortedMap<K, V>(comparator).with(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    public TreeSortedMap<K, V> with(K key, V value)
    {
        this.put(key, value);
        return this;
    }

    public TreeSortedMap<K, V> with(K key1, V value1, K key2, V value2)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        return this;
    }

    public TreeSortedMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        this.put(key3, value3);
        return this;
    }

    public TreeSortedMap<K, V> with(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4)
    {
        this.put(key1, value1);
        this.put(key2, value2);
        this.put(key3, value3);
        this.put(key4, value4);
        return this;
    }

    @Override
    public TreeSortedMap<K, V> with(Pair<K, V>... pairs)
    {
        ArrayIterate.forEach(pairs, new MapCollectProcedure<Pair<K, V>, K, V>(this, Functions.<K>firstOfPair(), Functions.<V>secondOfPair()));
        return this;
    }

    public int size()
    {
        return this.treeMap.size();
    }

    public MutableSortedMap<K, V> newEmpty()
    {
        return new TreeSortedMap<K, V>(this.comparator());
    }

    public V removeKey(K key)
    {
        return this.treeMap.remove(key);
    }

    @Override
    public TreeSortedMap<K, V> clone()
    {
        return new TreeSortedMap<K, V>(this);
    }

    @Override
    public boolean equals(Object o)
    {
        return this.treeMap.equals(o);
    }

    @Override
    public int hashCode()
    {
        return this.treeMap.hashCode();
    }

    public void forEachKeyValue(Procedure2<? super K, ? super V> procedure2)
    {
        MapIterate.forEachKeyValue(this.treeMap, procedure2);
    }

    public K firstKey()
    {
        return this.treeMap.firstKey();
    }

    public K lastKey()
    {
        return this.treeMap.lastKey();
    }

    public MutableSet<Entry<K, V>> entrySet()
    {
        return SetAdapter.adapt(this.treeMap.entrySet());
    }

    public MutableSet<K> keySet()
    {
        return SetAdapter.adapt(this.treeMap.keySet());
    }

    public MutableCollection<V> values()
    {
        return CollectionAdapter.adapt(this.treeMap.values());
    }

    public Comparator<? super K> comparator()
    {
        return this.treeMap.comparator();
    }

    public V get(Object key)
    {
        return this.treeMap.get(key);
    }

    public V put(K key, V value)
    {
        return this.treeMap.put(key, value);
    }

    public V remove(Object key)
    {
        return this.treeMap.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> map)
    {
        this.treeMap.putAll(map);
    }

    public void clear()
    {
        this.treeMap.clear();
    }

    public boolean containsKey(Object key)
    {
        return this.treeMap.containsKey(key);
    }

    public MutableSortedMap<K, V> headMap(K toKey)
    {
        return SortedMapAdapter.adapt(this.treeMap.headMap(toKey));
    }

    public MutableSortedMap<K, V> tailMap(K fromKey)
    {
        return SortedMapAdapter.adapt(this.treeMap.tailMap(fromKey));
    }

    public MutableSortedMap<K, V> subMap(K fromKey, K toKey)
    {
        return SortedMapAdapter.adapt(this.treeMap.subMap(fromKey, toKey));
    }

    public boolean containsValue(Object value)
    {
        return this.treeMap.containsValue(value);
    }

    @Override
    public String toString()
    {
        return this.treeMap.toString();
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.comparator());
        out.writeInt(this.size());
        for (Entry<K, V> entry : this.treeMap.entrySet())
        {
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.treeMap = new TreeMap<K, V>((Comparator<? super K>) in.readObject());
        int size = in.readInt();
        for (int i = 0; i < size; ++i)
        {
            this.treeMap.put((K) in.readObject(), (V) in.readObject());
        }
    }
}
