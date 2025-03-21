// implemetation of Java's ArrayList data structure

public class List<T> {
    private T[] internalArray;
    int firstEmptyIndex;

    public List() {
        this.internalArray = (T[]) new Object[10];
        firstEmptyIndex = 0;
    }

    // method to add a new element to the list
    public void add(T value) {
        if (firstEmptyIndex == internalArray.length) {
            expand();
        }
        this.internalArray[firstEmptyIndex] = value;
        this.firstEmptyIndex++;
    }

    // helper method to automatically expand array if it's already full
    private void expand() {
        int newArraySize = (int) (internalArray.length * 1.5);
        T[] newArray = (T[]) new Object[newArraySize];
        for (int i = 0; i < firstEmptyIndex; i++) {
            newArray[i] = this.internalArray[i];
        }
        this.internalArray = newArray;
    }

    // method to check if the list contains a given value
    public boolean contains(T value) {
        return indexOfValue(value) >= 0;
    }

    // method to remove one given value from the list (if the list contains this value)
    public void remove(T value) {
        int index = indexOfValue(value);
        if (index < 0) {
            return;
        } else {
            moveLeft(index);
            firstEmptyIndex--;
        }
    }

    // method to find the index of the first occurence of a given value
    public int indexOfValue(T value) {
        for (int i = 0; i < this.firstEmptyIndex; i++) {
            if (this.internalArray[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // helper method to move all elements of the list one position to the left
    private void moveLeft(int index) {
        for (int i = index; i < firstEmptyIndex - 1; i++) {
            this.internalArray[i] = this.internalArray[i + 1];
        }
    }

    // method to retrieve the value from the list for a given index
    public T value(int index) {
        if (index < 0 || index >= firstEmptyIndex) {
            throw new IndexOutOfBoundsException("Index " + index + " outside of admissible range [0, " + this.firstEmptyIndex + "]");
        }
        return this.internalArray[index];
    }

    // method to detemine the size of the list
    public int size() {
        return this.firstEmptyIndex;
    }
}
