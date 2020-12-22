package com.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compare.LocationStatisticsChainedComparator;
import com.comparingCases.Casescomparator;
import com.models.LocationStatistics;
import com.services.DataService;

import java.util.Collections;
import java.util.List;

@Controller
public class Control {

    @Autowired
    DataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStatistics> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
    @GetMapping("/top/{maximum}")
    public String top(@PathVariable("maximum") int maximum,Model model )
    {
    	List<LocationStatistics> all = coronaVirusDataService.getAllStats();
    	Casescomparator a=new Casescomparator();
		Collections.sort(all, new LocationStatisticsChainedComparator(
                new Casescomparator() )
        );
		List<LocationStatistics> top=all.subList(0,maximum);
    	model.addAttribute("maxCase",top);
    	return "home2";
    }
}
