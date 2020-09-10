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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getAllActivities() {
        return activityService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@Valid @RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

}
