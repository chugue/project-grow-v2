-- 개인 유저
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('bluepig4b@naver.com','심유주','1234','010-9011-5822','부산광역시 부산진구 범향빌딩 405호','1999-09-30','1',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('blueocean@naver.com','이서연','1234','010-1555-5842','부산광역시 사상구 괘법동 234번지 괘법아파트','2000-10-06','1',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('hamimi1997@naver.com','김하형','1234','010-2684-5842','부산광역시 해운대구 우동 789번지 센텀시티 202호','1997-05-23','1',now());
insert into user_tb(email, my_name, password, phone, address, birth, role, created_at)
values ('96hoho@gmail.com','양승호','1234','010-3535-9985','대전광역시 유성구 궁동 789번지 유성타워 3동 405호','1996-11-25','1', now()),
       ('jjangjung@naver.com','장현정','1234','010-9252-8834','서울특별시 마포구 새창로2길 17 대우아파트 1001호','1990-05-12','1', now()),
       ('universe07@daum.net','최주호','1234','010-6452-2311','경기도 용인시 수지구 동천로 567번지 수지그린아파트 102동 1203호','1981-05-06','1', now()),
       ('jaesung00@gmail.com','류재성','1234','010-4456-6343','광주광역시 서구 상무대로 111번길 23 우동 1502호','1992-02-15','1', now()),
       ('sungkim_87@naver.com','김성훈','1234','010-6255-2435','서울특별시 강남구 역삼동 123번지 아이파크','1987-07-20','1', now()),
       ('snowhoon@naver.com','설동훈','1234','010-1125-0988','부산광역시 동래구 명장동 678번지 명장타워 703호','1996-12-01','1', now()),
       ('babie@gmail.com','서지민','1234','010-0253-7898','부산광역시 남구 대연동 345번지 대연센트럴빌라 506호','2003-06-22','1', now()),
       ('minjung@naver.com','이민정','1234','010-2214-3354','서울특별시 송파구 잠실동 789번지 롯데 캐슬 1107동 303호','2000-03-25','1', now()),
       ('woo1988@gmail.com','최혁우','1234','010-9985-3114','서울특별시 강남구 역삼동 123번지 아이파크','1988-09-18','1', now()),
       ('manhou@gmail.com','박만호','1234','010-3567-1252','대전광역시 대덕구 송촌동 555번지 송촌 아파트 601호', '1993-06-22','1', now());


-- 기업 유저
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo, created_at)
values ('blackpig4b@naver.com','이재용','1234','010-1234-5822','경기도 수원시 영통구 삼성로 129 (매탄동, 삼성디지털시티)','1938-03-01','606-58-33101','SAMSUNG','www.samsung.com','2', '/images/sam.png',now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo, created_at)
values ('g@n','이혁근','1234','010-5678-5822','부산광역시 부산진구 범향빌딩403호','1999-06-02','301-15-12412','네이버','www.naver.com','2', '/images/naver.png', now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo, created_at)
values ('6@c','이준수','1234','010-9824-5421','서울특별시 송파구 위례성대로 2 (방이동) 장은빌딩','2011-03-10','606-17-12120','(주)우아한형제들','www.bamin.com','2', '/images/bemin.png', now());
insert into user_tb(email, my_name, password, phone, address, birth, business_number, comp_name, homepage, role, photo, created_at)
values ('yun@n','최상연','1234','010-8868-5232','서울특별시 송파구 송파대로 570','2010-07-01','709-88-35610','쿠팡','www.coupang.com','2', '/images/coupang.png', now()),
       ('y1@n','김정혁','1234','010-3252-6789','경기도 성남시 분당구 대왕판교로644번길 12','1997-03-11','176-67-88160','(주)엔씨소프트','www.ncsoft.com','2', '/images/ncsoft.png', now()),
       ('y2@n','이민정','1234','010-2467-0053','서울 구로구 디지털로26길 38 G타워','2000-05-12','303-91-33085','넷마블(주)','www.netmarble.com','2', '/images/netmarble.png', now()),
       ('y3@n','강혜정','1234','010-8865-2476','경기도 성남시 분당구 판교역로 166 카카오 판교아지트 A동 3층','1995-02-16','607-45-16054','(주)카카오','www.kakao.com','2', '/images/kakao.png', now()),
       ('y4@n','지현수','1234','010-5457-0975','경기도 성남시 분당구 판교로256번길 7 (삼평동)','1994-02-15','107-56-88921','(주)넥슨컴퍼니','www.nexon.com','2', '/images/nexon.png', now()),
       ('y5@n','정수지','1234','010-3758-1353','서울특별시 중구 통일로2길 16, 2층 (순화동, AIA타워)','2006-07-07','301-93-11325','한국닌텐도(주)','www.nintendo.im','2', '/images/nintendo.png', now()),
       ('y6@n','허만정','1234','010-6674-1422','서울특별시 영등포구 여의대로 128 (여의도동)','1947-01-05','606-32-44986','LG','www.lg.me','2', '/images/lg.png', now());


-- 이력서 테이블
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link,  created_at)
values (1, '신입 백엔드 개발자', '부산', '대졸', '신입', 'ERD를 설계하고, MySQL, MariaDB를 사용할 수 있습니다. JavaScript, TypeScript, HTML/CSS 프론트엔드 개발도 가능합니다.', 'naver.com/universe00',now());
-- insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
-- values (1, '준비된 풀스택 개발자', '부산', '대졸', '신입', 'HTML/CSS를 능숙하게 다룰 수 있으며, 독학으로 쌓아온 백엔드 지식을 기반으로 더 많은 사람들을 만나 배워가고자 노력하는 개발자입니다.', 'naver.com/universe00',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (2, 'Back-End / Software Engineer', '부산', '고졸', '신입', '[성장]을 목표로 달려나가며, 도전적인 것을 좋아하는 이 서 연 입니다!', 'nate.com/blueocean',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (3, 'Java-Spring 개발자', '부산', '대졸', '미들(4~8년)', 'Java를 메인으로 Back-end 개발에 관심을 가지고 있습니다. 다양한 환경과 회사를 접하며 웹서비스를 개발하고, 서비스 해본 경험이 있습니다.', 'naver.com/githa',now());
insert into resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
values (4, '프론트엔드 개발자 이력서', '대전', '대졸', '미들(4~8년)', '빠르고 원활한 웹 경험을 제공하는데 기여했으며, UX경험을 최적화 시켜, 프런트 엔드 분야에서 성장하고자 합니다.', 'naver.com/hoho',now());

INSERT INTO resume_tb(user_id, title, area, edu, career, introduce, port_link, created_at)
VALUES (5, '끊임없이 개선하고, 발전하는 개발자', '서울', '대졸', '시니어(10년 이상)', '사용자들에게 편리함을 제공하는 것을 최우선으로 삼고 개발하는 개발자입니다.', 'naver.com/codingsin', NOW()),
       (6, '능력있고 성실한 개발자!', '판교분당', '대졸', '시니어(10년 이상)', '겟인데어 회사 대표, 한국전력 계측,계량,고조파 측정 플러터 앱 개발', 'naver.com/getinthere',now()),
       (7, 'Web Developer', '광주', '고졸', '신입', '신입이지만 다양한 프로젝트 경험을 갖고 있으며 팀원들과의 협업을 중요시합니다.', 'naver.com/projectexp', NOW()),
       (8, '4년자 백엔드 개발자', '부산', '고졸', '미들(4~8년)', '자바 개발자로서의 역량을 키워나가고 있습니다. 성장 가능성이 높은 개발자!', 'naver.com/javadev', NOW()),
       (9, '서버 개발자', '부산', '대졸', '신입', '데이터 분석 및 머신러닝에 관심이 많으며 실무 경험도 풍부합니다.', 'naver.com/snowhoon', NOW()),
       (10, '백엔드 개발자(Java, Spring)', '부산', '대졸', '신입', '다양한 언어와 기술에 능숙하며 프로젝트 관리 역량도 뛰어납니다.', 'naver.com/datasci', NOW()),
       (11, '프론트엔드 개발자', '서울', '고졸', '신입', '기술적으로 성장하기 위해 노력하며, 동료와 좋은 커뮤니케이션 하고자 하는 개발자입니다!', 'naver.com/fullstackdev', NOW()),
       (12, '데이터 엔지니어', '서울', '대졸', '시니어(10년 이상)', '대량의 데이터 처리와 데이터 파이프라인 구축에 경험이 있습니다', 'naver.com/dataengineer', NOW()),
       (13, '준비된 풀스택 개발자', '대전', '대졸', '미들(4~8년)', '웹개발에 흥미가 있으며, 풍부한 실전 경험을 바탕으로 자기주도적으로 공부하고 성장하는 개발자입니다.', 'naver.com/androiddev', NOW());



-- 공고 테이블
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (14, '경기도', '대규모 | 백엔드 개발자 (Backend Developer)', '학력무관', '경력무관', 'Java/Kotlin, Spring, JPA 기반 애플리케이션 개발 경험 및 역량 보유한 분, MySQL, Oracle 등 활용 역량이 있는 분', '2024-04-10', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (15, '부산', '백엔드 개발자 - Spring (경력무관)', '대졸', '신입', 'Spring 기반으로 백엔드 비즈니스 로직을 개발하고 배포. 백엔드 개발 경험이 있고, 기본적인 SQL 문법, JOIN, Subquery 등을 활용할 줄 아시는 분', '2024-05-22', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (16, '서울', '프런트 엔드/백엔드 개발자 채용', '학력무관', '시니어(10년 이상)', '프론트엔드(React)+백엔드(Spring) 풀스택 경력 2년 이상 또는 모바일(Android, iOS, Flutter)+백엔드(Spring) 경력 2년 이상의 경험이 있으신 분', '2024-04-09', '풀스택', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (17, '서울', '[24년도 공개 채용] 데이터 분석가', '학력무관', '미들(4~8년)', '데이터 분석 및 모델링에 4년 이상의 경험이 있으신 분, 분석적 사고 및 문제 해결 능력을 보유하신 분', '2024-03-29', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (18, '경기도', '게임 개발자 채용 공고', '학력무관', '미들(4~8년)', '게임 개발 프로젝트에 주도적으로 참여한 경험 (엔지니어로서 기획을 조율하고 게임 모딩에 적합한 기능구성 필요)', '2024-03-15', '게임개발', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (19, '서울', '백엔드 개발자 (Java/Kotlin)', '대졸', '신입', 'Java/Kotlin 중 1개 이상의 프로그래밍 언어에 익숙하신 분, Spring 등 웹 프레임워크 활용 경험이 있으신 분', '2024-05-22', '백엔드', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (20, '경기도', '프론트엔드 개발자 (React, TypeScript)', '학력무관', '경력무관', '- JavaScript, HTML, CSS 등 마크업, TypeScript에 대한 이해가 있으신 분', '2024-04-09', '프론트', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (21, '부산', '24년도 풀스택 Web 개발자 채용 공고', '학력무관', '신입', 'HTML, CSS, JavaScript, MySQL 등 웹 기본 전반, 열정과 근성이 있는 분', '2024-03-29', '풀스택', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (22, '서울', '[경력] Frontend Engineer', '학력무관', '미들(4~8년)', '5년 이상 Frontend 개발 경력 혹은 그에 준하는 실력이 있는 분', '2024-04-10', '프론트', NOW());
INSERT INTO jobs_tb(user_id, area, title, edu, career, content, dead_line, task, created_at)
VALUES (23, '서울', '[채용공고] 백엔드 개발자 정규직 채용', '대졸', '경력무관', 'Java, Spring boot를 다룰 수 있고, Git을 통해 협업을 해보신 분', '2024-05-22', '백엔드', NOW());



-- 스킬 테이블 (개인)
insert into skill_tb (resume_id, name, role) values(1, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(1, 'JavaScript', 1);
insert into skill_tb (resume_id, name, role) values(1, 'HTML', 1);
insert into skill_tb (resume_id, name, role) values(1, 'MySQL', 1);

-- insert into skill_tb (resume_id, name, role) values(2, 'HTML', 1);
-- insert into skill_tb (resume_id, name, role) values(2, 'jquery', 1);
-- insert into skill_tb (resume_id, name, role) values(2, 'React', 1);

insert into skill_tb (resume_id, name, role) values(2, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(2, 'Spring', 1);
insert into skill_tb (resume_id, name, role) values(2, 'MySQL', 1);

insert into skill_tb (resume_id, name, role) values(3, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(3, 'Spring', 1);

insert into skill_tb (resume_id, name, role) values(4, 'HTML', 1);
insert into skill_tb (resume_id, name, role) values(4, 'React', 1);

insert into skill_tb (resume_id, name, role) values(5, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(5, 'JavaScript', 1);
insert into skill_tb (resume_id, name, role) values(5, 'Spring', 1);
insert into skill_tb (resume_id, name, role) values(5, 'MySQL', 1);
insert into skill_tb (resume_id, name, role) values(5, 'Oracle', 1);

insert into skill_tb (resume_id, name, role) values(6, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(6, 'Spring', 1);

insert into skill_tb (resume_id, name, role) values(7, 'Spring', 1);
insert into skill_tb (resume_id, name, role) values(7, 'HTML', 1);

insert into skill_tb (resume_id, name, role) values(8, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(8, 'Spring', 1);
insert into skill_tb (resume_id, name, role) values(8, 'MySQL', 1);

insert into skill_tb (resume_id, name, role) values(9, 'Oracle', 1);

insert into skill_tb (resume_id, name, role) values(10, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(10, 'Spring', 1);

insert into skill_tb (resume_id, name, role) values(11, 'HTML', 1);
insert into skill_tb (resume_id, name, role) values(11, 'React', 1);
insert into skill_tb (resume_id, name, role) values(11, 'Vue.js', 1);
insert into skill_tb (resume_id, name, role) values(11, 'JavaScript', 1);

insert into skill_tb (resume_id, name, role) values(12, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(12, 'Oracle', 1);

insert into skill_tb (resume_id, name, role) values(13, 'Java', 1);
insert into skill_tb (resume_id, name, role) values(13, 'Spring', 1);
insert into skill_tb (resume_id, name, role) values(13, 'HTML', 1);
insert into skill_tb (resume_id, name, role) values(13, 'React', 1);



-- 스킬 테이블 (기업)
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'Spring', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'MySQL', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(1, 'Oracle', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(2, 'Spring', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(2, 'MySQL', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(2, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(2, 'JSP', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'Spring', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'MySQL', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'Vue.js', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'React', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(3, 'HTML', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(4, 'Spring', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(4, 'Oracle', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(5, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(5, 'React', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(5, 'Vue.js', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(6, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(6, 'Spring', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(7, 'JavaScript', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(7, 'HTML', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(7, 'React', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(8, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(8, 'HTML', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(8, 'JavaScript', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(8, 'MySQL', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(8, 'React', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(9, 'HTML', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(9, 'JavaScript', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(9, 'React', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(9, 'Vue.js', 2);

INSERT INTO skill_tb(jobs_id, name, role) VALUES(10, 'Java', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(10, 'Spring', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(10, 'JavaScript', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(10, 'jquery', 2);
INSERT INTO skill_tb(jobs_id, name, role) VALUES(10, 'MySQL', 2);


-- 스크랩 테이블
-- insert into scrap_tb(user_id, resume_id, created_at) values(1, 1, now());
-- insert into scrap_tb(user_id, resume_id, created_at) values(2, 1, now());
-- insert into scrap_tb(user_id, jobs_id, created_at) values(3, 1, now());

-- 스크랩 테이블 (기업)

insert into scrap_tb(user_id, resume_id, created_at) values(6, 1, now());
insert into scrap_tb(user_id, resume_id, created_at) values(6, 2, now());
insert into scrap_tb(user_id, resume_id, created_at) values(6, 3, now());

insert into scrap_tb(user_id, resume_id, created_at) values(1, 1, now());
insert into scrap_tb(user_id, resume_id, created_at) values(2, 2, now());


-- 기업에 지원한 유저
-- values (첫번째 이력서가 , 첫번째 공고에 지원, 대기중)


-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(1,1,2,now());
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(2,1,1,now());
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(1,2,3,now());
--
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(3,1,1,now());
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(5,1,1,now());
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(6,1,1,now());
--
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(1,2,1,now());
-- insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(2,3,1,now());




-- 기업이 제안한 이력서
-- 첫번째 이력서를 첫번째 공고가 제안함, 대기중
insert into offer_tb(resume_id, jobs_id, status, created_at) values(1,1,1,now());
insert into offer_tb(resume_id, jobs_id, status, created_at) values(1,2,1,now());
insert into offer_tb(resume_id, jobs_id, status, created_at) values(1,3,1,now());
