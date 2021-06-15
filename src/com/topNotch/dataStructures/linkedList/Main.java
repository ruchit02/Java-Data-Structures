package com.topNotch.dataStructures.linkedList;

import java.util.PriorityQueue;
import java.util.Random;
import com.topNotch.dataStructures.deque.TNDeque;
import com.topNotch.dataStructures.graphs.DFS;
import com.topNotch.dataStructures.map.TNHashMap;
import com.topNotch.dataStructures.priorityQueue.TNPriorityQueue;
import com.topNotch.dataStructures.trees.TNBSTree;

public class Main {

	public static void main( String[] args ) throws Exception {
		
		
		DFS dfs = new DFS( 20 ) ;
		dfs.addVertex( 'A' );
		dfs.addVertex( 'B' );
		dfs.addVertex( 'C' );
		dfs.addVertex( 'D' );
		dfs.addVertex( 'E' );
		dfs.addVertex( 'F' );
	    dfs.addVertex( 'G' );
	    dfs.addVertex( 'H' );
	    dfs.addVertex( 'I' );
		
		/*dfs.addEdge( 2 , 0 );
		dfs.addEdge( 2 , 5 );
		dfs.addEdge( 0 , 1 );
		dfs.addEdge( 0 , 3 );
		dfs.addEdge( 1 , 3 );
		dfs.addEdge( 1 , 4 );
		dfs.addEdge( 3 , 5 );
		dfs.addEdge( 3 , 6 );
		dfs.addEdge( 6 , 5 );
		dfs.addEdge( 4 , 6 );
		dfs.unweightedGraph( dfs.getVertex(0) );*/
		
		dfs.addEdge( 0 , 1 , 15 );
		dfs.addEdge( 0 , 2 , 25 );
		dfs.addEdge( 1 , 4 , 10 );
		dfs.addEdge( 1 , 7 , 5 );
		dfs.addEdge( 2 , 3 , 10 );
		dfs.addEdge( 2 , 4 , 20 );
		dfs.addEdge( 4 , 5 , 10 );
		dfs.addEdge( 4 , 6 , 5 );
		dfs.addEdge( 7 , 8 , 15 );
		dfs.addEdge( 8 , 1 , 4 );
		
		dfs.weightedGraph( dfs.getVertex( 0 ) );
		dfs.unweightedGraph( dfs.getVertex(0) );
		
		/*int size = 3097152 ;
		int halfSize = (size/2) - 1 ;
		
		Random random = new Random() ;
		int[] integers = random.ints( size , 1 , size+1 ).distinct().toArray() ;
		int[] temp = { 26 , 1 , 10 , 19 , 9 , 30 , 22 , 3 , 18 , 20 , 4 , 6 , 29 , 5 , 16 , 28 , 27 , 17 } ; 
		
		//26 , 1 , 10 , 19 , 9 , 30 , 22 , 3 , 18 , 20 , 4 , 6 , 29 , 5 , 16 , 28 , 27 , 17
		
		TNPriorityQueue<Integer> priorityQueue = new TNPriorityQueue<>() ;
		
		
		for( int i = 0 ; i < integers.length ; ++i ) {
			
			priorityQueue.insert( new Integer( integers[i] ) );
		}
		
		long priorityStartTime = System.nanoTime() ;
		
		int queueSize = priorityQueue.size() ;
		for( int i = 0 ; i < queueSize-1 ; ++i ) {
			
			int elem = priorityQueue.deleteMin().intValue() ;
		}
		
		long priorityEndTime = System.nanoTime() - priorityStartTime ;
		System.out.println( priorityQueue.toString() + "\nTime taken to HEAP SORT " + integers.length + " elements : " + priorityEndTime/1000000 + " milliSeconds" );
 		
		/*priorityQueue.deleteMin() ;
		System.out.println( "\n\n\n\n" );
		priorityQueue.levelOrderTraversal();*/
		
		
        /*TNBSTree<Integer> intTree = new TNBSTree<>() ;
		
		long treeStartTime = System.nanoTime() ;
		
		for( int i = 0 ; i < integers.length ; ++i ) {
			intTree.insert( new Integer( integers[i] ) );
		}
		
		long treeFinishTime = System.nanoTime() - treeStartTime ;
		
		System.out.println( "\nTree Insert Time Taken | " + treeFinishTime/1000000 + " milliSeconds" );
		
		long treeSortStartTime = System.nanoTime() ;
		
		//Object[] sortedTree = intTree.sort() ;
		
		long treeSortFinishTime = System.nanoTime() - treeSortStartTime ;
		
		/*intTree.levelOrderTraversal() ;  
		intTree.mirror();
		System.out.println( "\nMIRROR TREE\n\n" );
		intTree.levelOrderTraversal() ; */
		
		/*System.out.println( "Time taken to TREE SORT " + intTree.size() + " elements" + " : " +  treeSortFinishTime/1000000 + " milliSeconds"
				          + "\nTree Height : " + intTree.height() );*/
		
		
		
		/*TNDeque<Integer> deque = new TNDeque<>() ;
		long dequeStartTime = System.nanoTime() ;
		
		for( int i = 0 ; i < size ; i++ ) {
			
			deque.addFirst( new Integer(i) ) ;		
		}
		
		long dequeEndTime = System.nanoTime() - dequeStartTime ;
		System.out.println( "ArrayDeque insert time taken | " + dequeEndTime ) ;
		
		long dequeSearchStartTime = System.nanoTime() ;
		
		int dequeElem = deque.search( 2000000 ).intValue() ;
		
		long dequeSearchEndTime = System.nanoTime() - dequeSearchStartTime ;
		System.out.println( "ArrayDeque search time taken | " + dequeSearchEndTime 
				          + "\nSearched elem | " + dequeElem ) ;
		
		
		
		
		
		
		TNHashMap<Integer , Integer> map = new TNHashMap<>() ;
		
		long mapStartTime = System.nanoTime() ;
		
		for( int i = 0 ; i < size ; ++i ) {
			map.put( new Integer(i) , new Integer(i) );
		}
		
		long mapEndTime = System.nanoTime() - mapStartTime ;
		
		System.out.println( map.toString() + "\nHashMap Insert Time Taken | " + mapEndTime ) ;
		
		long mapGetStartTime = System.nanoTime() ;
		
		int value = map.get( 400000 ).intValue() ;
		
        long mapGetEndTime = System.nanoTime() - mapGetStartTime ;
		
		System.out.println( "\nHashMap Search Time Taken | " + mapGetEndTime + "\nValue Returned " + value ) ;*/
		
		
		
		
		TNLinkedList<Integer> dll = new TNLinkedList<>() ;
		long dllStartTime = System.nanoTime() ;
		
        for( int i = 1 ; i <= 10 ; i++ ) {
			
			dll.addLast( new Integer(i) ) ;
			System.out.println( dll.getLast() ) ;
		}
		
       dll.reverse(); 
       
       for( int i = 0 ; i <= 9 ; i++ ) {
			
			System.out.println( dll.get( i ) ) ;
	   }
        
       dll.deleteAlternate();
       System.out.println( "Size" + dll.size() ) ;
       
       for( int i = 0 ; i <= 4 ; i++ ) {
			
			System.out.println( dll.get( i ) ) ;
	   }
       
        //System.out.println( "LinkedList Search time taken : " + dllDeleteEndTime + "\nSearched Element | " + halfSize );*/
	}
}
