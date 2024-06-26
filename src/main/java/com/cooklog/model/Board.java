package com.cooklog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_idx")
	private User user;

	private String content;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "readcnt")
	private Integer readCount;

	@Formula("(select count(*) from likes where likes.board_id = id)")
	private int likesCount;

	@OneToMany(mappedBy = "board")
	@OrderBy("order ASC")
	private List<Image> images = new ArrayList<>();

	@OneToMany(mappedBy = "board")
	private List<Likes> likes = new ArrayList<>();

	@OneToMany(mappedBy = "board")
	@OrderBy("id ASC")
	private List<Tag> tags=new ArrayList<>();

	@OneToMany(mappedBy = "board")
	@OrderBy("id DESC") //최근에 등록된 게시물부터 보여주기 위함
	private List<Bookmark> bookmarks = new ArrayList<>();

	public void update(String content, LocalDateTime updatedAt){
		this.content=content;
		this.updatedAt=updatedAt;
	}

	public void updateReadCnt(int readCount){
		this.readCount=readCount;
	}
}
