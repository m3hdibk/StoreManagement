package com.mehdi.storemanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticsDashboardResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -7822160093790655597L;

    private LocalDate startDate;
    private LocalDate endDate;
    private double result;



}
