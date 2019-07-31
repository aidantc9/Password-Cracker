public class MyHashMap <K,V>{//Note that this implementation uses a extendable array
	private  int initialCap=840347; 
	private final float loadFactor = 0.75f;
	private int size;
	private Entry<K, V>[] buckets;//list of entries 
	private int maxsize;// the current max allowed ie capacity*load factor
	private int sumProbs;//sum of all the probs 
	private int displacements;//number of displacements 


	private static class Entry <k,v> {//entry class that holds the entry object
        private  k key;
		private v value;
		private Entry <k,v> next;

		public Entry (k key, v value){
			this.key = key;
			this.value = value;

		}
    }

	public MyHashMap (){//creates the hashmap without a given initial cap 
		buckets= (Entry<K,V>[]) new Entry[initialCap];//creats the list of initial cap size
		size=0;
		maxsize=(int) (initialCap*loadFactor);
	}
	public MyHashMap (int N){
		buckets= (Entry<K,V>[]) new Entry[N];//creates the list with  initial size n 
		size=0;
		maxsize=(int) (initialCap*loadFactor);
		initialCap=N;
	}
	
	public int linearProb(K key){//finds the hascode of string note that abs is used because some keys can have neg hash codes and you cant have negative indexs
		return java.lang.Math.abs(key.hashCode() % buckets.length);
	}
	


	public V get(K key){//get the value for that key in the map
		if (key == null) {//this check just makes sure that if the key is null it does allow the method to go through 
			throw new IllegalArgumentException("no null keys allowed");
		}
		
		Entry<K, V> en = buckets[linearProb(key)];//finds initial position 
		while (en != null && !key.equals(en.key)){//if there is a element in that pos then keep moving forward in the list ie linear prob
			en=en.next;//going to the next element 
		}
		if (en==null){//element couldnt be found 
			return null;
		}
		else{
			return en.value;//return the correct value 
		}

		
	}
	
	public V put (K key, V value){//putting a element in the map
		if (key == null) {
			throw new IllegalArgumentException("no null keys allowed");
		}
		
		int index = linearProb(key);//finds initial intext
		sumProbs++;//first prob
		Entry<K, V> en = buckets[index];
		V temp;
		if (en!=null){//if the element is null than a displacement must occur 
			displacements++;
		}
		while (en!= null){//same as for get going through the list in a linear probing fashion and finding the correct place to put it
			if (en.key==key){
				temp=en.value;
				en.value=value;
				return temp;
			}
			en=en.next;
			sumProbs++;//at the end of each loop session a prob has occured 
		}
		if (size++ > maxsize){//this is used to check if we have reach to max size allowed for the map and therefore it calls the rehash method which rebuilds the map with a larger size
			reHash();
			index = linearProb(key);
		}
		en = new Entry<K, V>(key, value);//adds the new element to the list 
      	en.next= buckets[index];
      	buckets[index] = en;
		size= size++;

		return null;
		


	}
	
	public void reHash(){
		int newCap =2*buckets.length +1;//increasing the size by 2 and adding one 
		maxsize = (int) (newCap*loadFactor);//recalculating the new max size
		Entry<K, V>[] original = buckets;//the original list 
		buckets = (Entry<K,V>[]) new Entry[newCap]; //making the pointer of the old array point to a new larger array
		int index;
		int testD;
		Entry<K, V> next;
		Entry<K, V> en;
		sumProbs=0;
		for (int i =0;i<original.length;i++){
			en = original[i];//taking old element
			testD=0;
			
			while(en!=null){//this works like before and using linear probbing to keep moving the element forward in the list until a spot it found 
				index= linearProb(en.key);
				next = en.next;
              	en.next=buckets[index];
              	buckets[index] = en;
             	en = next;
             	sumProbs++;
             	testD++;
             		

				}
			if (testD>0){//if a displacement is required increse the value 
				displacements++;
			}
      		


		}

	}
	
	public boolean containsKey(K key){//checks if a key is in the hashmap
		if (key == null) {//same as before
			throw new IllegalArgumentException("no null keys allowed");
		}
		Entry<K, V> en = buckets[linearProb(key)];
		while (en != null){//keeps going through the mao till the item is found 
			if(key.equals(en.key)){
				return true;
			}
			en=en.next;
		}
			return false;

	}
	
	

	public V replace (K key, V value){//work like put and contains key combined 
		if (key == null) {
			throw new IllegalArgumentException("no null keys allowed");
		}
			int index = linearProb(key);
			Entry<K, V> en = buckets[index];
			V temp;
			while (en!= null){//go through the list trying to find the key
				if (en.key==key){//if the key is found replace it 
					temp=en.value;
					en.value=value;
					return temp;
				}
			en=en.next;
			}
			return null;
	}
	public int size(){//number of elements
		return size;
	}
	public int capacity(){// the amount the list can hold
		return buckets.length;
	}
	public double currentLoadFactor(){//the current load factor of the hashmap
		double si=size;
		double len=buckets.length;
		return si/len;
	}
	public double avgProb(){//takes the total number of probs and divides it by the number of elements 
		double si=size;
		return sumProbs/si;
	}
	public int getDis (){//get the number of displacements 
		return displacements;
	}

}