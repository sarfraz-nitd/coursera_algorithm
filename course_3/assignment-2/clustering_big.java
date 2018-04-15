import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
import java.util.BitSet;  
import java.util.HashMap;  
import java.util.Map.Entry;  
  
 
public class clustering_big {      
 
    //point and its leader  
    static HashMap<BitSet, BitSet> clusters = new HashMap<BitSet, BitSet>();  
    static int n;  
    static int numBits;  
  
    public static BitSet getBitSet(String str){  
        String str2[] = str.split(" ");  
        BitSet b = new BitSet(numBits);  
        int j = numBits-1;  
        b.clear();  
        for (int i = 0; i < str2.length; i++){  
            if (Integer.parseInt(str2[i]) == 1){  
                b.flip(j);  
            }  
            j--;  
        }  
        return b;  
    }  
  
    public static BitSet find(BitSet b){  
        while (!b.equals(clusters.get(b))){  
            //b = (BitSet) clusters.get(b).clone();  
            b = clusters.get(b);  
        }  
        return b;  
    }  
  
    public static void union (BitSet a, BitSet b){  
        //actually smaller cluster should be merged with bigger one. Here do it randomly. Cluster sizes should be maintained for  
        //it to work.  
        BitSet pa = find(a);  
        BitSet pb = find(b);  
        if (!pa.equals(pb)){  
            clusters.put(pa, pb);  
        }  
    }  
  
    public static ArrayList<BitSet> getMembers(BitSet s){  
        BitSet sbackup = (BitSet) s.clone();  
        ArrayList<BitSet> ret = new ArrayList<BitSet>();  
        for(int i = 0; i <= numBits-1; i++){  
            BitSet s1 = new BitSet();  
            s1.clear();  
            s1 = (BitSet) sbackup.clone();  
            s1.flip(i);  
            if (clusters.containsKey(s1)){  
                ret.add(s1);  
            }  
        }  
        //now flip 2 bits to create distance of 2  
        for(int i = 0; i <= numBits-1; i++){  
            BitSet s1 = new BitSet();  
            s1.clear();  
            s1 = (BitSet) sbackup.clone();  
            s1.flip(i);  
            for (int j = i+1; j<=numBits-1; j++){  
                BitSet s2 = new BitSet();  
                s2 = (BitSet) s1.clone();  
                s2.flip(j);  
                if (clusters.containsKey(s2)) ret.add(s2);  
            }  
        }  
        return ret;  
    }  
  
    public static void main(String[] args) {  
        //int distance = 2; //must be <= 2  
        try {  
            FileInputStream f = new FileInputStream("clustering2.txt");  
            DataInputStream d = new DataInputStream(f);  
            BufferedReader br = new BufferedReader(new InputStreamReader(d));  
            String str = br.readLine();  
            n = Integer.parseInt(str.split(" ")[0]);  
            numBits = Integer.parseInt(str.split(" ")[1]);  
            int count2 = 0;  
            while((str = br.readLine())!= null){  
                BitSet b = getBitSet(str);  
//              if (clusters.containsKey(b)) {  
//                  System.out.println(" a duplicate found " + b.toString());  
//              }  
                clusters.put(b, b);  
                //count2++;  
            }  
            //System.out.println( count2 + " entries are read ");  
            //System.out.println(" number of entries in DHT " + clusters.size());  
  
            for (BitSet s : clusters.keySet()){  
                //for all at distance of 1 or 2 from s  
                ArrayList<BitSet> members = getMembers(s);  
                //System.out.println(" members sizes "+members.size());  
                if (members.size() == 0) count2++;  
  
                for (BitSet m : members){  
                    union(s,m);  
                }  
            }  
            System.out.println(" number of points with zero neighbours with <=2 distance "+count2);   
            int count = 0;  
            //parent of a parent is itself..each cluster has a single parent.   
            for(Entry<BitSet, BitSet> e : clusters.entrySet()){  
                if (e.getKey().equals(e.getValue())){  
                    count++;  
                }  
            }  
            System.out.println(" num clusters " + count);  
  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}

//6118
