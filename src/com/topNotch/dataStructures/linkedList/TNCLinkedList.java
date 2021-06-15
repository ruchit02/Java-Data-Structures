package com.topNotch.dataStructures.linkedList;

public class TNCLinkedList<E extends Comparable<E>> {

	private LinkedNode headNode ;
	private int length ;
	
	public TNCLinkedList() {
		
	}
	
	public boolean checkWhetherLoopExists() {
		
		if( headNode == null ) { return false ; }
		
		LinkedNode tortoise = headNode ;
		LinkedNode hare = headNode ;
		
		while( hare != null && hare.nextNode != null ) {
			
			hare = hare.nextNode.nextNode ;
			tortoise = tortoise.nextNode ;
			
			if( tortoise.equals( hare ) ) { return true ; }
		}
		
		return false ;
	}
	
	public E ifLoopExistsGetLoopHead() {
		
		if( headNode == null ) { return null ; }
		
		boolean LOOP_EXISTS = false ;
		
		LinkedNode tortoise = headNode ;
		LinkedNode hare = headNode ;
		
		while( hare != null && hare.nextNode != null ) {
			
			hare = hare.nextNode.nextNode ;
			tortoise = tortoise.nextNode ;
			
			if( tortoise.equals( hare ) ) { LOOP_EXISTS = true ; break ; }
		}
		
		if( LOOP_EXISTS ) {
			
			tortoise = headNode ;
			
			while( !tortoise.equals( hare ) ) {
				
				hare = hare.nextNode ; 
				tortoise = tortoise.nextNode ;
			}
			
			return hare.elem ;
		}
		
		 return null ;
	}
	
	public int ifLoopExistsGetLoopLength() {
		
        if( headNode == null ) { return 0 ; }
		
		boolean LOOP_EXISTS = false ;
		
		LinkedNode tortoise = headNode ;
		LinkedNode hare = headNode ;
		
		while( hare != null && hare.nextNode != null ) {
			
			hare = hare.nextNode.nextNode ;
			tortoise = tortoise.nextNode ;
			
			if( tortoise.equals( hare ) ) { LOOP_EXISTS = true ; break ; }
		}
		
		if( LOOP_EXISTS ) {
			
			int counter = 1 ;
			tortoise = tortoise.nextNode ;
			
			while( !tortoise.equals( hare ) ) {
				
				tortoise = tortoise.nextNode ; 
				++counter ;
			}
			
			return counter ;
		}
		
		return 0 ;
	}
	
	public void splitLinkedListInEqualParts() {
		
		if( headNode == null || headNode.nextNode == headNode ) { return ; }
		
		LinkedNode tortoise = headNode ;
		LinkedNode hare = headNode ;
		
		while( hare.nextNode != headNode && hare.nextNode.nextNode != headNode ) {
			
			tortoise = tortoise.nextNode ;
			hare = hare.nextNode.nextNode ;
		}
		
		if( hare.equals( headNode ) ) {
			
			hare = hare.nextNode ;
			
			LinkedNode tempNode = tortoise.nextNode ;
			tortoise.nextNode = headNode ;
			hare.nextNode = tempNode ;
			
			return ;
		}
		
		if( hare.nextNode.nextNode.equals( headNode ) ) {
			
			hare = hare.nextNode ;
		}
		
		LinkedNode tempNode = tortoise.nextNode ;
		tortoise.nextNode = headNode ;
		hare.nextNode = tempNode ;
	}
	
    private class LinkedNode {
		
		private E elem ;
		private LinkedNode nextNode ;
		
		private LinkedNode( E elem ) {
			
			this.elem = elem ;
		}
	}
}
