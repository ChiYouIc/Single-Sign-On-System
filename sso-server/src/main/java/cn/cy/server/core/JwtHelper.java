package cn.cy.server.core;

import cn.cy.common.util.SpringUtil;
import cn.cy.server.config.properties.JwtProperties;
import cn.hutool.core.map.MapUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author: 开水白菜
 * @description: Json Web Token 工具
 * @create: 2021-08-29 17:30
 **/
@Slf4j
public class JwtHelper {

    private final String iis;

    private final Long expireTime;

    /**
     * 创建签名时所使用的密钥
     */
    private final String base64EncodedSecretKey;

    /**
     * 生成签名时所使用的加密算法
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private JwtHelper() {
        JwtProperties properties = SpringUtil.getBean(JwtProperties.class);
        this.iis = properties.getIss();
        this.expireTime = properties.getExpireTime();
        this.base64EncodedSecretKey = Base64.encodeBase64String(properties.getSecret().getBytes());
    }


    private static JwtHelper instance() {
        return JwtHelperHolder.INSTANCE;
    }

    /**
     * 创建 Json Web Token 字符串
     *
     * @param claims 将添加到载荷部分的信息，例如用户名，用户ID
     * @return token
     */
    public static String encode(DefaultClaims claims) {
        // 头信息
        Map<String, Object> headMap = MapUtil.newHashMap();
        headMap.put("alg", SIGNATURE_ALGORITHM.getValue());
        headMap.put("type", "JWT");

        // 签发时间(iat)：载荷部分的标准字段之一
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 下面就是在为 payload 添加各种标准声明和私有声明
        return Jwts.builder()
                .setHeader(headMap)
                // 负荷部分的标准字段/附加字段，一般卸载标准字段之前
                .setClaims(claims)
                // JWT ID（jti）
                .setId(UUID.randomUUID().toString())
                // 签发时间（iat）
                .setIssuedAt(now)
                // 签发人
                .setSubject(instance().iis)
                // 设置签名生成的算法和密钥
                .signWith(SIGNATURE_ALGORITHM, instance().base64EncodedSecretKey)
                // 过期时间（exp）
                .setExpiration(new Date(nowMillis + instance().expireTime))
                .compact();
    }

    /**
     * JWT Token 由头部、载荷和签名三个部分组成。签名部分是由加密算法生成的，无法反向解密。
     * 而头部和负荷部分是由 Base64 编码算法生成，是可以反向解码的。
     * 这就是为什么不能在 JWT Token 中存放敏感数据的原因。
     *
     * @param jwtToken 加密后的 Token
     * @return claims 返回负荷部分的键值对
     */
    public static Claims decode(String jwtToken) {
        isVerify(jwtToken);
        // 得到 defaultParser
        return Jwts.parser()
                // 设置签名的密钥
                .setSigningKey(instance().base64EncodedSecretKey)
                // 设置需要解析的 jwt
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * 校验 token
     * 在这里可以使用官方的校验，或
     * 自定义校验规则，例如在 token 中携带密码，进行加密处理后的数据库中的加密密码比较
     *
     * @param jwtToken 被校验的 jwt token
     * @return 是否通过
     */
    public static boolean isVerify(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(Base64.decodeBase64(instance().base64EncodedSecretKey));
        JWTVerifier verifier = JWT.require(algorithm).build();

        boolean verify = true;

        // 检验不通过时，会抛出异常
        try {
            verifier.verify(jwtToken);
        } catch (JWTVerificationException e) {
            log.error("Token verify error: {}.", e.getMessage());
            verify = false;
        }

        return verify;
    }

    private static class JwtHelperHolder {
        private static final JwtHelper INSTANCE = new JwtHelper();
    }

}
