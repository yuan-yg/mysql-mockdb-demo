package com.supernova.mysqlmockdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.supernova.mysqlmockdemo.models.UserEmail;

@Repository
public interface EmailRepository extends CrudRepository<UserEmail, Integer>  {	
}
