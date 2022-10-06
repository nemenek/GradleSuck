package travel.service;

import java.util.Comparator;

import travel.domain.Trip;

public class SortTrip implements Comparator<Trip>{
	public int compare(Trip t1, Trip t2) {
		if(t1.getStartDate().isBefore(t2.getStartDate())) {
			return 1;
		}
		else if(t1.getStartDate().isAfter(t2.getStartDate())) {
			return -1;
		}
		return 0;
	}
}