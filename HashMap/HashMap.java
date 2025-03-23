import java.util.ArrayList;

public class HashMap <K, V> {
    private ArrayList<Pair<K, V>>[] internalArray;
    private int numberOfValues;

    public HashMap() {
        this.internalArray = new ArrayList[32];
        numberOfValues = 0;
    }

    // method to retrieve number of values
    public int getNumberOfValues() {
        return this.numberOfValues;
    }

    // method to retrieve a value by key
    public V getValue(K key) {
        int hashValue = calculateIndexForKey(internalArray, key);
        if (internalArray[hashValue] == null) {
            return null;
        }
        
        ArrayList<Pair<K,V>> list = internalArray[hashValue];

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return list.get(i).getValue();
            }
        }

        return null;
    }

    // helper method to calculate a hashValue (i.e. index position in internalArray) based on key provided
    public int calculateIndexForKey(ArrayList<Pair<K, V>>[] array, K key) {
        int hashValue = Math.abs(key.hashCode() % array.length);
        return hashValue;
    }

    // method to add key-value pair in the HashMap
    public void add(K key, V value) {
        ArrayList<Pair<K,V>> list = getListByKey(key);
        int index = getIndexOfKey(list, key);

        if (index < 0) {
            list.add(new Pair(key, value));
            this.numberOfValues++;
        } else {
            list.get(index).setValue(value);
        }

        if (1.0 * this.numberOfValues / this.internalArray.length > 0.75) {
            growArray();
        }
    }

    // helper method to get ArrayList for a given key
    private ArrayList<Pair<K,V>> getListByKey(K key) {
        int hashValue = calculateIndexForKey(internalArray, key);
        if (internalArray[hashValue] == null) {
            internalArray[hashValue] = new ArrayList<>();
        }
        return internalArray[hashValue];
    }

    // helper method to get index of a given key
    private int getIndexOfKey(ArrayList<Pair<K,V>> list, K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    // helper method to double the size of internal array once the amount of values becomes > 0.75*array length
    private void growArray() {
        ArrayList<Pair<K,V>>[] newArray = new ArrayList[this.internalArray.length * 2];
        
        for (int i = 0; i < this.internalArray.length; i++) {
            // copy values from the old array into a new one
            if (internalArray[i] != null) {
                copyValuesFromList(newArray, i);
            }
        }
        // replace old array with a new one
        this.internalArray = newArray;
    }

    // helper method to copy elements of ArrayList at a given index to the new array
    private void copyValuesFromList(ArrayList<Pair<K,V>>[] newArray, int indexOfList) {
        
        for (int i = 0; i < this.internalArray[indexOfList].size(); i++) {
            Pair<K,V> pair = this.internalArray[indexOfList].get(i);
            int hashValue = calculateIndexForKey(newArray, pair.getKey());
            
            if (newArray[hashValue] == null) {
                newArray[hashValue] = new ArrayList<>();
            }

            newArray[hashValue].add(pair);
        }
    }

    // method to remove value by a given key
    public V remove(K key) {
        ArrayList<Pair<K,V>> list = getListByKey(key);
        if (list.size() == 0) {
            return null;
        }

        int index = getIndexOfKey(list, key);
        if (index < 0) {
            return null;
        }

        Pair <K,V> pair = list.get(index);
        list.remove(index);
        this.numberOfValues--;
        return pair.getValue();
    }
}
