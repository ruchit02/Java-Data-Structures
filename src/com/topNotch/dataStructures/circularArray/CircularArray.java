package com.topNotch.dataStructures.circularArray;

public class CircularArray {

	private int CAPACITY = 16 ;
	private int[] buffer = new int[CAPACITY] ;
	
	private int read = 0 ; 
	private int write = 0 ;
	
	private int length = 0 ;
	
	public CircularArray() {
		
	}
	
	public void enQueue( int elem ) throws Exception {
		
		if( read == (write + 1)%CAPACITY ) { throw new Exception("Queue is Full") ; }
		
		buffer[write] = elem ;
		
		write = (write+1)%CAPACITY ;
		
		++length ;
	}
	
	public int deQueue() throws Exception {
		
		if( read == write ) { throw new Exception("Queue is Empty") ; }
		
		int data = buffer[read] ;
		read = (read+1)%CAPACITY ;
		
		--length ;
		
		return data ;
	}
	
	public boolean isEmpty(){ return (size()>0)? false : true ;  }
	
	public int size() { return length ; }
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder() ;
		
		int i = 0 ;
		while( i < size() ) {
			
			sb.append( "\n[Element : " + buffer[i] + " | Position : " + i + "]" ) ;
			i++ ;
		}
		
		sb.append( "\nCapacity : " + buffer.length + " | Filled Size : " + size() ) ;
		
		return sb.toString() ;
	}
}