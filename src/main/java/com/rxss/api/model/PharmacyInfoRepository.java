package com.rxss.api.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyInfoRepository extends CrudRepository<PharmacyInfo, Long> {}
