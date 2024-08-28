package com.ultimet.user.wrapper.request;

import com.ultimet.user.base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AttendanceForm {

    private Integer id;

    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkIn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkOut;

    private String latitude;

    private String longitude;

    private String remarks;

    public boolean validate(BaseResponse response) {
        if (this.userId == null) {
            response.set(302, "User id cannot be null");
            return false;
        }
        if (this.date == null || !this.date.equals(new Date())) {
            response.set(302, "Invalid date");
            return false;
        }
        if (this.checkIn == null || !this.checkIn.equals(new Date())) {
            response.set(302, "Invalid checkIn date");
            return false;
        }
        if (this.latitude == null || this.latitude.isEmpty()) {
            response.set(302, "Invalid location detail");
            return false;
        }
        if (this.longitude == null || this.longitude.isEmpty()) {
            response.set(302, "Invalid location detail");
            return false;
        }
        return true;
    }
}
