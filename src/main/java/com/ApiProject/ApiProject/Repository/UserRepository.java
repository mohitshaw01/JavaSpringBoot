package com.ApiProject.ApiProject.Repository;

import com.ApiProject.ApiProject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE u.email = :email")
    UserModel findByEmail(@Param("email") String email);

}
