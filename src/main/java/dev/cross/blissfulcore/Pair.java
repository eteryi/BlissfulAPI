package dev.cross.blissfulcore;

public class Pair<K, V> {
    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    private final K first;
    private final V sec;

    private Pair(K first, V second) {
        this.first = first;
        this.sec = second;
    }

    public K first() {
        return this.first;
    }

    public V second() {
        return this.sec;
    }
}
