package com.topNotch.dataStructures.map;

public class TNHashMap<K,V> {

	private int[] primes = { 13 , 31 , 61 , 127 , 251 , 509 , 1021
			               , 2039 , 4093 , 8191 , 16381 , 32749 , 65521 
			               , 131071 , 262139 , 524287 , 1048573 , 2097143
			               , 4194301 , 8388547 , 16777213 , 33554393 
			               , 67108859 , 134217689 , 268435399 , 536870909 
			               , 1073741789 , 2147483647 } ;
	
	private int CAPACITY = 16 ;	
	private double LOAD_FACTOR = 0.75 ;
	private int THRESHOLD = (int)(CAPACITY*LOAD_FACTOR) ;
	private TNBucketTable table ;
	private int R = 13 ;
	private int LARGEST_BUCKET_SIZE = 0 ;
	
	public TNHashMap() {
		
		this.table = new TNBucketTable( CAPACITY ) ;
	}
	
	public void put( K key , V value ) {
		
		int index = 0 ;
		int hash = 31*key.hashCode() ;
		
		checkCapacity( ) ;
		
		while( true ) {
			
			int location = doubleHashing( hash , index ) ;
			
			if( location != 0 ) {
				
				if( table.getBuckets()[location] == null ) {
					
					TNLinkedBucket bucket = new TNLinkedBucket() ;
					table.getBuckets()[location] = bucket ;
					
					bucket.setHeadNode( new TNLinkedNode( key , value ) );
					
					++bucket.bucketSize ;
					++table.tableSize ;
				}else {
					
					TNLinkedBucket bucket = (TNLinkedBucket)table.getBuckets()[location] ;
					TNLinkedNode tempNode = bucket.getHeadNode() ;
					
					bucket.setHeadNode( new TNLinkedNode( key , value ) ) ;
					bucket.getHeadNode().setNextNode( tempNode );
					
					++bucket.bucketSize ;
					
					if( bucket.getSize() > LARGEST_BUCKET_SIZE ) {
						LARGEST_BUCKET_SIZE = bucket.getSize() ;
					}
				}
				
				++this.table.sumOfAllBucketElements ;
				
				break ;
			}
			
			++index ;
		}
	}
	
	private void checkCapacity(  ) {
		
		if( (this.table.getTableSize() >= THRESHOLD) && (this.table.sumOfAllBucketElements >= (4*CAPACITY)) ) {
			
			TNBucketTable tempTable = this.table ;
			int tempCapacity = CAPACITY ;
			int numOfElemsInPrevTable = this.table.sumOfAllBucketElements ;
			
			int i = 0 ;
			
			while( primes[i] < CAPACITY ) {
				++i ;
			}
			
			R = primes[i] ;
 			CAPACITY = 2*CAPACITY ;
 			THRESHOLD = (int)(CAPACITY*LOAD_FACTOR) ;
 			
 			this.table = new TNBucketTable( CAPACITY ) ;
 			
 			for( int m = 0 ; m < tempCapacity ; ++m ) {
 				
 				if( tempTable.getBuckets()[m] != null ) {
 					
 					if( tempCapacity == 1000000 ) {
 						System.out.println( m + " | " + CAPACITY + " | " + R );
 					}
 					
 					this.table.getBuckets()[m] = (TNLinkedBucket)tempTable.getBuckets()[m] ;
 					tempTable.getBuckets()[m] = null ;
 					++this.table.tableSize ;
 				}
 			}
 			
 			this.table.sumOfAllBucketElements = numOfElemsInPrevTable ;
		}
	}
	
    private int doubleHashing( int hash , int index ) {
		
		return (primaryHashFunction( hash ) + (index*secondaryHashFunction( hash )))%CAPACITY ;
	}
	
	private int primaryHashFunction( int hash ) {
		
		return hash%CAPACITY ;
	}
	
	private int secondaryHashFunction( int hash ) {
		
		return R - (hash%R) ;
	}
	
	
	public V get( K key ) {
		
		int hash = 31*key.hashCode() ;
		
		int capacity = CAPACITY ;
		int i = 0;
		
		CAPACITY = 8 ;
		
		while( CAPACITY != capacity ) {
			
			R = primes[i] ;
			CAPACITY = 2*CAPACITY ;
			
			int location = 0 ;
			int index = 0 ;
			
			while( location == 0 ) {
				
				location = doubleHashing( hash , index ) ;
				++index ;
			}
			
			if( this.table.buckets[location] != null ) {
				
				TNLinkedBucket bucket = (TNLinkedBucket)this.table.getBuckets()[location] ;
				
				TNLinkedNode headNode = bucket.headNode ;
				
				while( headNode != null ) {
					
					if( key.equals( headNode.getKey() ) ) {
						
						return headNode.getValue() ;
					}
					
					headNode = headNode.getNextNode() ;
				}
			}
			
			++i ;
		}
		
		
		return null ;
	}
	
	//THE TABLE
	private class TNBucketTable {
		
		private int sumOfAllBucketElements = 0 ;
		private int tableSize ;
		private Object[] buckets ;
		
		private TNBucketTable( int tableSize ) {
			
			buckets = new Object[tableSize] ;
		}
		
		private Object[] getBuckets() {
			return this.buckets ;
		}
		
		private int getTableSize() {
			return this.tableSize ;
		}
	}
	
	
	//THE BUCKET
	private class TNLinkedBucket {
		
		private TNLinkedNode headNode ;
		private int bucketSize ;
		
		public TNLinkedNode getHeadNode() {
			return this.headNode ;
		}
		
		public void setHeadNode( TNLinkedNode headNode ) {
			this.headNode = headNode ;
		}
		
		public int getSize() {
			return this.bucketSize ;
		}
	}
	
	//THE NODE
	private class TNLinkedNode {
		
		private TNLinkedNode nextNode ;
		private K key ;
		private V value ;
		
		public TNLinkedNode( K key , V value ) {
			
			this.key = key ;
			this.value = value ;
		}
		
		public K getKey() {
			return this.key ;
		}
		
		public TNLinkedNode getNextNode() {
			return this.nextNode ;
		}
		
		public V getValue() {
			return this.value ;
		}
		
		public void setNextNode( TNLinkedNode nextNode ) {
			this.nextNode = nextNode ;
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder() ;
		
		sb.append( "\n\nOCCUPIED TABLE CELLS | " + this.table.getTableSize() + "\nTOTAL TABLE CELLS | " + CAPACITY
				 + "\nLARGEST BUCKET SIZE | " + this.LARGEST_BUCKET_SIZE
				 + "\nSUM OF ALL BUCKET ELEMENTS | " + this.table.sumOfAllBucketElements ) ;
		
		return sb.toString() ;
	}
}
