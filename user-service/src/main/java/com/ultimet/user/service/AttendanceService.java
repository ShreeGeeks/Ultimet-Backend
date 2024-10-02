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

    public BaseResponse checkIn(AttendanceForm attendanceForm, User user) {
        BaseResponse response = new BaseResponse();
        try {
            Attendance attendance = attendanceRepository
                    .findByUserIdAndDateAndCheckOutIsNull(user.getId(), attendanceForm.getDate());
            if (attendance != null) {
                return response.set(302, "You have already checked in for this date");
            }
            attendance = attendanceRepository.save(
                    attendanceMapper.toEntity(attendanceForm, user));
            response.set(200, "Checked in successfully", attendanceMapper.toDto(attendance));
        } catch (Exception e) {
            log.error("Exception while checkIn() : ", e);
            response.setSomethingWentWrong();
        }
        return response;
    }

    public BaseResponse checkOut(AttendanceForm attendanceForm, User user) {
        BaseResponse response = new BaseResponse();
        try {
            if (attendanceForm.getId() == null) {
                return response.set(302, "Invalid input");
            }
            Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendanceForm.getId());
            if (optionalAttendance.isEmpty()) {
                return response.set(302, "No record found");
            }
            Attendance attendance = optionalAttendance.get();
            if (attendanceForm.getCheckOut() == null
                    || attendanceForm.getCheckOut().before(attendance.getCheckIn())) {
                return response.set(302, "Invalid check-out date");
            }

            attendance.setCheckOut(attendanceForm.getCheckOut());
            attendance.setTotalHours(attendance.getTotalHours());
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
