package com.topNotch.dataStructures.linkedList;

public class TNSLinkedList<E extends Comparable<E>> {

	private LinkedNode headNode ;
	private int length ;
	
	public TNSLinkedList() { }
	
	public E findNthNodeFromEndOfLinkedList( int n ) {
		
		if( n > length ) { return null ; }
		
		LinkedNode temp1 = headNode ;
		LinkedNode temp2 = headNode ;
		
		int count = n ;
		
		while( --count > 0 ) {
			
			temp2 = temp2.nextNode ;
		}
		
		
		while( temp2.nextNode != null ) {
			
			temp2 = temp2.nextNode ;
			temp1 = temp1.nextNode ;
		}
		
		return temp1.elem ;
	}
	
	public void insertNodeInSortedLinkedList( E elem ) {
		
		if( headNode == null ) { return ; }
		
		LinkedNode prevNode = null ;
		LinkedNode currNode = headNode ;
		LinkedNode newNode = null ;
		
		while( currNode != null ) {
			
			if( elem.compareTo( currNode.elem ) < 0 ) {
				
				newNode = new LinkedNode(elem) ;
				
				if( prevNode != null ) { prevNode.nextNode = newNode ; } 
				newNode.nextNode = currNode ;
				
				break ;
			}
			
			prevNode = currNode ;
			currNode = prevNode.nextNode ;
		}
		
		if( currNode == null ) { 
			
			newNode = new LinkedNode(elem) ;
			prevNode.nextNode = newNode ;
			newNode.nextNode = currNode ;
		}
		
		if( currNode.equals( headNode ) ) { headNode = newNode ; }
	}
	
	public void reverseLinkedList() {
		
		if( headNode == null ) { return ; }
		
		LinkedNode prevNode = null ;
		LinkedNode currNode = headNode ;
		LinkedNode nextNode = currNode.nextNode ;
		
		while( nextNode != null ) {
			
			currNode.nextNode = prevNode ;
			
			prevNode = currNode ;
			currNode = nextNode ;
			nextNode = currNode.nextNode ;
		}
		
		currNode.nextNode = prevNode ;
	}
	
	public E findMergePointOfTwoLinkedLists( TNSLinkedList<E> list1 , TNSLinkedList<E> list2 ) {
		
		int list1Length = 0 ;
		int list2Length = 0 ;
		int diff = 0 ;
		
		LinkedNode temp1 = list1.headNode ;
		LinkedNode temp2 = list2.headNode ;
		
		if( temp1 == null || temp2 == null ) { return null ; }
		
		while( temp1 != null ) {
			
			temp1 = temp1.nextNode ;
			++list1Length ;
		}
		
		while( temp2 != null ) {
			
			temp2 = temp2.nextNode ;
			++list2Length ;
		}
		
		temp1 = list1.headNode ;
		temp2 = list2.headNode ;
		
		if( list1Length > list2Length ) {
			diff = list1Length - list2Length ;
			
			while( diff-- > 0 ) {
				temp1 = temp1.nextNode ;
			}
		}else {
			
			if( list2Length > list1Length ) {
				diff = list2Length - list1Length ;
				
				while( diff-- > 0 ) {
					temp2 = temp2.nextNode ;
				}
			}
		}
		
		while( !temp1.equals( temp2 ) ) {
			
			temp1 = temp1.nextNode ;
			temp2 = temp2.nextNode ;
		}
		
		return temp1.elem ;
	}
	
	public E findMiddleOfLinkedList() {
		
		if( headNode == null ) { return null ; }
		
		if( headNode.nextNode == null ) { return headNode.elem ; } 
		
		LinkedNode tortoise = headNode ;
		LinkedNode hare = headNode.nextNode.nextNode ;
		
		while( hare != null && hare.nextNode != null ) {
			
			hare = hare.nextNode.nextNode ;
			tortoise = tortoise.nextNode ;
		}
		
		return tortoise.elem ;
	}
	
	public void printElementsOfLinkedListInReverseOrder() {
		
		if( headNode == null ) { return ; }
		
		reverseOrdering( headNode ) ;
	}
	
	private void reverseOrdering( LinkedNode rootNode ) {
		
		if( rootNode != null ) {
			
			reverseOrdering( rootNode.nextNode ) ;
			System.out.println( rootNode.elem );
		}
	}
	
	public boolean isLinkedListOfEvenLength( TNSLinkedList<E> list ) {
		
		LinkedNode hare = headNode ;
		
		while( hare != null && hare.nextNode != null ) {
			
			hare = hare.nextNode.nextNode ;
		}
		
		if( hare == null ) { return true ; }
		
		return false ;
	}
	
	public TNSLinkedList<E> mergeTwoLinkedListsIntoThirdInSortedOrder( TNSLinkedList<E> list1 , TNSLinkedList<E> list2 ) {
		
		LinkedNode temp1 = list1.headNode ;
		LinkedNode temp2 = list2.headNode ;
		
		if( temp1 == null ) { return list2 ; }
		
		if( temp2 == null ) { return list1 ; }
		
		TNSLinkedList<E> mergedList = new TNSLinkedList<E>() ;
		mergedList.headNode = mergeHelper( temp1 , temp2 ) ; 
		
		return mergedList ;
	}
	
	private LinkedNode mergeHelper( LinkedNode first , LinkedNode second ) {
		
		if( first == null ) { return secondMerger( second ) ; }
		
		if( second == null ) { return firstMerger( first ) ; } 
		
		LinkedNode newNode = null ;
		
		if( first.elem.compareTo( second.elem ) <= 0 ) {
			newNode = new LinkedNode( first.elem ) ;
			newNode.nextNode = mergeHelper( first.nextNode , second ) ;
			
		}else {
			newNode = new LinkedNode( second.elem ) ;
			newNode.nextNode = mergeHelper( first , second.nextNode ) ;
		}
		
		return newNode ;
	}
	
	private LinkedNode firstMerger( LinkedNode first ) {
		
        if( first == null ) { return null ; }
		
		LinkedNode newNode = new LinkedNode( first.elem ) ;
		newNode.nextNode = firstMerger( first.nextNode ) ;
		
		return newNode ;
	}
	
	private LinkedNode secondMerger( LinkedNode second ) {
		
		if( second == null ) { return null ; }
		
		LinkedNode newNode = new LinkedNode( second.elem ) ;
		newNode.nextNode = secondMerger( second.nextNode ) ;
		
		return newNode ;
	}
	
	public void reverseLinkedListElemsInPairs() {
		
		pairElemsReversingHelper( headNode ) ;
	}
	
	private void pairElemsReversingHelper( LinkedNode curr ) {
		
		if( curr == null || curr.nextNode == null ) { return ; } 
		
		pairElemsReversingHelper( curr.nextNode.nextNode ) ;
		
		E elem = curr.nextNode.elem ;
		curr.nextNode.elem = curr.elem ;
		curr.elem = elem ;
	}
	
	public void reverseLinkedListNodesInPairs() {
		
		pairNodesReversingHelper( headNode ) ;
	}
	
	private void pairNodesReversingHelper( LinkedNode curr ) {
		
		if( curr == null || curr.nextNode == null ) { return ; } 
		
		pairNodesReversingHelper( curr.nextNode.nextNode ) ;
		
		LinkedNode tempNode = curr.nextNode.nextNode ;
		LinkedNode nextNode = curr.nextNode ;
		
		curr.nextNode = tempNode ;
		nextNode.nextNode = curr ;
	}
	
    public boolean checkWhetherLinkedListIsPalindromic() {
		
    	if( headNode == null ) { return false ; }
    	
    	if( headNode.nextNode == null ) { return true ; }
    	
    	int rightPointer = 1 ;
    	
    	LinkedNode tortoise = headNode ;
    	LinkedNode hare = headNode ;
    	
		while( hare.nextNode != null && hare.nextNode.nextNode != null ) {
			
			tortoise = tortoise.nextNode ;
			hare = hare.nextNode.nextNode ;
			++rightPointer ;
		}
		
		LinkedNode prevNode = tortoise ;
		LinkedNode currNode = tortoise.nextNode ;
		LinkedNode nextNode = currNode.nextNode ;
		
		while( currNode != null && nextNode != null ) {
			
			currNode.nextNode = prevNode ;
			
			prevNode = currNode ;
			currNode = nextNode ;
			nextNode = currNode.nextNode ;
		}
		
		if( currNode != null ) { currNode.nextNode = prevNode ; }
		
		LinkedNode headNode = this.headNode ;
		LinkedNode tailNode = currNode ;
		
		while( headNode.elem.compareTo( tailNode.elem ) == 0 && rightPointer-- > 0 ) {
			
			headNode = headNode.nextNode ;
			tailNode = tailNode.nextNode ;
		}
		
		if( rightPointer == 0 ) { return true ; } 
		
		return false ;
	}
	
    public E findLastModularNodeFromBeginning( int k ) {
    	
    	if( k == 0 ) { return null ; }
    	
    	int i = 1 ;
    	
    	LinkedNode currNode = headNode ;
    	LinkedNode modularNode = null ; 
    	
    	while( currNode != null ) {
    		
    		if( i%k == 0 ) {
    			modularNode = currNode ;
    		}
    		
    		currNode = currNode.nextNode ;
    		++i ;
    	}
    	
    	if( k > i ) { return null ; }
    	
    	return modularNode.elem ;
    }
    
    public E findFirstModularNodeFromEnd( int k ) {
    	
    	if( k == 0 ) { return null ; }
    	
    	LinkedNode modularNode = null ;
    	LinkedNode currNode = headNode ;
    	
    	int i = 1 ;
    	
    	while( i < k ) {
    		
    		currNode = currNode.nextNode ;
    		
    		if( currNode == null ) { return null ; }
    		
    		++i ;
    	}
    	
    	modularNode = headNode ;
    	
    	while( currNode.nextNode != null ) {
    		
    		currNode = currNode.nextNode ;
    		modularNode = modularNode.nextNode ;
    	}
    	
    	return modularNode.elem ;
    }
    
    public E findFractionalNode( int k ) {
    	
    	LinkedNode modularNode = null ;
    	LinkedNode currNode = headNode ;
    	
    	int i = 1 ;
    	
    	while( currNode != null ) {
    		
    		//get the very first fractional node
    		if( i%k == 0 ) {
    			
    			modularNode = currNode ;
    			break ;
    		}
    		
    		currNode = currNode.nextNode ;
    		++i ;
    	}
    	
    	if( currNode == null ) { return null ; }
    	
    	return modularNode.elem ;
    }
    
	private class LinkedNode {
		
		private E elem ;
		private LinkedNode nextNode ;
		
		private LinkedNode( E elem ) {
			
			this.elem = elem ;
		}
	}
}
