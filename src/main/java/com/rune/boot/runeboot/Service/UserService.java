package com.rune.boot.runeboot.Service;

import com.rune.boot.runeboot.Controller.UserInfo;
import com.rune.boot.runeboot.Controller.UserRepository;
import com.rune.boot.runeboot.VO.UserVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    /**
     * 회원정보 저장
     *
     * @param userVo 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    public Long save(UserVO userVo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userVo.setPassword(encoder.encode(userVo.getPassword()));

        return userRepository.save(UserInfo.builder()
                .email(userVo.getEmail())
                .auth(userVo.getAuth())
                .password(userVo.getPassword()).build()).getCode();
    }

}