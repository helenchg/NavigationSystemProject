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
	private final int COUNTRY_ROAD_SPEED = 1;
	private final int HIGHWAY_SPEED = 2;
	private final int SUBURB_SPEED = 1;

	public static void main(String[] args) {
		Graph m = new Graph();
		m.listRatings();
	}

	
	/**
	 * 
	 * This constructor reads from map.txt and creates our Graph data structure.  Nodes are created first and stored in
	 * a HashTable.  The Edges are added in afterwards.
	 *
	 */
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

	/**
	 * 
	 * This method uses a Priority Queue and a custom comparator to order all destinations in order of their 1-5 rating.
	 *
	 * @return - ArrayList of destinations ranked from order of their 1-5 rating
	 */
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

	
	/**
	 * 
	 * Returns the straight line distance between two nodes using the Pythagorean Theorem.  This is used to create
	 * the distance cost in each edge.
	 *
	 * @param firstNode - first node to be considered
	 * @param secondNode - second node to be considered
	 * @return - distance in miles between nodes
	 */
	private int distance(Node firstNode, Node secondNode) {
		int x1 = firstNode.x;
		int y1 = firstNode.y;
		int x2 = secondNode.x;
		int y2 = secondNode.y;
		return (int) Math.ceil(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
	}

	/**
	 *
	 * Returns the time needed to travel the straight line distance between two nodes.  This is used to create the
	 * time cost in each edge.
	 *
	 * @param firstNode - first node to be considered
	 * @param secondNode - second node to be considered
	 * @param distance - distance between these nodes, found in a separate method
	 * @return - time needed to travel between these nodes in minutes
	 */
	private int time(Node firstNode, Node secondNode, int distance) {
		if (firstNode.cost == 1 && secondNode.cost == 1){
			return (int) distance / HIGHWAY_SPEED;
		}

		if ((firstNode.cost == 1 && secondNode.cost == 2) || (firstNode.cost == 2 && secondNode.cost == 1)){
			return (int) distance / SUBURB_SPEED;
		}

		return (int) distance / COUNTRY_ROAD_SPEED;
	}

	/**
	 * 
	 * These nodes represent each destination in our data structure.
	 *
	 * @author MET
	 */
	public class Node {
		private String name;
		private double rating;
		public Node last = null;
		public int cost;
		public int dist = Integer.MAX_VALUE;;
		public int x;
		public int y;
		public ArrayList<String> connections = new ArrayList<String>();
		public ArrayList<Edge> edges = new ArrayList<Edge>();
		private int tripDistLeftOver = Integer.MAX_VALUE;
		private int tripTimeLeftOver = Integer.MAX_VALUE;

		/**
		 * 
		 * This constructor creates our node and stores it's name.
		 *
		 * @param name - name of this destination
		 */
		public Node(String name) {
			this.name = name;
		}

		/**
		 * 
		 * This is used by the Graph constructor to indicate whether this destination is a point of interest or a city.
		 *
		 * @return - 1 if this is a city, 2 otherwise
		 */
		public void setCost(int cost) {
			this.cost = cost;
		}

		/**
		 *
		 * This is used by the Graph constructor to set this destination's 1 through 5 rating.
		 *
		 * @param rating - the 1-5 rating of this destination 
		 */
		public void setRating(double rating) {
			this.rating = rating;
		}

		/**
		 *
		 * This method returns the 1-5 rating of this destination.
		 *
		 * @return - the 1-5 rating of this destination
		 */
		public double getRating() {
			return this.rating;
		}

		/**
		 * 
		 * This is used by the Graph constructor to set this destination's x coordinate.
		 *
		 * @param x - the X location in Nevada of this destination
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * 
		 * This is used by the Graph constructor to set this destination's y coordinate.
		 *
		 * @param y - the Y location in Nevada of this destination
		 */
		public void setY(int y) {
			this.y = y;
		}

		/**
		 * 
		 * This is used by the Graph constructor to add the name of a destination that this node will be 
		 * connected to later by an Edge
		 *
		 * @param connection - String name of another node
		 */
		public void addConnection(String connection) {
			connections.add(connection);
		}

		/**
		 * 
		 * Returns the name of the destination that this node represents.
		 *
		 * @return - This node's name
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * Add an instance of Edge, representing a road between cities, to this city.
		 *
		 * @param newEdge - Edge to be added.
		 */
		public void addEdge(Edge newEdge) {
			edges.add(newEdge);
			return;
		}

		/**
		 * 
		 * This method returns an ArrayList of nodes used by the trip finder to suggest a trip to the user. This
		 * method uses distance as its cost.
		 *
		 * @param remainingDistance - distance left in the trip
		 * @return - a list of nodes to be suggested to the user for a trip
		 */
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
			currentBest.add(this);
			
			return currentBest;
		}
		
		/**
		 * 
		 * This method returns an ArrayList of nodes used by the trip finder to suggest a trip to the user. This
		 * method uses time as its cost.
		 *
		 * @param remainingTime - time left in the trip
		 * @return - a list of nodes to be suggested to the user for a trip
		 */
		public ArrayList<Node> timeTrip(int remainingTime) {
			this.tripTimeLeftOver = remainingTime;
			ArrayList<Node> currentBest = new ArrayList<Node>();
			for(Edge current : edges){
				if(current.distance<remainingTime){
					ArrayList<Node> temp = current.end.timeTrip(remainingTime-current.time);         
					if(current.end.tripTimeLeftOver<this.tripTimeLeftOver){
						this.tripTimeLeftOver = current.end.tripTimeLeftOver;
						currentBest = temp;
					}
				}
			}
			currentBest.add(this);
			
			return currentBest;
		}

	}

	/**
	 * 
	 * This class establishes connections between nodes representing roads between real life destinations. It is
	 * important to note that for two connected nodes A and B, there is an edge going from A to B, and a second 
	 * going from B to A.
	 *
	 * @author MET
	 */
	public class Edge {

		public Node start;
		public Node end;
		public int distance;
		public int time;

		/**
		 * 
		 * This constructor is used by the Graph constructor to create each node's edges.
		 *
		 * @param start - starting Node
		 * @param end - ending Node
		 * @param distance - length of road in miles
		 * @param time - time required to traverse this road
		 */
		public Edge(Node start, Node end, int distance, int time) {
			this.start = start;
			this.end = end;
			this.distance = distance;
			this.time = time;
		}

	}

	/**
	 * 
	 * Djikstra's Algorithm for finding the shortest path.
	 * 
	 * @param starting - starting node
	 * @param ending - final destination
	 * @return - ArrayList of nodes describing the order to cities to visit.
	 */
	public ArrayList<Node> shortestPath(Node starting, Node ending) {
        starting.dist = 0;
        if(starting.connections.contains(ending.name)){
               ArrayList<Node> arrIm = new ArrayList<Node>();
               arrIm.add(starting);
               arrIm.add(ending);
               return arrIm;
        }
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
//                    System.out.println(cur.name);
                      a.add(cur);
                      cur = cur.last;
               }
               a.add(starting);
        }
        return a;
       	}
  
		/**
		 * 
		 * Djikstra's Algorithm for finding the quickest path.
		 * 
		 * @param starting - starting node
		 * @param ending - final destination
		 * @return - ArrayList of nodes describing the order to cities to visit.
		 */
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
	                      a.add(cur);
	                      cur = cur.last;
	               }
	               a.add(starting);
	        }
	        return a;
	  }

	/**
	 * 
	 * Given an arrayList of nodes representing a trip, returns the distance in miles that this trip contains.
	 *
	 * @param input - ArrayList of nodes representing a path.
	 * @return - trip distance in miles
	 */
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
	
	/**
	 * 
	 * Given an arrayList of nodes representing a trip, returns the time in minutes that this trip contains.
	 *
	 * @param input - ArrayList of nodes representing a path.
	 * @return - trip time in minutes
	 */
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
	
	/**
	 * 
	 * Given a starting location and a distance allotment, this method will return a suggested out and back trip.
	 *
	 * @param distance - maximum trip distance
	 * @param start - starting location
	 * @return - suggested trip
	 */
	public ArrayList<Node> distanceTripCreator(int distance, String start){
		distance = distance/2;
		ArrayList<Node> output = new ArrayList<Node>();
		ArrayList<Node> returned = new ArrayList<Node>();
		
		returned = nodes.get(start).distTrip(distance);
		int length = returned.size();
		for(int i = length - 1; i>=0;i--){
			output.add(returned.get(i));
		}
		for(int i = length - 2; i>=0; i--){
			output.add(output.get(i));
		}
		return output;
		
	}
	
	/**
	 * 
	 * Given a starting location and a time allotment, this method will return a suggested out and back trip.
	 *
	 * @param time - maximum trip time
	 * @param start - starting location
	 * @return - suggested trip
	 */
	public ArrayList<Node> timeTripCreator(int time, String start){
		time = time/2;
		ArrayList<Node> output = new ArrayList<Node>();
		ArrayList<Node> returned = new ArrayList<Node>();
		
		returned = nodes.get(start).timeTrip(time);
		int length = returned.size();
		for(int i = length - 1; i>=0;i--){
			output.add(returned.get(i));
		}
		for(int i = length - 2; i>=0; i--){
			output.add(output.get(i));
		}
		return output;
		
	}
}
