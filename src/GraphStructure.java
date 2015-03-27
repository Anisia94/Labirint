import java.util.*;

public class GraphStructure {
	public static String Start;
	public static String Final;

	private Map<String, LinkedHashSet<String>> map = new HashMap();

	public void addEdge(String node1, String node2) {
		LinkedHashSet<String> adjacent = map.get(node1);
		if (adjacent == null) {
			adjacent = new LinkedHashSet();
			map.put(node1, adjacent);
		}
		adjacent.add(node2);
	}

	public void addTwoWayVertex(String node1, String node2) {
		addEdge(node1, node2);
		addEdge(node2, node1);
	}

	public boolean isConnected(String node1, String node2) {
		Set adjacent = map.get(node1);
		if (adjacent == null) {
			return false;
		}
		return adjacent.contains(node2);
	}

	public LinkedList<String> adjacentNodes(String last) {
		LinkedHashSet<String> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList();
		}
		return new LinkedList<String>(adjacent);
	}

	public static GraphStructure genGraph(Integer[][] maze) {
		GraphStructure graphStructure = new GraphStructure();

		for (Integer i = 0; i < maze.length; i++) {
			for (Integer j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 2) {
					Final = j.toString() + "," + i.toString();
				}
				if (maze[i][j] == -1) {
					Start = j.toString() + "," + i.toString();
				}
				try {
					if (maze[i - 1][j] != 1) {
						graphStructure.addEdge(j.toString() + "," + i.toString(), j.toString() + "," + Integer.toString(i - 1));

					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					if (maze[i + 1][j] == -1 || maze[i + 1][j] == 0 || maze[i + 1][j] == 2) {
						graphStructure.addEdge(j.toString() + "," + i.toString(), j.toString() + "," + Integer.toString(i + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					if (maze[i][j - 1] == -1 || maze[i][j - 1] == 0 || maze[i][j - 1] == 2) {
						graphStructure.addEdge(j.toString() + "," + i.toString(), Integer.toString(j - 1) + "," + i.toString());

					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					if (maze[i][j + 1] == -1 || maze[i][j + 1] == 0 || maze[i][j + 1] == 2) {
						graphStructure.addEdge(j.toString() + "," + i.toString(), Integer.toString(j + 1) + "," + i.toString());
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}

			}
		}
		return graphStructure;
	}

}
