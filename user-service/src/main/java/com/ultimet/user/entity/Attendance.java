package com.ultimet.user.entity;

import com.ultimet.user.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
@EqualsAndHashCode(callSuper = true)
public class Attendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date")
    private Date date;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @Column(name = "total_minutes")
    private Double totalMinutes;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "remarks")
    private String remarks;

    public Double getTotalMinutes() {
        if (checkIn == null || checkOut == null) {
            return 0.0;
        }
        double totalMills = (double) (checkOut.getTime() - checkIn.getTime());
        System.out.println(totalMinutes);
        return totalMills / (60 * 1000);
    }
}
