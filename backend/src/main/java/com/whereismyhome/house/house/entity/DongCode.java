package com.whereismyhome.house.house.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name="dongcode")
@NoArgsConstructor
public class DongCode {
    @Id @Column(name="dongCode")
    private String dongCode;

    @Column(name="sidoName")
    private String sidoName;

    @Column(name="gugunName")
    private String gugunName;

    @Column(name="dongName")
    private String dongName;
}
