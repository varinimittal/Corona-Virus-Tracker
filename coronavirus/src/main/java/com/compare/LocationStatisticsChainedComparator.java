package com.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.models.LocationStatistics;

public class LocationStatisticsChainedComparator implements Comparator<LocationStatistics> {
	 
    private List<Comparator<LocationStatistics>> listComparators;
 
    
    public LocationStatisticsChainedComparator(Comparator<LocationStatistics> ...comparators) {
        this.listComparators = Arrays.asList(comparators);
    }
 
    
    public int compare(LocationStatistics emp1, LocationStatistics emp2) {
        for (Comparator<LocationStatistics> comparator : listComparators) {
            int result = comparator.compare(emp1, emp2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
