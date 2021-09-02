package graphs;

import map.Map;
import map.ProbeHashMap;
import positional_list.*;
import util.Position;

/**
 * Implements a Graph ADT using an Adjacency Map
 */
public class AdjacencyMapGraph<V,E> implements Graph<V,E>{
	
	//-------------------private InnerVertex and InnerEdge classes--------------------------
	
	private class InnerVertex<V> implements Vertex<V>{
		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		/**
		 * Constructs a new InnerVertex 
		 */
		public InnerVertex(V elem, boolean graphIsDirected) {
			element = elem;
			outgoing = new ProbeHashMap<>();
			if (graphIsDirected)
				incoming = new ProbeHashMap<>();
			else
				incoming = outgoing;
		}
		/**@return the element associated with the Vertex*/
		public V getElement() { return element; };
		/**Stores the position of the Vertex within the Graph's vertex list*/
		public void setPosition(Position<Vertex<V>> position) { pos = position; }
		/**@return the position of the Vertex within the Graph's vertex list*/
		public Position<Vertex<V>> getPosition() { return pos; }
		/**@return the Map<Vertex,Edge> of outgoing Edges*/
		public Map<Vertex<V>,Edge<E>> getOutgoing() { return outgoing; }
		/**@return a Map<Vertex,Edge> of incoming Edges*/
		public Map<Vertex<V>,Edge<E>> getIncoming() { return incoming; }
		/**@returns true if this Edge instance belongs to the given graph, false otherwise*/
		public boolean validate(Graph<V,E> graph) {
			return AdjacencyMapGraph.this == graph && pos != null;
		}
	}
	
	private class InnerEdge<E> implements Edge<E>{
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;
		/**
		 * Constructs a new InnerEdge from u to v
		 * @param elem the element stored at the Edge
		 */
		@SuppressWarnings("unchecked")
		public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[] {u, v};
		}
		/**@return the element associated with the Edge*/
		public E getElement() { return element; };
		/**@return reference to the endpoint array*/
		public Vertex<V>[] getEndpoints() { return endpoints; }
		/**Stores the position of the Vertex within the Graph's vertex list*/
		public void setPosition(Position<Edge<E>> p) { pos = p; }
		/**@return the position of the Vertex within the Graph's vertex list*/
		public Position<Edge<E>> getPosition() { return pos; }
		/**@returns true if this Edge instance belongs to the given graph, false otherwise*/
		public boolean validate(Graph<V,E> graph) {
			return AdjacencyMapGraph.this == graph && pos != null;
		}
	}
	
	//--------------------------------------------------------------------------------------
	
	
	private boolean isDirected;
	private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
	private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
	
	/** Constructs an empty Graph - either directed or undirected*/
	public AdjacencyMapGraph(boolean directed) { isDirected = directed; }
	
	
	/** @return the number of vertices in the Graph	 */
	public int numVertices() { return vertices.size(); };
	
	/** @return the number of edges in the Graph */
	public int numEdges() { return edges.size(); };
	
	/** @return an iterable of all vertices in the graph */
	@SuppressWarnings("unchecked")
	public Iterable<Vertex<V>> vertices() { return (Iterable<Vertex<V>>) vertices; }
	
	/** @return an iterable of all edges in the Graph*/
	@SuppressWarnings("unchecked")
	public Iterable<Edge<E>> edges() { return (Iterable<Edge<E>>) edges; }
	
	/**
	 * Get the Edge that connects Vertex u to Vertex v
	 * @param u - a Vertex
	 * @param v - a Vertex
	 * @return the Edge between u and v, or null if u and v are not adjacent
	 * @throws IllegalArgumentException if either u or v are not valid Vertices
	 */
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException{
		InnerVertex<V> vertex = validate(v);
		return vertex.getOutgoing().get(u);
	}
	
	/**
	 * Get the Vertices on each end of Edge e
	 * @return an array containing the two end point vertices for Edge e
	 * @throws IllegalArgumentException if e is not a valid Edge
	 */
	public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException{
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}
	
	/**
	 * Get the Vertex opposite Vertex v on Edge e 
	 * @param v - a Vertex
	 * @param e - an Edge
	 * @return the vertex opposite vertex v incident to edge e
	 * @throws IllegalArgumentException if v is not a valid Vertex or e is not a valid Edge
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		InnerVertex<V> vertex = validate(v);
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if (endpoints[0] == vertex)
			return endpoints[1];
		else if (endpoints[1] == vertex)
			return endpoints[0];
		else throw new IllegalArgumentException("v is not a valid vertex");
	}
	
	/**
	 * Get the number of outgoing edges for which Vertex v is origin
	 * @param v - a Vertex
	 * @return the number of outgoing edges from vertex v
	 * @throws IllegalArgumentException if v is not a valid Vertex
	 */
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vertex = validate(v);
		return vertex.getOutgoing().size();
	}
	
	/**
	 * Get the number of edges for which Vertex v is destination
	 * @param v - a Vertex
	 * @return the number of incoming edges for Vertex v
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vertex = validate(v);
		return vertex.getIncoming().size();
	}
	
	/**
	 * Get the outgoing edges for which Vertex v is origin
	 * @param v - a Vertex
	 * @return an iterable of the outgoing edges from vertex v
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vertex = validate(v);
		return vertex.getOutgoing().values();
	}
	
	/**
	 * Get the number of edges for which Vertex v is destination
	 * @param v - a Vertex
	 * @return an iterable of the incoming edges for vertex v
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	public Iterable<Edge<E>> inComingEdges(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vertex = validate(v);
		return vertex.getIncoming().values();
	}
	
	/**
	 * Insert a new Vertex with element x in the Graph
	 * @param element - the element
	 * @return the new Vertex
	 */
	public Vertex<V> insertVertex(V element) {
		InnerVertex<V> v = new InnerVertex<>(element, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}
	
	/**
	 * Inserts a new Edge between two Vertices
	 * @param u - a Vertex
	 * @param v - a Vertex
	 * @param element - the value stored at at the edge
	 * @return the new edge
	 * @throws IllegalArgumentException if either u or v is not a valid Vertex
	 */
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
		if (getEdge(u, v) == null) {
			InnerVertex<V> origin = validate(u);
			InnerVertex<V> destination = validate(v);
			InnerEdge<E> e = new InnerEdge<>(origin, destination, element);
			e.setPosition(edges.addLast(e));
			origin.getOutgoing().put(destination, e);
			destination.getIncoming().put(origin, e);
			return e;
		} else
			throw new IllegalArgumentException("Edge from u to v already exists");
	}
	
	/**
	 * Removes a Vertex from the Graph
	 * @param e a Vertex in the Graph
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vertex = validate(v);
		for (Edge<E> e: vertex.getOutgoing().values())
			removeEdge(e);
		for (Edge<E> e: vertex.getIncoming().values())
			removeEdge(e);
		vertices.remove(vertex.getPosition());
	}
		
	/**
	 * Removes an Edge from the Graph
	 * @param e - an Edge in the Graph
	 * @throws IllegalArgumentException if e is not a valid Edge
	 */
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = endVertices(e);
		InnerVertex<V> origin = validate(endpoints[0]);
		InnerVertex<V> destination = validate(endpoints[1]);
		origin.getOutgoing().remove(destination);
		destination.getIncoming().remove(origin);
		edges.remove(edge.getPosition());
	}
	
	
	//--------------------------Utility Methods--------------------------------

	
	/**
	 * Check whether v is a valid Vertex
	 * @param v - a Vertex
	 * @return an InnerVertex
	 * @throws IllegalArgumentException if v is not a valid Vertex
	 */
	private InnerVertex<V> validate(Vertex<V> v) throws IllegalArgumentException{
		if (!(v instanceof Vertex))
			throw new IllegalArgumentException("Not a valid Vertex");
		InnerVertex<V> vertex = (InnerVertex<V>) v;
		if (!vertex.validate(this))
			throw new IllegalArgumentException("Not a valid Vertex");
		return  vertex;
	}
	
	/**
	 * Check whether e is a valid Edge
	 * @param e - an Edge
	 * @return an InnerEdge
	 * @throws IllegalArgumentException if e is not a valid edge
	 */
	private InnerEdge<E> validate(Edge<E> e) throws IllegalArgumentException{
		if (!(e instanceof Edge))
			throw new IllegalArgumentException("Not a valid Edge");
		InnerEdge<E> edge = (InnerEdge<E>) e;
		if (!edge.validate(this))
			throw new IllegalArgumentException("Not a valid Edge");
		return edge;
	}
	

}
