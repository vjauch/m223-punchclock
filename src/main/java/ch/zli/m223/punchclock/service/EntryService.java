package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntryService {
    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        if (entry.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Crate Entry: Entry ID must not be set!");
        }
        return entryRepository.saveAndFlush(entry);
    }

    public Entry updateEntry(Entry entry) {
        if (entryRepository.existsById(entry.getId())) {
            return entryRepository.saveAndFlush(entry);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update Entry: Entry with ID {} does not exist!");
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }
}
