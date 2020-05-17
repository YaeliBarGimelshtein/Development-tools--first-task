package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
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

	public String searchFlights(Scanner scan) {
		int choice;
		String returnStr="";
		do {
			menu();
			choice=scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("plese enter the kind of flight you want to find (arrival/departure)");
				String value;
				scan.nextLine();
				value=scan.nextLine();
				if(value.equals("arrival")) {
					returnStr= arrivals.toString();
				}else
					returnStr= departures.toString();
				break;
			case 2:
				System.out.println("plese enter the airline youre looking for");
				String value2;
				value2=scan.nextLine();
				StringBuffer sb= new StringBuffer();
				for (int i = 0; i < arrivals.size(); i++) {
					if(arrivals.get(i).getAirline().equals(value2)) {
						sb.append(arrivals.get(i).toString());
					}
				}
				for (int i = 0; i < departures.size(); i++) {
					if(departures.get(i).getAirline().equals(value2)) {
						sb.append(departures.get(i).toString());
					}
				}
				returnStr= sb.toString();
				break;
			case 3:
				System.out.println("plese enter the origin youre looking for");
				String value3;
				scan.nextLine();
				value3=scan.nextLine();
				StringBuffer sb2= new StringBuffer();
				for (int i = 0; i < arrivals.size(); i++) {
					if(arrivals.get(i).getOrigin().equals(value3)) {
						sb2.append(arrivals.get(i).toString());
					}
				}
				for (int i = 0; i < departures.size(); i++) {
					if(departures.get(i).getOrigin().equals(value3)) {
						sb2.append(departures.get(i).toString());
					}
				}
				returnStr= sb2.toString();
				break;
			case 4:
				System.out.println("plese enter the destenation youre looking for");
				String value4;
				value4=scan.nextLine();
				StringBuffer sb3= new StringBuffer();
				for (int i = 0; i < arrivals.size(); i++) {
					if(arrivals.get(i).getDestination().equals(value4)) {
						sb3.append(arrivals.get(i).toString());
					}
				}
				for (int i = 0; i < departures.size(); i++) {
					if(departures.get(i).getDestination().equals(value4)) {
						sb3.append(departures.get(i).toString());
					}
				}
				returnStr= sb3.toString();
				break;
			case 5:
				System.out.println("plese enter the flight number youre looking for");
				String value5;
				value5=scan.nextLine();
				StringBuffer sb4= new StringBuffer();
				for (int i = 0; i < arrivals.size(); i++) {
					if(arrivals.get(i).getFlightNumber().equals(value5)) {
						sb4.append(arrivals.get(i).toString());
					}
				}
				for (int i = 0; i < departures.size(); i++) {
					if(departures.get(i).getFlightNumber().equals(value5)) {
						sb4.append(departures.get(i).toString());
					}
				}
				returnStr= sb4.toString();
				break;
			case 6:
				System.out.println("plese enter the dates youre looking for by year month day hours minutes");
				String valueFirst, valueLast;
				scan.nextLine();
				valueFirst = scan.nextLine();
				LocalDateTime fisrtDate = stringToDate(valueFirst);
				System.out.println("and now for the end time");
				valueLast = scan.nextLine();
				LocalDateTime lastDate = stringToDate(valueLast);
				StringBuffer sb5= new StringBuffer();
				for (int i = 0; i < arrivals.size(); i++) {
					if (arrivals.get(i).getDateAndTime().isAfter(fisrtDate)
							&& arrivals.get(i).getDateAndTime().isBefore(lastDate)) {
						sb5.append(arrivals.get(i).toString());
					}
				}

				for (int j = 0; j < departures.size(); j++) {
						if (departures.get(j).getDateAndTime().isAfter(fisrtDate)
								&& departures.get(j).getDateAndTime().isBefore(lastDate)) {
							sb5.append(departures.get(j).toString());
					}
				}
				returnStr= sb5.toString();
				break;
			case 7:
				break;
			default:
				System.out.println("you entered a wrong number, try again");
				break;
			}
				
		} while (choice!=7);
		return returnStr;
		
	}
	public String searchByKindArival() {
		return arrivals.toString();
	}
	public String searchByKindDepartue() {
		return departures.toString();
	}
	
	
	
	

	public static final int kind=1;
	public static final int airline=2;
	public static final int origin=3;
	public static final int destenation=4;
	public static final int date=6;
	public static final int number=5;
	public static final int exit=7;
	
	public static void menu() {
		System.out.println("to search by kind press "+kind);
		System.out.println("to search by airline press "+airline);
		System.out.println("to search by origin press "+origin);
		System.out.println("to search by destenation press "+destenation);
		System.out.println("to search by number press "+number);
		System.out.println("to search by date press "+date);
		System.out.println("to exit to main menu "+exit);
	}
	
	public LocalDateTime stringToDate(String dateandTime) {
		String[] dateAndTimeS= dateandTime.split(" ");
		String date=dateAndTimeS[0];
		String[] dateArray= date.split("/");
		int year= Integer.parseUnsignedInt(dateArray[2]);
		int month=Integer.parseUnsignedInt(dateArray[1]);
		int day=Integer.parseUnsignedInt(dateArray[0]);
		String time=dateAndTimeS[1];
		String[] timeArray= time.split(":");
		int hours=Integer.parseUnsignedInt(timeArray[0]);
		int minutes=Integer.parseUnsignedInt(timeArray[1]);
		LocalDateTime finalDate=LocalDateTime.of(year, month, day, hours, minutes);
		return finalDate;
	}
	
}
