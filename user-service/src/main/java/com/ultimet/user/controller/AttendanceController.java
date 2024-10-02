package com.ultimet.user.controller;

import com.ultimet.user.base.BaseController;
import com.ultimet.user.base.BaseResponse;
import com.ultimet.user.entity.User;
import com.ultimet.user.service.AttendanceService;
import com.ultimet.user.wrapper.request.AttendanceForm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("attendance")
public class AttendanceController extends BaseController {

    private final AttendanceService attendanceService;

    @PostMapping("/checkIn")
    public BaseResponse checkIn(@RequestBody AttendanceForm attendanceForm, HttpServletRequest request) {
        log.info("Executing checkIn() with : {}", attendanceForm);
        User user = super.getUserNameByHeader(request);
        if (user == null) {
            return new BaseResponse().setUnauthorized();
        }
        return attendanceService.checkIn(attendanceForm, user);
    }

    @PostMapping("/checkOut")
    public BaseResponse checkOut(@RequestBody AttendanceForm attendanceForm, HttpServletRequest request) {
        log.info("Executing checkOut() with : {}", attendanceForm);
        User user = super.getUserNameByHeader(request);
        if (user == null) {
            return new BaseResponse().setUnauthorized();
        }
        return attendanceService.checkOut(attendanceForm, user);
    }

    @GetMapping("/getAttendanceHistory")
    public BaseResponse getAttendanceHistory(HttpServletRequest request) {
        log.info("Executing getAttendanceHistory()");
        User user = super.getUserNameByHeader(request);
        if (user == null) {
            return new BaseResponse().setUnauthorized();
        }
        return attendanceService.getAttendanceHistory(user);
    }
}
