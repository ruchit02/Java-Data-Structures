package com.topNotch.dataStructures.graphs;

import com.topNotch.dataStructures.linkedList.TNLinkedList;
import com.topNotch.dataStructures.priorityQueue.TNPriorityQueue;

public class DFS {

	public enum States{
		
		WHITE , GREY , BLACK 
	}
	
	private int maxVertices ;
	private int vertexCount ;
	private final boolean[][] adjMatrix ;
	private final Vertex[] vertices ;
	private final int[][] edgeCost ;
	//private final States[] vertexState ;
	
	
	public DFS( int maxVertices ) {
		
		this.maxVertices = maxVertices ;
		this.vertices = new Vertex[maxVertices] ;
		this.edgeCost = new int[maxVertices][maxVertices] ;
		this.adjMatrix = new boolean[maxVertices][maxVertices] ;
	}
	
	public void addEdge( int x , int y ) {
		
		if( x >= 0 && x < maxVertices && y >= 0 && y < maxVertices ) {
			
			adjMatrix[x][y] = true ;
			//adjMatrix[y][x] = true ;
		}
	}
	
	public void addEdge( int x , int y , int edgeCost ) {
		
        if( x >= 0 && x < maxVertices && y >= 0 && y < maxVertices && edgeCost > 0 ) {
			
			adjMatrix[x][y] = true ;
			this.edgeCost[x][y] = edgeCost ;
		}
	}
	
	public void removeEdge( int x , int y ) {
		
        if( x >= 0 && x < maxVertices && y >= 0 && y < maxVertices ) {
			
			adjMatrix[x][y] = false ;
			adjMatrix[y][x] = false ;
		}
	}
	
	public boolean isEdge( int x , int y ) {
		
        if( x >= 0 && x < maxVertices && y >= 0 && y < maxVertices ) {
			
			return adjMatrix[x][y] ;
		}
        
		return false ;
	}
	
	public void addVertex( char label ) {
		
		if( vertexCount == maxVertices ) { return ; }
		
		vertices[vertexCount++] = new Vertex( label ) ;
		vertices[vertexCount-1].queuePosition = vertexCount-1 ;
	}
	
	public Vertex getVertex( int position ) {
		
		return vertices[position] ;
	}
	
	public void dfs() {
		
		runDFS( 0 , vertices ) ;
	}
	
	private void runDFS( int x , Vertex[] vertices ) {
		
		vertices[x].state = States.GREY ;
		
		for( int y = 0 ; y < vertexCount ; ++y ) {
			
			if( isEdge( x , y ) && vertices[y].state == States.WHITE ) {
				
				runDFS( y , vertices ) ;
			}
		}
		
		vertices[x].state = States.BLACK ;
		System.out.println( "Done with the vertex : " + vertices[x].label );
	}
	
	public void bfs() {
		
		runBFS( 0 , vertices ) ;
	}
	
	private void runBFS( int x , Vertex[] vertices ) {
		
		for( int y = 0 ; y < vertexCount ; ++y ) {
			
			if( isEdge( x , y ) && vertices[y].state == States.WHITE ) {
				
				vertices[y].state = States.GREY ;
				runBFS( x , vertices ) ;
				
				return ;
			}
		}
	}
	
	public void unweightedGraph( Vertex source ) {
		
        for( int i = 0 ; i < vertexCount ; ++i ) {
        	
        	vertices[i].distance = -1 ;
        }
        
        TNLinkedList<Vertex> dll = new TNLinkedList<Vertex>() ; 
        dll.addFirst( source ) ;
        source.distance = 0 ;
        
        while( !dll.isEmpty() ) {
        	
        	Vertex v = dll.removeFirst() ;
        	int x = v.queuePosition ; 
        	
        	for( int y = 0 ; y < vertexCount ; ++y ) {
            	
            	if( isEdge( x , y ) && vertices[y].distance == -1 ) {
            		
            		vertices[y].distance = v.distance + 1 ;
            		vertices[y].predecessor = v ;
            		dll.addLast( vertices[y] );
            		System.out.println( "X : " + x + " Y : " + y );
            		System.out.println( "Vertex v : " + v.label + " Vertex y : " + vertices[y].label );
            	}
            }
        }
        
        for( int i = 0 ; i < vertexCount ; ++i ) {
        	
        	System.out.println( "Vertex : " + vertices[i].label + " Distance : " + vertices[i].distance );
        }
	}
	
	public void weightedGraph( Vertex source ) {
		
		for( int i = 0 ; i < vertexCount ; ++i ) {
			
			vertices[i].distance = -1 ;
		}
		
		source.distance = 0 ;
		
		TNPriorityQueue<Vertex> queue = new TNPriorityQueue<Vertex>() ;
		queue.insert( source );
		
		while( !queue.isEmpty() ) {
			
			Vertex v = queue.deleteMin() ;
			int x = v.queuePosition ;
			
			for( int y = 0 ; y < vertexCount ; ++y ) {
				
				if( isEdge( x , y ) ) {
					
					if( vertices[y].distance == -1 ) {
						
						vertices[y].distance = v.distance + edgeCost[x][y] ;
						vertices[y].predecessor = v ;
						queue.insert( vertices[y] );
					}
					
					if( vertices[y].distance > (v.distance + edgeCost[x][y]) ) {
						
						vertices[y].distance = v.distance + edgeCost[x][y] ;
						vertices[y].predecessor = v ;
					}
				}
			}
		}
		
		for( int i = 0 ; i < vertexCount ; ++i ) {
			
			if( i != 0 ) {
				
				System.out.println( "Vertex : " + vertices[i].label +  " Predecessor : " + vertices[i].predecessor.label + " Distance : " + vertices[i].distance );
			}else {
				
				System.out.println( "Vertex : " + vertices[i].label +  " Predecessor : " + "null" + " Distance : " + vertices[i].distance );
			}
			
		}
	}
	
	private class Vertex implements Comparable<Vertex>{
		
		private int distance ;
		private int queuePosition ;
		private char label ;
		
		private Vertex predecessor ;
		
		private States state ;
		
		
		private Vertex( char label ) {
			
			this.label = label ;
			this.state = States.WHITE ;
			this.distance = -1 ;
			this.predecessor = null ;
		}

		@Override
		public int compareTo(Vertex v) {
			
            int result = Integer.compare( distance , v.distance ); 
			
			return result ;
		}
	}
}
