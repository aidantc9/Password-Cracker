import java.util.*;
import java.io.*;
public class PasswordCracker {
	
	public void createDatabase (ArrayList<String> commonPasswords, DatabaseInterface database) throws UnsupportedEncodingException{
		Sha1 sha = new Sha1();
		String temp="";
		String tempH="";
		String temp2="";
		for (int i =0; i< commonPasswords.size();i++){
			//adding normal password
			
			temp=commonPasswords.get(i);
			tempH=sha.hash(temp);
			database.save(temp,tempH);

			
			for (int j =0;j<5;j++){//adding some of the combinations
				temp=commonPasswords.get(i);

				if (j==0){
					temp=Capitalize(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addYear(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addAt(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);
					
					temp=add3(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add1(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					

				}
				else if(j==1){

					temp=addYear(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add1(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addAt(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add3(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=Capitalize(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);
					
				}
				else if(j==2){
					
					temp=addAt(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=Capitalize(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add1(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addYear(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add3(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);
					
				}
				else if(j==3){

					temp=add3(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=Capitalize(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addYear(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addAt(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add1(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);


				}
				else{
					temp=add1(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addYear(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=Capitalize(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=add3(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);

					temp=addAt(temp);
					tempH=sha.hash(temp);
					database.save(temp,tempH);
				}
				
				

				
				}
			}


		}
		
	


	
	public String Capitalize(String temp){//methods that capitalizes the first letter of a string 
		temp= temp.substring(0,1).toUpperCase() + temp.substring(1);
		return temp;
	}
	public String addYear(String temp){//adds a year at the end of the string 
		temp=temp+"2018";
		return temp;
	}
	public String addAt (String temp){//swaps all a's in a string with @'s'
		temp=temp.replaceAll("a","@");
		return temp;
	}
	public String add3 (String temp){//swaps all e's in a string with 3's'
		temp=temp.replaceAll("e","3");
		return temp;
	}
	public String add1 (String temp){//swaps all i's in a string with 1's'
		temp=temp.replaceAll("i","1");
		return temp;
	}

	

	public String crackPassword(String encryptedPassword, DatabaseInterface database){//takes the encrypted password and does a search of the map to find the value
		String password="";
		password=database.decrypt(encryptedPassword);
		if (password==null){
			System.out.println("Could not find password");
		}
		return password;
	} 
}