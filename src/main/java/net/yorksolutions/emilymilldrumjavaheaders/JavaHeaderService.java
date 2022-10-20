package net.yorksolutions.emilymilldrumjavaheaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class JavaHeaderService {

    private final HttpServletRequest request;
    private static String remoteAddr = "";
    public JavaHeaderService(HttpServletRequest request) {
        this.request = request;
    }

//    public String ip(){
//        if (request != null){
//            remoteAddr = request.getHeader("X-FORWARDED-FOR");
//            if (remoteAddr == null || "".equals(remoteAddr)) {
//                remoteAddr = request.getRemoteAddr();
//            }
//        }
//        return remoteAddr;
//    }

    public Map<String, String> getRequestHeadersInMap() {

        Map<String, String> result = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }
        return result;
    }

    public String getClientIp() {

        final String LOCALHOST_IPV4 = "127.0.0.1";
        final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

        String ipAddress = request.getHeader("X-Forwarded-For");
        if(!StringUtils.hasLength(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if(!StringUtils.hasLength(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if(!StringUtils.hasLength(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if(StringUtils.hasLength(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
        return ipAddress;
    }

    public String date(){
        Date date = new Date();
        return date.toString();
    }

    public String md5(String incString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytesOfMessage = incString.getBytes(StandardCharsets.UTF_8);
        byte[] theMD5digest = md.digest(bytesOfMessage);

        //converting byte array into signum representation
        BigInteger no = new BigInteger(1, theMD5digest);
        //converting message digest into hex value
        StringBuilder sb = new StringBuilder();
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = sb.append("0").append(hashtext).toString();
        }

        return hashtext;
        //return theMD5digest.toString();
    }

//    public String jsonTest(String jsonInc) {
//        //
//        //        System.out.println(valid + " " + jsonInc);
//        boolean valid = false;
////        const json = '{"result":true, "count":42}';
//        String error = "invalid json";
//        try {
//            JSONParser jsonParsing = new JSONParser(jsonInc);
//            JacksonJsonParser
//                    valid = true;
//            return "Valid";
//        } catch (JsonParseException,IllegalArgumentException){
//            valid = false;
//            System.out.println(error);
//            return "Invalid";
//        }
////        const str = JSON.stringify(obj);
////        console.log(str);
//        // expected output: {"result":true,"count":42}
//        // r
//    }


    // https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/api/org/springframework/boot/json/package-summary.html
    // the answer is here somewhere lol
    public boolean jsonTest(String json) {
        try {
            JSONParser jsonParsing = new JSONParser(json);
//            final JsonParser parser = (JsonParser) new ObjectMapper().getFactory()
//                    .createParser(json);
            while (jsonParsing != null) {
            }
            return true;
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
            return false;
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//            return false;
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
            return  false;
        }
    }


}