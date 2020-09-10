package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * Exposes a list of activities
     * @return all activities
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getAllActivities() {
        return activityService.findAll();
    }

    /**
     * Creates an activity
     * @param activity the activity to be created
     * @return the created activity
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@Valid @RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

}
