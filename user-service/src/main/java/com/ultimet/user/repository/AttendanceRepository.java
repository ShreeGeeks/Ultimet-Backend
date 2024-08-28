package com.ultimet.user.repository;

import com.ultimet.user.entity.Attendance;
import com.ultimet.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findAllByUserOrderByDateDesc(User user);
}
