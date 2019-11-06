package com.smart.db.mapper;

import com.smart.db.model.MplTask;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author lwq
 */
public interface IMplTaskDAO {

    List<MplTask> selectJobByName(@Param("taskName") String taskName);
}
