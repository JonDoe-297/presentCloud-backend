package com.yunbanke.daoyun.infrastructure.Persistence;

import com.yunbanke.daoyun.infrastructure.entity.FilePath;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilepathRepository extends CrudRepository<FilePath, Integer> {
    public List<FilePath> getFilePathsByUserid(Integer userId);
    public List<FilePath> getFilePathByFilepathid(Integer filePathId);
}
