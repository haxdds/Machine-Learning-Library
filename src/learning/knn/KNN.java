package learning.knn;


import java.util.*;
import java.util.stream.Collectors;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by Lagrange on 6/24/2017.
 */
public class KNN {
    private HashMap<String, float[][]> dictionary;
    private List<float[]> nn;
    private float[] predict;

    public KNN(HashMap<String, float[][]> dictionary){
        this.dictionary = dictionary;
    }


    public String predict(float[] predict, int k){
        this.predict = predict;
        if(dictionary.keySet().size() >= k || k % 2 == 0) throw new IllegalArgumentException("K must be bigger than number of groups");
        HashMap<Float, float[]> map = new HashMap<>();
        List<Float> distances = new ArrayList<>();
        for(String s: dictionary.keySet()){
            for(float[] f: dictionary.get(s)){
                float distance = distance(f, predict);
                map.put(distance, f);
                distances.add(distance);
            }
        }
        Collections.sort(distances);
        float[][] nearestNeighbors = new float[k][predict.length];
        for(int i = 0; i < k; i++){
            nearestNeighbors[i] = map.get(distances.get(i));
        }
        HashMap<String, Integer> votes = new HashMap<>();
        for(String s: dictionary.keySet()) {
            votes.put(s, 0);
        }
        for(float[] f: nearestNeighbors){
            votes.put(getKey(f), votes.get(getKey(f)) + 1);
        }
        nn = Arrays.asList(nearestNeighbors);
        return Collections.max(votes.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public HashMap<String, float[][]> getDictionary() {
        return dictionary;
    }

    private float distance(float[] a, float[] b){
        if(a.length  != b.length) throw new IllegalArgumentException("Arrays must be same length");
        float sum = 0;
        for(int k = 0; k < a.length; k++){
            sum += Math.pow((a[k]-b[k]),2);
        }
        return (float)Math.sqrt(sum);
    }

    public List<float[]> getNn() {
        return nn;
    }

    private String getKey(float[] value){
        String key = null;
        for(String k: dictionary.keySet()){
            for(float[] f: dictionary.get(k)){
                  if(equals(f,value))
                      return k;
            }
        }
       return key;
    }

    private boolean equals(float[] a, float[] b){
        if(a.length  != b.length) throw new IllegalArgumentException("Arrays must be same length");
        for(int k = 0; k < a.length; k++)
            if(a[k] != b[k]) return false;
        return true;
    }


    public float[] getPredict() {
        return predict;
    }
}
