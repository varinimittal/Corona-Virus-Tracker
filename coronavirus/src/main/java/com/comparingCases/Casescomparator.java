package com.comparingCases;

import java.util.Comparator;

import com.models.LocationStatistics;

public class Casescomparator implements Comparator<LocationStatistics>
{
	public int compare(LocationStatistics  ar0,LocationStatistics  ar1)
	{
		return ar0.latestTotalCases-ar1.latestTotalCases;
	}
	
}