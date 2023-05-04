package com.central.backend.service;

import com.central.common.model.KpnMovie;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;


public interface IKpnMovieService extends ISuperService<KpnMovie> {
    List<KpnMovie> getKpnMovie(Map<String, Object> params);
}
