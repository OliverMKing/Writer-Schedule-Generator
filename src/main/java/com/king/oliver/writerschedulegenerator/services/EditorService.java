package com.king.oliver.writerschedulegenerator.services;

import com.king.oliver.writerschedulegenerator.model.Editor;

public interface EditorService extends CrudService<Editor, Long> {

    Editor findByName(String name);
}
