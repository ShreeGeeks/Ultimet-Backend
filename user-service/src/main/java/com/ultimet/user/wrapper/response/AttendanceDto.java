package com.ultimet.user.wrapper.response;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceDto {

    private Integer id;

    private UserDto userDto;

    private Date date;

    private Date checkIn;

    private Date checkOut;

    private String latitude;

    private String longitude;

    private String remarks;
}
