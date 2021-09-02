package graphs;

/**
 * Interface for the Graph ADT
 */
public interface Graph<V,E> {

	/**
	 * @return the number of vertices in the Graph
	 */
	int numVertices();
	
	/**
	 * @return the number of edges in the Graph
	 */
	int numEdges();
	
	/**
	 * @return an iterable of all vertices in the graph
	 */
	Iterable<Vertex<V>> vertices();
	
	/**
	 * @return an iterable of all edges in the Graph
	 */
	Iterable<Edge<E>> edges();
	
	/**
	 * @param u - a Vertex
	 * @param v - a Vertex
	 * @return the Edge between u and v, or null if u and v are not adjacent
	 * @throws IllegalArgumentException if either u or v are not valid Vertices
	 */
	Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException;
	
	/**
	 * @return an array containing the two end point vertices for Edge e
	 * @throws IllegalArgumentException if e is not a valid Edge
	 */
	Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException;
	
	/**
	 * @param v - a Vertex
	 * @param e - an Edge
	 * @return the vertex opposite vertex v incident to edge e
	 * @throws IllegalArgumentException if v is not a valid Vertex or e is not a valid Edge
	 */
	Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;
	
	/**
	 * @param v - a Vertex
	 * @return the number of outgoing edges from vertex v
	 * @throws IllegalArgumentException if v is not a valid Vertex
	 */
	int outDegree(Vertex<V> v) throws IllegalArgumentException;
	
	/**
	 * @param v - a Vertex
	 * @return the number of incoming edges for Vertex v
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	int inDegree(Vertex<V> v) throws IllegalArgumentException;
	
	/**
	 * @param v - a Vertex
	 * @return an iterable of the outgoing edges from vertex v
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException;
	
	/**
	 * @param v - a Vertex
	 * @return an iterable of the incoming edges for vertex v
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	Iterable<Edge<E>> inComingEdges(Vertex<V> v) throws IllegalArgumentException;
	
	/**
	 * Insert a new Vertex with element x in the Graph
	 * @param element - the element
	 * @return the new Vertex
	 */
	Vertex<V> insertVertex(V element);
	
	/**
	 * Inserts a new Edge between two Vertices
	 * @param u - a Vertex
	 * @param v - a Vertex
	 * @param element - the value stored at at the edge
	 * @return the new edge
	 * @throws IllegalArgumentException if either u or v is not a valid Vertex
	 */
	Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException;
	
	/**
	 * Removes a Vertex from the Graph
	 * @param e a Vertex in the Graph
	 * @throws IllegalArgumentException  if v is not a valid Vertex
	 */
	void removeVertex(Vertex<V> v) throws IllegalArgumentException;
	
	/**
	 * Removes an Edge from the Graph
	 * @param e - an Edge in the Graph
	 * @throws IllegalArgumentException  if e is not a valid Edge
	 */
	void removeEdge(Edge<E> e) throws IllegalArgumentException;
	
	
}
