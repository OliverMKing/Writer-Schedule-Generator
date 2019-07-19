package com.king.oliver.writerschedulegenerator.services;

import com.king.oliver.writerschedulegenerator.model.Writer;

public interface WriterService extends CrudService<Writer, Long> {

    Writer findByName(String name);
}
