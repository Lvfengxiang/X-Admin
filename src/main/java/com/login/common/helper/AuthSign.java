package com.login.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吕凤祥
 * @packge com.util.auth
 * @data 2019-02-19 11:55
 * @project CurrenCy-Cloud
 */
public class AuthSign {
    public  static String sign(String openId,Integer userId,String sessionKey) throws UnsupportedEncodingException {
        //过期时间 两天
        Date date=new Date(System.currentTimeMillis()+ 172800*86400000);
        //私钥及加密算法
        Algorithm algorithm= Algorithm.HMAC256("userId");
        //设置头部信息
        Map<String,Object>header=new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        //附带userName userId信息 ，生成签名
        return JWT.create().withHeader(header)
                .withClaim("userId",userId)
                .withClaim("sessionKey",sessionKey)
                .withExpiresAt(date)
                .sign(algorithm);
    }
    //校验token
    public  static  boolean verify(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC256("userId");
            JWTVerifier verifier= JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    //解密token参数
    public  static Map<String, Object> verifyToken(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC256("userId");
            JWTVerifier verifier= JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            Map<String, Claim> claimMap=jwt.getClaims();
            Map<String,Object> map=new HashMap();
            map.put("userId",claimMap.get("userId").asInt() );
            map.put("sessionKey",claimMap.get("sessionKey").asString() );
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }
}
