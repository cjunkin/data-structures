package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int bucketNum;
        int size = oomages.size();
        int[] buckets = new int[M];
        for (Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;
        }
        for (int i = 0; i < M; i++) {
            if ((buckets[i] < size / 50) || (buckets[i] > size / 2.5)) {
                return false;
            }
        }
        return true;
    }
}
