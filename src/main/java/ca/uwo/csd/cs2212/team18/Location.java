/**
 * Location class reads the list of cities from Open Weather Map API and creates
 * a text file with all locations listed as: City, Country code, ID #
 * With each city on a separate line
 */

package ca.uwo.csd.cs2212.team18;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

class Location {
	
	String name, countryCode, idNumber;

	public Location(String id, String nm, String c) {
		name = nm;
		countryCode = c;
		idNumber = id;
	}

	public static void main (String[] args) throws IOException {

		ArrayList<Location> locations = new ArrayList<Location>();
		URL url = new URL("http://openweathermap.org/help/city_list.txt");
		Scanner s = new Scanner (url.openStream());
		s.nextLine();
		
		while (s.hasNextLine()) {
			String str = s.nextLine();
			String[] tokens= str.split("\t");
			locations.add(new Location(tokens[0], tokens[1], tokens[4]));
		} 
		
		PrintWriter out = new PrintWriter("unorderedList.txt");
		for (int i = 0; i < locations.size(); i++) {
			Location value = locations.get(i);
			if(value.name.isEmpty()){
				out.println(value.countryCode + " [" 
						+ value.idNumber + "]");
			}
			else 
				out.println(value.name + ", " 
					+ value.countryCode + " [" 
					+ value.idNumber + "]");
		}
		
		out.close();
		s.close();   
	}
}