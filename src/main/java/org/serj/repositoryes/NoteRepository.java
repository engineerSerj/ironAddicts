package org.serj.repositoryes;

import org.serj.domains.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Integer> {

    List<Note> findByTag(String tag);
}
