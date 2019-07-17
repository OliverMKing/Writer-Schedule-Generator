package com.king.oliver.writerschedulegenerator.repositories;

import com.king.oliver.writerschedulegenerator.model.Editor;
import org.springframework.data.repository.CrudRepository;

public interface EditorRepository extends CrudRepository<Editor, Long> {

    Editor findByName(String name);
}
