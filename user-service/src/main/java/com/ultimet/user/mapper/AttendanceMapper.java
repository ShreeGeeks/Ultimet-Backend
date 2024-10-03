package com.ultimet.user.mapper;

import com.ultimet.user.entity.Attendance;
import com.ultimet.user.entity.User;
import com.ultimet.user.utils.Utils;
import com.ultimet.user.wrapper.request.AttendanceForm;
import com.ultimet.user.wrapper.response.AttendanceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AttendanceMapper {

    private final UserMapper userMapper;

    public Attendance toEntity(AttendanceForm attendanceForm, User user) {
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(attendanceForm.getDate());
        attendance.setCheckIn(attendanceForm.getCheckIn());
        attendance.setCheckOut(attendanceForm.getCheckOut());
        attendance.setTotalMinutes(attendance.getTotalMinutes());
        attendance.setLatitude(attendanceForm.getLatitude());
        attendance.setLongitude(attendanceForm.getLongitude());
        attendance.setRemarks(attendanceForm.getRemarks());
        return attendance;
    }

    public AttendanceDto toDto(Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setId(attendance.getId());
        attendanceDto.setUserDto(userMapper.toDto(attendance.getUser()));
        attendanceDto.setDate(attendance.getDate());
        attendanceDto.setCheckIn(attendance.getCheckIn());
        attendanceDto.setCheckOut(attendance.getCheckOut());
        attendanceDto.setTotalHours(Utils.minuteToHour(attendance.getTotalMinutes()));
        attendanceDto.setLatitude(attendance.getLatitude());
        attendanceDto.setLongitude(attendance.getLongitude());
        attendanceDto.setRemarks(attendance.getRemarks());
        return attendanceDto;
    }
}
