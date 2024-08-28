package com.ultimet.user.service;

import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.entity.Attendance;
import com.ultimet.user.entity.User;
import com.ultimet.user.mapper.AttendanceMapper;
import com.ultimet.user.repository.AttendanceRepository;
import com.ultimet.user.repository.UserRepository;
import com.ultimet.user.wrapper.request.AttendanceForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final UserRepository userRepository;

    public BaseResponse checkIn(AttendanceForm attendanceForm) {
        BaseResponse response = new BaseResponse();
        try {
            Optional<User> optionalUser = userRepository.findById(attendanceForm.getUserId());
            if (optionalUser.isEmpty()) {
                return response.set(302, "User not found");
            }
            Attendance attendance = attendanceRepository.save(attendanceMapper.toEntity(attendanceForm,
                    optionalUser.get()));
            response.set(200, "Checked in successfully", attendanceMapper.toDto(attendance));
        } catch (Exception e) {
            log.error("Exception while checkIn() : ", e);
            response.setSomethingWentWrong();
        }
        return response;
    }

    public BaseResponse checkOut(AttendanceForm attendanceForm) {
        BaseResponse response = new BaseResponse();
        try {
            if (attendanceForm.getId() == null) {
                return response.set(302, "Invalid input");
            }
            Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendanceForm.getId());
            if (optionalAttendance.isEmpty()) {
                return response.set(302, "No record found");
            }
            if (attendanceForm.getCheckOut() == null) {
                return response.set(302, "Invalid checkOut date");
            }
            Attendance attendance = optionalAttendance.get();
            attendance.setCheckOut(attendanceForm.getCheckOut());
            attendance = attendanceRepository.save(attendance);
            response.set(200, "Checked out successfully", attendanceMapper.toDto(attendance));
        } catch (Exception e) {
            log.error("Exception while checkIn() : ", e);
            response.setSomethingWentWrong();
        }
        return response;
    }

    public BaseResponse getAttendanceHistory(User user) {
        BaseResponse response = new BaseResponse();
        try {
            List<Attendance> attendanceList;
            attendanceList = attendanceRepository.findAllByUserOrderByDateDesc(user);
            if (attendanceList.isEmpty()) {
                return response.set(200, "No record found");
            }
            response.set(200, "Success", attendanceList.stream().map(attendanceMapper::toDto));
        } catch (Exception e) {
            log.error("Exception while getAttendanceHistory() : ", e);
            response.setSomethingWentWrong();
        }
        return response;
    }
}
