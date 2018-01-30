package com.ms.cas.repository;

import com.ms.cas.entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by wuketao on 2018/1/30.
 */
@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResource,Long>,JpaSpecificationExecutor<RoleResource> {
}
