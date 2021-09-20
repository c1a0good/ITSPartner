package task.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface RequestService {
    String getClientIp(HttpServletRequest request);
    String getClientCountry(String ip) throws IOException, GeoIp2Exception;
}