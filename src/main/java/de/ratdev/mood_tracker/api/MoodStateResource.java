package de.ratdev.mood_tracker.api;

import de.ratdev.mood_tracker.persistance.model.MoodState;
import de.ratdev.mood_tracker.service.MoodStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EisenRatte on 03.07.2016.
 */
@RestController
@RequestMapping(path = "moodState")
public class MoodStateResource {


    @Autowired
    private MoodStateService moodStateService;
    @CacheEvict
    @RequestMapping(path = "add", method = RequestMethod.POST)
    public void add(@Validated MoodState moodState, HttpServletResponse response) throws IOException {
        moodStateService.addMoodState(moodState);
        response.sendRedirect("/");
    }
}
