import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class prims {
	static int[][] graph;
	static int numNodes;
	static int[] tree;//it has just edges tree[i] contains j if (i,j) is in tree. This array is NOT symmetric. init to -1
	/*
	 * implements slow O(mn) algo default one
	 */
	public static void primsSlow(){
		int weight = 0;
		boolean[] status = new boolean[numNodes];
		for (boolean i : status)
			i = false;
		status[0] = true;

		int minw = Integer.MAX_VALUE;
		int mini =0 ;
		int minj = 0;
		while(!isComplete(status)){
			for(int i =0;i<numNodes;i++){
				if (status[i]) continue;
				//find in graph an edge with (i,j) where j=true and trace minimum
				for(int j =0;j<numNodes;j++){
					if (!status[j]) continue;
					if (minw > graph[i][j]){
						minw = graph[i][j];
						mini=i;//new node
						minj=j;//old node
					}					
				}
			}
			status[mini] = true;
			tree[minj] = mini;
			weight += graph[mini][minj];
			minw = Integer.MAX_VALUE;
		}
		System.out.println("weight "+weight);  //-3612829

	}

	public static boolean isComplete(boolean[] status){
		for (boolean b : status){
			if (!b) return false;
		}
		return true;

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream f = new FileInputStream("edges.txt");
			DataInputStream d = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(f));
			numNodes = Integer.parseInt(b.readLine().split(" ")[0]);
			graph = new int[numNodes][numNodes];
			for (int i =0;i<numNodes;i++)
				for(int j=0; j < numNodes; j++)
					graph[i][j] =Integer.MAX_VALUE;
			String str;
			while((str=b.readLine())!=null){
				int i = Integer.parseInt(str.split(" ")[0])-1;
				int j = Integer.parseInt(str.split(" ")[1])-1;
				graph[i][j] = Integer.parseInt(str.split(" ")[2]);
				graph[j][i] = Integer.parseInt(str.split(" ")[2]);
			}
			tree = new int[numNodes];
			for (int i : tree)
				i=-1;
			primsSlow();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}





