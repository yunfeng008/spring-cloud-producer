package com.trecco.dzp.service;

import com.trecco.dzp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    User save(User user);

    @Query("select u from User u where u.userName like ?1")
    List<User> findByAndSort(String userName, Sort sort);

    /**
     * Query creation
     * this translates into the following query:
     * select u from User u where u.idCard = ?1
     */
    User findByIdCard(String idCard);

    /**
     * Native Queries
     * The @Query annotation allows for running native queries by setting the nativeQuery flag to true
     * @param idCard
     * @return
     */
    @Query(value = "select * from user where ID_CARD = ?1", nativeQuery = true)
    User findByIdCard2(String idCard);

    /**
     * Using @Query
     * @param idCard
     * @return
     */
    @Query("select u from User u where u.idCard = ?1")
    User findByIdCard3(String idCard);

    /**
     * Declare native count queries for pagination at the query method by using @Query
     * @param name
     * @param pageable
     * @return
     */
    @Query(value = "select * from user where USER_NAME = ?1",
            countQuery = "select count(*) from user where USER_NAME = ?1",
            nativeQuery = true)
    Page<User> findByLastNameWithPageable(String name, Pageable pageable);

    /**
     * Using Named Parameters
     * @param firstName
     * @param lastName
     * @return

    @Query("select u from User u where u.firstName = :firstName or u.lastName = :lastName")
    List<User> findByFirstNameOrLastName(@Param("firstName") String firstName,
                                         @Param("lastName") String lastName);

     */

    /**
     * find all users
     * @param sort
     * @return
     */
    @Override
    List<User> findAll(Sort sort);

    /**
     * update a usr by Modifying Queries
     * @param userName
     * @param idCard
     * @return
     */
    @Modifying
    @Query("update User u set u.userName = ?1 where u.idCard = ?2")
    int updateUser(String userName, String idCard);

    /**
     * delete a user by idCard
     * @param idCard
     */
    void deleteByIdCard(String idCard);

    /**
     * Using a derived delete query
     * @param idCard
     */
    @Modifying
    @Query("delete from User u where u.idCard = ?1")
    void deleteByIdCard2(String idCard);

    /**
     * delete a user by id
     * @param id
     */
    @Modifying
    @Query(value = "delete from user where id = ?1", nativeQuery = true)
    void deleteById(int id);
}