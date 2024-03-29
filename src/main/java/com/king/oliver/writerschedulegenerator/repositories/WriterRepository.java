package com.king.oliver.writerschedulegenerator.repositories;

import com.king.oliver.writerschedulegenerator.model.Writer;
import org.springframework.data.repository.CrudRepository;

public interface WriterRepository extends CrudRepository<Writer, Long> {

    Writer findByName(String name);
}
