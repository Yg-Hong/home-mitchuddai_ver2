package com.whereismyhome.house.house.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="houseinfo")
public class HouseInfo {
    @Id
    private Long aptCode;
    private int buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyundongCode;


    private String dongCode;
    private String landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
}
