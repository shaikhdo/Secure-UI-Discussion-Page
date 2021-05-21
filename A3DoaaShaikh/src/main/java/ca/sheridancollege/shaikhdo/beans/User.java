package ca.sheridancollege.shaikhdo.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor

public class User {
	
	private Long userId;
	
	@NonNull
	private String email;
	@NonNull
	private String encryptedPassword;
	@NonNull
	private Boolean enabled;
	
	private String userPost;
	
	@DateTimeFormat(pattern = "uuuu-MM-dd")
	private LocalDate sub_date = LocalDate.now();
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime sub_time = LocalTime.now();

}
