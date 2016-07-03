package de.ratdev.mood_tracker.api;

import de.ratdev.mood_tracker.api.model.ChartData;
import de.ratdev.mood_tracker.service.ChartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EisenRatte on 03.07.2016.
 */
@RestController
@RequestMapping(path = "chartData", method = RequestMethod.GET)
public class ChartDataResource {

    @Autowired
    private ChartDataService chartDataService;

    @RequestMapping(path = "/")
    public ChartData getChartData(){
        return  chartDataService.getChartData();
    }
}
