import java.util.*

// A simple example to demonstrate the use of WeakHashMap
// to prevent memory leaks
class Example {
    static class Key {
        private String identifier;
 
        public Key(String id) {
            this.identifier = id;
        }
 
        @Override
        public int hashCode() {
            return identifier.hashCode();
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Key key = (Key) o;
            return Objects.equals(identifier, key.identifier);
        }
    }
 
    public static void main(String[] args) {
        // Use a weak hash map to store key-value pairs
        WeakHashMap<Key, String> map = new WeakHashMap<>();
 
        // Put some key-value pairs in the map
        Key key1 = new Key("Key 1");
        Key key2 = new Key("Key 2");
        Key key3 = new Key("Key 3");
 
        map.put(key1, "Value 1");
        map.put(key2, "Value 2");
        map.put(key3, "Value 3");
 
        System.out.println("Map size before removing the references: " + map.size());
 
        // Remove the references to the keys
        key1 = null;
        key2 = null;
        key3 = null;
 
        // Call garbage collector to remove the keys
        System.gc();
 
        // Wait for GC to finish its job
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        System.out.println("Map size after removing the references: " + map.size());
    }
}
