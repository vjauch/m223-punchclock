package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    /**
     * Exposes a list of entries
     * @return all entries
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    /**
     * Exposes a list of entries
     * @return al entries created by the current principal
     */
    @GetMapping("my")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getEntriesOfUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return entryService.findAllOfUser(username);
    }

    /**
     * Creates an entry
     * @param entry the entry to be created
     * @return the created entry
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    /**
     * Updates an existing entry
     * @param entry the updated version of the entry
     * @return the updated entry
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@Valid @RequestBody Entry entry) {
        return entryService.updateEntry(entry);
    }

    /**
     * Deletes an entry
     * @param id the id of the entry to be deleted
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
    }
}
