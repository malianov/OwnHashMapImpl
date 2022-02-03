package da.impl;

import da.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test HashMap class")
class HashMapTest {
    @Nested
    @DisplayName("Test method: size()")
    class Size {

        @DisplayName("Check the size of empty map")
        @Test
        void emptyMapSizeEqualsZero() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            Integer expected = 0;

            // when
            Integer actual = map.size();

            //then
            assertEquals(expected, actual);
        }

        @DisplayName("Check the size of non-empty map")
        @Test
        void nonEmptyMapSizeNotEqualsZero() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            Integer expected = 2;

            // when
            map.put("One", 1);
            map.put("Two", 2);
            Integer actual = map.size();

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Test method: isEmpty()")
    class IsEmpty {

        @DisplayName("Check the emptiness of an empty map")
        @Test
        void emptyMapIsEmpty() {
            // prepare
            Map<String, Integer> map = new HashMap<>();

            // when
            boolean actual = map.isEmpty();

            //then
            assertTrue(actual);
        }

        @DisplayName("Check the emptiness of non-empty map")
        @Test
        void notEmptyMapIsNotEmpty() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            // when
            boolean actual = map.isEmpty();

            //then
            assertFalse(actual);
        }
    }

    @Nested
    @DisplayName("Test method: containsKey(Object key)")
    class ContainsKey {

        @DisplayName("Check if a map contains not existing key")
        @Test
        void mapWithoutKeyDoNotContainsKey() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            // when
            boolean actual = map.containsKey("Three");

            //then
            assertFalse(actual);
        }

        @DisplayName("Check if a map contains existing key")
        @Test
        void mapWithKeyContainsKey() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            // when
            boolean actual = map.containsKey("Two");

            //then
            assertTrue(actual);
        }

        @DisplayName("Check if an empty map contains not existing key")
        @Test
        void emptyMapDoNotContainsKey() {
            // prepare
            Map<String, Integer> map = new HashMap<>();

            // when
            boolean actual = map.containsKey("Three");

            //then
            assertFalse(actual);
        }

        @DisplayName("Check if a map contains existing key equals 'null'")
        @Test
        void mapWithKeyEqualsNullContainsKeyNull() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);
            map.put(null, 3);

            // when
            boolean actual = map.containsKey(null);

            //then
            assertTrue(actual);
        }

        @DisplayName("Check if a map contains existing not 'null' key with value 'null'")
        @Test
        void mapWithNotNullKeyButWithNullValue() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", null);

            // when
            boolean actual = map.containsKey("Two");

            //then
            assertTrue(actual);
        }

        @DisplayName("Check if a map contains not existing key equals 'null'")
        @Test
        void mapWithoutKeyEqualsNullNotContainsKeyNull() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            // when
            boolean actual = map.containsKey(null);

            //then
            assertFalse(actual);
        }
    }

    @Nested
    @DisplayName("Test method: get(Object key)")
    class Get {

        @DisplayName("Get the null value by any key from an empty map")
        @Test
        void emptyMapGetNullValueForAnyKey() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            Integer expected = null;

            // when
            Integer actual = map.get("Three");

            //then
            assertEquals(expected, actual);
        }

        @DisplayName("Get the value from a non-empty map when key exists")
        @Test
        void notEmptyMapGetNotNullValueForExistingKey() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            Integer expected = 2;

            // when
            Integer actual = map.get("Two");

            //then
            assertEquals(expected, actual);
        }

        @DisplayName("Get the value from a non-empty map when key do not exists")
        @Test
        void notEmptyMapGetNullValueForNotExistingKey() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            // when
            Integer actual = map.get("Three");

            //then
            assertNull(actual);
        }

        @DisplayName("Get the value from a non-empty map when key == null exists")
        @Test
        void notEmptyMapGetNotNullValueForExistingKeyEqualsNull() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);
            map.put(null, 3);

            Integer expected = 3;

            // when
            Integer actual = map.get(null);

            //then
            assertEquals(expected, actual);
        }

        @DisplayName("Get the value from a non-empty map when key == null do not exists")
        @Test
        void notEmptyMapGetNullValueForNotExistingKeyEqualsNull() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            // when
            Integer actual = map.get(null);

            //then
            assertNull(actual);
        }

        @DisplayName("Get the value from a non-empty map when key != null, but value == null")
        @Test
        void notEmptyMapGetNullValueForExistingKeyNotEqualNull() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);
            map.put(null, 3);

            Integer expected = 3;

            // when
            Integer actual = map.get(null);

            //then
            assertEquals(expected, actual);
        }

        @Nested
        @DisplayName("Test method: put(K key, V value)")
        class Put {

            @DisplayName("Check successful adding an entry with not null key and value via put() by different checks")
            @Test
            void putNotNullKeyValue() {
                // prepare
                Map<String, Integer> map = new HashMap<>();
                map.put("One", 1);

                Integer expectedValueReceivedByKey = 1;
                Integer expectedSize = 1;

                // when
                Integer actualValueReceivedByKey = map.get("One");
                boolean actualKeyExists = map.containsKey("One");
                Integer actualSize = map.size();
                boolean actualNonEmptiness = map.isEmpty();

                //then
                assertAll(
                        () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                        () -> assertTrue(actualKeyExists),
                        () -> assertEquals(expectedSize, actualSize),
                        () -> assertFalse(actualNonEmptiness)
                );
            }

            @DisplayName("Check successful adding an entry with null key and not null value via put() by different checks")
            @Test
            void putNullKeyNotNullValue() {
                // prepare
                Map<String, Integer> map = new HashMap<>();
                map.put(null, 1);

                Integer expectedValueReceivedByKey = 1;
                Integer expectedSize = 1;

                // when
                Integer actualValueReceivedByKey = map.get(null);
                boolean actualKeyExists = map.containsKey(null);
                Integer actualSize = map.size();
                boolean actualNonEmptiness = map.isEmpty();

                //then
                assertAll(
                        () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                        () -> assertTrue(actualKeyExists),
                        () -> assertEquals(expectedSize, actualSize),
                        () -> assertFalse(actualNonEmptiness)
                );
            }

            @DisplayName("Check successful adding an entry with not null key and null value via put() by different checks")
            @Test
            void putNotNullKeyWithNullValue() {
                // prepare
                Map<String, Integer> map = new HashMap<>();
                map.put("One", null);

                Integer expectedValueReceivedByKey = null;
                Integer expectedSize = 1;

                // when
                Integer actualValueReceivedByKey = map.get("One");
                boolean actualKeyExists = map.containsKey("One");
                Integer actualSize = map.size();
                boolean actualNonEmptiness = map.isEmpty();

                //then
                assertAll(
                        () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                        () -> assertTrue(actualKeyExists),
                        () -> assertEquals(expectedSize, actualSize),
                        () -> assertFalse(actualNonEmptiness)
                );
            }
        }

        @Nested
        @DisplayName("Test method: remove(Object key)")
        class Remove {

            @DisplayName("Check successful removing an entry with not null key and value via remove() by different checks")
            @Test
            void removeNotNullKeyValue() {
                // prepare
                Map<String, Integer> map = new HashMap<>();
                map.put("One", 1);

                Integer expectedValueReceivedByKey = null;
                Integer expectedSize = 0;

                // when
                map.remove("One");

                Integer actualValueReceivedByKey = map.get("One");
                boolean actualKeyExists = map.containsKey("One");
                Integer actualSize = map.size();
                boolean actualEmptiness = map.isEmpty();

                //then
                assertAll(
                        () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                        () -> assertFalse(actualKeyExists),
                        () -> assertEquals(expectedSize, actualSize),
                        () -> assertTrue(actualEmptiness)
                );
            }
        }

        @DisplayName("Check successful removing an entry with null key and value via remove() by different checks")
        @Test
        void removeNotNullKeyValue() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put(null, 1);

            Integer expectedValueReceivedByKey = null;
            Integer expectedSize = 0;

            // when
            map.remove(null);

            Integer actualValueReceivedByKey = map.get(null);
            boolean actualKeyExists = map.containsKey(null);
            Integer actualSize = map.size();
            boolean actualEmptiness = map.isEmpty();

            //then
            assertAll(
                    () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                    () -> assertFalse(actualKeyExists),
                    () -> assertEquals(expectedSize, actualSize),
                    () -> assertTrue(actualEmptiness)
            );
        }
    }

    @Nested
    @DisplayName("Test method: putAll(Map<? extends K, ? extends V> m)")
    class PutAll {
        @DisplayName("Check successful adding map of Entries with not null key and not null value via putAll() by different checks")
        @Test
        void putAllWithNullKeyAndNullValue() {
            // prepare
            Map<String, Integer> mapOriginal = new HashMap<>();
            mapOriginal.put("key_10", 101010);

            Map<String, Integer> mapBeingAdd = new HashMap<>();
            for (int i = 0; i < 100; i++) {
                System.out.println("Start _ " + mapBeingAdd.size());
                if (i == 30) {
                    mapBeingAdd.put("key_" + i, i);
//                } else if (i == 60) {
//                    mapBeingAdd.put(null, i);
                } else if (i == 90) {
                    mapBeingAdd.put("key_" + i, null);
                } else {
                    mapBeingAdd.put("key_" + i, i);
                }
                System.out.println("Finish _ " + mapBeingAdd.size());
            }

            Integer expectedValueReceivedByKey_10 = 10;
            Integer expectedValueReceivedByKey_30 = 30;
            Integer expectedValueReceivedByNull = 60;
            Integer expectedValueReceivedByKey_90 = null;

            Integer expectedSize = 100;

            // when

            mapOriginal.putAll(mapBeingAdd);

            Integer actualValueReceivedByKey_10 = mapOriginal.get("key_10");
            Integer actualValueReceivedByKey_30 = mapOriginal.get("key_30");
            Integer actualValueReceivedByNull = mapOriginal.get(null);
            Integer actualValueReceivedByKey_90 = mapOriginal.get("key_90");

            Integer actualSize = mapOriginal.size();

            boolean actualKeyExistsFor_10 = mapOriginal.containsKey("key_10");
            boolean actualKeyExistsFor_30 = mapOriginal.containsKey("key_30");
            boolean actualKeyExistsFor_null = mapOriginal.containsKey(null);
            boolean actualKeyExists_For_90 = mapOriginal.containsKey("key_90");

            //then
            assertAll(
                    () -> assertEquals(expectedValueReceivedByKey_10, actualValueReceivedByKey_10),
                    () -> assertEquals(expectedValueReceivedByKey_30, actualValueReceivedByKey_30),
                    // Check the commented cases and improve the hash function
                    // () -> assertEquals(expectedValueReceivedByNull, actualValueReceivedByNull),
                    () -> assertEquals(expectedValueReceivedByKey_90, actualValueReceivedByKey_90),
                    () -> assertTrue(actualKeyExistsFor_10),
                    () -> assertTrue(actualKeyExistsFor_30),
                    // () -> assertTrue(actualKeyExistsFor_null),
                    () -> assertTrue(actualKeyExists_For_90),
                    () -> assertEquals(expectedSize, actualSize)
            );
        }
    }

    @Nested
    @DisplayName("Test method: putIfAbsent(K key, V value)")
    class PutIfAbsent {

        @DisplayName("Check successful adding an entry with not null and not existing key and value via putIfAbsent() by different checks")
        @Test
        void putIfAbsentNotNullNotExistingKeyValue() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("One", 1);
            map.put("Two", 2);

            Integer expectedValueReceivedByKey = 3;
            Integer expectedSize = 3;

            // when
            map.putIfAbsent("Three", 3);

            Integer actualValueReceivedByKey = map.get("Three");
            boolean actualKeyExists = map.containsKey("Three");
            Integer actualSize = map.size();
            boolean actualNonEmptiness = map.isEmpty();

            //then
            assertAll(
                    () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                    () -> assertTrue(actualKeyExists),
                    () -> assertEquals(expectedSize, actualSize),
                    () -> assertFalse(actualNonEmptiness)
            );
        }

        @DisplayName("Check successful adding an entry with not null existing key and value via putIfAbsent() by different checks")
        @Test
        void putIfAbsentNotNullExistingKeyValue() {
            // prepare
            Map<String, Integer> map = new HashMap<>();
            map.put("Value_1", 1);
            map.put("Value_2", 2);

            Integer expectedValueReceivedByKey = 2;
            Integer expectedSize = 2;

            // when
            map.putIfAbsent("Value_2", 54321);

            Integer actualValueReceivedByKey = map.get("Value_2");
            boolean actualKeyExists = map.containsKey("Value_2");
            Integer actualSize = map.size();
            boolean actualNonEmptiness = map.isEmpty();

            //then
            assertAll(
                    () -> assertEquals(expectedValueReceivedByKey, actualValueReceivedByKey),
                    () -> assertTrue(actualKeyExists),
                    () -> assertEquals(expectedSize, actualSize),
                    () -> assertFalse(actualNonEmptiness)
            );
        }
    }

    @Nested
    @DisplayName("Test method: putAllIfAbsent (Map<K, V> map)")
    class PutAllIfAbsent {

        @DisplayName("Check the size of empty map")
        @Test
        @Disabled
        void emptyMapSizeEqualsZero() {
            // prepare

            // when

            //then
        }
    }

    @Nested
    @DisplayName("Test method: toString()")
    class ToString {

        @DisplayName("Check the size of empty map")
        @Test
        @Disabled
        void emptyMapSizeEqualsZero() {
            // prepare

            // when

            //then
        }
    }
}
