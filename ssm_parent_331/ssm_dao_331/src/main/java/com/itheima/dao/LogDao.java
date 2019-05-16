package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface LogDao {

    @Insert("insert into sys_log values (log_seq.nextval,#{visitTime},#{username},#{ip},#{method})")
    public void save(SysLog log);
}
