import java.util.*;

public class BFSImplementation {
	static public ArrayList<ArrayList<Locatie>> allPath;
	public static int count;
	public static Hashtable hash;
	static public ArrayList<Locatie> Npath;
	public class CustomComparator implements Comparator<ArrayList<Locatie>> {
		@Override
		public int compare(ArrayList<Locatie> o1, ArrayList<Locatie> o2) {
			return o1.size()<o2.size()?-1:1;
		}
	}
	public BFSImplementation() {
		allPath = new ArrayList<ArrayList<Locatie>>();
		hash = new Hashtable();
		count = 0;
	}
	public static void breadthFirst(GraphStructure graph, LinkedList<String> visited) {
		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());

		for (String node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(GraphStructure.Final)) {
				count++;
				visited.add(node);
				printPath(count, visited);
				visited.removeLast();
				break;
			}
		}
		// in breadth-first, recursion needs to come after visiting adjacent nodes
		for (String node : nodes) {
			if (visited.contains(node) || node.equals(GraphStructure.Final)) {
				continue;
			}
			visited.addLast(node);
			breadthFirst(graph, visited);
			visited.removeLast();
		}
	}

	public static void printPath(int count, LinkedList<String> visited) {
		String temp = "";
		Locatie locatie;
		Npath = new ArrayList<Locatie>();
		for (String node : visited) {
			locatie = new Locatie(Integer.parseInt(node.split(",")[0]), Integer.parseInt(node.split(",")[1]));
			Npath.add(locatie);
		}
		allPath.add(Npath);
		hash.put(count, temp);
		//System.out.println("exp = " + hash.toString());
	}

}