import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Queue;




public class Graph {
	public Hashtable<String, Node> nodes = new Hashtable<String, Node>();
	private final int COUNTRY_ROAD_SPEED = 50;
	private final int HIGHWAY_SPEED = 80;
	private final int SUBURB_SPEED = 35;
	
	public static void main(String[] args){
		Graph m = new Graph();
		m.listRatings();
	}
	
	public Graph() {
		try{
			BufferedReader mapFile = new BufferedReader(new FileReader("src\\map.txt"));
			
			for(int i=0;i<10;i++) mapFile.readLine();
			
			String line = new String();

			line = mapFile.readLine();
			while(!line.contains(" end ")){
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
				while(!line.equals("X")){
					newNode.addConnection(line);
					line = mapFile.readLine();
				}
				line = mapFile.readLine();
				line = mapFile.readLine();
//				System.out.println(newNode.name);
				nodes.put(newNode.getName(), newNode);
			}
			
			mapFile.close();
		} catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		for(Node current: nodes.values()){

			for(int j=0;j<current.connections.size();j++){
				int distance = distance(current, nodes.get(current.connections.get(j)));
				int time = time(current, nodes.get(current.connections.get(j)), distance);
				current.addEdge(new Edge(current, nodes.get(current.connections.get(j)), distance, time));
			}
		}
		
		System.out.println("beep");
		//insert more delicious code here.
	}

	public ArrayList<Node> listRatings(){
		Comparator sort = new Comparator<Node>(){ public int compare(Node n1, Node n2){
			if(n1.getRating()>n2.getRating()) return -1;
			if(n1.getRating()<n2.getRating()) return 1;
			return 0;
		}};
		
		PriorityQueue<Node> ratings = new PriorityQueue<Node>(1, sort);
		
		for(Node current: nodes.values()) ratings.offer(current);
		
		ArrayList<Node> output = new ArrayList<Node>();
		
		while(!ratings.isEmpty()) output.add(ratings.poll());

		return output;
	}
	
	private int distance(Node firstNode, Node secondNode){
		int x1 = firstNode.x;
		int y1 = firstNode.y;
		int x2 = secondNode.x;
		int y2 = secondNode.y;
		return (int) Math.ceil(Math.sqrt((x1-x2)^2 + (y1-y2)^2));
	}
	
	private int time(Node firstNode, Node secondNode, int distance){
		if(firstNode.cost == 1 && secondNode.cost == 1) return distance*HIGHWAY_SPEED;
		
		if((firstNode.cost == 1 && secondNode.cost == 2) || (firstNode.cost == 2 && secondNode.cost == 1)) return distance*SUBURB_SPEED;
		
		return distance*COUNTRY_ROAD_SPEED;
	}
	
	
	
	
	
	public class Node {
		private String name;
		private double rating;
		public int cost;
		public int x;
		public int y;
		public ArrayList<String> connections = new ArrayList<String>();
		public ArrayList<Edge> edges = new ArrayList<Edge>();
		
		public Node(String name){
			this.name = name;
		}
		
		public int getCost(){
			return this.cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public void setRating(double rating){
			this.rating = rating;
		}
		
		public double getRating(){
			return this.rating;
		}

		public void setX(int x){
			this.x = x;
		}
		
		public void setY(int y){
			this.y = y;
		}

		public void addConnection(String connection) {
			connections.add(connection);
		}
		
		public String getName(){
			return name;
		}
		
		public void addEdge(Edge newEdge){
			edges.add(newEdge);
			return;
		}

	}

	public class Edge {

		private Node start;
		public Node end;
		public int distance;
		private int time;
		
		public Edge(Node start, Node end, int distance, int time){
			this.start = start;
			this.end = end;
			this.distance = distance;
			this.time = time;
		}
		
		public Node getEnd(){
			return this.end;
		}
		
	}
	
	private class MET {
		Node begin;
		ArrayList<Node> travelPlanner; // The nodes the user wishes to visit
										// (multiple)
		ArrayList<Node> shortestPath; // The nodes in which the user should
										// visit
		MyStorage b = new MyStorage();

		MET(Node a, ArrayList<Node> all) { // Constructor used for travel
											// planner
			travelPlanner.addAll(all);
			shortestPath.add(a);
		}

		MET(Node a) { // Normal Constructor used for one path
			shortestPath.add(a);
		}

		public void findShortestPathStart(Node begin, Node end) {
			MyStorage b = new MyStorage();
			Queue<Node> open = new PriorityQueue<Node>();
			Queue<Edge> closed = new PriorityQueue<Edge>();
			open.offer(begin);
			while (!open.isEmpty()) {
				Node x = open.poll(); // Put X on closed
				if (x.equals(end)) {
					b.didSucceed();
					break;
				} else {
					ArrayList<Edge> neighborsCopy = new ArrayList<Edge>(x.edges);
					for (int i = 0; i < neighborsCopy.size(); i++) {
						if (neighborsCopy.get(i) != null && neighborsCopy.get(i).end != null) {
							if (open.contains(neighborsCopy.get(i).end)
									|| closed.contains(neighborsCopy.get(i).end)) {
								// discard children of X if already on open or
								// closed
								neighborsCopy.set(i, null);
								closed.offer(neighborsCopy.get(i));
							}
						}
					}
					// Put remaining children on right end of open
					// First, sort using A* algorithm
					// neighborsCopy.sort(); need our own comparator or something for this
					//    to sort based on neighborsCopy's edges' cost field
				}
			}
		}
	}

	//
	private class MyStorage {

		private boolean success;

		MyStorage() {
			success = false;
		}

		public void didNotSucceed() {
			success = false;
		}

		public void didSucceed() {
			success = true;
		}

		public boolean isSuccessful() {
			return success;
		}

	}
	//
	// void getShortestDistance{
	//
	// }

}
