package com.ms.cas.repository;

import com.ms.cas.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by wuketao on 2018/1/30.
 */
@Repository
public interface ApplicationRepository  extends JpaRepository<Application, String>, JpaSpecificationExecutor<Application> {
}
