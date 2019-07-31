import java.util.*;
public class DatabaseStandard implements DatabaseInterface {
	private HashMap<String, String> dataB;
	private int initialC =16;
	
	public DatabaseStandard (){//creates database without initial cap
		 dataB = new HashMap<String, String>();

	}
	public DatabaseStandard (int N){//creates with initial cap
		initialC=N;
		 dataB = new HashMap<String, String>(N);

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
		System.out.println("*** DatabaseStandard Statistics ***");
		System.out.println("Size is "+this.size()+" passwords");
		System.out.println("Initial Number of Indexes when created "+ initialC);
		System.out.println("*** End DatabaseStandard Statistics ***");

	}

	public HashMap getDataB (){//used to gain access to the hashmap
		return this.dataB;
	}
}