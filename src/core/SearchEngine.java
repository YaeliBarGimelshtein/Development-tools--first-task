package core;

import java.util.Scanner;

public class SearchEngine {
	private boolean byKind, byAirline, byOrigin,ByDestenation, byFlighNumber, byDate;
	private String kind,airline,origin,destenation, flightNumber,dateFirst, dateLast;
	
	public SearchEngine(Scanner scan) {
		System.out.println("If you would like to search by kind enter true else false");
		byKind=scan.nextBoolean();
		if(byKind) {
			System.out.println("Arrivals or Departures?");
			kind=scan.nextLine();
		}
		System.out.println("If you would like to search by Airline enter true else false");
		byAirline=scan.nextBoolean();
		if(byAirline) {
			System.out.println("which airline?");
			airline=scan.nextLine();
		}
		System.out.println("If you would like to search by Origin enter true else false");
		byOrigin=scan.nextBoolean();
		if(byOrigin) {
			System.out.println("which origin?");
			origin=scan.nextLine();
		}
		System.out.println("If you would like to search by Destenation enter true else false");
		ByDestenation=scan.nextBoolean();
		if(ByDestenation) {
			System.out.println("which destination?");
			destenation=scan.nextLine();
		}
		System.out.println("If you would like to search by Fligh Number enter true else false");
		byFlighNumber=scan.nextBoolean();
		if(byFlighNumber) {
			System.out.println("which flight number?");
			flightNumber=scan.nextLine();
		}
		System.out.println("If you would like to search by Date enter true else false");
		byDate=scan.nextBoolean();
		if(byDate) {
			System.out.println("which date first? dd/mm/yyyy hh:mm");
			dateFirst=scan.nextLine();
			System.out.println("which date last? dd/mm/yyyy hh:mm");
			dateLast=scan.nextLine();
		}
	}
	public SearchEngine(boolean byKind, boolean byAirline, boolean byOrigin, boolean ByDestenation ,
			boolean byFlighNumber, boolean byDate, String kind, String airline, String origin, String destenation,
			String flightNumber,String datefirst, String dateLast) {
		this.byKind=byKind;
		this.byAirline=byAirline;
		this.byOrigin=byOrigin;
		this.ByDestenation=ByDestenation;
		this.byFlighNumber=byFlighNumber;
		this.byDate=byDate;
		this.kind=kind;
		this.airline=airline;
		this.origin=origin;
		this.destenation=destenation;
		this.flightNumber=flightNumber;
		this.dateFirst=datefirst;
		this.dateLast=dateLast;
	}
	

	public boolean isByKind() {
		return byKind;
	}


	public boolean isByAirline() {
		return byAirline;
	}


	public boolean isByOrigin() {
		return byOrigin;
	}


	public boolean isByDestenation() {
		return ByDestenation;
	}


	public boolean isByFlighNumber() {
		return byFlighNumber;
	}


	public boolean isByDate() {
		return byDate;
	}
	public String getKind() {
		return kind;
	}
	
	public String getAirline() {
		return airline;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestenation() {
		return destenation;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	
	public String getDatefirst() {
		return dateFirst;
	}
	public String getDatelast() {
		return dateLast;
	}

	
	
	
}
