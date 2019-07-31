public class DatabaseMine implements DatabaseInterface{
	private MyHashMap<String, String> dataB;

	public DatabaseMine (){//creates database without initial cap
		dataB = new MyHashMap<String, String>();
	}
	public DatabaseMine (int N){//creates with initial cap
		dataB = new MyHashMap<String, String>(N);
	}
	
	public String save(String plainPassword, String encryptedPassword){//save the element in th database
		String oldV="";
		if(dataB.containsKey(encryptedPassword)==false){//if its not in the database put it in there
			dataB.put(encryptedPassword,plainPassword);
			return null;
		}
		else{//if it is in the database replace it 
			oldV=dataB.replace(encryptedPassword, plainPassword);
			return oldV;
		}
		
	}
	public String decrypt(String encryptedPassword){//takes in a hash a finds the password associated with it
		String password="";
		if (dataB.containsKey(encryptedPassword)==true){
			password= dataB.get(encryptedPassword);
			return password;
		}
		else{
			return null;
		}
		
	}
	public int size(){//number of elements
		return dataB.size();
	}

	public void printStatistics(){//prints satistics 
		System.out.println("*** DatabaseMine Statistics ***");
		System.out.println("Size is "+this.size()+" passwords");
		System.out.println("Number of Indexes "+dataB.capacity());
		System.out.println("Load Factor is "+dataB.currentLoadFactor());
		System.out.println("Average Number of Probes is "+dataB.avgProb());
		System.out.println("Number of displacements (due to collisions) "+dataB.getDis());
		System.out.println("*** End DatabaseMine Statistics ***");

	}

	public MyHashMap getDataB (){//gain access to the hashmap
		return this.dataB;
	}
	
}















