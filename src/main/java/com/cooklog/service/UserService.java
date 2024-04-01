package com.cooklog.service;

import java.util.List;

import com.cooklog.dto.JoinDTO;
import com.cooklog.dto.UserDTO;
import com.cooklog.dto.UserUpdateRequestDTO;
import com.cooklog.model.Role;

import com.cooklog.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface UserService {

	//회원 가입 정보 저장
	void joinSave(JoinDTO joinDTO);

//	void join(@Valid UserDTO userDTO);

//	//이메일 중복 검색
//	boolean emailExists(String email);

	UserDTO findUserById(Long id);

//	//비밀번호 유효성 검사
//	boolean isValidPassword(String password);

	// 모든 유저의 정보를 가져오는 메소드
	List<UserDTO> findAllUsers();

	// 사용자 프로필 수정 메소드
	User updateUserProfile(Long userIdx, UserUpdateRequestDTO userUpdateRequestDTO);

	// 사용자의 역할을 업데이트하는 메소드 추가
	void updateUserRole(Long userId, Role role);

	// 사용자 탈퇴유무 업데이트 메소드
	void updateUserDeleted(Long userId);

	// 사용자가 올린 게시물 갯수 가져오는 메소드
	Long getNumberOfBoardByUserId(Long userIdx);
}
