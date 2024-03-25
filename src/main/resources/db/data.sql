-- 사용자 테이블
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('bluepig4b@naver.com','심유주','1234','010-9011-5822','부산광역시 부산진구 범향빌딩 405호','1999-09-30',1,'0ff7e86a-d0db-4d7c-ad5b-bd68682ccb11_user01.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('blueocean@naver.com','이서연','1234','010-1555-5842','부산광역시 사상구 괘법동 234번지 괘법아파트','2000-10-06',1,'702385bb-c937-4204-a142-7583f908bfaf_user02.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, img_file_name,created_at)
values ('hamimi1997@naver.com','김하형','1234','010-2684-5842','부산광역시 해운대구 우동 789번지 센텀시티 202호','1997-05-23',1,'706ffd65-ca99-4b91-a4e1-c73741f9b23f_user03.png',now());


-- 기업 사용자 테이블
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name,created_at)
values ('blackpig4b@naver.com','이재용','1234','010-1234-5822','경기도 수원시 영통구 삼성로 129 (매탄동, 삼성디지털시티)','1938-03-01','606-58-33101','SAMSUNG','www.samsung.com',2,'/images/sam.png','0cba0e4d-debb-4413-b079-d9195992e447_lg.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name, created_at)
values ('g@n','이혁근','1234','010-5678-5822','부산광역시 부산진구 범향빌딩403호','1999-06-02','301-15-12412','네이버','www.naver.com',2, '/images/naver.png','7d7ce466-b2b0-424d-bc2b-c233d98030f6_kakao.png', now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo,img_file_name,created_at)
values ('6@c','이준수','1234','010-9824-5421','서울특별시 송파구 위례성대로 2 (방이동) 장은빌딩','2011-03-10','606-17-12120','(주)우아한형제들','www.bamin.com',2, '/images/bemin.png','8a6fc873-37df-48d4-8756-c4bf86193552_kia.png', now());

-- 이력서 테이블
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link,  created_at)
values (1, '신입 백엔드 개발자', '부산', '대졸', '신입', 'ERD를 설계하고, MySQL, MariaDB를 사용할 수 있습니다. JavaScript, TypeScript, HTML/CSS 프론트엔드 개발도 가능합니다.', 'naver.com/universe00',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (2, 'Back-End / Software Engineer', '부산', '고졸', '신입', '[성장]을 목표로 달려나가며, 도전적인 것을 좋아하는 이 서 연 입니다!', 'nate.com/blueocean',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (3, 'Java-Spring 개발자', '부산', '대졸', '미들(4~8년)', 'Java를 메인으로 Back-end 개발에 관심을 가지고 있습니다. 다양한 환경과 회사를 접하며 웹서비스를 개발하고, 서비스 해본 경험이 있습니다.', 'naver.com/githa',now());


-- 공고 테이블
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (4, '경기도', '대규모 | 백엔드 개발자 (Backend Developer)', '학력무관', '경력무관', 'Java/Kotlin, Spring, JPA 기반 애플리케이션 개발 경험 및 역량 보유한 분, MySQL, Oracle 등 활용 역량이 있는 분', '2024-04-10', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (5, '부산', '백엔드 개발자 - Spring (경력무관)', '대졸', '신입', 'Spring 기반으로 백엔드 비즈니스 로직을 개발하고 배포. 백엔드 개발 경험이 있고, 기본적인 SQL 문법, JOIN, Subquery 등을 활용할 줄 아시는 분', '2024-05-22', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, deadline, task, created_at)
VALUES (6, '서울', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '프론트엔드(React)+백엔드(Spring) 풀스택 경력 2년 이상 또는 모바일(Android, iOS, Flutter)+백엔드(Spring) 경력 2년 이상의 경험이 있으신 분', '2024-04-09', '풀스택', NOW());

-- 스킬 테이블 (개인)
insert into skill_tb (resume_id, name, role) values(1, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(2, 'JavaScript', 1);
insert into skill_tb (resume_id, name, role) values(3, 'HTML/CSS', 1);

-- 스킬 테이블 (기업)
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'Jquery', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'JavaScript', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(2, 'Spring', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'JSP', 2);





