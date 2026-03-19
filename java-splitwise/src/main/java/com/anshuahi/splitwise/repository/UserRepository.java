package com.anshuahi.splitwise.repository;

import com.anshuahi.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByName(String phone);
    List<User> findAllByPhone(String phone);
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE %:prefix% OR u.phone LIKE %:prefix%")
    List<User> searchByNameOrPhonePrefix(@Param("prefix") String prefix);
//    @Query("""
//        SELECT u FROM User u
//        WHERE LOWER(u.name) LIKE LOWER(CONCAT(:prefix, '%'))
//           OR u.phone LIKE CONCAT(:prefix, '%')
//    """)
//    List<User> searchUsers(@Param("prefix") String prefix);
}
