import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
	public Hashtable<String, Node> nodes = new Hashtable<String, Node>();
	private final int COUNTRY_ROAD_SPEED = 50;
	private final int HIGHWAY_SPEED = 80;
	private final int SUBURB_SPEED = 35;

	public static void main(String[] args) {
		Graph m = new Graph();
		m.listRatings();
	}

	public Graph() {
		try {
			BufferedReader mapFile = new BufferedReader(new FileReader("src\\map.txt"));

			for (int i = 0; i < 10; i++)
				mapFile.readLine();

			String line = new String();

			line = mapFile.readLine();
			while (!line.contains(" end ")) {
				Node newNode = new Node(line);
				line = mapFile.readLine();
				newNode.setRating(Double.parseDouble(line));
				line = mapFile.readLine();
				newNode.setCost(Integer.parseInt(line));
				line = mapFile.readLine();
				newNode.setX(Integer.parseInt(line));
				line = mapFile.readLine();
				newNode.setY(Integer.parseInt(line));

				line = mapFile.readLine();
				while (!line.equals("X")) {
					newNode.addConnection(line);
					line = mapFile.readLine();
				}
				line = mapFile.readLine();
				line = mapFile.readLine();
				// System.out.println(newNode.name);
				nodes.put(newNode.getName(), newNode);
			}

			mapFile.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		for (Node current : nodes.values()) {

			for (int j = 0; j < current.connections.size(); j++) {
				int distance = distance(current, nodes.get(current.connections.get(j)));
				int time = time(current, nodes.get(current.connections.get(j)), distance);
				current.addEdge(new Edge(current, nodes.get(current.connections.get(j)), distance, time));
			}
		}

		System.out.println("beep");
		// insert more delicious code here.
	}

	public ArrayList<Node> listRatings() {
		Comparator sort = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.getRating() > n2.getRating())
					return -1;
				if (n1.getRating() < n2.getRating())
					return 1;
				return 0;
			}
		};

		PriorityQueue<Node> ratings = new PriorityQueue<Node>(1, sort);

		for (Node current : nodes.values())
			ratings.offer(current);

		ArrayList<Node> output = new ArrayList<Node>();

		while (!ratings.isEmpty())
			output.add(ratings.poll());

		return output;
	}

	private int distance(Node firstNode, Node secondNode) {
		int x1 = firstNode.x;
		int y1 = firstNode.y;
		int x2 = secondNode.x;
		int y2 = secondNode.y;
		return (int) Math.ceil(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
	}

	private int time(Node firstNode, Node secondNode, int distance) {
		if (firstNode.cost == 1 && secondNode.cost == 1){
			return (int) distance / HIGHWAY_SPEED;
		}

		if ((firstNode.cost == 1 && secondNode.cost == 2) || (firstNode.cost == 2 && secondNode.cost == 1)){
			return (int) distance / SUBURB_SPEED;
		}

		return (int) distance / COUNTRY_ROAD_SPEED;
	}

	public class Node {
		private String name;
		private double rating;
		public Node last;
		public int cost;
		public int dist;
		public int x;
		public int y;
		public ArrayList<String> connections = new ArrayList<String>();
		public ArrayList<Edge> edges = new ArrayList<Edge>();
		private int tripDistLeftOver = Integer.MAX_VALUE;
		private int tripTimeLeftOver = Integer.MAX_VALUE;

		public Node(String name) {
			this.dist = Integer.MAX_VALUE;
			this.name = name;
			last = null;
		}

		public int getCost() {
			return this.cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}

		public double getRating() {
			return this.rating;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void addConnection(String connection) {
			connections.add(connection);
		}

		public String getName() {
			return name;
		}

		public void addEdge(Edge newEdge) {
			edges.add(newEdge);
			return;
		}

		public ArrayList<Node> distTrip(int remainingDistance) {
			this.tripDistLeftOver = remainingDistance;
			ArrayList<Node> currentBest = new ArrayList<Node>();
			for(Edge current : edges){
				if(current.distance<remainingDistance){
					ArrayList<Node> temp = current.end.distTrip(remainingDistance-current.distance);         
					if(current.end.tripDistLeftOver<this.tripDistLeftOver){
						this.tripDistLeftOver = current.end.tripDistLeftOver;
						currentBest = temp;
					}
				}
			}
			
			return currentBest;
		}
		
		public ArrayList<Node> timeTrip(int remainingTime) {
			this.tripTimeLeftOver = remainingTime;
			ArrayList<Node> currentBest = new ArrayList<Node>();
			for(Edge current : edges){
				if(current.distance<remainingTime){
					ArrayList<Node> temp = current.end.timeTrip(remainingTime-current.distance);         
					if(current.end.tripTimeLeftOver<this.tripTimeLeftOver){
						this.tripTimeLeftOver = current.end.tripTimeLeftOver;
						currentBest = temp;
					}
				}
			}
			
			return currentBest;
		}

	}

	public class Edge {

		public Node start;
		public Node end;
		public int distance;
		public int time;

		public Edge(Node start, Node end, int distance, int time) {
			this.start = start;
			this.end = end;
			this.distance = distance;
			this.time = time;
		}

		public Node getEnd() {
			return this.end;
		}

	}

	/**
	 * Djikstra's Algorithm for finding the shorting path.
	 * 
	 * @param graph
	 * @param starting
	 * @param ending
	 * @return
	 */
	public ArrayList<Node> shortestPath(Node starting, Node ending) {
		starting.dist = 0;
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(starting);
		int tempCost = 0;
		Edge tempEdge = null;
		Node tempNode = null;
		ArrayList<Node> a = new ArrayList<Node>();
		while (!nodes.isEmpty()) {
			Node cur = nodes.remove();
			for (int i = 0; i < cur.edges.size(); i++) {
				tempEdge = cur.edges.get(i);
				tempNode = tempEdge.end;
				tempCost = tempEdge.distance;
				int comparableDist = cur.dist + tempCost;
				if (comparableDist < tempNode.dist) {
					nodes.remove(tempNode);
					tempNode.dist = comparableDist;
					tempNode.last = cur;
					nodes.add(tempNode);
				}
			}
			cur = ending;
			while(cur.last!=null){
//				System.out.println(cur.name);
				a.add(cur);
				cur = cur.last;
			}
		}
		return a;
	}
	
	public ArrayList<Node> quickestPath(Node starting, Node ending) {
		starting.dist = 0;
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(starting);
		int tempCost = 0;
		Edge tempEdge = null;
		Node tempNode = null;
		ArrayList<Node> a = new ArrayList<Node>();
		while (!nodes.isEmpty()) {
			Node cur = nodes.remove();
			for (int i = 0; i < cur.edges.size(); i++) {
				tempEdge = cur.edges.get(i);
				tempNode = tempEdge.end;
				tempCost = tempEdge.time;
				int comparableDist = cur.dist + tempCost;
				if (comparableDist < tempNode.dist) {
					nodes.remove(tempNode);
					tempNode.dist = comparableDist;
					tempNode.last = cur;
					nodes.add(tempNode);
				}
			}
			cur = ending;
			while(cur.last!=null){
//				System.out.println(cur.name);
				a.add(cur);
				cur = cur.last;
			}
		}
		return a;
	}
	
	public int findDistance(ArrayList<Node> input){
		int output = 0;
		
		for(int i=0; i<input.size() - 1; i++){
			for(Edge currentEdge: input.get(i).edges){
				if(currentEdge.end.equals(input.get(i+1))){
					output = output + currentEdge.distance;
				}
			}
		}
		return output;
	}
	
	public int findTime(ArrayList<Node> input){
		int output = 0;
		
		for(int i=0; i<input.size() - 1; i++){
			for(Edge currentEdge: input.get(i).edges){
				if(currentEdge.end.equals(input.get(i+1))){
					output = output + currentEdge.time;
				}
			}
		}
		return output;
	}
	
	public ArrayList<Node> distanceTripCreator(int distance, String start){
		ArrayList<Node> output = new ArrayList<Node>();
		
		output = nodes.get(start).distTrip(distance);
		int length = output.size();
		for(int i = length - 2; i>=0; i--){
			output.add(output.get(i));
		}
		return output;
		
	}
	public ArrayList<Node> timeTripCreator(int time, String start){
		ArrayList<Node> output = new ArrayList<Node>();
		
		output = nodes.get(start).timeTrip(time);
		int length = output.size();
		for(int i = length - 2; i>=0; i--){
			output.add(output.get(i));
		}
		return output;
		
	}
}
