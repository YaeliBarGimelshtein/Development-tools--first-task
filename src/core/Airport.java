package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Airport {
	private String name;
	private  List <Flight> departures;
	private List <Flight> arrivals;
	
	public Airport(String name) {
		this.name=name;
		departures=new ArrayList<Flight>();
		arrivals=new ArrayList<Flight>();
	}
	
	public void addFlight(Flight flight) {
		if(flight.getKind().equalsIgnoreCase("Arrival")) {
			arrivals.add(flight);
		}else
			departures.add(flight);
	}

	public void addFlight(Scanner scan) throws ParseException {
		System.out.println("Enter flight details");
		scan.nextLine();
		Flight flight =new Flight(scan);
		addFlight(flight);
	}
	

	public void writeToFile(File f) throws FileNotFoundException {
		PrintWriter writer=new PrintWriter(f);
		writer.println(this.name);
		writer.println(departures.size());
		for (int i = 0; i < departures.size(); i++) {
			departures.get(i).write(writer);
		}
		writer.println(arrivals.size());
		for (int i = 0; i < arrivals.size(); i++) {
			arrivals.get(i).write(writer);
		}
		writer.close();
	}

	public void readFromFile(File f) throws FileNotFoundException, ParseException {
		Scanner scan=new Scanner(f);
		this.name=scan.nextLine();
		int sizeDepartues=Integer.parseInt(scan.nextLine());
		for (int i = 0; i < sizeDepartues; i++) {
			Flight f1= new Flight();
			f1.read(scan);
			this.departures.add(i, f1);
		}
		int sizeArrivals=Integer.parseInt(scan.nextLine());
		for (int i = 0; i < sizeArrivals; i++) {
			Flight f1= new Flight();
			f1.read(scan);
			this.arrivals.add(i, f1);
		}
	}

	public String showDepartures() {
		if (departures.size() == 0) {
			String str="No departures yet";
			return str;
		} else {
			sortFlights(departures);
			StringBuffer sb = new StringBuffer("Here are all the departures:\n");
			for (int i = 0; i < departures.size(); i++) {
				sb.append(departures.get(i).toString());
			}
			String allDepartures = sb.toString();
			return allDepartures;
		}
	}
	
	public String showArrivals() {
		if (arrivals.size() == 0) {
			String str="No arrivals yet";
			return str;
		} else {
			sortFlights(arrivals);
			StringBuffer sb = new StringBuffer("Here are all the arrivals:\n");
			for (int i = 0; i < arrivals.size(); i++) {
				sb.append(arrivals.get(i).toString());
			}
			String allArrivals = sb.toString();
			return allArrivals;
		}
	}
	
	Comparator<Flight> compareByDate=new Comparator<Flight>() {
		public int compare(Flight flight1,Flight flight2) {
			return flight1.getDateAndTime().compareTo(flight2.getDateAndTime());
		 }
	};
	
	private void sortFlights(List <Flight> flights) {
		Collections.sort(flights,compareByDate);
	}
	
	
	public String toString() {
		return "Airport: " + name +"has "+ departures.size()+" departures planned and "+arrivals.size()+
				"arrivals planned";
	}

	
}
