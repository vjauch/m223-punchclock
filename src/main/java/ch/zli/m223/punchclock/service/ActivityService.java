package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.repository.ActivityRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        if (!activityRepository.exists(Example.of(activity))) {
            return activityRepository.saveAndFlush(activity);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Crate activity: Activity '" + activity.getName() + "' already exists!");
        }
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }
}
