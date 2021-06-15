package com.topNotch.dataStructures.linkedList;

import java.util.NoSuchElementException;

public class TNLinkedList<E> {

	private LinkNode headNode = null ;
	private LinkNode tailNode = null ;
	private int length = 0 ;
	
	public TNLinkedList() {
		
	}
	
	//methods
	public boolean add( E elem ) {
		
		if( (headNode == null) && (tailNode == null) ) {
			
			LinkNode firstOne = new LinkNode( elem ) ;
			firstOne.setPrevNode( null );
			firstOne.setNextNode( null );
			
			headNode = firstOne ;
			tailNode = firstOne ;
			
			++length ;
			
			return true ;
		}
		
		if( (headNode == tailNode) && (headNode != null) ) {
			
			LinkNode secondOne = new LinkNode(elem) ;
			secondOne.setPrevNode( headNode );
			secondOne.setNextNode( null );
			
			headNode.setNextNode( secondOne );
			
			tailNode = secondOne ;
			
			++length ;
			
			return true ;
		}

		if( (size() > 0) && (headNode != tailNode) ){
			
			LinkNode dll = new LinkNode(elem) ;
			dll.setPrevNode( tailNode ) ;
			dll.setNextNode( null );
			
			tailNode.setNextNode( dll );
			
			tailNode = dll ;
			
			++length ;
			
			return true ;
		}
		
		return false ;
	}
	
	public void add( int index , E elem ) {
		
		if( index < 0 || index > size() ) { throw new IndexOutOfBoundsException() ; } 
		
		if( index == 0 ) { addFirst(elem) ; }
		
		if( index == size() ) { addLast(elem) ; }
		
		if( index > 0 && index < size() ) {
			
			int headToIndexDistance = 0 + index ;
			int tailToIndexDistance = size() - index ;
			
			if( headToIndexDistance >= tailToIndexDistance ) {
				
				LinkNode interiorNode = new LinkNode(elem) ;
				
				LinkNode prevNode = null ;
				LinkNode currNode = headNode ;
				LinkNode nextNode = currNode.getNextNode() ;
				
				int i = 0 ;
				while( i < index ) {
					
					prevNode = currNode ;
					currNode = nextNode ;
					nextNode = nextNode.getNextNode() ;
					++i ;
				}
				
				prevNode.setNextNode( interiorNode ) ;
				
				interiorNode.setPrevNode( prevNode ) ;
				interiorNode.setNextNode( currNode ) ;
				
				currNode.setPrevNode( interiorNode );
				currNode.setNextNode( nextNode );
				
				++length ;
			}
			else {
				
				LinkNode interiorNode = new LinkNode(elem) ;
				
				LinkNode prevNode = tailNode.getPrevNode() ;
				LinkNode currNode = tailNode ;
				LinkNode nextNode = null ;
				
				int i = size()-1 ;
				while( i > index ) {
					
					nextNode = currNode ;
					currNode = prevNode ;
					prevNode = prevNode.getPrevNode() ;
					--i ;
				}
				
				prevNode.setNextNode( interiorNode );
				
				interiorNode.setPrevNode( prevNode );
				interiorNode.setNextNode( currNode );
				
				currNode.setPrevNode( interiorNode );
				currNode.setNextNode( nextNode );
				
				++length ;
			}
		}
	}
	
	public void addFirst( E elem ) {
		
		LinkNode newFirstNode = new LinkNode(elem) ;
		newFirstNode.setPrevNode( null );
		newFirstNode.setNextNode( headNode );
		
		if( headNode != null ) { headNode.setPrevNode( newFirstNode );  }
		
		headNode = newFirstNode ;
		
		if( tailNode == null ) { tailNode = newFirstNode ; }
		
		++length ;
	}
	
	public void addLast( E elem ) {
		
		LinkNode newLastNode = new LinkNode(elem) ;
		newLastNode.setPrevNode( tailNode );
		newLastNode.setNextNode( null );
		
		if( tailNode != null ) { tailNode.setNextNode( newLastNode ) ; }
		
		tailNode = newLastNode ;
		
		if( headNode == null ) { headNode = newLastNode ; }
		
		++length ;
	}
	
	public void clear() {
		
		if( (headNode != null) && (tailNode != null) ) {
			
			LinkNode currNode = headNode ;
			LinkNode tempNode = headNode ;
			
			while( currNode.getNextNode() != null  ) {
				
				tempNode = currNode ;
				currNode = currNode.getNextNode() ;
				
				tempNode.setNextNode( null );
				tempNode.setPrevNode( null );
				
				tempNode = null ;
				
				--length ;
			}
			
			currNode.setPrevNode( null );
			currNode.setNextNode( null );
			currNode = null ;
			
			--length ;
		}
	}
	
	public E get( int index ) {
		
		if( index < 0 || index >= size() ) { throw new IndexOutOfBoundsException() ; }
		
		if( isEmpty() ) { throw new NoSuchElementException() ; } 
		
		int headToIndexDistance = 0 + index ;
		int tailToIndexDistance = size() - index ;
		
		if( (headToIndexDistance <= tailToIndexDistance) ) {
			
			LinkNode currNode = headNode ;
			
			int i = 0 ;
			while( i++ < index ) {
				
				currNode = currNode.getNextNode() ;
			}
			
			return currNode.elem ;
		}
		else {
			
			LinkNode currNode = tailNode ; 
			
			int i = size() ;
			while( --i > index ) {
				
				currNode = currNode.getPrevNode() ;
			}
			
			return currNode.elem ;
		}
	}
	
	public E getFirst() {
		
		if( !isEmpty() ) {
			
			return headNode.elem ;
		}
		
		throw new NoSuchElementException() ;
	}
	
	public E getLast() {
		
		if( !isEmpty() ) {
			
			return tailNode.elem ;
		}
		
		throw new NoSuchElementException() ;
	}
	
	public E remove( int index ) {
		
		if( index < 0 || index >= size() ) { throw new IndexOutOfBoundsException() ; }
		
		if( isEmpty() ) { throw new NoSuchElementException() ; }
		
		int headToIndexDistance = 0 + index ;
		int tailToIndexDistance = size() - index ;
		
		if( headToIndexDistance <= tailToIndexDistance ) {
			
			LinkNode prevNode = null ;
			LinkNode currNode = headNode ;
			LinkNode nextNode = currNode.getNextNode() ;
			
			int i = 0 ;
			while( i < index ) {
				
				prevNode = currNode ;
				currNode = nextNode ;
				nextNode = nextNode.getNextNode() ;
				++i ;
			}
			
			if( currNode == headNode ) { headNode = nextNode ; } 
			
			E data = currNode.elem ;
			
			currNode.setNextNode( null );
			currNode.setPrevNode( null );
			
			if( prevNode != null ) { prevNode.setNextNode( nextNode ); }
			
			if( nextNode != null ) { nextNode.setPrevNode( prevNode ); }
			
			--length ;
			
			return data ;
		}
		else {
			
			LinkNode prevNode = tailNode.getPrevNode() ;
			LinkNode currNode = tailNode ;
			LinkNode nextNode = null ;
			
			
			int i = size()-1 ;
			while( i > index ) {
				
				nextNode = currNode ;
				currNode = prevNode ;
				prevNode = prevNode.getPrevNode() ;
				--i ;
			}
			
			if( currNode == tailNode ) { tailNode = prevNode ; }
			
			E data = currNode.elem ;
			
			currNode.setPrevNode( null );
			currNode.setNextNode( null );
			
			if( prevNode != null ) { prevNode.setNextNode( nextNode ); }
			if( nextNode != null ) { nextNode.setPrevNode( prevNode ); }
			
			--length ;
			
			return data ;
		}
	}
	
	public E removeFirst() {
		
		if( !isEmpty() ) {
			
			E data = headNode.elem ;
			
			if( headNode.getNextNode() != null ) {
				
				LinkNode newHeadNode = headNode.getNextNode() ;
				newHeadNode.setPrevNode(null);
				
				headNode.setNextNode( null );
				
				headNode = newHeadNode ;
			}
			else { headNode = null ; }
			
			--length ;
			
			return data ;
		}
		
		throw new NoSuchElementException() ;
	}
	
	public E removeLast() {
		
		if( !isEmpty() ) {
			
			E data = tailNode.elem ;
			
			if( tailNode.getPrevNode() != null ) {
				
				LinkNode newTailNode = tailNode.getPrevNode() ;
				newTailNode.setNextNode( null );
				
				tailNode.setPrevNode( null );
				
				tailNode = newTailNode ;
			}
			else { tailNode = null ; }
			
			--length ;
			
			return data ;
		}
		
		throw new NoSuchElementException() ;
	}
	
	public void set( int index , E elem ) {
		
		if( (index < 0) || (index >= size()) ) { throw new IndexOutOfBoundsException() ; }
		
		if( isEmpty() ) { throw new NoSuchElementException() ; }
		
		int headToIndexDistance = 0 + index ;
		int tailToIndexDistance = size() - index ;
		
		if( headToIndexDistance <= tailToIndexDistance ) {
			
			LinkNode currNode = headNode ;
			
			int i = 0 ;
			while( i < index ) {
				
				currNode = currNode.getNextNode() ;
				++i ;
			}
			
			currNode.elem = elem ;
		}
		else {
			
			LinkNode currNode = tailNode ;
			
			int i = size()-1 ;
			while( i > index ) {
				
				currNode = currNode.getPrevNode() ;
				
				--i ;
			}
			
			currNode.elem = elem ;
		}
	}
	
	//
	public void reverse() {
		
		LinkNode prevNode = null ;
		LinkNode currNode = headNode ;
		LinkNode nextNode = headNode.getNextNode() ;
		
		while( nextNode != null ) {
			
			currNode.setNextNode( prevNode );
			currNode.setPrevNode( nextNode );
			
			prevNode = currNode ;
			currNode = nextNode ;
			nextNode = currNode.nextNode ;
		}
		
		currNode.setNextNode( prevNode );
		currNode.setPrevNode( nextNode );
		
		LinkNode tempNode = headNode ;
		headNode = currNode ;
		tailNode = tempNode ;
	}
	
	public void deleteAlternate() {
		
		if( size() < 2 ) { return ; }
		
		LinkNode prevNode = headNode ;
		LinkNode currNode = prevNode.getNextNode() ;
		LinkNode nextNode = currNode.getNextNode() ;
		
		while( currNode != null ) {
			
			--length ;
			
			nextNode = currNode.getNextNode() ;
			
			currNode.setNextNode( null ) ; 
			currNode.setPrevNode( null ) ;
			
			prevNode.setNextNode( nextNode );
			
			if( nextNode == null ) { tailNode = prevNode ; break ; }
			
			nextNode.setPrevNode( prevNode );
			
			prevNode = nextNode ;
			currNode = prevNode.getNextNode() ;
		}
	}
	
	public boolean isEmpty() {
		
		return (length > 0)? false : true ;
	}
	
	public int size() {
		
		return length ;
	}
	
	//overriden methods 
	/*@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder() ;
		
		LinkNode currNode = headNode ;
		
		int i = 0 ;
		while( currNode.getNextNode() != null ) {
			
			sb.append( "\n[Element : " + currNode.getElement() + " | Position : " + i + "]" ) ;
			currNode = currNode.getNextNode() ;
			++i ;
		}
		
		sb.append( "\n[Element : " + currNode.getElement() + " | Position : " + i + "]" ) ;
		sb.append( "\nSize : " + size() );
		
		return sb.toString() ;
	}*/
	
	public class LinkNode {
		
		private LinkNode prevNode ;
		private LinkNode nextNode ;
		private E elem ;
		
		public LinkNode() {
			
			this.elem = null ;
			this.prevNode = null ;
			this.nextNode = null ;
		}
		
		public LinkNode( E elem ) {
			
			this.elem = elem ;
			this.prevNode = null ;
			this.nextNode = null ;
		}
		
		public LinkNode getPrevNode() { return this.prevNode ; }
		
		public LinkNode getNextNode() { return this.nextNode ; }
		
		public E getElement() { return this.elem ; }
		
		public void setPrevNode( LinkNode prevNode ) { this.prevNode = prevNode ; }
		
		public void setNextNode( LinkNode nextNode ) { this.nextNode = nextNode ; }
	}
}
