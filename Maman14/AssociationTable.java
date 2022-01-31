import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class AssociationTable<K extends Comparable<K>, V> {
    
    private TreeMap<K, V> treemap;

 
    public AssociationTable() {
        treemap = new TreeMap<K,V>();
    }


    public AssociationTable(K[] keys, V[] values) throws IllegalArgumentException {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("error: got keys array and values array of a different length");
        }
        treemap = new TreeMap<K,V>();
        for (int i = 0; i < keys.length; i++) {
            treemap.put(keys[i], values[i]);
        }
    }


    public void add(K key, V value) {
        treemap.put(key, value);
    }


    public V get(K key) {
        return treemap.get(key);
    }

    public boolean contains(K key) {
        if (treemap.containsKey(key)) {
            return true;
        }
        return false;
    }


    public boolean remove(K key) {
        if (!this.contains(key)) {
            return false;
        }
        treemap.remove(key);
        return true;
    }


    public int size() {
        return treemap.size(); 
    }

    public Iterator<Map.Entry<K,V>> keyIterator() {
        return treemap.entrySet().iterator();
    }

}