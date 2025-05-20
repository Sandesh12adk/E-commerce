package com.example.E_commerce.repo;

import com.example.E_commerce.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends CrudRepository<Address,Integer> {

    @Query(value = "SELECT * FROM address WHERE user_id= :userId",nativeQuery = true)
    public Optional<Address> finByUserId(@Param("userId") int userId);
}
